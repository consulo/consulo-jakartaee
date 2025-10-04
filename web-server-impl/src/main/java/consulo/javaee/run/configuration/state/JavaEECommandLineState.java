package consulo.javaee.run.configuration.state;

import com.intellij.java.execution.configurations.PatchedRunnableState;
import consulo.execution.DefaultExecutionResult;
import consulo.execution.ExecutionResult;
import consulo.execution.configuration.CommandLineState;
import consulo.execution.executor.Executor;
import consulo.execution.runner.ExecutionEnvironment;
import consulo.execution.runner.ProgramRunner;
import consulo.jakartaee.webServer.impl.oss.server.JavaeeStartupPolicy;
import consulo.jakartaee.webServer.impl.run.localRun.ExecutableObject;
import consulo.jakartaee.webServer.impl.run.localRun.ScriptHelper;
import consulo.javaee.run.configuration.JavaEEConfigurationImpl;
import consulo.process.ExecutionException;
import consulo.process.ProcessHandler;
import jakarta.annotation.Nonnull;

import java.util.Collections;

/**
 * @author VISTALL
 * @since 2017-07-16
 */
public class JavaEECommandLineState extends CommandLineState implements PatchedRunnableState {
    public JavaEECommandLineState(ExecutionEnvironment environment) {
        super(environment);
    }

    @Override
    @Nonnull
    public ExecutionResult execute(@Nonnull Executor executor, @Nonnull ProgramRunner runner) throws ExecutionException {
        ProcessHandler processHandler = startProcess();
        JavaEEDeploymentConsole console =
            new JavaEEDeploymentConsole(executor, (JavaEEConfigurationImpl) getEnvironment().getRunProfile(), getEnvironment().getProject());
        console.getConsoleView().attachToProcess(processHandler);
        return new DefaultExecutionResult(console, processHandler, createActions(console.getConsoleView(), processHandler, executor));
    }

    @Nonnull
    @Override
    protected ProcessHandler startProcess() throws ExecutionException {
        JavaEEConfigurationImpl configuration = (JavaEEConfigurationImpl) getEnvironment().getRunProfile();

        JavaeeStartupPolicy startupPolicy = (JavaeeStartupPolicy) configuration.getStartupPolicy();

        ScriptHelper startupScriptHelper = startupPolicy.createStartupScriptHelper(getEnvironment().getRunner());

        assert startupScriptHelper != null;

        ExecutableObject defaultScript = startupScriptHelper.getDefaultScript(configuration);

        assert defaultScript != null;

        return defaultScript.createProcessHandler(null, Collections.emptyMap());
    }
}
