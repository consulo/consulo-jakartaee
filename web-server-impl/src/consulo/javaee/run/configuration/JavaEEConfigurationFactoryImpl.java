package consulo.javaee.run.configuration;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.swing.Icon;

import com.intellij.execution.configurations.ConfigurationType;
import com.intellij.execution.configurations.RunConfiguration;
import com.intellij.javaee.oss.server.JavaeeConfigurationFactory;
import com.intellij.javaee.oss.server.JavaeeConfigurationType;
import com.intellij.javaee.oss.server.JavaeeServerModel;
import com.intellij.javaee.run.configuration.J2EEConfigurationFactory;
import com.intellij.javaee.run.configuration.ServerModel;
import com.intellij.javaee.run.localRun.ExecutableObjectStartupPolicy;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.projectRoots.Sdk;
import com.intellij.openapi.projectRoots.SdkTable;
import consulo.javaee.bundle.JavaEEServerBundleType;

/**
 * @author VISTALL
 * @since 09-Jul-17
 */
public class JavaEEConfigurationFactoryImpl extends JavaeeConfigurationFactory
{
	public JavaEEConfigurationFactoryImpl(ConfigurationType type, String name, Icon icon, boolean local, JavaEEServerBundleType bundleType)
	{
		super(type, name, icon, local, bundleType);
	}

	@Override
	public boolean isApplicable(@Nonnull Project project)
	{
		return J2EEConfigurationFactory.getInstance().isConfigurationApplicable((JavaeeConfigurationType) getType(), project);
	}

	@Override
	public void onNewConfigurationCreated(@Nonnull RunConfiguration configuration)
	{
		super.onNewConfigurationCreated(configuration);

		JavaEEConfigurationImpl javaEEConfiguration = (JavaEEConfigurationImpl) configuration;

		JavaeeServerModel serverModel = (JavaeeServerModel) javaEEConfiguration.getServerModel();

		Sdk sdk = SdkTable.getInstance().findMostRecentSdkOfType(myBundleType);
		if(sdk != null)
		{
			javaEEConfiguration.APPLICATION_SERVER_NAME = sdk.getName();
		}

		serverModel.onNewConfigurationCreated();
	}

	@Override
	public void onConfigurationCopied(@Nonnull RunConfiguration configuration)
	{
		super.onConfigurationCopied(configuration);

		JavaEEConfigurationImpl javaEEConfiguration = (JavaEEConfigurationImpl) configuration;

		JavaeeServerModel serverModel = (JavaeeServerModel) javaEEConfiguration.getServerModel();

		serverModel.onConfigurationCopied();
	}

	@Override
	public boolean canConfigurationBeSingleton()
	{
		return false;
	}

	@Nonnull
	@Override
	protected ServerModel createServerModel()
	{
		JavaeeConfigurationType type = (JavaeeConfigurationType) getType();
		return myLocal ? type.createLocalModel() : type.createRemoteModel();
	}

	@Nullable
	@Override
	protected ExecutableObjectStartupPolicy createPolicy()
	{
		JavaeeConfigurationType type = (JavaeeConfigurationType) getType();
		return type.createStartupPolicy();
	}
}
