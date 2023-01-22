package consulo.javaee.bundle;

import consulo.content.bundle.SdkType;
import consulo.execution.configuration.ui.SettingsEditor;
import consulo.jakartaee.webServer.impl.deployment.DeploymentModel;
import consulo.jakartaee.webServer.impl.oss.server.JavaeeIntegration;
import consulo.jakartaee.webServer.impl.run.configuration.CommonModel;
import consulo.remoteServer.configuration.deployment.DeploymentSource;
import org.jetbrains.annotations.NonNls;

/**
 * @author VISTALL
 * @since 09-Jul-17
 */
public abstract class JavaEEServerBundleType extends SdkType
{
	public JavaEEServerBundleType(@NonNls String name)
	{
		super(name);
	}

	public DeploymentModel createNewDeploymentModel(CommonModel commonModel, DeploymentSource source)
	{
		return null;
	}

	public SettingsEditor<DeploymentModel> createAdditionalDeploymentSettingsEditor(CommonModel commonModel, DeploymentSource source)
	{
		return null;
	}

	public boolean isJreCustomizable()
	{
		return false;
	}

	@Deprecated
	public abstract JavaeeIntegration getIntegration();
}
