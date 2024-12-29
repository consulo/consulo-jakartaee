/*
 * Copyright (c) 2004 - 2009 by Fuhrer Engineering AG, CH-2504 Biel/Bienne, Switzerland
 */

package consulo.jakartaee.webServer.impl.oss.server;

import consulo.jakartaee.webServer.impl.appServerIntegrations.AppServerDeployedFileUrlProvider;
import consulo.jakartaee.webServer.impl.appServerIntegrations.AppServerIntegration;
import consulo.jakartaee.webServer.impl.appServerIntegrations.ApplicationServerUrlMapping;
import consulo.jakartaee.webServer.impl.deployment.DeploymentModel;
import consulo.jakartaee.webServer.impl.oss.JavaeeBundle;
import consulo.jakartaee.webServer.impl.oss.descriptor.JavaeeDescriptorsManager;
import consulo.jakartaee.webServer.impl.serverInstances.J2EEServerInstance;
import consulo.compiler.artifact.ArtifactUtil;
import consulo.javaee.module.extension.JavaEEModuleExtension;
import consulo.ui.image.Image;
import org.jetbrains.annotations.NonNls;

import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.function.Function;

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
		return JavaeeBundle.message("Integration.name", getName());
	}

	@Override
	@Nonnull
	public AppServerDeployedFileUrlProvider getDeployedFileUrlProvider()
	{
		return new ApplicationServerUrlMapping()
		{
			@Override
			@Nullable
			public String getUrlForDeployedFile(@Nonnull J2EEServerInstance instance, @Nonnull DeploymentModel deployment, @Nonnull JavaEEModuleExtension<?> facet, @Nonnull String path)
			{
				if(instance instanceof JavaeeServerInstance)
				{
					String root = ((JavaeeServerInstance) instance).getContextRoot(facet);
					if(root != null)
					{
						JavaeeServerModel model = (JavaeeServerModel) instance.getCommonModel().getServerModel();
						return ArtifactUtil.concatPaths(model.getDefaultUrlForBrowser(false), root, path);
					}
				}
				return null;
			}
		};
	}

	@Nonnull
	public abstract String getName();

	@Nonnull
	public Image getIcon()
	{
		throw new UnsupportedOperationException();
	}

	@Nullable
	@NonNls
	@Deprecated
	public String getNameFromTemplate(String template) throws Exception
	{
		return null;
	}

	@Nullable
	@NonNls
	@Deprecated
	public String getVersionFromTemplate(String template) throws Exception
	{
		return null;
	}

	@Nonnull
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
			throw new FileNotFoundException(JavaeeBundle.message("Error.fileNotFound", path));
		}
	}
}
