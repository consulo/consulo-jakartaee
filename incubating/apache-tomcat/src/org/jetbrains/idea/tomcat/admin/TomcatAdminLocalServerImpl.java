package org.jetbrains.idea.tomcat.admin;

import com.intellij.execution.ExecutionException;
import com.intellij.execution.configurations.RuntimeConfigurationException;
import com.intellij.execution.process.ProcessAdapter;
import com.intellij.execution.process.ProcessEvent;
import com.intellij.javaee.deployment.DeploymentModel;
import com.intellij.javaee.deployment.DeploymentStatus;
import com.intellij.javaee.oss.admin.JavaeeAdminDeployCallback;
import com.intellij.javaee.oss.admin.JavaeeAdminStartCallback;
import com.intellij.javaee.oss.admin.jmx.JmxAdminException;
import com.intellij.javaee.oss.server.JavaeeServerInstance;
import com.intellij.openapi.util.Key;
import com.intellij.openapi.util.Ref;
import com.intellij.openapi.util.io.FileUtil;
import org.jdom.Document;
import org.jdom.Element;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.idea.tomcat.*;
import org.jetbrains.idea.tomcat.server.TomcatLocalModel;

import java.io.File;

/**
 * Created by IntelliJ IDEA.
 * User: michael.golubev
 */
public class TomcatAdminLocalServerImpl extends TomcatAdminServerBase<TomcatLocalModel> {

  @NonNls private static final String STARTED_SUFFIX = "org.apache.jk.server.JkMain start";
  @NonNls private static final String STARTED_PREFIX = "INFO: Server startup in";
  @NonNls private static final String STARTING_MESSAGE = "Starting service Tomcat-Apache";

  @NonNls private static final String WORKDIR_ATTR = "workDir";
  @NonNls private static final String CONTEXT_CLASS_NAME = "className";

  @NonNls private static final String SESSION_MANAGER_ELEMENT = "Manager";
  @NonNls private static final String SESSION_PATHNAME_ATTR = "pathname";
  @NonNls private static final String SESSION_DIRNAME = "sessions";
  @NonNls private static final String SESSION_FILENAME = "SESSIONS.ser";

  private final JavaeeServerInstance myServerInstance;

  private boolean myIsStartedUp = false;

  public TomcatAdminLocalServerImpl(TomcatLocalModel serverModel,
                                    TomEEAgentAdminServerImpl tomEEAdmin,
                                    JavaeeServerInstance serverInstance) {
    super(serverModel, tomEEAdmin);
    myServerInstance = serverInstance;
  }

  @Override
  public void start(String host, int port, String username, String password, JavaeeAdminStartCallback callback)
    throws Exception {
    super.start(host, port, username, password, callback);
    myServerInstance.getProcessHandler().addProcessListener(new ProcessAdapter() {

      public void onTextAvailable(ProcessEvent event, Key outputType) {
        final String text = event.getText();
        if (!myIsStartedUp && isStartingMessage(text)) {
          myIsStartedUp = true;
        }
      }
    });
  }

  @Override
  public boolean doConnectTomcat() {
    return isUseJmx() ? doConnectJmx() : myIsStartedUp;
  }

  private boolean isStartingMessage(final String text) {
    final TomcatLocalModel tomcatModel = getServerModel();
    final String trimmedText = text.trim();
    if (trimmedText.startsWith(STARTED_PREFIX) || trimmedText.endsWith(STARTED_SUFFIX)) {
      return true;
    }
    if (!tomcatModel.isVersion5OrHigher()) {
      return trimmedText.contains(STARTING_MESSAGE);
    }
    if (trimmedText.contains(TomcatStartupPolicy.getDefaultCatalinaFileName() + " start")) {
      return true;
    }
    return false;
  }

  @Override
  public void startDeploy(DeploymentModel deployment, final File source, JavaeeAdminDeployCallback callback) {
    try {
      new DeployStep(deployment) {

        @Override
        protected void doPerform() throws ExecutionException {
          undeployBeforeDeploy(getDeploymentModel());
        }
      }.perform();

      if (isUseJmx()) {
        super.startDeploy(deployment, source, callback);
      }
      else {
        new DeployStep(deployment) {

          @Override
          protected void doPerform() throws ExecutionException {
            new Tomcat4Deployer(getServerModel()).deploy(getDeploymentModel().getContextPath());
          }
        }.perform();
        callback.setDeploymentStatus(deployment, DeploymentStatus.DEPLOYED);
      }
    }
    catch (JmxAdminException e) {
      callback.setDeploymentStatus(deployment, DeploymentStatus.FAILED, e);
    }
  }

