package com.intellij.javaee.oss.admin.jmx;

import java.io.File;
import java.io.IOException;

import javax.management.JMException;
import javax.management.MBeanServerConnection;

import com.intellij.javaee.deployment.DeploymentModel;
import com.intellij.javaee.deployment.DeploymentStatus;
import com.intellij.javaee.oss.admin.JavaeeAdminDeployCallback;
import com.intellij.javaee.oss.admin.JavaeeAdminStartCallback;

/**
 * @author VISTALL
 * @since 03-Jul-17
 */
public abstract class JavaeeJmxAdminServerBase implements JavaeeJmxAdminServer
{
	public static class DeploymentContext
	{
		private DeploymentModel myDeploymentModel;

		public DeploymentModel getDeploymentModel()
		{
			return myDeploymentModel;
		}

		public File getSource()
		{
			return null;
		}
	}

	public static class JmxAdminCommandBase<R>
	{
		protected Integer getTimeoutSeconds()
		{
			return 1 * 60;
		}

		protected R doExecute(MBeanServerConnection connection) throws JMException, IOException
		{
			return null;
		}

		protected String getAttribute(MBeanServerConnection connection, Object objectName, String key) throws JMException, IOException
		{
			return null;
		}

		protected Object invokeOperation(MBeanServerConnection connection,
				Object objectName,
				String attName,
				String hostObjectName,
				String contextPath,
				String deploymentPath) throws JMException, IOException
		{
			return null;
		}

		protected Object invokeOperation(MBeanServerConnection connection, Object objectName, String attName, String deploymentMBean)
		{
			return null;
		}

		protected Object createObjectName(String name)
		{
			return null;
		}

		public R adminExecute()
		{
			return null;
		}
	}

	protected abstract JmxAdminCommandBase<Boolean> createConnectCommand();

	protected abstract boolean isUndeploySyncNeeded();

	protected abstract DeploymentStatus doGetDeploymentStatus(DeploymentContext context) throws JmxAdminException;

	protected abstract boolean doDeploy(DeploymentContext context) throws JmxAdminException;

	public void startUpdateDeploymentStatus(DeploymentModel deployment, File source, JavaeeAdminDeployCallback callback)
	{
		//TODO [VISTALL] stub
	}

	public void start(String host, int port, String username, String password, JavaeeAdminStartCallback callback) throws Exception
	{
		//TODO [VISTALL] stub
	}

	public void startUndeploy(DeploymentModel deployment, File source, JavaeeAdminDeployCallback callback)
	{
		//TODO [VISTALL] stub
	}

	public void startDeploy(DeploymentModel deployment, File source, JavaeeAdminDeployCallback callback)
	{
		//TODO [VISTALL] stub
	}

	public void shutdown()
	{
		//TODO [VISTALL] stub
	}

	public boolean doConnect()
	{
		return false;
	}

	protected abstract boolean doUndeploy(DeploymentContext context) throws JmxAdminException;
}
