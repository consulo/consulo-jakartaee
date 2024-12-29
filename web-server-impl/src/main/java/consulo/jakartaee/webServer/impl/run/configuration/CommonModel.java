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

import consulo.jakartaee.webServer.impl.appServerIntegrations.AppServerIntegration;
import consulo.jakartaee.webServer.impl.deployment.DeploymentModel;
import consulo.jakartaee.webServer.impl.deployment.DeploymentSettings;
import consulo.annotation.DeprecationInfo;
import consulo.compiler.artifact.Artifact;
import consulo.compiler.scope.CompileScope;
import consulo.content.bundle.Sdk;
import consulo.execution.configuration.ModuleRunConfiguration;
import consulo.javaee.bundle.JavaEEServerBundleType;
import org.jetbrains.annotations.NonNls;

import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import java.util.Collection;
import java.util.List;

public interface CommonModel extends ModuleRunConfiguration
{
	@NonNls
	String LOCALHOST = "localhost";

	@Deprecated
	@DeprecationInfo("Use #getServerBundleType()")
	AppServerIntegration getIntegration();

	boolean isLocal();

	@Nullable
	@Deprecated
	@DeprecationInfo("Use #getServerBundle()")
	default Sdk getApplicationServer()
	{
		return getServerBundle();
	}

	@Nonnull
	JavaEEServerBundleType getServerBundleType();

	@Nullable
	Sdk getServerBundle();

	String getHost();

	int getPort();

	ServerModel getServerModel();

	void initialize();

	Collection<? extends DeploymentModel> getDeploymentModels();

	List<Artifact> getDeployedArtifacts();

	List<Artifact> getArtifactsToBuild();

	@Nullable
	DeploymentModel getDeploymentModel(Artifact artifact);

	void setUrlToOpenInBrowser(@Nullable String newUrl);

	@Nonnull
	DeploymentSettings getDeploymentSettings();

	String getUrlToOpenInBrowser();

	CompileScope getCompileScope();
}
