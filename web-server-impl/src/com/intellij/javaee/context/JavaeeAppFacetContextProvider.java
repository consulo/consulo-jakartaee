package com.intellij.javaee.context;

import org.jetbrains.annotations.NotNull;
import com.intellij.javaee.deployment.DeploymentModel;
import consulo.javaee.module.extension.JavaEEApplicationModuleExtension;
import consulo.javaee.module.extension.JavaEEModuleExtension;

/**
 * Created by IntelliJ IDEA.
 * User: michael.golubev
 */
public class JavaeeAppFacetContextProvider implements FacetContextProvider {

  @Override
  public Class<? extends JavaEEModuleExtension> getFacetId() {
    return JavaEEModuleExtension.class;
  }

  @Override
  public String getDeploymentContext(@NotNull WebModuleContextProvider webModuleContextProvider,
                                     @NotNull DeploymentModel deploymentModel,
                                     @NotNull JavaEEModuleExtension facet) {
    return webModuleContextProvider.getContext((JavaEEApplicationModuleExtension) facet, null);
  }
}
