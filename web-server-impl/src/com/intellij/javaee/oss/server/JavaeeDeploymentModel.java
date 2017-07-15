/*
 * Copyright (c) 2004 - 2009 by Fuhrer Engineering AG, CH-2504 Biel/Bienne, Switzerland
 */

package com.intellij.javaee.oss.server;

import com.intellij.javaee.deployment.DeploymentModel;
import com.intellij.javaee.deployment.DeploymentSource;
import com.intellij.javaee.run.configuration.CommonModel;
import com.intellij.packaging.artifacts.ArtifactPointer;

class JavaeeDeploymentModel extends DeploymentModel
{
	JavaeeDeploymentModel(CommonModel config, ArtifactPointer pointer)
	{
		super(config, pointer);
	}

	JavaeeDeploymentModel(CommonModel parentConfiguration, DeploymentSource deploymentSource)
	{
		super(parentConfiguration, deploymentSource);
	}

	//@Override
	public boolean isDeploymentSourceSupported(DeploymentSource source)
	{
		return ((JavaeeServerModel) getCommonModel().getServerModel()).isDeploymentSourceSupported(source);
	}
}
