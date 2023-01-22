/*
 * Copyright (c) 2004 - 2009 by Fuhrer Engineering AG, CH-2504 Biel/Bienne, Switzerland
 */

package consulo.jakartaee.webServer.impl.oss.server;

import com.intellij.javaee.artifact.JavaeeArtifactUtil;
import consulo.jakartaee.webServer.impl.deployment.DeploymentMethod;
import consulo.jakartaee.webServer.impl.deployment.DeploymentModel;
import consulo.jakartaee.webServer.impl.deployment.DeploymentProviderEx;
import consulo.jakartaee.webServer.impl.serverInstances.J2EEServerInstance;
import consulo.jakartaee.webServer.impl.web.artifact.WebArtifactUtil;
import consulo.compiler.artifact.ArtifactType;
import consulo.project.Project;
import org.jetbrains.annotations.NonNls;

import javax.annotation.Nullable;
import java.util.Arrays;
import java.util.Collection;

public class JavaeeDeploymentProvider extends DeploymentProviderEx
{

	private static final DeploymentMethod[] METHODS = {};

	private final boolean deployArchivesOnly;

	public JavaeeDeploymentProvider()
	{
		this(true);
	}

	public JavaeeDeploymentProvider(boolean deployArchivesOnly)
	{
		this.deployArchivesOnly = deployArchivesOnly;
	}

	public DeploymentMethod[] getAvailableMethods()
	{
		return METHODS;
	}

	public void doDeploy(Project project, J2EEServerInstance instance, DeploymentModel deployment)
	{
		((JavaeeServerInstance) instance).deploy(deployment);
	}

	public void startUndeploy(J2EEServerInstance instance, DeploymentModel deployment)
	{
		((JavaeeServerInstance) instance).undeploy(deployment);
	}

	public void updateDeploymentStatus(J2EEServerInstance instance, DeploymentModel deployment)
	{
		((JavaeeServerInstance) instance).updateDeploymentStatus(deployment);
	}

	@Override
	public Collection<? extends ArtifactType> getSupportedArtifactTypes()
	{
		if(deployArchivesOnly)
		{
			ArtifactType ear = JavaeeArtifactUtil.getInstance().getEarArtifactType();
			ArtifactType jar = JavaeeArtifactUtil.getInstance().getEjbJarArtifactType();
			ArtifactType war = WebArtifactUtil.getInstance().getWarArtifactType();
			return Arrays.asList(ear, jar, war);
		}
		else
		{
			return super.getSupportedArtifactTypes();
		}
	}

	@Nullable
	@NonNls
	public String getHelpId()
	{
		return null;
	}
}
