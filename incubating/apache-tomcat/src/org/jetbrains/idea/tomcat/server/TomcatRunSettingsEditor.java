package org.jetbrains.idea.tomcat.server;

import com.intellij.javaee.oss.server.JavaeeRunSettingsEditor;
import com.intellij.openapi.options.ConfigurationException;
import org.jetbrains.idea.tomcat.TomcatBundle;

import javax.swing.*;

/**
 * Created by IntelliJ IDEA.
 * User: michael.golubev
 */
public abstract class TomcatRunSettingsEditor<T extends TomcatServerModel> extends JavaeeRunSettingsEditor<T> {

  public TomcatRunSettingsEditor() {
    super(TomcatIntegration.getInstance());
  }

  protected int getJndiPort(JTextField portField, T serverModel) throws ConfigurationException {
    int port;
    try {
      port = getPort(portField, TomcatBundle.message("error.message.invalid.jmx.port.number"));
    }
    catch (ConfigurationException e) {
      if (serverModel.isUseJmx()) {
        throw e;
      }
      else {
        port = TomcatServerModel.DEFAULT_JNDI_PORT;
      }
    }
    return port;
  }
}