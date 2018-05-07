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

import java.util.Collection;
import java.util.List;

import org.jetbrains.annotations.NonNls;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import com.intellij.execution.configurations.ModuleRunConfiguration;
import com.intellij.javaee.appServerIntegrations.AppServerIntegration;
import com.intellij.javaee.deployment.DeploymentModel;
import com.intellij.javaee.deployment.DeploymentSettings;
import com.intellij.openapi.compiler.CompileScope;
import com.intellij.openapi.projectRoots.Sdk;
import com.intellij.packaging.artifacts.Artifact;
import consulo.annotations.DeprecationInfo;
import consulo.javaee.bundle.JavaEEServerBundleType;

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
