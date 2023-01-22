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

import com.intellij.javaee.J2EEBundle;
import consulo.jakartaee.webServer.impl.appServerIntegrations.AppServerIntegration;
import consulo.content.bundle.Sdk;
import consulo.execution.configuration.ConfigurationFactory;
import consulo.execution.configuration.ConfigurationType;
import consulo.execution.configuration.RunConfiguration;
import consulo.javaee.bundle.JavaEEServerBundleType;
import consulo.language.psi.PsiFile;
import consulo.project.Project;
import consulo.ui.image.Image;
import org.jetbrains.annotations.NonNls;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

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

	@Nonnull
	public JavaEEServerBundleType getBundleType()
	{
		throw new UnsupportedOperationException();
	}

	@Nullable
	@NonNls
	public String getUrlToOpenInBrowser(@Nonnull Sdk server, @Nonnull PsiFile psiFile)
	{
		return null;
	}

	@Nonnull
	public Image getLocalIcon()
	{
		return getIcon();
	}

	@Nonnull
	public Image getRemoteIcon()
	{
		return getIcon();
	}
}
