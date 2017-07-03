package org.jetbrains.idea.tomcat.descriptor;

import com.intellij.javaee.oss.descriptor.JavaeeTemplatesBase;
import org.jetbrains.idea.tomcat.server.TomcatIntegration;

/**
 * Created by IntelliJ IDEA.
 * User: michael.golubev
 */
public class TomcatTemplates extends JavaeeTemplatesBase {

  public TomcatTemplates() {
    super(TomcatIntegration.getInstance());
  }
}
