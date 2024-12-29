/*
 * Copyright 2000-2014 JetBrains s.r.o.
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
package consulo.jakartaee.webServer.impl.web.artifact;

import consulo.jakartaee.webServer.impl.appServerIntegrations.ApplicationServerUrlMapping;
import consulo.compiler.artifact.Artifact;
import consulo.compiler.artifact.ArtifactType;
import consulo.content.library.Library;
import consulo.ide.ServiceManager;
import consulo.jakartaee.web.module.extension.JavaWebModuleExtension;
import consulo.project.Project;

import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import java.util.Collection;

/**
 * @author nik
 */
public abstract class WebArtifactUtil {

  public static WebArtifactUtil getInstance() {
    return ServiceManager.getService(WebArtifactUtil.class);
  }

  public abstract boolean isWebApplication(@Nonnull ArtifactType artifactType);

  /**
   * @deprecated use {@link ApplicationServerUrlMapping} instead
   */
  @Nullable
  public abstract String getContextRoot(@Nonnull Artifact earArtifact, @Nonnull JavaWebModuleExtension webFacet);

  @Nullable
  public abstract String getModuleWebUri(@Nonnull Artifact earArtifact, @Nonnull JavaWebModuleExtension webFacet);

  public abstract ArtifactType getExplodedWarArtifactType();

  public abstract ArtifactType getWarArtifactType();

  public abstract Collection<? extends Artifact> getWebArtifacts(@Nonnull Project project);

  public abstract void addLibrary(@Nonnull Library library, @Nonnull Artifact artifact, @Nonnull Project project);

  public abstract Collection<? extends ArtifactType> getWebArtifactTypes();
}
