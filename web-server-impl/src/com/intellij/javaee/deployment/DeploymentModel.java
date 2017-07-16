/*
 * Copyright 2000-2007 JetBrains s.r.o.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.intellij.javaee.deployment;

import org.jdom.Element;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import com.intellij.javaee.run.configuration.CommonModel;
import com.intellij.javaee.run.configuration.ServerModel;
import com.intellij.openapi.util.Comparing;
import com.intellij.openapi.util.DefaultJDOMExternalizer;
import com.intellij.openapi.util.InvalidDataException;
import com.intellij.openapi.util.JDOMExternalizable;
import com.intellij.openapi.util.WriteExternalException;
import com.intellij.packaging.artifacts.Artifact;
import com.intellij.packaging.artifacts.ArtifactPointer;
import com.intellij.remoteServer.configuration.deployment.ArtifactDeploymentSource;
import com.intellij.remoteServer.configuration.deployment.DeploymentSource;
import com.intellij.remoteServer.impl.configuration.deploySource.impl.ArtifactDeploymentSourceImpl;

public abstract class DeploymentModel implements JDOMExternalizable, Cloneable
{
	@NonNls
	private static final String DEPLOYMENT_METHOD_NAME = "DEPLOYMENT_METHOD";
	@NonNls
	public static final String DEPLOYMENT_SOURCE_NAME = "DEPLOYMENT_SOURCE_NAME";

	private DeploymentMethod myDeploymentMethod = null;
	private CommonModel myParentConfiguration;
	private final DeploymentMethod[] myDeploymentMethods;
	private final DeploymentSource myDeploymentSource;

	protected DeploymentModel(@NotNull CommonModel parentConfiguration, ArtifactPointer artifactPointer)
	{
		this(parentConfiguration, new ArtifactDeploymentSourceImpl(artifactPointer));
	}

	protected DeploymentModel(CommonModel parentConfiguration, DeploymentSource deploymentSource)
	{
		myParentConfiguration = parentConfiguration;
		DeploymentMethod[] deploymentMethods = DeploymentMethod.EMPTY_ARRAY;
		DeploymentProvider deploymentProvider = myParentConfiguration.getIntegration().getDeploymentProvider(parentConfiguration.isLocal());
		if(deploymentProvider == null)
		{
			deploymentProvider = myParentConfiguration.getServerModel().getDeploymentProvider();
		}
		if(deploymentProvider != null)
		{
			DeploymentMethod[] availableMethods = deploymentProvider.getAvailableMethods();
			if(availableMethods != null)
			{
				deploymentMethods = availableMethods;
			}
		}
		myDeploymentMethods = deploymentMethods;
		myDeploymentSource = deploymentSource;
	}

	@NotNull
	public final DeploymentMethod[] getAvailableMethods()
	{
		return myDeploymentMethods;
	}

	@Nullable
	public Artifact getArtifact()
	{
		return myDeploymentSource instanceof ArtifactDeploymentSource ? ((ArtifactDeploymentSource) myDeploymentSource).getArtifact() : null;
	}

	@Nullable
	public ArtifactPointer getArtifactPointer()
	{
		return myDeploymentSource instanceof ArtifactDeploymentSource ? ((com.intellij.remoteServer.configuration.deployment.ArtifactDeploymentSource) myDeploymentSource).getArtifactPointer() : null;
	}

	@Override
	public void readExternal(Element element) throws InvalidDataException
	{
		DefaultJDOMExternalizer.readExternal(this, element);
		String methodName = element.getAttributeValue(DEPLOYMENT_METHOD_NAME);
		if(methodName != null)
		{
			setDeploymentMethod(findMethodByName(methodName));
		}
	}

	@Nullable
	private DeploymentMethod findMethodByName(String deplMethodName)
	{
		DeploymentMethod found = null;
		for(DeploymentMethod availableMethod : myDeploymentMethods)
		{
			if(Comparing.equal(availableMethod.getName(), deplMethodName))
			{
				found = availableMethod;
			}
		}
		return found;
	}

	public boolean shouldRedeployOnDeploymentSourceContentChange()
	{
		return false;
	}

	public boolean isValid()
	{
		return myDeploymentSource.isValid();
	}

	@Override
	public void writeExternal(Element element) throws WriteExternalException
	{
		DefaultJDOMExternalizer.writeExternal(this, element);
		DeploymentMethod deploymentMethod = getDeploymentMethod();
		if(deploymentMethod != null)
		{
			element.setAttribute(DEPLOYMENT_METHOD_NAME, deploymentMethod.getName());
		}
	}

	@Nullable
	public DeploymentMethod getDeploymentMethod()
	{
		if(myDeploymentMethod == null)
		{
			for(DeploymentMethod method : myDeploymentMethods)
			{
				if(method.isApplicable(myParentConfiguration))
				{
					myDeploymentMethod = method;
					break;
				}
			}
		}
		return myDeploymentMethod;
	}

	public void setDeploymentMethod(DeploymentMethod deploymentMethod)
	{
		myDeploymentMethod = deploymentMethod;
	}

	@NotNull
	public DeploymentSource getDeploymentSource()
	{
		return myDeploymentSource;
	}

	public CommonModel getCommonModel()
	{
		return myParentConfiguration;
	}

	public ServerModel getServerModel()
	{
		return myParentConfiguration.getServerModel();
	}

}
