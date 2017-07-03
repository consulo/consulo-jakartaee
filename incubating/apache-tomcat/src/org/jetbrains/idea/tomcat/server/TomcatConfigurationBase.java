package org.jetbrains.idea.tomcat.server;

import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.idea.tomcat.TomcatPersistentDataWrapper;
import org.jetbrains.idea.tomcat.TomcatStartupPolicy;
import com.intellij.javaee.appServerIntegrations.ApplicationServer;
import com.intellij.javaee.deployment.JspDeploymentManager;
import com.intellij.javaee.oss.server.JavaeeConfigurationType;
import com.intellij.javaee.run.configuration.CommonModel;
import com.intellij.javaee.run.configuration.ServerModel;
import com.intellij.javaee.run.localRun.ExecutableObjectStartupPolicy;
import com.intellij.javaee.web.WebUtil;
import com.intellij.openapi.deployment.DeploymentUtil;
import com.intellij.psi.PsiFile;
import consulo.javaee.module.extension.JavaWebModuleExtension;

/**
 * Created by IntelliJ IDEA.
 * User: michael.golubev
 */
public class TomcatConfigurationBase extends JavaeeConfigurationType
{

  public TomcatConfigurationBase(TomcatIntegration integration) {
    super(integration);
  }

  @Override
  @NotNull
  protected ServerModel createLocalModel() {
    return new TomcatLocalModel();
  }

  @Override
  @NotNull
  protected ServerModel createRemoteModel() {
    return new TomcatRemoteModel();
  }

  @Override
  @NotNull
  protected ExecutableObjectStartupPolicy createStartupPolicy() {
    return new TomcatStartupPolicy();
  }

  @Override
  public String getUrlToOpenInBrowser(@NotNull ApplicationServer server, @NotNull PsiFile psiFile) {
    final JavaWebModuleExtension webFacet = WebUtil.getWebFacet(psiFile);
    if (webFacet == null) return null;

    final int port = new TomcatPersistentDataWrapper(server).getSourceLocalPort();
    @NonNls final String root = "http://" + CommonModel.LOCALHOST + ":" + port;
    final String relativePath = JspDeploymentManager.getInstance().computeRelativeTargetPath(psiFile, webFacet);
    if (relativePath == null) return null;
    return DeploymentUtil.concatPaths(root, relativePath);
  }
}
