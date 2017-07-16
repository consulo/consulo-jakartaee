package consulo.javaee.run.configuration.state;

import java.util.Collections;

import org.jetbrains.annotations.NotNull;
import com.intellij.execution.ExecutionException;
import com.intellij.execution.Executor;
import com.intellij.execution.configurations.CommandLineState;
import com.intellij.execution.process.ProcessHandler;
import com.intellij.execution.runners.ExecutionEnvironment;
import com.intellij.javaee.oss.server.JavaeeStartupPolicy;
import com.intellij.javaee.run.localRun.ExecutableObject;
import com.intellij.javaee.run.localRun.ScriptHelper;
import consulo.javaee.run.configuration.JavaEEConfigurationImpl;

/**
 * @author VISTALL
 * @since 16-Jul-17
 */
public class JavaEECommandLineState extends CommandLineState
{
	public JavaEECommandLineState(Executor executor, ExecutionEnvironment environment)
	{
		super(environment);
	}

	@NotNull
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
