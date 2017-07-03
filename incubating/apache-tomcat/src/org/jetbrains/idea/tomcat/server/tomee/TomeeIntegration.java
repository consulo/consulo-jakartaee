package org.jetbrains.idea.tomcat.server.tomee;

import com.intellij.javaee.openapi.ex.AppServerIntegrationsManager;
import com.intellij.javaee.oss.server.JavaeeServerHelper;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.idea.tomcat.TomcatBundle;
import org.jetbrains.idea.tomcat.server.TomcatIntegration;
import org.jetbrains.idea.tomcat.server.TomcatServerHelper;

/**
 * Created by IntelliJ IDEA.
 * User: michael.golubev
 */
public class TomeeIntegration extends TomcatIntegration {

  public static TomeeIntegration getTomeeInstance() {
    return AppServerIntegrationsManager.getInstance().getIntegration(TomeeIntegration.class);
  }

  @NotNull
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

  @Override
  protected JavaeeServerHelper createServerHelper() {
    return new TomcatServerHelper(this, true);
  }
}
