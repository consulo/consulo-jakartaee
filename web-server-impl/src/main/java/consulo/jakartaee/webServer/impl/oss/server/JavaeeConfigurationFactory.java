/*
 * Copyright (c) 2004 - 2009 by Fuhrer Engineering AG, CH-2504 Biel/Bienne, Switzerland
 */

package consulo.jakartaee.webServer.impl.oss.server;

import consulo.jakartaee.webServer.impl.run.configuration.CommonModel;
import consulo.jakartaee.webServer.impl.run.configuration.J2EEConfigurationFactory;
import consulo.jakartaee.webServer.impl.run.configuration.ServerModel;
import consulo.jakartaee.webServer.impl.run.localRun.ExecutableObjectStartupPolicy;
import consulo.execution.configuration.ConfigurationFactoryEx;
import consulo.execution.configuration.ConfigurationType;
import consulo.execution.configuration.RunConfiguration;
import consulo.javaee.bundle.JavaEEServerBundleType;
import consulo.localize.LocalizeValue;
import consulo.project.Project;
import consulo.ui.image.Image;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public abstract class JavaeeConfigurationFactory extends ConfigurationFactoryEx
{
	private final LocalizeValue myName;
	private final Image myIcon;
	protected final boolean myLocal;
	protected JavaEEServerBundleType myBundleType;

	protected JavaeeConfigurationFactory(ConfigurationType type, LocalizeValue name, Image icon, boolean local, JavaEEServerBundleType bundleType)
	{
		super(type);
		myName = name;
		myIcon = icon;
		myLocal = local;
		myBundleType = bundleType;
	}

	@Nonnull
	@Override
	public LocalizeValue getName()
	{
		return myName;
	}

	@Override
	public Image getIcon()
	{
		return myIcon;
	}

	@Override
	public RunConfiguration createConfiguration(String name, RunConfiguration template)
	{
		RunConfiguration config = super.createConfiguration(name, template);
		((CommonModel) config).initialize();
		return config;
	}

	@Override
	public RunConfiguration createTemplateConfiguration(Project project)
	{
		J2EEConfigurationFactory factory = J2EEConfigurationFactory.getInstance();
		return factory.createJ2EERunConfiguration(this, project, createServerModel(), myBundleType, myLocal, createPolicy());
	}

	@Nonnull
	protected abstract ServerModel createServerModel();

	@Nullable
	protected abstract ExecutableObjectStartupPolicy createPolicy();
}
