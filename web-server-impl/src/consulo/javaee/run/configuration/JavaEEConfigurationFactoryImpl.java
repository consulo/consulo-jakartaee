package consulo.javaee.run.configuration;

import javax.swing.Icon;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import com.intellij.execution.configurations.ConfigurationType;
import com.intellij.javaee.oss.server.JavaeeConfigurationFactory;
import com.intellij.javaee.oss.server.JavaeeConfigurationType;
import com.intellij.javaee.oss.server.JavaeeIntegration;
import com.intellij.javaee.run.configuration.J2EEConfigurationFactory;
import com.intellij.javaee.run.configuration.ServerModel;
import com.intellij.javaee.run.localRun.ExecutableObjectStartupPolicy;
import com.intellij.openapi.project.Project;

/**
 * @author VISTALL
 * @since 09-Jul-17
 */
public class JavaEEConfigurationFactoryImpl extends JavaeeConfigurationFactory
{
	public JavaEEConfigurationFactoryImpl(ConfigurationType type, String name, Icon icon, boolean local, JavaeeIntegration integration)
	{
		super(type, name, icon, local, integration);
	}

	@Override
	public boolean isApplicable(@NotNull Project project)
	{
		return J2EEConfigurationFactory.getInstance().isConfigurationApplicable((JavaeeConfigurationType) getType(), project);
	}

	@NotNull
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
