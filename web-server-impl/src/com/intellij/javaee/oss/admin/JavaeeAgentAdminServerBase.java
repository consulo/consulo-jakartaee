package com.intellij.javaee.oss.admin;

import java.io.File;
import java.util.List;

import com.intellij.javaee.deployment.DeploymentModel;
import com.intellij.javaee.oss.agent.AgentProxyFactory;
import com.intellij.javaee.oss.agent.ParametersMap;

/**
 * @author VISTALL
 * @since 03-Jul-17
 */
public abstract class JavaeeAgentAdminServerBase
{
	public JavaeeAgentAdminServerBase(AgentProxyFactory agentProxyFactory, List<File> instanceLibraries, List<Class<?>> classes, String specificsModuleName, String specificsJarPath, String className)
	{
	}

	protected abstract void setupDeployParameters(DeploymentModel deployment, File source, ParametersMap deployParameters) throws Exception;

	protected abstract String getDeploymentName(DeploymentModel deployment, File source) throws Exception;
}
