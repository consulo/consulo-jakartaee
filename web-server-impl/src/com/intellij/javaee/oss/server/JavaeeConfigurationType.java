/*
 * Copyright (c) 2004 - 2009 by Fuhrer Engineering AG, CH-2504 Biel/Bienne, Switzerland
 */

package com.intellij.javaee.oss.server;

import javax.swing.Icon;

import org.jetbrains.annotations.NotNull;
import com.intellij.execution.configurations.ConfigurationFactory;
import com.intellij.execution.configurations.RunConfiguration;
import com.intellij.javaee.oss.JavaeeBundle;
import com.intellij.javaee.run.configuration.J2EEConfigurationType;
import com.intellij.javaee.run.configuration.ServerModel;
import com.intellij.javaee.run.localRun.ExecutableObjectStartupPolicy;
import com.intellij.openapi.project.Project;

public abstract class JavaeeConfigurationType extends J2EEConfigurationType
{
	private JavaeeIntegration myIntegration;

	protected JavaeeConfigurationType(JavaeeIntegration integration)
	{
		myIntegration = integration;
	}

	@NotNull
	public String getId()
	{
		return getClass().getSimpleName();
	}

	public Icon getIcon()
	{
		return JavaeeIntegration.getInstance().getIcon();
	}

	public String getDisplayName()
	{
		return JavaeeBundle.getText("ConfigurationType.name", JavaeeIntegration.getInstance().getName());
	}

	public String getConfigurationTypeDescription()
	{
		return JavaeeBundle.getText("ConfigurationType.description", JavaeeIntegration.getInstance().getName());
	}

	@Override
	protected RunConfiguration createJ2EEConfigurationTemplate(ConfigurationFactory factory, Project project, boolean isLocal)
	{
		return null;
	}

	@NotNull
	protected abstract ServerModel createLocalModel();

	@NotNull
	protected abstract ServerModel createRemoteModel();

	@NotNull
	protected abstract ExecutableObjectStartupPolicy createStartupPolicy();
}
