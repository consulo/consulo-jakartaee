/*
 * Copyright (c) 2004 - 2009 by Fuhrer Engineering AG, CH-2504 Biel/Bienne, Switzerland
 */

package consulo.jakartaee.webServer.impl.oss.server;

import consulo.jakartaee.webServer.impl.localize.JakartaWebServerLocalize;
import consulo.jakartaee.webServer.impl.run.configuration.J2EEConfigurationType;
import consulo.jakartaee.webServer.impl.run.configuration.ServerModel;
import consulo.jakartaee.webServer.impl.run.localRun.ExecutableObjectStartupPolicy;
import consulo.execution.configuration.ConfigurationFactory;
import consulo.execution.configuration.RunConfiguration;
import consulo.javaee.bundle.JavaEEServerBundleType;
import consulo.localize.LocalizeValue;
import consulo.project.Project;
import consulo.ui.image.Image;

import javax.annotation.Nonnull;

public abstract class JavaeeConfigurationType extends J2EEConfigurationType
{
	private JavaEEServerBundleType myBundleType;

	protected JavaeeConfigurationType(JavaEEServerBundleType bundleType)
	{
		myBundleType = bundleType;
		init();
	}

	@Nonnull
	@Override
	public JavaEEServerBundleType getBundleType()
	{
		return myBundleType;
	}

	@Override
	@Nonnull
	public String getId()
	{
		return getClass().getSimpleName();
	}

	@Override
	public Image getIcon()
	{
		return myBundleType.getIcon();
	}

	@Override
	public LocalizeValue getDisplayName()
	{
		return JakartaWebServerLocalize.configurationtypeName(myBundleType.getPresentableName());
	}

	@Override
	public LocalizeValue getConfigurationTypeDescription()
	{
		return JakartaWebServerLocalize.configurationtypeDescription(myBundleType.getPresentableName());
	}

	@Override
	protected RunConfiguration createJ2EEConfigurationTemplate(ConfigurationFactory factory, Project project, boolean isLocal)
	{
		return null;
	}

	@Nonnull
	public abstract ServerModel createLocalModel();

	@Nonnull
	public abstract ServerModel createRemoteModel();

	@Nonnull
	public abstract ExecutableObjectStartupPolicy createStartupPolicy();
}