  @Override
  protected boolean doDeploy(final DeploymentContext context) throws JmxAdminException {
    final Ref<Boolean> isContextCustom = new Ref<>();
    new DeployStep(context.getDeploymentModel()) {

      @Override
      protected void doPerform() throws ExecutionException {
        TomcatModuleDeploymentModel deploymentModel = getDeploymentModel();
        addApplicationContext(deploymentModel);
        Element tomcatContext = TomcatUtil.findContextElement(getServerModel().getBaseDirectoryPath(), deploymentModel);
        isContextCustom.set(tomcatContext != null && tomcatContext.getAttributeValue(CONTEXT_CLASS_NAME) != null);
      }
    }.perform();
    return isContextCustom.get() || super.doDeploy(context);
  }

  private void undeployBeforeDeploy(TomcatModuleDeploymentModel deploymentModel) throws ExecutionException {
    TomcatContexts contexts = new TomcatContexts(getServerModel(), getServerModel().getBaseDirectoryPath());
    for (TomcatContextItem contextItem : contexts.getItems()) {
      String docBase = contextItem.getElement().getAttributeValue(TomcatContexts.DOC_BASE_ATTR);
      if (docBase != null && docBase.equals(deploymentModel.getDeploymentSource().getFilePath())) {
        contextItem.remove();
      }
    }
  }

  @Override
  public void startUndeploy(DeploymentModel deployment, File source, JavaeeAdminDeployCallback callback) {
    final String contextPath = ((TomcatDeploymentModelBase)deployment).getContextPath();
    if (isUseJmx()) {
      final String contextXML = TomcatUtil.getContextXML(getServerModel().getBaseDirectoryPath(), contextPath);
      final File contextXmlFile = new File(contextXML);
      if (contextXmlFile.exists()) {
        FileUtil.delete(contextXmlFile);
      }
      super.startUndeploy(deployment, source, callback);
    }
    else {
      try {
        new TomcatContexts(null, getServerModel().getBaseDirectoryPath()).addOrRemoveContextElementInServerXml(contextPath, null);
        new Tomcat4Deployer(getServerModel()).undeploy(contextPath);
      }
      catch (ExecutionException e) {
        LOG.debug(e);
      }
      callback.setDeploymentStatus(deployment, DeploymentStatus.NOT_DEPLOYED);
    }
  }

  @Override
  protected String prepareDeployment(TomcatDeploymentModelBase deploymentModel, String deploymentPath) throws JmxAdminException {
    return deploymentPath;
  }

  @Override
  public void startUpdateDeploymentStatus(DeploymentModel deployment, File source, JavaeeAdminDeployCallback callback) {
    if (isUseJmx()) {
      super.startUpdateDeploymentStatus(deployment, source, callback);
    }
    else {
      try {
        TomcatDeploymentModelBase tomcatDeployment = (TomcatDeploymentModelBase)deployment;
        final Element contextElement = TomcatUtil.findContextElement(getServerModel().getBaseDirectoryPath(), tomcatDeployment);
        final DeploymentStatus status = contextElement != null ? DeploymentStatus.DEPLOYED : DeploymentStatus.NOT_DEPLOYED;
        callback.setDeploymentStatus(deployment, status);
      }
      catch (ExecutionException e) {
        LOG.error(e);
      }
    }
  }

