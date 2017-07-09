package consulo.javaee.bundle;

import org.jetbrains.annotations.NonNls;
import com.intellij.javaee.deployment.DeploymentModel;
import com.intellij.javaee.deployment.DeploymentSource;
import com.intellij.javaee.oss.server.JavaeeIntegration;
import com.intellij.javaee.run.configuration.CommonModel;
import com.intellij.openapi.options.SettingsEditor;
import com.intellij.openapi.projectRoots.SdkType;

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
