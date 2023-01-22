package consulo.jakartaee.webServer.impl.oss.admin.jmx;

import java.io.File;

import consulo.jakartaee.webServer.impl.deployment.DeploymentModel;
import consulo.jakartaee.webServer.impl.oss.admin.JavaeeAdminDeployCallback;
import consulo.jakartaee.webServer.impl.oss.admin.JavaeeAdminStartCallback;

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
