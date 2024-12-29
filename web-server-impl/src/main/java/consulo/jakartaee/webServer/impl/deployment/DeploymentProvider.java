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
package consulo.jakartaee.webServer.impl.deployment;

import com.intellij.javaee.artifact.JavaeeArtifactUtil;
import consulo.jakartaee.webServer.impl.run.configuration.CommonModel;
import consulo.jakartaee.webServer.impl.serverInstances.J2EEServerInstance;
import consulo.compiler.artifact.ArtifactType;
import consulo.project.Project;
import org.jetbrains.annotations.NonNls;

import jakarta.annotation.Nullable;
import java.util.Collection;
import java.util.List;

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
