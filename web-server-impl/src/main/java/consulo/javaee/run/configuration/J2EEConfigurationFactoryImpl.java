package consulo.javaee.run.configuration;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import jakarta.inject.Singleton;

import com.intellij.execution.RunnerAndConfigurationSettings;
import com.intellij.execution.configurations.ConfigurationFactory;
import com.intellij.execution.configurations.RunConfiguration;
import com.intellij.javaee.appServerIntegrations.ApplicationServer;
import com.intellij.javaee.run.configuration.J2EEConfigurationFactory;
import com.intellij.javaee.run.configuration.J2EEConfigurationType;
import com.intellij.javaee.run.configuration.JavaCommandLineStartupPolicy;
import com.intellij.javaee.run.configuration.ServerModel;
import com.intellij.javaee.run.localRun.ExecutableObjectStartupPolicy;
import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiFile;
import consulo.javaee.bundle.JavaEEServerBundleType;

/**
 * @author VISTALL
 * @since 09-Jul-17
 */
@Singleton
public class J2EEConfigurationFactoryImpl extends J2EEConfigurationFactory
{
	@Override
	public RunConfiguration createJ2EERunConfiguration(ConfigurationFactory factory,
			Project project,
			ServerModel serverSpecific,
			JavaEEServerBundleType integration,
			boolean isLocal,
			JavaCommandLineStartupPolicy startupPolicy)
	{
		throw new UnsupportedOperationException();
	}

	@Override
	public RunConfiguration createJ2EERunConfiguration(ConfigurationFactory factory,
			Project project,
			ServerModel serverSpecific,
			JavaEEServerBundleType integration,
			boolean isLocal,
			ExecutableObjectStartupPolicy startupPolicy)
	{
		return new JavaEEConfigurationImpl(project, factory, "Unnamed", integration, serverSpecific, isLocal, startupPolicy);
	}

	@Nullable
	@Override
	public RunnerAndConfigurationSettings createSettingsByFile(@Nonnull PsiFile psiFile, @Nonnull J2EEConfigurationType configurationType)
	{
		return null;
	}

	@Override
	public RunnerAndConfigurationSettings addAppServerConfiguration(@Nonnull Project project, @Nonnull ConfigurationFactory type, @Nonnull ApplicationServer appServer)
	{
		return null;
	}

	@Override
	public ConfigurationFactory createFactory(J2EEConfigurationType type, boolean isLocal, String name)
	{
		return new JavaEEConfigurationFactoryImpl(type, name, isLocal ? type.getLocalIcon() : type.getRemoteIcon(), isLocal, type.getBundleType());
	}

	@Override
	public boolean isConfigurationApplicable(@Nonnull J2EEConfigurationType type, @Nonnull Project project)
	{
		return true;
	}
}