  public static void addApplicationContext(TomcatDeploymentModelBase tomcatModuleDeploymentModel) throws ExecutionException {
    try {
      TomcatLocalModel serverModel = (TomcatLocalModel)tomcatModuleDeploymentModel.getServerModel();
      String contextPath = tomcatModuleDeploymentModel.getContextPath();

      Element contextElement = TomcatUtil.findContextElement(serverModel.getSourceBaseDirectoryPath(), tomcatModuleDeploymentModel);

      if (contextElement == null) {
        contextElement = new Element(TomcatContexts.CONTEXT_ELEMENT_NAME);
        //contextElement.addContent((Comment)TomcatConstants.CONTEXT_COMMENT.clone());
      }

      if (serverModel.PRESERVE_SESSIONS && serverModel.isVersion7OrHigher()) {
        String contextName = TomcatUtil.getContextName(contextPath);
        File contextSessionDir = new File(serverModel.getBaseDirectoryPath(), SESSION_DIRNAME + File.separator + contextName);
        if (contextSessionDir.exists() || FileUtil.createDirectory(contextSessionDir)) {
          String contextFilePath = contextSessionDir.getAbsolutePath() + File.separator + SESSION_FILENAME;
          Element managerElement = new Element(SESSION_MANAGER_ELEMENT);
          managerElement.setAttribute(SESSION_PATHNAME_ATTR, contextFilePath);
          contextElement.addContent(managerElement);
        }
        else {
          LOG.debug("Can't create context session directory " + contextSessionDir);
        }
      }

      final String deploymentPath = tomcatModuleDeploymentModel.getDeploymentSource().getFilePath();
      if (deploymentPath == null) {
        throw new ExecutionException(TomcatBundle.message("exception.text.neither.exploded.directory.nor.jar.file.configured"));
      }

      if (!new File(deploymentPath).exists()) {
        throw new ExecutionException(TomcatBundle.message("exception.text.file.not.found.for.web.module", deploymentPath));
      }

      //remove unpacked WAR directory
      if (tomcatModuleDeploymentModel.getDeploymentSource().isArchive()) {
        final String contextXML = TomcatUtil.getContextXML(serverModel.getSourceBaseDirectoryPath(), contextPath);
        final String xmlName = new File(contextXML).getName();
        final String dirName = xmlName.substring(0, xmlName.length() - 4);

        final Element localHost = new TomcatServerXmlWrapper(serverModel.getBaseDirectoryPath()).findLocalHost();

        final String appBase = localHost.getAttributeValue(TomcatConstants.APP_BASE_ATTR);
        FileUtil.delete(new File(appBase, dirName));
      }

      if (!((TomcatModuleDeploymentModel)tomcatModuleDeploymentModel).isEEArtifact()) {
        contextElement.setAttribute(TomcatContexts.PATH_ATTR, contextPath);
      }
      contextElement.setAttribute(TomcatContexts.DOC_BASE_ATTR, deploymentPath);

      if (serverModel.isVersion5OrHigher()) {
        final String contextXML = TomcatUtil.getContextXML(serverModel.getBaseDirectoryPath(), contextPath);
        final File targetContextXmlFile = new File(contextXML);
        targetContextXmlFile.getParentFile().mkdirs();

        final Document xmlDocument;
        if (contextElement.getDocument() != null && contextElement.isRootElement()) {
          xmlDocument = contextElement.getDocument().clone();
        }
        else {
          xmlDocument = new Document();
          xmlDocument.setRootElement(contextElement.clone());
        }
        TomcatUtil.saveXMLFile(xmlDocument, targetContextXmlFile, true);
      }
      else {
        String root = FileUtil.toSystemDependentName(TomcatUtil.getGeneratedFilesPath(serverModel));
        String scratchdir = root + File.separator + TomcatConstants.CATALINA_WORK_DIRECTORY_NAME + File.separator
                            + new File(TomcatUtil.getContextXML(serverModel.getBaseDirectoryPath(), contextPath)).getName();
        new File(scratchdir).mkdirs();

        contextElement.setAttribute(WORKDIR_ATTR, scratchdir);

        new TomcatContexts(serverModel, serverModel.getBaseDirectoryPath())
          .addOrRemoveContextElementInServerXml(contextPath, contextElement);
      }
    }
    catch (RuntimeConfigurationException e) {
      throw new ExecutionException(e.getMessage());
    }
  }

  private static abstract class DeployStep {

    private final TomcatModuleDeploymentModel myDeploymentModel;

    public DeployStep(DeploymentModel deploymentModel) {
      myDeploymentModel = (TomcatModuleDeploymentModel)deploymentModel;
    }

    public final TomcatModuleDeploymentModel getDeploymentModel() {
      return myDeploymentModel;
    }

    public void perform() throws JmxAdminException {
      try {
        doPerform();
      }
      catch (ExecutionException e) {
        throw new JmxAdminException(e);
      }
    }

    protected abstract void doPerform() throws ExecutionException;
  }
}