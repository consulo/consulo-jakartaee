package org.jetbrains.idea.tomcat.admin;

import com.intellij.execution.ExecutionException;
import com.intellij.execution.configurations.RuntimeConfigurationException;
import com.intellij.javaee.oss.admin.jmx.JmxAdminException;
import com.intellij.openapi.util.io.FileUtil;
import org.jdom.Document;
import org.jdom.Element;
import jakarta.annotation.Nonnull;
import org.jetbrains.idea.tomcat.TomcatContexts;
import org.jetbrains.idea.tomcat.TomcatDeploymentModelBase;
import org.jetbrains.idea.tomcat.TomcatModuleDeploymentModel;
import org.jetbrains.idea.tomcat.TomcatUtil;
import org.jetbrains.idea.tomcat.server.TomcatRemoteModel;

import java.io.File;
import java.io.IOException;

/**
 * Created by IntelliJ IDEA.
 * User: michael.golubev
 */
public class TomcatAdminRemoteServerImpl extends TomcatAdminServerBase<TomcatRemoteModel> {

  public TomcatAdminRemoteServerImpl(TomcatRemoteModel serverModel, TomEEAgentAdminServerImpl tomEEAdmin) {
    super(serverModel, tomEEAdmin);
  }

  @Override
  public boolean doConnectTomcat() {
    if (isUseJmx()) {
      return doConnectJmx();
    }
    else {
      return true;
    }
  }

  @Override
  protected String prepareDeployment(TomcatDeploymentModelBase deploymentModel, String deploymentPath) throws JmxAdminException {
    try {
      TomcatRemoteModel serverModel = getServerModel();
      String preparedPath = serverModel.prepareDeployment(deploymentPath);
      if (serverModel.isContextDescriptorExist()) {
        File contextXml = addApplicationContext(deploymentModel, preparedPath);
        serverModel.transferContextFile(contextXml);
      }
      return preparedPath;
    }
    catch (RuntimeConfigurationException e) {
      throw new JmxAdminException(e);
    }
  }

  @Nonnull
  public File addApplicationContext(TomcatDeploymentModelBase tomcatModuleDeploymentModel, String preparedPath)
    throws JmxAdminException {
    try {
      String contextPath = tomcatModuleDeploymentModel.getContextPath();
      String contextName = TomcatUtil.getContextName(contextPath);

      File contextDir = FileUtil.createTempDirectory(contextName, "-context-xml", true);

      Element contextElement = TomcatUtil.findContextInContextXml(tomcatModuleDeploymentModel);

      if (contextElement == null) {
        contextElement = new Element(TomcatContexts.CONTEXT_ELEMENT_NAME);
      }

      if (!((TomcatModuleDeploymentModel)tomcatModuleDeploymentModel).isEEArtifact()) {
        contextElement.setAttribute(TomcatContexts.PATH_ATTR, contextPath);
      }
      contextElement.setAttribute(TomcatContexts.DOC_BASE_ATTR, preparedPath);


      final File targetContextXmlFile = new File(contextDir, contextName + ".xml");

      final Document xmlDocument;
      if (contextElement.getDocument() != null && contextElement.isRootElement()) {
        xmlDocument = contextElement.getDocument().clone();
      }
      else {
        xmlDocument = new Document();
        xmlDocument.setRootElement(contextElement.clone());
      }
      TomcatUtil.saveXMLFile(xmlDocument, targetContextXmlFile, true);
      return targetContextXmlFile;
    }
    catch (ExecutionException | IOException e) {
      throw new JmxAdminException(e);
    }
  }
}
