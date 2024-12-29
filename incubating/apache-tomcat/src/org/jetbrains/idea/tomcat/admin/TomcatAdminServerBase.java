package org.jetbrains.idea.tomcat.admin;

import java.io.File;
import java.io.IOException;

import jakarta.annotation.Nullable;
import javax.management.JMException;
import javax.management.MBeanServerConnection;

import org.jetbrains.idea.tomcat.TomcatDeploymentModelBase;
import org.jetbrains.idea.tomcat.server.TomcatServerModel;
import com.intellij.javaee.deployment.DeploymentModel;
import com.intellij.javaee.oss.admin.JavaeeAdminDeployCallback;
import com.intellij.javaee.oss.admin.JavaeeAdminStartCallback;
import com.intellij.javaee.oss.admin.jmx.JmxAdminException;
import com.intellij.openapi.diagnostic.Logger;

/**
 * Created by IntelliJ IDEA.
 * User: michael.golubev
 */
public abstract class TomcatAdminServerBase<T extends TomcatServerModel> extends TomcatJmxAdminServerBase
{
	protected static final Logger LOG = Logger.getInstance("#" + TomcatAdminServerBase.class.getName());

	private final T myServerModel;

	private final TomEEAgentAdminServerImpl myTomEEAdmin;

	private boolean myStateReadByName;
	private boolean myServerStateAvailable;
	private boolean myDeployerAvailable;

	public TomcatAdminServerBase(T serverModel, @Nullable TomEEAgentAdminServerImpl tomEEAdmin)
	{
		super("Catalina");
		myServerModel = serverModel;
		myTomEEAdmin = tomEEAdmin;
	}

	@Override
	protected TomcatConnectorCommandBase<Boolean> createConnectCommand()
	{
		return new TomcatConnectorCommandBase<Boolean>()
		{

			@Override
			protected Boolean doExecute(MBeanServerConnection connection) throws JMException, IOException
			{
				if(myServerStateAvailable && !"STARTED".equals(getAttribute(connection, createObjectName(getServerObjectName()), "stateName")))
				{
					return false;
				}

				final String attributeName = "modelerType";
				getAttribute(connection, createObjectName(getFactoryObjectName()), attributeName);
				if(myDeployerAvailable)
				{
					getAttribute(connection, createObjectName(getDeployerObjectName()), attributeName);
				}
				return true;
			}
		};
	}

	@Override
	public void start(String host, int port, String username, String password, JavaeeAdminStartCallback callback) throws Exception
	{
		myStateReadByName = myServerModel.isVersionHigher6032();
		myServerStateAvailable = myServerModel.isVersion7OrHigher();
		myDeployerAvailable = myServerModel.versionHigher(5, 5, 0);

		super.start(host, port, username, password, callback);

		if(myTomEEAdmin != null)
		{
			myTomEEAdmin.start(host, myServerModel.getLocalPort(), username, password, callback);
		}
	}

	@Override
	public final boolean doConnect()
	{
		return doConnectTomcat() && (myTomEEAdmin == null || myTomEEAdmin.doConnect());
	}

	protected final boolean doConnectJmx()
	{
		return super.doConnect();
	}

	protected abstract boolean doConnectTomcat();

	@Override
	public void shutdown()
	{
		if(myTomEEAdmin != null)
		{
			myTomEEAdmin.shutdown();
		}
		super.shutdown();
	}

	protected final boolean isUseJmx()
	{
		return myServerModel.isUseJmx();
	}

	@Override
	protected boolean isStateReadByName()
	{
		return myStateReadByName;
	}

	protected final T getServerModel()
	{
		return myServerModel;
	}

	@Override
	public void startDeploy(DeploymentModel deployment, File source, JavaeeAdminDeployCallback callback)
	{
		if(useTomEEAdmin(deployment))
		{
			myTomEEAdmin.startDeploy(deployment, source, callback);
			return;
		}

		super.startDeploy(deployment, source, callback);
	}

	@Override
	public void startUndeploy(DeploymentModel deployment, File source, JavaeeAdminDeployCallback callback)
	{
		if(useTomEEAdmin(deployment))
		{
			myTomEEAdmin.startUndeploy(deployment, source, callback);
			return;
		}

		super.startUndeploy(deployment, source, callback);
	}

	@Override
	public void startUpdateDeploymentStatus(DeploymentModel deployment, File source, JavaeeAdminDeployCallback callback)
	{
		if(useTomEEAdmin(deployment))
		{
			myTomEEAdmin.startUpdateDeploymentStatus(deployment, source, callback);
			return;
		}

		super.startUpdateDeploymentStatus(deployment, source, callback);
	}

	private boolean useTomEEAdmin(DeploymentModel deployment)
	{
		return myTomEEAdmin != null && ((TomcatServerModel) deployment.getServerModel()).useTomEEDeployer(deployment);
	}

	@Override
	protected boolean doDeploy(DeploymentContext context) throws JmxAdminException
	{
		TomcatDeploymentModelBase tomcatDeployment = (TomcatDeploymentModelBase) context.getDeploymentModel();

		final String deploymentPath = prepareDeployment(tomcatDeployment, context.getSource().getAbsolutePath());
		final String contextPath = tomcatDeployment.getContextPath();

		final String deploymentMBean = new TomcatConnectorCommandBase<String>()
		{

			@Override
			protected String doExecute(MBeanServerConnection connection) throws JMException, IOException
			{
				return (String) invokeOperation(connection, createObjectName(getFactoryObjectName()), "createStandardContext", getHostObjectName(), contextPath, deploymentPath);
			}

			@Override
			protected Integer getTimeoutSeconds()
			{
				return null;
			}
		}.adminExecute();

		String expectedDeploymentMBean = getDeploymentMBean(tomcatDeployment);
		if(!expectedDeploymentMBean.equals(deploymentMBean))
		{
			LOG.debug("Unexpected deployment MBean: '" + deploymentMBean + "', while '" + expectedDeploymentMBean + "' is expected");
			return false;
		}

		return true;
	}


	protected abstract String prepareDeployment(TomcatDeploymentModelBase deploymentModel, String deploymentPath) throws JmxAdminException;

	@Override
	protected boolean doUndeploy(DeploymentContext context) throws JmxAdminException
	{
		TomcatDeploymentModelBase tomcatDeployment = (TomcatDeploymentModelBase) context.getDeploymentModel();
		final String deploymentMBean = getDeploymentMBean(tomcatDeployment);
		new TomcatConnectorCommandBase()
		{

			@Override
			protected Object doExecute(MBeanServerConnection connection) throws JMException, IOException
			{
				return invokeOperation(connection, createObjectName(getFactoryObjectName()), "removeContext", deploymentMBean);
			}
		}.adminExecute();
		return true;
	}
}
