package consulo.javaee.run.configuration;

import consulo.annotation.component.ServiceImpl;
import consulo.jakartaee.webServer.impl.appServerIntegrations.ApplicationServer;
import consulo.jakartaee.webServer.impl.run.configuration.J2EEConfigurationFactory;
import consulo.jakartaee.webServer.impl.run.configuration.J2EEConfigurationType;
import consulo.jakartaee.webServer.impl.run.configuration.JavaCommandLineStartupPolicy;
import consulo.jakartaee.webServer.impl.run.configuration.ServerModel;
import consulo.jakartaee.webServer.impl.run.localRun.ExecutableObjectStartupPolicy;
import consulo.execution.RunnerAndConfigurationSettings;
import consulo.execution.configuration.ConfigurationFactory;
import consulo.execution.configuration.RunConfiguration;
import consulo.javaee.bundle.JavaEEServerBundleType;
import consulo.language.psi.PsiFile;
import consulo.localize.LocalizeValue;
import consulo.project.Project;
import jakarta.inject.Singleton;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/**
 * @author VISTALL
 * @since 09-Jul-17
 */
@Singleton
@ServiceImpl
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
		return new JavaEEConfigurationFactoryImpl(type, LocalizeValue.of(name), isLocal ? type.getLocalIcon() : type.getRemoteIcon(), isLocal, type.getBundleType());
	}

	@Override
	public boolean isConfigurationApplicable(@Nonnull J2EEConfigurationType type, @Nonnull Project project)
	{
		return true;
	}
}
