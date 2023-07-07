package consulo.javaee.run.configuration;

import consulo.content.bundle.Sdk;
import consulo.content.bundle.SdkTable;
import consulo.execution.configuration.ConfigurationType;
import consulo.execution.configuration.RunConfiguration;
import consulo.jakartaee.webServer.impl.oss.server.JavaeeConfigurationFactory;
import consulo.jakartaee.webServer.impl.oss.server.JavaeeConfigurationType;
import consulo.jakartaee.webServer.impl.oss.server.JavaeeServerModel;
import consulo.jakartaee.webServer.impl.run.configuration.J2EEConfigurationFactory;
import consulo.jakartaee.webServer.impl.run.configuration.ServerModel;
import consulo.jakartaee.webServer.impl.run.localRun.ExecutableObjectStartupPolicy;
import consulo.javaee.bundle.JavaEEServerBundleType;
import consulo.localize.LocalizeValue;
import consulo.project.Project;
import consulo.ui.image.Image;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/**
 * @author VISTALL
 * @since 09-Jul-17
 */
public class JavaEEConfigurationFactoryImpl extends JavaeeConfigurationFactory
{
	public JavaEEConfigurationFactoryImpl(ConfigurationType type, LocalizeValue name, Image icon, boolean local, JavaEEServerBundleType bundleType)
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
