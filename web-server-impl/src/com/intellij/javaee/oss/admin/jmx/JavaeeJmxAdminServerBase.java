package com.intellij.javaee.oss.admin.jmx;

import java.io.IOException;

import javax.management.JMException;
import javax.management.MBeanServerConnection;

import com.intellij.javaee.deployment.DeploymentModel;
import com.intellij.javaee.deployment.DeploymentStatus;

/**
 * @author VISTALL
 * @since 03-Jul-17
 */
public abstract class JavaeeJmxAdminServerBase
{
	public static class DeploymentContext
	{
		private DeploymentModel myDeploymentModel;

		public DeploymentModel getDeploymentModel()
		{
			return myDeploymentModel;
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

		public R adminExecute()
		{
			return null;
		}
	}

	protected abstract boolean isUndeploySyncNeeded();

	protected abstract DeploymentStatus doGetDeploymentStatus(DeploymentContext context) throws JmxAdminException;
}
