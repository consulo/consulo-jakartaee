package consulo.javaee.deployment.impl;

import consulo.annotation.component.ServiceImpl;
import consulo.jakartaee.webServer.impl.deployment.DeploymentManager;
import consulo.jakartaee.webServer.impl.deployment.DeploymentModel;
import consulo.jakartaee.webServer.impl.deployment.DeploymentProvider;
import consulo.jakartaee.webServer.impl.deployment.DeploymentStatus;
import consulo.jakartaee.webServer.impl.run.configuration.CommonModel;
import consulo.jakartaee.webServer.impl.serverInstances.J2EEServerInstance;
import consulo.compiler.artifact.Artifact;
import consulo.javaee.module.extension.JavaEEModuleExtension;
import consulo.project.Project;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;

import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;

import java.io.File;
import java.util.List;

/**
 * @author VISTALL
 * @since 2017-07-17
 */
@Singleton
@ServiceImpl
public class DeploymentManagerImpl extends DeploymentManager {
    private final Project myProject;

    @Inject
    public DeploymentManagerImpl(Project project) {
        myProject = project;
    }

    @Override
    public void setDeploymentStatus(
        DeploymentModel model,
        DeploymentStatus status,
        CommonModel instanceConfiguration,
        J2EEServerInstance serverInstance
    ) {
    }

    @Nonnull
    @Override
    public DeploymentStatus getDeploymentStatus(@Nonnull DeploymentModel model, CommonModel instanceConfiguration) {
        return DeploymentStatus.DISCONNECTED;
    }

    @Override
    public boolean isKeepDeployedAfterDisconnect(DeploymentModel model, CommonModel instanceConfiguration) {
        return false;
    }

    @Override
    public void setKeepDeployedAfterDisconnect(DeploymentModel deploymentModel, CommonModel commonModel, boolean state) {

    }

    @Override
    public void initKeepDeployedAfterDisconnect(J2EEServerInstance instance, CommonModel instanceConfiguration) {
    }

    @Override
    public void deployModules(
        J2EEServerInstance instance,
        CommonModel runConfiguration,
        boolean makeBeforeDeploy,
        @Nullable Runnable callback,
        List<DeploymentModel> modules
    ) {
    }

    @Override
    public void undeployNonKeptModules(J2EEServerInstance instance, CommonModel runConfiguration) {
    }

    @Override
    public void clean(J2EEServerInstance instance, CommonModel runConfiguration) {
    }

    @Nullable
    @Override
    public File getDeploymentSource(DeploymentModel model) {
        return null;
    }

    @Nullable
    @Override
    public String getDeploymentSourcePath(DeploymentModel model) {
        return null;
    }

    @Override
    public void updateAllDeploymentStatus(J2EEServerInstance instance, CommonModel runConfiguration) {
    }

    @Nullable
    @Override
    public DeploymentModel getModelForFacet(@Nonnull CommonModel commonModel, @Nonnull JavaEEModuleExtension facet) {
        return null;
    }

    @Nonnull
    @Override
    public List<Artifact> getSupportedArtifacts(@Nonnull DeploymentProvider deploymentProvider) {
        return null;
    }
}
