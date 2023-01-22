package consulo.jakartaee.webServer.impl.context;

import javax.annotation.Nonnull;

import consulo.jakartaee.webServer.impl.deployment.DeploymentModel;
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
