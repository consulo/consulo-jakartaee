package org.jetbrains.idea.tomcat.server;

import com.intellij.execution.configurations.ConfigurationTypeUtil;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;

/**
 * Created by IntelliJ IDEA.
 * User: michael.golubev
 */
public class TomcatConfiguration extends TomcatConfigurationBase {

  @NonNls
  private static final String ID = "#com.intellij.j2ee.web.tomcat.TomcatRunConfigurationFactory";

  public static TomcatConfiguration getInstance() {
    return ConfigurationTypeUtil.findConfigurationType(TomcatConfiguration.class);
  }

  public TomcatConfiguration() {
    super(TomcatIntegration.getInstance());
  }

  @NotNull
  @Override
  public String getId() {
    return ID;
  }
}