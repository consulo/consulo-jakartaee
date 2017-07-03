package org.jetbrains.idea.tomcat.server;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.jetbrains.annotations.NonNls;
import org.jetbrains.idea.tomcat.TomcatBundle;
import org.jetbrains.idea.tomcat.TomcatModuleDeploymentModel;
import org.jetbrains.idea.tomcat.admin.TomEEAgentAdminServerImpl;
import org.jetbrains.idea.tomcat.admin.TomcatAdminServerBase;
import org.jetbrains.idea.tomcat.server.tomee.TomeeExtensions;
import com.intellij.execution.ExecutionException;
import com.intellij.execution.configurations.RuntimeConfigurationError;
import com.intellij.execution.configurations.RuntimeConfigurationException;
import com.intellij.javaee.appServerIntegrations.ApplicationServer;
import com.intellij.javaee.deployment.DeploymentModel;
import com.intellij.javaee.oss.admin.JavaeeAdmin;
import com.intellij.javaee.oss.admin.JavaeeAdminClientImpl;
import com.intellij.javaee.oss.agent.ProcessAgentProxyFactory;
import com.intellij.javaee.oss.server.JavaeeServerInstance;
import com.intellij.javaee.oss.server.JavaeeServerModel;
import com.intellij.javaee.oss.util.Version;
import com.intellij.javaee.ui.packaging.ExplodedEarArtifactType;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.roots.ui.configuration.ModulesConfigurator;
import com.intellij.openapi.util.Ref;
import com.intellij.openapi.util.io.FileUtilRt;
import com.intellij.packaging.artifacts.Artifact;
import com.intellij.packaging.artifacts.ArtifactManager;
import com.intellij.packaging.elements.PackagingElement;
import com.intellij.packaging.elements.PackagingElementResolvingContext;
import com.intellij.packaging.impl.artifacts.ArtifactUtil;
import com.intellij.packaging.impl.elements.ArtifactPackagingElement;
import com.intellij.packaging.impl.elements.DirectoryPackagingElement;
import com.intellij.util.Processor;
import consulo.javaee.artifact.ExplodedWarArtifactType;
import consulo.javaee.artifact.WarArtifactType;

/**
 * Created by IntelliJ IDEA.
 * User: michael.golubev
 */
public abstract class TomcatServerModel extends JavaeeServerModel {

  @NonNls
  private static final String TOMCAT_LOCALHOST_LOG_NAME = "Tomcat Localhost";
  @NonNls
  private static final String TOMCAT_LOCALHOST_LOG_ID = "Tomcat";
  @NonNls
  protected static final String COMPATIBLE_TOMCAT_LOCALHOST_LOG_ID = "TOMCAT_LOCALHOST_LOG_ID";

  public static final int DEFAULT_JNDI_PORT = 1099;

  @Override
  protected String getDefaultUsername() {
    return "";
  }

  @Override
  protected String getDefaultPassword() {
    return "";
  }

  @Override
  protected int getServerPort() {
    return getJndiPort();
  }

  @Override
  public int getPingPort() {
    return isUseJmx() ? getServerPort() : getCommonModel().getPort();
  }

  @Override
  protected JavaeeAdmin createServerAdmin(JavaeeServerInstance serverInstance) throws Exception {
    TomEEAgentAdminServerImpl tomEEAgent
      = isTomEE() ? new TomEEAgentAdminServerImpl(new ProcessAgentProxyFactory(serverInstance), getLibraries()) : null;
    return new JavaeeAdminClientImpl(createTomcatServerAdmin(serverInstance, tomEEAgent));
  }

  @Override
  protected List<File> getLibraries() throws ExecutionException {
    List<File> libraries = super.getLibraries();
    libraries.add(getPathUnderHome("lib"));
    return libraries;
  }

  protected abstract TomcatAdminServerBase<?> createTomcatServerAdmin(JavaeeServerInstance serverInstance,
                                                                      TomEEAgentAdminServerImpl tomEEAdmin) throws Exception;

  @Override
  protected String getLogFileId() {
    return TOMCAT_LOCALHOST_LOG_ID;
  }

  @Override
  protected List<LogFileFactory> getLogFileFactories() {
    return Arrays.<LogFileFactory>asList(new TomcatDefaultLocalhostLogFileFactory(), new TomcatCompatibleLocalhostLogFileFactory());
  }

  public Project getProject() {
    return getCommonModel().getProject();
  }

