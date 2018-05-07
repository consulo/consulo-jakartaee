package consulo.javaee.deployment.impl;

import java.io.File;
import java.util.List;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import com.intellij.javaee.deployment.DeploymentManager;
import com.intellij.javaee.deployment.DeploymentModel;
import com.intellij.javaee.deployment.DeploymentProvider;
import com.intellij.javaee.deployment.DeploymentStatus;
import com.intellij.javaee.run.configuration.CommonModel;
import com.intellij.javaee.serverInstances.J2EEServerInstance;
import com.intellij.openapi.project.Project;
import com.intellij.packaging.artifacts.Artifact;
import consulo.javaee.module.extension.JavaEEModuleExtension;

/**
 * @author VISTALL
 * @since 17-Jul-17
 */
public class DeploymentManagerImpl extends DeploymentManager
{
	private final Project myProject;

	public DeploymentManagerImpl(Project project)
	{
		myProject = project;
	}

	@Override
	public void setDeploymentStatus(DeploymentModel model, DeploymentStatus status, CommonModel instanceConfiguration, J2EEServerInstance serverInstance)
	{

	}

	@Nonnull
	@Override
	public DeploymentStatus getDeploymentStatus(@Nonnull DeploymentModel model, CommonModel instanceConfiguration)
	{
		return DeploymentStatus.DISCONNECTED;
	}

	@Override
	public boolean isKeepDeployedAfterDisconnect(DeploymentModel model, CommonModel instanceConfiguration)
	{
		return false;
	}

	@Override
	public void setKeepDeployedAfterDisconnect(DeploymentModel deploymentModel, CommonModel commonModel, boolean state)
	{

	}

	@Override
	public void initKeepDeployedAfterDisconnect(J2EEServerInstance instance, CommonModel instanceConfiguration)
	{

	}

	@Override
	public void deployModules(J2EEServerInstance instance, CommonModel runConfiguration, boolean makeBeforeDeploy, @Nullable Runnable callback, List<DeploymentModel> modules)
	{

	}

	@Override
	public void undeployNonKeptModules(J2EEServerInstance instance, CommonModel runConfiguration)
	{

	}

	@Override
	public void clean(J2EEServerInstance instance, CommonModel runConfiguration)
	{

	}

	@Nullable
	@Override
	public File getDeploymentSource(DeploymentModel model)
	{
		return null;
	}

	@Nullable
	@Override
	public String getDeploymentSourcePath(DeploymentModel model)
	{
		return null;
	}

	@Override
	public void updateAllDeploymentStatus(J2EEServerInstance instance, CommonModel runConfiguration)
	{

	}

	@Nullable
	@Override
	public DeploymentModel getModelForFacet(@Nonnull CommonModel commonModel, @Nonnull JavaEEModuleExtension facet)
	{
		return null;
	}

	@Nonnull
	@Override
	public List<Artifact> getSupportedArtifacts(@Nonnull DeploymentProvider deploymentProvider)
	{
		return null;
	}
}
