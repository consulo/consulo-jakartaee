package consulo.javaee.run.configuration.state;

import com.intellij.java.execution.configurations.PatchedRunnableState;
import consulo.jakartaee.webServer.impl.oss.server.JavaeeStartupPolicy;
import consulo.jakartaee.webServer.impl.run.localRun.ExecutableObject;
import consulo.jakartaee.webServer.impl.run.localRun.ScriptHelper;
import consulo.execution.DefaultExecutionResult;
import consulo.execution.ExecutionResult;
import consulo.execution.configuration.CommandLineState;
import consulo.execution.executor.Executor;
import consulo.execution.runner.ExecutionEnvironment;
import consulo.execution.runner.ProgramRunner;
import consulo.execution.ui.console.ConsoleView;
import consulo.javaee.run.configuration.JavaEEConfigurationImpl;
import consulo.process.ExecutionException;
import consulo.process.ProcessHandler;

import javax.annotation.Nonnull;
import java.util.Collections;

/**
 * @author VISTALL
 * @since 16-Jul-17
 */
public class JavaEECommandLineState extends CommandLineState implements PatchedRunnableState
{
	public JavaEECommandLineState(ExecutionEnvironment environment)
	{
		super(environment);
	}

	@Override
	@Nonnull
	public ExecutionResult execute(@Nonnull final Executor executor, @Nonnull final ProgramRunner runner) throws ExecutionException
	{
		final ProcessHandler processHandler = startProcess();
		final ConsoleView console = new JavaEEDeploymentConsole(executor, (JavaEEConfigurationImpl) getEnvironment().getRunProfile(), getEnvironment().getProject());
		console.attachToProcess(processHandler);
		return new DefaultExecutionResult(console, processHandler, createActions(console, processHandler, executor));
	}

	@Nonnull
	@Override
	protected ProcessHandler startProcess() throws ExecutionException
	{
		JavaEEConfigurationImpl configuration = (JavaEEConfigurationImpl) getEnvironment().getRunProfile();

		JavaeeStartupPolicy startupPolicy = (JavaeeStartupPolicy) configuration.getStartupPolicy();

		ScriptHelper startupScriptHelper = startupPolicy.createStartupScriptHelper(getEnvironment().getRunner());

		assert startupScriptHelper != null;

		ExecutableObject defaultScript = startupScriptHelper.getDefaultScript(configuration);

		assert defaultScript != null;

		return defaultScript.createProcessHandler(null, Collections.emptyMap());
	}
}
