package com.intellij.javaee.context;

import javax.annotation.Nonnull;

import com.intellij.javaee.deployment.DeploymentModel;
import consulo.javaee.module.extension.JavaEEModuleExtension;

/**
 * Created by IntelliJ IDEA.
 * User: michael.golubev
 */
public interface FacetContextProvider {

  Class<? extends JavaEEModuleExtension> getFacetId();

  String getDeploymentContext(@Nonnull WebModuleContextProvider webModuleContextProvider,
                              @Nonnull DeploymentModel deploymentModel,
                              @Nonnull JavaEEModuleExtension<?> facet);
}
