package com.intellij.javaee.artifact;

import java.util.Collection;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import com.intellij.openapi.components.ServiceManager;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.roots.libraries.Library;
import com.intellij.packaging.artifacts.Artifact;
import com.intellij.packaging.artifacts.ArtifactType;
import com.intellij.packaging.elements.PackagingElement;
import com.intellij.packaging.elements.PackagingElementResolvingContext;
import consulo.javaee.module.extension.JavaEEModuleExtension;

/**
 * @author nik
 */
public abstract class JavaeeArtifactUtil {
  public static JavaeeArtifactUtil getInstance() {
    return ServiceManager.getService(JavaeeArtifactUtil.class);
  }

  public abstract Collection<? extends ArtifactType> getAllJavaeeArtifactTypes();

  public abstract <F extends JavaEEModuleExtension<?>> Collection<F> getFacetsIncludedInArtifact(@NotNull Project project, @NotNull Artifact artifact,
                                                                                    @Nullable Class<F> facetType);

  @NotNull
  public abstract Collection<Artifact> getArtifactsContainingFacet(@NotNull JavaEEModuleExtension facet, @Nullable ArtifactType artifactType);

  public abstract boolean isJavaeeApplication(@NotNull ArtifactType artifactType);

  public abstract boolean isEjbApplication(@NotNull ArtifactType artifactType);

  public abstract ArtifactType getExplodedEarArtifactType();

  public abstract ArtifactType getEarArtifactType();

  public abstract ArtifactType getExplodedEjbArtifactType();

  public abstract ArtifactType getEjbJarArtifactType();

  public abstract PackagingElement<?> createFacetResourcesElement(@NotNull JavaEEModuleExtension facet);

  public abstract boolean isArchive(ArtifactType artifactType);

  @NotNull
  public abstract Collection<Artifact> getArtifactsContainingFacet(@NotNull JavaEEModuleExtension facet, PackagingElementResolvingContext context, Collection<? extends ArtifactType> artifactTypes, boolean transitively);

  public abstract void addLibraryToAllArtifactsContainingFacet(@NotNull Library library, @NotNull JavaEEModuleExtension facet);

  @Nullable
  public abstract String getRelativePath(@NotNull JavaEEModuleExtension from, @NotNull JavaEEModuleExtension to);
}
