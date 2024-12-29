package com.intellij.javaee.artifact;

import consulo.compiler.artifact.Artifact;
import consulo.compiler.artifact.ArtifactType;
import consulo.compiler.artifact.element.PackagingElement;
import consulo.compiler.artifact.element.PackagingElementResolvingContext;
import consulo.content.library.Library;
import consulo.ide.ServiceManager;
import consulo.javaee.module.extension.JavaEEModuleExtension;
import consulo.project.Project;

import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import java.util.Collection;

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
