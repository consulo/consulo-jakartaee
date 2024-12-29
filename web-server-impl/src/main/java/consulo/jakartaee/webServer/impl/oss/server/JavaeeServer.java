/*
 * Copyright (c) 2004 - 2009 by Fuhrer Engineering AG, CH-2504 Biel/Bienne, Switzerland
 */

package consulo.jakartaee.webServer.impl.oss.server;

import java.io.File;
import java.util.Map;

import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;

import consulo.jakartaee.webServer.impl.deployment.DeploymentModel;
import consulo.jakartaee.webServer.impl.deployment.DeploymentStatus;
import consulo.javaee.module.extension.JavaEEModuleExtension;

public abstract class JavaeeServer
{

	private String host;

	private int port;

	private String username;

	private String password;

	protected abstract boolean isConnected() throws Exception;

	@Nonnull
	protected abstract DeploymentStatus handleDeployment(DeploymentModel deployment, File source, boolean deploy, boolean undeploy) throws Exception;

	@Nullable
	protected abstract String getContextRoot(JavaEEModuleExtension<?> facet);

	protected void getContextRoots(JavaEEModuleExtension<?> facet, Map<String, String> roots)
	{
	}

	protected boolean isStartupScriptTerminating()
	{
		return false;
	}

	protected String getHost()
	{
		return host;
	}

	protected int getPort()
	{
		return port;
	}

	protected String getUsername()
	{
		return username;
	}

	protected String getPassword()
	{
		return password;
	}

	protected boolean isAppModule(DeploymentModel deployment)
	{
		/*JavaEEModuleExtension<?> facet = deployment.getFacet();
		return (facet != null) && JavaeeApplicationFacet.ID.equals(facet.getTypeId()); */
		return false;
	}

	protected boolean isEjbModule(DeploymentModel deployment)
	{
		/*JavaEEModuleExtension<?> facet = deployment.getFacet();
		return (facet != null) && EjbFacet.ID.equals(facet.getTypeId());  */
		return false;
	}

	protected boolean isWebModule(DeploymentModel deployment)
	{
		/*JavaEEModuleExtension<?> facet = deployment.getFacet();
		return (facet != null) && WebFacet.ID.equals(facet.getTypeId());  */
		return false;
	}

	protected boolean isRemote(DeploymentModel deployment)
	{
		return !deployment.getCommonModel().isLocal() && !"localhost".equals(deployment.getCommonModel().getHost());
	}

	void setHost(String host)
	{
		this.host = host;
	}

	void setPort(int port)
	{
		this.port = port;
	}

	void setUsername(String username)
	{
		this.username = username;
	}

	void setPassword(String password)
	{
		this.password = password;
	}
}
