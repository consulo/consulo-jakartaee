package consulo.javaee.run.runner;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import com.intellij.debugger.impl.GenericDebuggerRunner;
import com.intellij.execution.ExecutionException;
import com.intellij.execution.configurations.RunProfile;
import com.intellij.execution.configurations.RunProfileState;
import com.intellij.execution.executors.DefaultDebugExecutor;
import com.intellij.execution.runners.ExecutionEnvironment;
import com.intellij.execution.ui.RunContentDescriptor;
import consulo.javaee.run.configuration.JavaEEConfigurationImpl;

/**
 * @author VISTALL
 * @since 11-Jul-17
 */
public class JavaEEDebugRunner extends GenericDebuggerRunner
{
	@NotNull
	@Override
	public String getRunnerId()
	{
		return "JavaEEDebug";
	}

	@Override
	public boolean canRun(@NotNull String executorId, @NotNull RunProfile profile)
	{
		return profile instanceof JavaEEConfigurationImpl && DefaultDebugExecutor.EXECUTOR_ID.equals(executorId);
	}

	@Nullable
	@Override
	protected RunContentDescriptor createContentDescriptor(@NotNull RunProfileState state, @NotNull ExecutionEnvironment env) throws ExecutionException
	{
		//TODO [VISTALL] support it
		/*JavaEEConfigurationImpl runProfile = (JavaEEConfigurationImpl) env.getRunProfile();
		RemoteConnection connection = new RemoteConnection(true, "127.0.0.1", String.valueOf(runProfile.JPDA_ADDRESS), false);
		return attachVirtualMachine(state, env, connection, true);     */
		return null;
	}
}