  @Override
  public void checkConfiguration() throws RuntimeConfigurationException {
    Set<String> contexts = new HashSet<>();
    boolean tomEE = isTomEE();
    for (DeploymentModel deploymentModel : getCommonModel().getDeploymentModels()) {
      final TomcatModuleDeploymentModel model = (TomcatModuleDeploymentModel)deploymentModel;
      if (model.isEEArtifact()) {
        if (!tomEE) {
          throw new RuntimeConfigurationError(TomcatBundle.message("error.unsupported.artifact.type"));
        }
      }
      else {
        if (!contexts.add(model.CONTEXT_PATH)) {
          throw new RuntimeConfigurationError(TomcatBundle.message("error.duplicate.context.path.text", model.CONTEXT_PATH));
        }
      }

      final Artifact artifact = model.getArtifact();
      if (artifact != null && ExplodedEarArtifactType.getInstance().equals(artifact.getArtifactType())) {
        ArtifactManager artifactManager = ArtifactManager.getInstance(getProject());
        final PackagingElementResolvingContext resolvingContext = artifactManager.getResolvingContext();
        final Ref<RuntimeConfigurationError> errorRef = new Ref<>();

        Processor<PackagingElement<?>> processor = new Processor<PackagingElement<?>>() {

          @Override
          public boolean process(PackagingElement<?> element) {
            if (element instanceof DirectoryPackagingElement) {
              DirectoryPackagingElement packagingDir = (DirectoryPackagingElement)element;
              if (isContainingExplodedWar(packagingDir, resolvingContext)) {
                String packageDirName = packagingDir.getDirectoryName();
                if (TomeeExtensions.getInstance()
                  .isValidExtension(WarArtifactType.getInstance(), FileUtilRt.getExtension(packageDirName))) {
                  String message = TomcatBundle.message("error.wrong.exploded.war.dir.name", packageDirName);
                  RuntimeConfigurationError error = new RuntimeConfigurationError(message);
                  error.setQuickFix(() -> ModulesConfigurator.showArtifactSettings(getCommonModel().getProject(), artifact));
                  errorRef.set(error);
                }
              }
            }
            return true;
          }

          private boolean isContainingExplodedWar(DirectoryPackagingElement packagingDir, PackagingElementResolvingContext context) {
            for (PackagingElement packagingElement : packagingDir.getChildren()) {
              if (packagingElement instanceof ArtifactPackagingElement) {
                Artifact nestedArtifact = ((ArtifactPackagingElement)packagingElement).findArtifact(context);
                if (nestedArtifact != null && ExplodedWarArtifactType.getInstance().equals(nestedArtifact.getArtifactType())) {
                  return true;
                }
              }
            }
            return false;
          }
        };

        ArtifactUtil.processRecursivelySkippingIncludedArtifacts(artifact, processor, resolvingContext);
        if (!errorRef.isNull()) {
          throw errorRef.get();
        }
      }
    }
  }

  public ApplicationServer getApplicationServer() {
    return getCommonModel().getApplicationServer();
  }

  public boolean versionHigher(int major, int minor, int micro) {
    return new Version(getVersion()).compare(major, minor, micro) >= 0;
  }

  public boolean isVersion5OrHigher() {
    return versionHigher(5, 0, 0);
  }

  public boolean isVersion7OrHigher() {
    return versionHigher(7, 0, 0);
  }

  public boolean isVersion8OrHigher() {
    return versionHigher(8, 0, 0);
  }

  public boolean isUseJmx() {
    return isVersion5OrHigher();
  }

  public boolean isTomEE() {
    return TomcatIntegration.isTomEE(getHome());
  }

  @Override
  public boolean undeployBeforeDeploy(DeploymentModel deploymentModel) {
    return isTomEE() && useTomEEDeployer(deploymentModel);
  }

  public boolean useTomEEDeployer(DeploymentModel deploymentModel) {
    return versionHigher(7, 0, 34) || ((TomcatModuleDeploymentModel)deploymentModel).isEEArtifact();
  }

  public boolean isVersionHigher6032() throws IOException {
   return versionHigher(6, 0, 32);
  }

  protected abstract int getJndiPort();

  private class TomcatDefaultLocalhostLogFileFactory extends DefaultLogFileFactory {

    @Override
    protected String getName() {
      return TOMCAT_LOCALHOST_LOG_NAME;
    }
  }

  private class TomcatCompatibleLocalhostLogFileFactory extends TomcatDefaultLocalhostLogFileFactory {

    @Override
    public String getId() {
      return COMPATIBLE_TOMCAT_LOCALHOST_LOG_ID;
    }
  }
}
