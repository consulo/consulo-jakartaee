package org.jetbrains.idea.tomcat.admin;

import java.io.IOException;

import javax.management.JMException;
import javax.management.MBeanServerConnection;

import org.jetbrains.annotations.NonNls;
import org.jetbrains.idea.tomcat.TomcatDeploymentModelBase;
import com.intellij.javaee.deployment.DeploymentStatus;
import com.intellij.javaee.oss.admin.jmx.JavaeeJmxAdminServerBase;
import com.intellij.javaee.oss.admin.jmx.JmxAdminException;
import com.intellij.openapi.diagnostic.Logger;
import com.intellij.openapi.util.text.StringUtil;

/**
 * Created by IntelliJ IDEA.
 * User: michael.golubev
 */
public abstract class TomcatJmxAdminServerBase extends JavaeeJmxAdminServerBase
{

	private static final Logger LOG = Logger.getInstance("#" + TomcatJmxAdminServerBase.class.getName());

	@NonNls
	private static final String MBEAN_FACTORY_SUFFIX = "type=MBeanFactory";
	@NonNls
	private static final String MBEAN_HOST_SUFFIX = "type=Host,host=localhost";
	@NonNls
	private static final String MBEAN_SERVER_SUFFIX = "type=Server";
	@NonNls
	private static final String MBEAN_DEPLOYER_SUFFIX = "type=Deployer,host=localhost";

	@NonNls
	private final static String[] STARTING_STATE_NAMES = new String[]{
			"NEW",
			"INITIALIZING",
			"INITIALIZED",
			"STARTING_PREP",
			"STARTING"
	};

	private final String myRootName;

	public TomcatJmxAdminServerBase(@NonNls String rootName)
	{
		myRootName = rootName;
	}

	protected final String getFactoryObjectName()
	{
		return myRootName + ":" + MBEAN_FACTORY_SUFFIX;
	}

	protected final String getHostObjectName()
	{
		return myRootName + ":" + MBEAN_HOST_SUFFIX;
	}

	protected final String getServerObjectName()
	{
		return myRootName + ":" + MBEAN_SERVER_SUFFIX;
	}

	protected final String getDeployerObjectName()
	{
		return myRootName + ":" + MBEAN_DEPLOYER_SUFFIX;
	}

	protected String getDeploymentMBean(TomcatDeploymentModelBase deploymentModel)
	{
		String contextPath = deploymentModel.getContextPath();
		return myRootName + ":j2eeType=WebModule,name=//localhost" + (StringUtil.isEmpty(contextPath) ? "/" : contextPath) + ",J2EEApplication=none,J2EEServer=none";
	}

	@Override
	protected boolean isUndeploySyncNeeded()
	{
		return true;
	}

	@Override
	protected DeploymentStatus doGetDeploymentStatus(DeploymentContext context) throws JmxAdminException
	{
		TomcatDeploymentModelBase tomcatDeployment = (TomcatDeploymentModelBase) context.getDeploymentModel();
		ReadStateCommand readStateCommand;
		if(isStateReadByName())
		{
			readStateCommand = new ReadStateCommand<String>(tomcatDeployment)
			{

				@Override
				protected String getAttributeName()
				{
					return "stateName";
				}

				@Override
				protected String getStartedAttributeValue()
				{
					return "STARTED";
				}

				@Override
				public boolean isStarting()
				{
					for(String startingStateName : STARTING_STATE_NAMES)
					{
						if(isAttributeValueEquals(startingStateName))
						{
							return true;
						}
					}
					return false;
				}
			};
		}
		else
		{
			readStateCommand = new ReadStateCommand<Integer>(tomcatDeployment)
			{

				@Override
				protected String getAttributeName()
				{
					return "state";
				}

				@Override
				protected Integer getStartedAttributeValue()
				{
					return 1;
				}

				@Override
				public boolean isStarting()
				{
					return false;
				}
			};
		}

		readStateCommand.readAttribute();

		if(readStateCommand.isExist())
		{
			if(readStateCommand.isStarting())
			{
				return DeploymentStatus.ACTIVATING;
			}
			else if(readStateCommand.isStarted())
			{
				return DeploymentStatus.DEPLOYED;
			}
			else
			{
				return DeploymentStatus.FAILED;
			}
		}
		else
		{
			return DeploymentStatus.NOT_DEPLOYED;
		}
	}

	protected abstract boolean isStateReadByName();

	@Override
	protected abstract TomcatConnectorCommandBase<Boolean> createConnectCommand();

	protected abstract class TomcatConnectorCommandBase<R> extends JmxAdminCommandBase<R>
	{

		@Override
		protected Integer getTimeoutSeconds()
		{
			return 10 * 60;
		}
	}

	protected abstract class ReadAttributeCommand<V> extends TomcatConnectorCommandBase<V>
	{

		private final String myDeploymentMBean;
		private V myAttributeValue;

		public ReadAttributeCommand(TomcatDeploymentModelBase deploymentModel)
		{
			myDeploymentMBean = getDeploymentMBean(deploymentModel);
		}

		@Override
		protected V doExecute(MBeanServerConnection connection) throws JMException, IOException
		{
			return (V) getAttribute(connection, createObjectName(myDeploymentMBean), getAttributeName());
		}

		public void readAttribute() throws JmxAdminException
		{
			myAttributeValue = adminExecute();
			LOG.debug("state: " + myAttributeValue);
		}

		public boolean isExist()
		{
			return myAttributeValue != null;
		}

		protected final boolean isAttributeValueEquals(V expectedAttributeValue)
		{
			return expectedAttributeValue.equals(myAttributeValue);
		}

		@NonNls
		protected abstract String getAttributeName();
	}

	private abstract class ReadStateCommand<V> extends ReadAttributeCommand<V>
	{

		public ReadStateCommand(TomcatDeploymentModelBase deploymentModel)
		{
			super(deploymentModel);
		}

		public boolean isStarted()
		{
			return isAttributeValueEquals(getStartedAttributeValue());
		}

		public abstract boolean isStarting();

		protected abstract V getStartedAttributeValue();
	}
}
