package consulo.javaee.run.configuration;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import com.intellij.execution.RunnerAndConfigurationSettings;
import com.intellij.execution.configurations.ConfigurationFactory;
import com.intellij.execution.configurations.RunConfiguration;
import com.intellij.javaee.appServerIntegrations.AppServerIntegration;
import com.intellij.javaee.appServerIntegrations.ApplicationServer;
import com.intellij.javaee.oss.server.JavaeeIntegration;
import com.intellij.javaee.run.configuration.J2EEConfigurationFactory;
import com.intellij.javaee.run.configuration.J2EEConfigurationType;
import com.intellij.javaee.run.configuration.JavaCommandLineStartupPolicy;
import com.intellij.javaee.run.configuration.ServerModel;
import com.intellij.javaee.run.localRun.ExecutableObjectStartupPolicy;
import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiFile;

/**
 * @author VISTALL
 * @since 09-Jul-17
 */
public class J2EEConfigurationFactoryImpl extends J2EEConfigurationFactory
{
	@Override
	public RunConfiguration createJ2EERunConfiguration(ConfigurationFactory factory,
			Project project,
			ServerModel serverSpecific,
			AppServerIntegration integration,
			boolean isLocal,
			JavaCommandLineStartupPolicy startupPolicy)
	{
		return new JavaEEConfigurationImpl(project, factory, "Unnamed", (JavaeeIntegration) integration, serverSpecific);
	}

	@Override
	public RunConfiguration createJ2EERunConfiguration(ConfigurationFactory factory,
			Project project,
			ServerModel serverSpecific,
			AppServerIntegration integration,
			boolean isLocal,
			ExecutableObjectStartupPolicy startupPolicy)
	{
		return new JavaEEConfigurationImpl(project, factory, "Unnamed", (JavaeeIntegration) integration, serverSpecific);
	}

	@Nullable
	@Override
	public RunnerAndConfigurationSettings createSettingsByFile(@NotNull PsiFile psiFile, @NotNull J2EEConfigurationType configurationType)
	{
		return null;
	}

	@Override
	public RunnerAndConfigurationSettings addAppServerConfiguration(@NotNull Project project, @NotNull ConfigurationFactory type, @NotNull ApplicationServer appServer)
	{
		return null;
	}

	@Override
	public ConfigurationFactory createFactory(J2EEConfigurationType type, boolean isLocal, String name)
	{
		return new JavaEEConfigurationFactoryImpl(type, name, isLocal ? type.getLocalIcon() : type.getRemoteIcon(), isLocal, (JavaeeIntegration) type.getIntegration());
	}

	@Override
	public boolean isConfigurationApplicable(@NotNull J2EEConfigurationType type, @NotNull Project project)
	{
		return true;
	}
}
