package org.jetbrains.idea.tomcat.server;

import com.intellij.javaee.appServerIntegrations.ApplicationServerPersistentData;
import com.intellij.javaee.appServerIntegrations.ApplicationServerPersistentDataEditor;
import com.intellij.javaee.oss.server.JavaeeServerHelper;

/**
 * Created by IntelliJ IDEA.
 * User: michael.golubev
 */
public class TomcatServerHelper extends JavaeeServerHelper {

  private final TomcatIntegration myIntegration;
  private final boolean myTomEEOnly;

  public TomcatServerHelper(TomcatIntegration integration, boolean tomEEOnly) {
    super(integration);
    myIntegration = integration;
    myTomEEOnly = tomEEOnly;
  }

  public ApplicationServerPersistentData createPersistentDataEmptyInstance() {
    return new TomcatPersistentData();
  }

  public ApplicationServerPersistentDataEditor createConfigurable() {
    return new TomcatPersistentDataEditor(myIntegration, myTomEEOnly);
  }
}
