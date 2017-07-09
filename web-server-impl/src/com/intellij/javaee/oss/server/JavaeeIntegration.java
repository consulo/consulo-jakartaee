/*
 * Copyright (c) 2004 - 2009 by Fuhrer Engineering AG, CH-2504 Biel/Bienne, Switzerland
 */

package com.intellij.javaee.oss.server;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Collection;
import java.util.List;

import javax.swing.Icon;

import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import com.intellij.javaee.appServerIntegrations.AppServerDeployedFileUrlProvider;
import com.intellij.javaee.appServerIntegrations.AppServerIntegration;
import com.intellij.javaee.appServerIntegrations.ApplicationServerHelper;
import com.intellij.javaee.appServerIntegrations.ApplicationServerUrlMapping;
import com.intellij.javaee.deployment.DeploymentModel;
import com.intellij.javaee.oss.JavaeeBundle;
import com.intellij.javaee.oss.descriptor.JavaeeDescriptorsManager;
import com.intellij.javaee.serverInstances.J2EEServerInstance;
import com.intellij.openapi.deployment.DeploymentUtil;
import com.intellij.util.Function;
import consulo.javaee.module.extension.JavaEEModuleExtension;

@Deprecated
public abstract class JavaeeIntegration extends AppServerIntegration
{
	public static boolean checkDir(File f)
	{
		return true;
	}

	@Override
	public String getPresentableName()
	{
		return JavaeeBundle.getText("Integration.name", getName());
	}

	@Override
	@Nullable
	protected ApplicationServerHelper createServerHelper()
	{
		return new JavaeeServerHelper(this);
	}

	@Override
	@NotNull
	public AppServerDeployedFileUrlProvider getDeployedFileUrlProvider()
	{
		return new ApplicationServerUrlMapping()
		{
			@Override
			@Nullable
			public String getUrlForDeployedFile(@NotNull J2EEServerInstance instance, @NotNull DeploymentModel deployment, @NotNull JavaEEModuleExtension<?> facet, @NotNull String path)
			{
				if(instance instanceof JavaeeServerInstance)
				{
					String root = ((JavaeeServerInstance) instance).getContextRoot(facet);
					if(root != null)
					{
						JavaeeServerModel model = (JavaeeServerModel) instance.getCommonModel().getServerModel();
						return DeploymentUtil.concatPaths(model.getDefaultUrlForBrowser(false), root, path);
					}
				}
				return null;
			}
		};
	}

	@NotNull
	public abstract String getName();

	@NotNull
	public abstract Icon getIcon();

	@NotNull
	public abstract Icon getBigIcon();

	@Nullable
	@NonNls
	public abstract String getNameFromTemplate(String template) throws Exception;

	@Nullable
	@NonNls
	public abstract String getVersionFromTemplate(String template) throws Exception;

	@NotNull
	@NonNls
	protected String getServerVersion(String home) throws Exception
	{
		return null;
	}

	protected abstract void checkValidServerHome(String home, String version) throws Exception;

	protected abstract void addLibraryLocations(String home, List<File> locations);

	protected boolean allLibrariesFound(Collection<String> classes, Function<String, String> mapper)
	{
		return classes.isEmpty();
	}

	protected boolean allLibrariesExceptEjbFound(Collection<String> classes, Function<String, String> mapper)
	{
		return classes.isEmpty();
	}

	public JavaeeDescriptorsManager getDescriptorsManager()
	{
		return new JavaeeDescriptorsManager();
	}

	protected void collectDescriptors(JavaeeDescriptorsManager descriptorsManager)
	{
	}

	public String getContextRoot(JavaEEModuleExtension facet)
	{
		return null;
	}

	public String getServerVersion(JavaeePersistentData persistentData) throws Exception
	{
		return null;
	}

	protected void checkFile(@NonNls String home, @NonNls String path) throws IOException
	{
		if(!new File(home, path).exists())
		{
			throw new FileNotFoundException(JavaeeBundle.getText("Error.fileNotFound", path));
		}
	}
}
