package com.intellij.javaee.context;

import org.jetbrains.annotations.NotNull;
import com.intellij.javaee.deployment.DeploymentModel;
import consulo.javaee.module.extension.JavaEEModuleExtension;

/**
 * Created by IntelliJ IDEA.
 * User: michael.golubev
 */
public interface FacetContextProvider {

  Class<? extends JavaEEModuleExtension> getFacetId();

  String getDeploymentContext(@NotNull WebModuleContextProvider webModuleContextProvider,
                              @NotNull DeploymentModel deploymentModel,
                              @NotNull JavaEEModuleExtension<?> facet);
}
