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

import java.io.File;
import java.util.Collections;
import java.util.List;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import com.intellij.javaee.run.configuration.CommonModel;
import com.intellij.javaee.serverInstances.J2EEServerInstance;
import com.intellij.openapi.components.ServiceManager;
import com.intellij.openapi.project.Project;
import com.intellij.packaging.artifacts.Artifact;
import consulo.javaee.module.extension.JavaEEModuleExtension;

/**
 * author: lesya
 */
public abstract class DeploymentManager {
  public static DeploymentManager getInstance(Project project) {
    return ServiceManager.getService(project, DeploymentManager.class);
  }


  public abstract void setDeploymentStatus(DeploymentModel model, DeploymentStatus status,
                                           CommonModel instanceConfiguration, J2EEServerInstance serverInstance);

  @NotNull
  public abstract DeploymentStatus getDeploymentStatus(@NotNull DeploymentModel model, CommonModel instanceConfiguration);

  public abstract boolean isKeepDeployedAfterDisconnect(DeploymentModel model, CommonModel instanceConfiguration);

  public abstract void setKeepDeployedAfterDisconnect(DeploymentModel deploymentModel, CommonModel commonModel, boolean state);

  public abstract void initKeepDeployedAfterDisconnect(J2EEServerInstance instance, CommonModel instanceConfiguration);

  public void deployAllModules(J2EEServerInstance instance,
                               CommonModel runConfiguration,
                               boolean makeBeforeDeploy,
                               @Nullable Runnable callback) {
    deployModules(instance, runConfiguration, makeBeforeDeploy, callback, Collections.<DeploymentModel>emptyList());
  }

  public abstract void deployModules(J2EEServerInstance instance,
                                     CommonModel runConfiguration,
                                     boolean makeBeforeDeploy,
                                     @Nullable Runnable callback,
                                     List<DeploymentModel> modules);

  public abstract void undeployNonKeptModules(J2EEServerInstance instance, CommonModel runConfiguration);

  public abstract void clean(J2EEServerInstance instance, CommonModel runConfiguration);

  /**
   * @deprecated use {@code model.getDeploymentSource().getFile()} instead
   */
  @Nullable
  public abstract File getDeploymentSource(DeploymentModel model);

  /**
   * @deprecated use {@code model.getDeploymentSource().getFilePath()} instead
   */
  @Nullable
  public abstract String getDeploymentSourcePath(DeploymentModel model);

  public abstract void updateAllDeploymentStatus(J2EEServerInstance instance, CommonModel runConfiguration);

  @Nullable
  public abstract DeploymentModel getModelForFacet(@NotNull CommonModel commonModel, @NotNull JavaEEModuleExtension facet);

  @NotNull
  public abstract List<Artifact> getSupportedArtifacts(@NotNull DeploymentProvider deploymentProvider);
}
