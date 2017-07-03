package org.jetbrains.idea.tomcat.descriptor;

import com.intellij.javaee.oss.descriptor.JavaeeDescriptorsProviderBase;
import org.jetbrains.idea.tomcat.server.TomcatIntegration;

/**
 * Created by IntelliJ IDEA.
 * User: michael.golubev
 */
public class TomcatDescriptorsProvider extends JavaeeDescriptorsProviderBase {

  public TomcatDescriptorsProvider() {
    super(TomcatIntegration.getInstance());
  }
}
