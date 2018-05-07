package org.jetbrains.idea.tomcat.admin;

import com.intellij.javaee.deployment.DeploymentModel;
import com.intellij.javaee.oss.admin.JavaeeAgentAdminServerBase;
import com.intellij.javaee.oss.agent.AgentProxyFactory;
import com.intellij.javaee.oss.agent.ParametersMap;
import com.intellij.openapi.util.io.FileSystemUtil;
import com.intellij.openapi.util.io.FileUtil;
import javax.annotation.Nonnull;
import org.jetbrains.idea.tomcat.TomcatModuleDeploymentModel;
import org.jetbrains.idea.tomcat.agent.TomEEDeployPropertyNames;

import java.io.File;
import java.util.Collections;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: michael.golubev
 */
public class TomEEAgentAdminServerImpl extends JavaeeAgentAdminServerBase {

  private static final String SPECIFICS_MODULE_NAME = "tomeeSrv";

  private static final String SPECIFICS_JAR_PATH = "specifics/tomee-specifics.jar";

  public TomEEAgentAdminServerImpl(AgentProxyFactory agentProxyFactory,
                                   List<File> instanceLibraries)
    throws Exception {
    super(agentProxyFactory,
          instanceLibraries,
          Collections.<Class<?>>emptyList(),
          SPECIFICS_MODULE_NAME,
          SPECIFICS_JAR_PATH,
          "org.jetbrains.idea.tomcat.agent.TomEEAgent");
  }

  @Override
  protected void setupDeployParameters(DeploymentModel deployment, File source, ParametersMap deployParameters) throws Exception {
    TomcatModuleDeploymentModel tomeeDeployment = (TomcatModuleDeploymentModel)deployment;
    deployParameters.put(TomEEDeployPropertyNames.USE_CONTEXT_ROOT, Boolean.toString(!tomeeDeployment.isEEArtifact()));
    deployParameters.put(TomEEDeployPropertyNames.CONTEXT_ROOT, tomeeDeployment.CONTEXT_PATH);
  }

  @Nonnull
  @Override
  protected String getDeploymentName(DeploymentModel deployment, File source) throws Exception {
    String path = source.getAbsolutePath();
    String deploymentName = source.isDirectory() || path.endsWith(".jar") ? path : FileUtil.getNameWithoutExtension(path);
    if (FileSystemUtil.isSymLink(deploymentName)) {
      String resolvedPath = FileSystemUtil.resolveSymLink(deploymentName);
      if (resolvedPath != null) {
        return resolvedPath;
      }
    }
    return deploymentName;
  }
}
