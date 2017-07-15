/*
 * Copyright 2000-2012 JetBrains s.r.o.
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
package com.intellij.javaee.appServerIntegrations;

import java.util.Collection;
import java.util.Collections;

import javax.swing.Icon;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import com.intellij.ide.fileTemplates.FileTemplateGroupDescriptor;
import com.intellij.ide.fileTemplates.FileTemplateGroupDescriptorFactory;
import com.intellij.javaee.deployment.DeploymentModel;
import com.intellij.javaee.deployment.DeploymentProvider;
import com.intellij.javaee.run.configuration.CommonModel;
import com.intellij.javaee.run.localRun.ExecutableObject;
import com.intellij.openapi.extensions.ExtensionPointName;
import com.intellij.openapi.options.SettingsEditor;
import com.intellij.openapi.project.Project;
import com.intellij.packaging.artifacts.ArtifactType;
import com.intellij.remoteServer.configuration.deployment.DeploymentSource;
import consulo.javaee.module.extension.JavaEEModuleExtension;

/**
 * Application server integration component
 * Specific app server integrations should implement it
 */
@Deprecated
public abstract class AppServerIntegration implements FileTemplateGroupDescriptorFactory
{
	public static final ExtensionPointName<AppServerIntegration> EXTENSION_POINT = ExtensionPointName.create("com.intellij.javaee.appServerIntegration");

	public abstract String getPresentableName();

	@Nullable
	public Icon getIcon()
	{
		return null;
	}

  /*@Nullable
  public DataSourceProvider getDataSourceProvider() {
    return null;
  }*/

	@Nullable
	protected ApplicationServerHelper createServerHelper()
	{
		return null;
	}

	@Nullable
	public ApplicationServerPersistentDataEditor createNewServerEditor()
	{
		return null;
	}

  /*@Nullable
  public AppServerLibrariesProvider createLibrariesProvider(@NotNull ApplicationServerPersistentData persistentData) {
    return null;
  }*/

	@Nullable
	public AppServerSpecificValidator getAppServerSpecificValidator(@NotNull JavaEEModuleExtension facet, @NotNull ApplicationServer server)
	{
		return null;
	}

	@Nullable
	public AppServerSpecificValidator getAppServerSpecificValidator(@NotNull ApplicationServer server, final ArtifactType artifactType, @NotNull Project project)
	{
		return null;
	}

	public boolean isStartupScriptTerminating(@NotNull ExecutableObject startupScript)
	{
		return false;
	}

	public DeploymentModel createNewDeploymentModel(CommonModel commonModel, DeploymentSource source)
	{
		return null;
	}

	public SettingsEditor<DeploymentModel> createAdditionalDeploymentSettingsEditor(CommonModel commonModel, DeploymentSource source)
	{
		return null;
	}

	@Nullable
	public DeploymentProvider getDeploymentProvider(boolean local)
	{
		return null;
	}

	@NotNull
	public AppServerDeployedFileUrlProvider getDeployedFileUrlProvider()
	{
		return AppServerDeployedFileUrlProvider.DEFAULT;
	}

	/**
	 * @deprecated override {@link com.intellij.javaee.deployment.DeploymentProvider#getSupportedArtifactTypes()} instead
	 */
	@NotNull
	public Collection<Class<? extends JavaEEModuleExtension>> getSupportedFacetTypes()
	{
		return Collections.emptyList();
	}

	/**
	 * @deprecated register a separate {@link com.intellij.ide.fileTemplates.FileTemplateGroupDescriptorFactory} instance as an extension point instead
	 */
	@Nullable
	public FileTemplateGroupDescriptor getFileTemplatesDescriptor()
	{
		return null;
	}

	public boolean isKillOnDestroyWhileStarting()
	{
		return false;
	}

	public boolean isDebugAllowed(boolean isLocal)
	{
		return true;
	}

	public boolean isJreCustomizable()
	{
		return false;
	}
}
