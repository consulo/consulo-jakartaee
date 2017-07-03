package org.jetbrains.idea.tomcat.model;

import com.intellij.javaee.oss.descriptor.JavaeePresentationProvider;
import com.intellij.javaee.oss.server.JavaeeIntegration;
import org.jetbrains.idea.tomcat.server.TomcatIntegration;

/**
 * Created by IntelliJ IDEA.
 * User: michael.golubev
 */
public class TomcatPresentationProvider extends JavaeePresentationProvider {

  @Override
  protected JavaeeIntegration getIntegration() {
    return TomcatIntegration.getInstance();
  }
}
