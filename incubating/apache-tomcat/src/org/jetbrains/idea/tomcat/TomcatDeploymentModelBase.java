package org.jetbrains.idea.tomcat;

import com.intellij.javaee.deployment.DeploymentModel;
import com.intellij.javaee.run.configuration.CommonModel;
import com.intellij.remoteServer.configuration.deployment.DeploymentSource;

/**
 * Created by IntelliJ IDEA.
 * User: michael.golubev
 */
public abstract class TomcatDeploymentModelBase extends DeploymentModel {

  protected TomcatDeploymentModelBase(CommonModel parentConfiguration, DeploymentSource deploymentSource) {
    super(parentConfiguration, deploymentSource);
  }

  public abstract String getContextPath();
}
