package consulo.javaee.run.configuration.editor;

import com.intellij.javaee.deployment.DeploymentModel;
import com.intellij.javaee.run.configuration.CommonModel;
import com.intellij.openapi.Disposable;
import com.intellij.openapi.options.ConfigurationException;
import com.intellij.openapi.options.SettingsEditor;
import com.intellij.openapi.util.Disposer;
import com.intellij.packaging.artifacts.ArtifactPointer;
import com.intellij.remoteServer.configuration.deployment.ArtifactDeploymentSource;
import com.intellij.remoteServer.configuration.deployment.DeploymentSource;
import consulo.javaee.bundle.JavaEEServerBundleType;

/**
 * @author VISTALL
 * @since 09-Jul-17
 */
class DeployItem implements Disposable
{
	private final DeploymentSource myDeploymentSource;
	private final DeploymentModel myDeploymentModel;

	private SettingsEditor<DeploymentModel> myEditor;

	DeployItem(CommonModel commonModel, DeploymentSource deploymentSource, JavaEEServerBundleType bundleType)
	{
		myDeploymentSource = deploymentSource;

		myDeploymentModel = bundleType.createNewDeploymentModel(commonModel, deploymentSource);
		myEditor = bundleType.createAdditionalDeploymentSettingsEditor(commonModel, deploymentSource);

		Disposer.register(this, myEditor);
	}

	public SettingsEditor<DeploymentModel> getEditor()
	{
		return myEditor;
	}

	public void apply() throws ConfigurationException
	{
		myEditor.applyTo(myDeploymentModel);
	}

	public DeploymentModel getDeploymentModel()
	{
		return myDeploymentModel;
	}

	public DeploymentSource getDeploymentSource()
	{
		return myDeploymentSource;
	}

	public ArtifactPointer getArtifactPointer()
	{
		if(myDeploymentSource instanceof ArtifactDeploymentSource)
		{
			return ((ArtifactDeploymentSource) myDeploymentSource).getArtifactPointer();
		}
		return null;
	}

	@Override
	public void dispose()
	{
	}
}
