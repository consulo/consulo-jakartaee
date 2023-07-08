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
package consulo.jakartaee.webServer.impl.run.configuration;

import consulo.annotation.component.ComponentScope;
import consulo.annotation.component.ServiceAPI;
import consulo.execution.RunnerAndConfigurationSettings;
import consulo.execution.configuration.ConfigurationFactory;
import consulo.execution.configuration.RunConfiguration;
import consulo.ide.ServiceManager;
import consulo.jakartaee.webServer.impl.appServerIntegrations.ApplicationServer;
import consulo.jakartaee.webServer.impl.run.localRun.ExecutableObjectStartupPolicy;
import consulo.javaee.bundle.JavaEEServerBundleType;
import consulo.language.psi.PsiFile;
import consulo.localize.LocalizeValue;
import consulo.project.Project;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

@ServiceAPI(ComponentScope.APPLICATION)
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

	public abstract ConfigurationFactory createFactory(J2EEConfigurationType type, boolean isLocal, String id, LocalizeValue name);

	public abstract boolean isConfigurationApplicable(@Nonnull J2EEConfigurationType type, @Nonnull Project project);
}
