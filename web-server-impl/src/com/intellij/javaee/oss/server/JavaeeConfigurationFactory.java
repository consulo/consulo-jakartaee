/*
 * Copyright (c) 2004 - 2009 by Fuhrer Engineering AG, CH-2504 Biel/Bienne, Switzerland
 */

package com.intellij.javaee.oss.server;

import javax.swing.Icon;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import com.intellij.execution.configurations.ConfigurationFactory;
import com.intellij.execution.configurations.ConfigurationType;
import com.intellij.execution.configurations.RunConfiguration;
import com.intellij.javaee.run.configuration.CommonModel;
import com.intellij.javaee.run.configuration.J2EEConfigurationFactory;
import com.intellij.javaee.run.configuration.ServerModel;
import com.intellij.javaee.run.localRun.ExecutableObjectStartupPolicy;
import com.intellij.openapi.project.Project;

public abstract class JavaeeConfigurationFactory extends ConfigurationFactory
{
	private final String name;
	private final Icon icon;
	private final boolean local;
	private JavaeeIntegration myIntegration;

	protected JavaeeConfigurationFactory(ConfigurationType type, String name, Icon icon, boolean local, JavaeeIntegration integration)
	{
		super(type);
		this.name = name;
		this.icon = icon;
		this.local = local;
		myIntegration = integration;
	}

	@Override
	public String getName()
	{
		return name;
	}

	@Override
	public Icon getIcon()
	{
		return icon;
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
		return factory.createJ2EERunConfiguration(this, project, createServerModel(), myIntegration, local, createPolicy());
	}

	@NotNull
	protected abstract ServerModel createServerModel();

	@Nullable
	protected abstract ExecutableObjectStartupPolicy createPolicy();
}
