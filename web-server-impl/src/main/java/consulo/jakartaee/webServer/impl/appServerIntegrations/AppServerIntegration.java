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
package consulo.jakartaee.webServer.impl.appServerIntegrations;

import consulo.jakartaee.webServer.impl.deployment.DeploymentProvider;
import consulo.jakartaee.webServer.impl.run.localRun.ExecutableObject;
import consulo.annotation.component.ComponentScope;
import consulo.annotation.component.ExtensionAPI;
import consulo.compiler.artifact.ArtifactType;
import consulo.component.extension.ExtensionPointName;
import consulo.fileTemplate.FileTemplateGroupDescriptor;
import consulo.fileTemplate.FileTemplateGroupDescriptorFactory;
import consulo.javaee.module.extension.JavaEEModuleExtension;
import consulo.project.Project;
import consulo.ui.image.Image;

import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import java.util.Collection;
import java.util.Collections;

/**
 * Application server integration component
 * Specific app server integrations should implement it
 */
@Deprecated
@ExtensionAPI(ComponentScope.APPLICATION)
public abstract class AppServerIntegration implements FileTemplateGroupDescriptorFactory
{
	public static final ExtensionPointName<AppServerIntegration> EXTENSION_POINT = ExtensionPointName.create(AppServerIntegration.class);

	public abstract String getPresentableName();

	@Nullable
	public Image getIcon()
	{
		return null;
	}

  /*@Nullable
  public DataSourceProvider getDataSourceProvider() {
    return null;
  }*/

  /*@Nullable
  public AppServerLibrariesProvider createLibrariesProvider(@NotNull ApplicationServerPersistentData persistentData) {
    return null;
  }*/

	@Nullable
	public AppServerSpecificValidator getAppServerSpecificValidator(@Nonnull JavaEEModuleExtension facet, @Nonnull ApplicationServer server)
	{
		return null;
	}

	@Nullable
	public AppServerSpecificValidator getAppServerSpecificValidator(@Nonnull ApplicationServer server, final ArtifactType artifactType, @Nonnull Project project)
	{
		return null;
	}

	public boolean isStartupScriptTerminating(@Nonnull ExecutableObject startupScript)
	{
		return false;
	}

	/*public DeploymentModel createNewDeploymentModel(CommonModel commonModel, DeploymentSource source)
	{
		return null;
	}

	public SettingsEditor<DeploymentModel> createAdditionalDeploymentSettingsEditor(CommonModel commonModel, DeploymentSource source)
	{
		return null;
	} */

	@Nullable
	public DeploymentProvider getDeploymentProvider(boolean local)
	{
		return null;
	}

	@Nonnull
	public AppServerDeployedFileUrlProvider getDeployedFileUrlProvider()
	{
		return AppServerDeployedFileUrlProvider.DEFAULT;
	}

	/**
	 * @deprecated override {@link DeploymentProvider#getSupportedArtifactTypes()} instead
	 */
	@Nonnull
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
	/*
	public boolean isJreCustomizable()
	{
		return false;
	}   */
}
