package org.jetbrains.idea.tomcat.server.tomee;

import jakarta.annotation.Nonnull;
import org.jetbrains.idea.tomcat.TomcatBundle;
import org.jetbrains.idea.tomcat.server.TomcatIntegration;
import com.intellij.javaee.openapi.ex.AppServerIntegrationsManager;

/**
 * Created by IntelliJ IDEA.
 * User: michael.golubev
 */
@Deprecated
public class TomeeIntegration extends TomcatIntegration {

  public static TomeeIntegration getTomeeInstance() {
    return AppServerIntegrationsManager.getInstance().getIntegration(TomeeIntegration.class);
  }

  @Nonnull
  @Override
  public String getName() {
    return TomcatBundle.message("tomee.application.server.name");
  }

  @Override
  protected void checkValidServerHome(String home, String version) throws Exception {
    super.checkValidServerHome(home, version);
    if (!isTomEE(home)) {
      throw new Exception("The selected directory is not a TomEE home");
    }
  }
}
