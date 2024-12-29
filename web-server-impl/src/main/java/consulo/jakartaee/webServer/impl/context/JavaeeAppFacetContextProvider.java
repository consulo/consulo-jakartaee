package consulo.jakartaee.webServer.impl.context;

import jakarta.annotation.Nonnull;

import consulo.jakartaee.webServer.impl.deployment.DeploymentModel;
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
  public String getDeploymentContext(@Nonnull WebModuleContextProvider webModuleContextProvider,
                                     @Nonnull DeploymentModel deploymentModel,
                                     @Nonnull JavaEEModuleExtension facet) {
    return webModuleContextProvider.getContext((JavaEEApplicationModuleExtension) facet, null);
  }
}
