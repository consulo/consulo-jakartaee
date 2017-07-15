package org.jetbrains.idea.tomcat.descriptor;

import org.jetbrains.annotations.NonNls;
import org.jetbrains.idea.tomcat.TomcatBundle;
import com.intellij.javaee.oss.descriptor.JavaeeWebDescriptor;
import com.intellij.javaee.oss.server.JavaeeIntegration;

/**
 * Created by IntelliJ IDEA.
 * User: michael.golubev
 */
public class TomcatContextDescriptor extends JavaeeWebDescriptor {

  public TomcatContextDescriptor(JavaeeIntegration integration, Class<?> type, @NonNls String name) {
    super(integration, type, name);
  }

  @Override
  public String getPath() {
    return "META-INF";
  }

  @Override
  public String getTitle(JavaeeIntegration integration) {
    return TomcatBundle.message("tomcat.deployment.descriptor.title");
  }
}
