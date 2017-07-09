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
import consulo.javaee.bundle.JavaEEServerBundleType;

public abstract class JavaeeConfigurationType extends J2EEConfigurationType
{
	private JavaEEServerBundleType myBundleType;

	protected JavaeeConfigurationType(JavaEEServerBundleType bundleType)
	{
		myBundleType = bundleType;
		init();
	}

	@NotNull
	@Override
	public JavaEEServerBundleType getBundleType()
	{
		return myBundleType;
	}

	@Override
	@NotNull
	public String getId()
	{
		return getClass().getSimpleName();
	}

	@Override
	public Icon getIcon()
	{
		return myBundleType.getIcon();
	}

	@Override
	public String getDisplayName()
	{
		return JavaeeBundle.getText("ConfigurationType.name", myBundleType.getPresentableName());
	}

	@Override
	public String getConfigurationTypeDescription()
	{
		return JavaeeBundle.getText("ConfigurationType.description", myBundleType.getPresentableName());
	}

	@Override
	protected RunConfiguration createJ2EEConfigurationTemplate(ConfigurationFactory factory, Project project, boolean isLocal)
	{
		return null;
	}

	@NotNull
	public abstract ServerModel createLocalModel();

	@NotNull
	public abstract ServerModel createRemoteModel();

	@NotNull
	public abstract ExecutableObjectStartupPolicy createStartupPolicy();
}
