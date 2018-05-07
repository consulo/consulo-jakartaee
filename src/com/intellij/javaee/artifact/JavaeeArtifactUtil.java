package com.intellij.javaee.artifact;

import java.util.Collection;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
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

  public abstract <F extends JavaEEModuleExtension<?>> Collection<F> getFacetsIncludedInArtifact(@Nonnull Project project, @Nonnull Artifact artifact,
                                                                                    @Nullable Class<F> facetType);

  @Nonnull
  public abstract Collection<Artifact> getArtifactsContainingFacet(@Nonnull JavaEEModuleExtension facet, @Nullable ArtifactType artifactType);

  public abstract boolean isJavaeeApplication(@Nonnull ArtifactType artifactType);

  public abstract boolean isEjbApplication(@Nonnull ArtifactType artifactType);

  public abstract ArtifactType getExplodedEarArtifactType();

  public abstract ArtifactType getEarArtifactType();

  public abstract ArtifactType getExplodedEjbArtifactType();

  public abstract ArtifactType getEjbJarArtifactType();

  public abstract PackagingElement<?> createFacetResourcesElement(@Nonnull JavaEEModuleExtension facet);

  public abstract boolean isArchive(ArtifactType artifactType);

  @Nonnull
  public abstract Collection<Artifact> getArtifactsContainingFacet(@Nonnull JavaEEModuleExtension facet, PackagingElementResolvingContext context, Collection<? extends ArtifactType> artifactTypes, boolean transitively);

  public abstract void addLibraryToAllArtifactsContainingFacet(@Nonnull Library library, @Nonnull JavaEEModuleExtension facet);

  @Nullable
  public abstract String getRelativePath(@Nonnull JavaEEModuleExtension from, @Nonnull JavaEEModuleExtension to);
}
