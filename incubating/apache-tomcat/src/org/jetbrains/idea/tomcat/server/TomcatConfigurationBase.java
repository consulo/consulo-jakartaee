package org.jetbrains.idea.tomcat.server;

import jakarta.annotation.Nonnull;

import org.jetbrains.annotations.NonNls;
import org.jetbrains.idea.tomcat.TomcatPersistentDataWrapper;
import org.jetbrains.idea.tomcat.TomcatStartupPolicy;
import com.intellij.javaee.deployment.JspDeploymentManager;
import com.intellij.javaee.oss.server.JavaeeConfigurationType;
import com.intellij.javaee.run.configuration.CommonModel;
import com.intellij.javaee.run.configuration.ServerModel;
import com.intellij.javaee.run.localRun.ExecutableObjectStartupPolicy;
import com.intellij.javaee.web.WebUtil;
import com.intellij.openapi.deployment.DeploymentUtil;
import com.intellij.openapi.projectRoots.Sdk;
import com.intellij.psi.PsiFile;
import consulo.javaee.bundle.JavaEEServerBundleType;
import consulo.javaee.module.extension.JavaWebModuleExtension;

/**
 * Created by IntelliJ IDEA.
 * User: michael.golubev
 */
public class TomcatConfigurationBase extends JavaeeConfigurationType
{
	public TomcatConfigurationBase(JavaEEServerBundleType integration)
	{
		super(integration);
	}

	@Override
	@Nonnull
	public ServerModel createLocalModel()
	{
		return new TomcatLocalModel();
	}

	@Override
	@Nonnull
	public ServerModel createRemoteModel()
	{
		return new TomcatRemoteModel();
	}

	@Override
	@Nonnull
	public ExecutableObjectStartupPolicy createStartupPolicy()
	{
		return new TomcatStartupPolicy();
	}

	@Override
	public String getUrlToOpenInBrowser(@Nonnull Sdk server, @Nonnull PsiFile psiFile)
	{
		final JavaWebModuleExtension webFacet = WebUtil.getWebFacet(psiFile);
		if(webFacet == null)
		{
			return null;
		}

		final int port = new TomcatPersistentDataWrapper(server).getSourceLocalPort();
		@NonNls final String root = "http://" + CommonModel.LOCALHOST + ":" + port;
		final String relativePath = JspDeploymentManager.getInstance().computeRelativeTargetPath(psiFile, webFacet);
		if(relativePath == null)
		{
			return null;
		}
		return DeploymentUtil.concatPaths(root, relativePath);
	}
}
