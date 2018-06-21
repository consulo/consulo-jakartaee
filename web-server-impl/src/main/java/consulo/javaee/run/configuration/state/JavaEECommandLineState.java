package consulo.javaee.run.configuration.state;

import java.util.Collections;

import javax.annotation.Nonnull;

import com.intellij.execution.DefaultExecutionResult;
import com.intellij.execution.ExecutionException;
import com.intellij.execution.ExecutionResult;
import com.intellij.execution.Executor;
import com.intellij.execution.configurations.CommandLineState;
import com.intellij.execution.configurations.PatchedRunnableState;
import com.intellij.execution.process.ProcessHandler;
import com.intellij.execution.runners.ExecutionEnvironment;
import com.intellij.execution.runners.ProgramRunner;
import com.intellij.execution.ui.ConsoleView;
import com.intellij.javaee.oss.server.JavaeeStartupPolicy;
import com.intellij.javaee.run.localRun.ExecutableObject;
import com.intellij.javaee.run.localRun.ScriptHelper;
import consulo.javaee.run.configuration.JavaEEConfigurationImpl;

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
