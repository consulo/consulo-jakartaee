package consulo.jakartaee.webServer.impl.oss.admin;

import java.io.File;
import java.util.List;

import consulo.jakartaee.webServer.impl.deployment.DeploymentModel;
import consulo.jakartaee.webServer.impl.oss.admin.jmx.JavaeeJmxAdminServer;
import consulo.jakartaee.webServer.impl.oss.agent.AgentProxyFactory;
import consulo.jakartaee.webServer.impl.oss.agent.ParametersMap;

/**
 * @author VISTALL
 * @since 03-Jul-17
 */
public abstract class JavaeeAgentAdminServerBase implements JavaeeJmxAdminServer
{
	public JavaeeAgentAdminServerBase(AgentProxyFactory agentProxyFactory, List<File> instanceLibraries, List<Class<?>> classes, String specificsModuleName, String specificsJarPath, String className)
	{
	}

	protected abstract void setupDeployParameters(DeploymentModel deployment, File source, ParametersMap deployParameters) throws Exception;

	protected abstract String getDeploymentName(DeploymentModel deployment, File source) throws Exception;

	@Override
	public boolean doConnect()
	{
		return false;
	}

	@Override
	public void shutdown()
	{

	}

	@Override
	public void startDeploy(DeploymentModel deployment, File source, JavaeeAdminDeployCallback callback)
	{

	}

	@Override
	public void startUndeploy(DeploymentModel deployment, File source, JavaeeAdminDeployCallback callback)
	{

	}

	@Override
	public void start(String host, int port, String username, String password, JavaeeAdminStartCallback callback) throws Exception
	{

	}

	@Override
	public void startUpdateDeploymentStatus(DeploymentModel deployment, File source, JavaeeAdminDeployCallback callback)
	{

	}
}
