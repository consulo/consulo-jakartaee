/*
 * Copyright 2000-2010 JetBrains s.r.o.
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

import org.jetbrains.annotations.NotNull;
import com.intellij.openapi.components.ServiceManager;
import com.intellij.packaging.artifacts.ArtifactType;

/**
 * @author nik
 */
public abstract class JavaeeDeploymentUtil {
  public static JavaeeDeploymentUtil getInstance() {
    return ServiceManager.getService(JavaeeDeploymentUtil.class);
  }

  @NotNull
  public abstract ExternalFileDeploymentProvider createExternalFileDeploymentProvider(@NotNull Collection<? extends ArtifactType> supportedArtifactTypes);

  @NotNull
  public abstract ExternalFileDeploymentProvider createExternalFileDeploymentProvider(boolean directoriesAllowed, String... allowedExtensions);
}
