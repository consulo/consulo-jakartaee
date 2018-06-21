package com.intellij.javaee.oss.admin.jmx;

import java.io.File;

import com.intellij.javaee.deployment.DeploymentModel;
import com.intellij.javaee.oss.admin.JavaeeAdminDeployCallback;
import com.intellij.javaee.oss.admin.JavaeeAdminStartCallback;

/**
 * @author VISTALL
 * @since 08-Jul-17
 */
public interface JavaeeJmxAdminServer
{
	void startUpdateDeploymentStatus(DeploymentModel deployment, File source, JavaeeAdminDeployCallback callback);

	void start(String host, int port, String username, String password, JavaeeAdminStartCallback callback) throws Exception;

	void startUndeploy(DeploymentModel deployment, File source, JavaeeAdminDeployCallback callback);

	void startDeploy(DeploymentModel deployment, File source, JavaeeAdminDeployCallback callback);

	void shutdown();

	boolean doConnect();
}
