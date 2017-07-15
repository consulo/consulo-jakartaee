/*
 * Copyright 2000-2007 JetBrains s.r.o.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.intellij.javaee.run.configuration;

import javax.swing.Icon;

import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import com.intellij.execution.configurations.ConfigurationFactory;
import com.intellij.execution.configurations.ConfigurationType;
import com.intellij.execution.configurations.RunConfiguration;
import com.intellij.javaee.J2EEBundle;
import com.intellij.javaee.appServerIntegrations.AppServerIntegration;
import com.intellij.javaee.appServerIntegrations.ApplicationServer;
import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiFile;
import consulo.javaee.bundle.JavaEEServerBundleType;

public abstract class J2EEConfigurationType implements ConfigurationType
{
	private ConfigurationFactory myRemoteFactory;
	private ConfigurationFactory myLocalFactory;

	protected void init()
	{
		myRemoteFactory = J2EEConfigurationFactory.getInstance().createFactory(this, false, J2EEBundle.message("run.configuration.remote"));
		myLocalFactory = J2EEConfigurationFactory.getInstance().createFactory(this, true, J2EEBundle.message("run.configuration.local"));
	}

	public ConfigurationFactory getLocalFactory()
	{
		return myLocalFactory;
	}

	protected abstract RunConfiguration createJ2EEConfigurationTemplate(ConfigurationFactory factory, Project project, boolean isLocal);

	@Override
	public ConfigurationFactory[] getConfigurationFactories()
	{
		return new ConfigurationFactory[]{
				myLocalFactory,
				myRemoteFactory
		};
	}

	@Nullable
	@Deprecated
	public AppServerIntegration getBundle()
	{
		return getBundleType().getIntegration();
	}

	@NotNull
	public JavaEEServerBundleType getBundleType()
	{
		throw new UnsupportedOperationException();
	}

	@Nullable
	@NonNls
	public String getUrlToOpenInBrowser(@NotNull ApplicationServer server, @NotNull PsiFile psiFile)
	{
		return null;
	}

	@NotNull
	public Icon getLocalIcon()
	{
		return getIcon();
	}

	@NotNull
	public Icon getRemoteIcon()
	{
		return getIcon();
	}
}
