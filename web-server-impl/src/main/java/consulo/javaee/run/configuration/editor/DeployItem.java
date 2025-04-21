package consulo.javaee.run.configuration.editor;

import consulo.compiler.artifact.ArtifactPointer;
import consulo.configurable.ConfigurationException;
import consulo.disposer.Disposable;
import consulo.disposer.Disposer;
import consulo.execution.configuration.ui.SettingsEditor;
import consulo.jakartaee.webServer.impl.deployment.DeploymentModel;
import consulo.jakartaee.webServer.impl.run.configuration.CommonModel;
import consulo.javaee.bundle.JavaEEServerBundleType;
import consulo.remoteServer.configuration.deployment.ArtifactDeploymentSource;
import consulo.remoteServer.configuration.deployment.DeploymentSource;

/**
 * @author VISTALL
 * @since 2017-07-09
 */
class DeployItem implements Disposable {
    private final DeploymentSource myDeploymentSource;
    private final DeploymentModel myDeploymentModel;

    private SettingsEditor<DeploymentModel> myEditor;

    DeployItem(CommonModel commonModel, DeploymentSource deploymentSource, JavaEEServerBundleType bundleType) {
        myDeploymentSource = deploymentSource;

        myDeploymentModel = bundleType.createNewDeploymentModel(commonModel, deploymentSource);
        myEditor = bundleType.createAdditionalDeploymentSettingsEditor(commonModel, deploymentSource);

        Disposer.register(this, myEditor);
    }

    public SettingsEditor<DeploymentModel> getEditor() {
        return myEditor;
    }

    public void apply() throws ConfigurationException {
        myEditor.applyTo(myDeploymentModel);
    }

    public DeploymentModel getDeploymentModel() {
        return myDeploymentModel;
    }

    public DeploymentSource getDeploymentSource() {
        return myDeploymentSource;
    }

    public ArtifactPointer getArtifactPointer() {
        if (myDeploymentSource instanceof ArtifactDeploymentSource artifactDeploymentSource) {
            return artifactDeploymentSource.getArtifactPointer();
        }
        return null;
    }

    @Override
    public void dispose() {
    }
}
