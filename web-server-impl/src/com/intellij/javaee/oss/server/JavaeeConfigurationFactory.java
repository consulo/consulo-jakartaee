/*
 * Copyright (c) 2004 - 2009 by Fuhrer Engineering AG, CH-2504 Biel/Bienne, Switzerland
 */

package com.intellij.javaee.oss.server;

import javax.annotation.Nullable;
import javax.swing.Icon;

import javax.annotation.Nonnull;

import com.intellij.execution.configuration.ConfigurationFactoryEx;
import com.intellij.execution.configurations.ConfigurationType;
import com.intellij.execution.configurations.RunConfiguration;
import com.intellij.javaee.run.configuration.CommonModel;
import com.intellij.javaee.run.configuration.J2EEConfigurationFactory;
import com.intellij.javaee.run.configuration.ServerModel;
import com.intellij.javaee.run.localRun.ExecutableObjectStartupPolicy;
import com.intellij.openapi.project.Project;
import consulo.javaee.bundle.JavaEEServerBundleType;

public abstract class JavaeeConfigurationFactory extends ConfigurationFactoryEx
{
	private final String myName;
	private final Icon myIcon;
	protected final boolean myLocal;
	protected JavaEEServerBundleType myBundleType;

	protected JavaeeConfigurationFactory(ConfigurationType type, String name, Icon icon, boolean local, JavaEEServerBundleType bundleType)
	{
		super(type);
		myName = name;
		myIcon = icon;
		myLocal = local;
		myBundleType = bundleType;
	}

	@Override
	public String getName()
	{
		return myName;
	}

	@Override
	public Icon getIcon()
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
