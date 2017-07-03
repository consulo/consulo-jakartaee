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
package com.intellij.javaee.deployment;

import java.util.Collection;
import java.util.List;

import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.Nullable;
import com.intellij.javaee.artifact.JavaeeArtifactUtil;
import com.intellij.javaee.run.configuration.CommonModel;
import com.intellij.javaee.serverInstances.J2EEServerInstance;
import com.intellij.openapi.options.SettingsEditor;
import com.intellij.openapi.project.Project;
import com.intellij.packaging.artifacts.Artifact;
import com.intellij.packaging.artifacts.ArtifactPointer;
import com.intellij.packaging.artifacts.ArtifactType;

public abstract class DeploymentProvider {
  public abstract void doDeploy(Project project, J2EEServerInstance instance, DeploymentModel model);

  public abstract void startUndeploy(J2EEServerInstance activeInstance, DeploymentModel model);

  public abstract void updateDeploymentStatus(J2EEServerInstance instance, DeploymentModel model);

  public boolean isDeployOrderMatter() {
    return false;
  }

  public boolean isNeedUndeployOnDisconnect() {
    return false;
  }

  public void cleanDeployments(J2EEServerInstance instance, List<DeploymentModel> deploymentModels) {

  }

  public boolean isModuleDeployAllowed() {
    return false;
  }

  @Nullable
  public DeploymentMethod[] getAvailableMethods() {
    return null;
  }

  @Nullable @NonNls
  public String getHelpId() {
    return null;
  }

  public DeploymentModel createNewDeploymentModel(CommonModel commonModel, DeploymentSource source) {
    if (source instanceof ArtifactDeploymentSource) {
      return createNewDeploymentModel(commonModel, ((ArtifactDeploymentSource)source).getArtifactPointer());
    }
    return null;
  }

  /**
   * @deprecated override {@link #createNewDeploymentModel(com.intellij.javaee.run.configuration.CommonModel, DeploymentSource)} instead
   */
  public DeploymentModel createNewDeploymentModel(CommonModel commonModel, ArtifactPointer artifactPointer) {
    return null;
  }

  @Nullable
  public SettingsEditor<DeploymentModel> createAdditionalDeploymentSettingsEditor(CommonModel commonModel, DeploymentSource source) {
    if (source instanceof ArtifactDeploymentSource) {
      final Artifact artifact = ((ArtifactDeploymentSource)source).getArtifact();
      if (artifact != null) {
        return createAdditionalDeploymentSettingsEditor(commonModel, artifact);
      }
    }
    return null;
  }

  /**
   * @deprecated override {@link #createAdditionalDeploymentSettingsEditor(com.intellij.javaee.run.configuration.CommonModel, DeploymentSource)} instead
   */
  @Nullable
  public SettingsEditor<DeploymentModel> createAdditionalDeploymentSettingsEditor(CommonModel commonModel, Artifact artifact) {
    return null;
  }

  public boolean isResourcesReloadingSupported(CommonModel model, ArtifactType artifactType) {
    return model.isLocal() && !JavaeeArtifactUtil.getInstance().isArchive(artifactType);
  }

  public Collection<? extends ArtifactType> getSupportedArtifactTypes() {
    return JavaeeArtifactUtil.getInstance().getAllJavaeeArtifactTypes();
  }

  @Nullable
  public ExternalFileDeploymentProvider getExternalFileDeploymentProvider() {
    return JavaeeDeploymentUtil.getInstance().createExternalFileDeploymentProvider(getSupportedArtifactTypes());
  }

  /*public List<CustomDeploymentProvider> getCustomDeploymentProviders() {
    return Collections.emptyList();
  }*/
}
