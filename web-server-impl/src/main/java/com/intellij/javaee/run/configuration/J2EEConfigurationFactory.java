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

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.intellij.execution.RunnerAndConfigurationSettings;
import com.intellij.execution.configurations.ConfigurationFactory;
import com.intellij.execution.configurations.RunConfiguration;
import com.intellij.javaee.appServerIntegrations.ApplicationServer;
import com.intellij.javaee.run.localRun.ExecutableObjectStartupPolicy;
import com.intellij.openapi.components.ServiceManager;
import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiFile;
import consulo.javaee.bundle.JavaEEServerBundleType;

public abstract class J2EEConfigurationFactory
{
	public static J2EEConfigurationFactory getInstance()
	{
		return ServiceManager.getService(J2EEConfigurationFactory.class);
	}

	public abstract RunConfiguration createJ2EERunConfiguration(ConfigurationFactory factory,
			Project project,
			ServerModel serverSpecific,
			JavaEEServerBundleType integration,
			boolean isLocal,
			JavaCommandLineStartupPolicy startupPolicy);

	public abstract RunConfiguration createJ2EERunConfiguration(ConfigurationFactory factory,
			Project project,
			ServerModel serverSpecific,
			JavaEEServerBundleType integration,
			boolean isLocal,
			ExecutableObjectStartupPolicy startupPolicy);

	@Nullable
	public abstract RunnerAndConfigurationSettings createSettingsByFile(@Nonnull PsiFile psiFile, @Nonnull J2EEConfigurationType configurationType);

	public abstract RunnerAndConfigurationSettings addAppServerConfiguration(@Nonnull Project project, @Nonnull ConfigurationFactory type, @Nonnull ApplicationServer appServer);

	public abstract ConfigurationFactory createFactory(J2EEConfigurationType type, boolean isLocal, String name);

	public abstract boolean isConfigurationApplicable(@Nonnull J2EEConfigurationType type, @Nonnull Project project);
}
