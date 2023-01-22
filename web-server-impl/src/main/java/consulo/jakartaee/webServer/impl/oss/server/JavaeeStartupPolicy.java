/*
 * Copyright (c) 2004 - 2009 by Fuhrer Engineering AG, CH-2504 Biel/Bienne, Switzerland
 */

package consulo.jakartaee.webServer.impl.oss.server;

import com.intellij.java.execution.configurations.DebuggingRunnerData;
import consulo.execution.RuntimeConfigurationException;
import consulo.execution.configuration.EnvironmentVariable;
import consulo.execution.configuration.RunConfigurationBase;
import consulo.execution.configuration.RunnerSettings;
import consulo.execution.runner.ProgramRunner;
import consulo.jakartaee.webServer.impl.run.configuration.CommonModel;
import consulo.jakartaee.webServer.impl.run.localRun.*;
import consulo.util.lang.StringUtil;
import org.jetbrains.annotations.NonNls;

import javax.annotation.Nullable;
import java.util.Arrays;
import java.util.List;

public abstract class JavaeeStartupPolicy<T extends JavaeeServerModel> implements ExecutableObjectStartupPolicy
{
	protected ExecutableObject getDefaultStartupScript(T serverModel, boolean debug)
	{
		return null;
	}

	protected ExecutableObject getDefaultShutdownScript(T serverModel, boolean debug)
	{
		return null;
	}

	@Override
	public ScriptHelper createStartupScriptHelper(final ProgramRunner runner)
	{
		return new ScriptHelper()
		{
			@Override
			public ExecutableObject getDefaultScript(CommonModel config)
			{
				ExecutableObject startupScript = getDefaultStartupScript((T) config.getServerModel(), isDebug(runner));
				if(startupScript != null)
				{
					return startupScript;
				}

				JavaeeParameters params = new JavaeeParameters();
				getStartupParameters(params, getServerModel(config), isDebug(runner));
				return new CommandLineExecutableObject(params.get(), null);
			}

			@Override
			public void initRunnerSettings(RunConfigurationBase runConfiguration, RunnerSettings runnerSettings)
			{
				if((runConfiguration instanceof CommonModel) && (runnerSettings instanceof DebuggingRunnerData))
				{
					initSettings(getServerModel((CommonModel) runConfiguration), (DebuggingRunnerData) runnerSettings);
				}
			}

			@Override
			public void checkRunnerSettings(RunConfigurationBase runConfiguration, RunnerSettings runnerSettings) throws RuntimeConfigurationException
			{
				if((runConfiguration instanceof CommonModel) && (runnerSettings instanceof DebuggingRunnerData))
				{
					checkSettings(getServerModel((CommonModel) runConfiguration), (DebuggingRunnerData) runnerSettings);
				}
			}
		};
	}

	@Override
	public ScriptHelper createShutdownScriptHelper(final ProgramRunner runner)
	{
		return new ScriptHelper()
		{
			@Override
			public ExecutableObject getDefaultScript(CommonModel config)
			{
				ExecutableObject startupScript = getDefaultShutdownScript((T) config.getServerModel(), isDebug(runner));
				if(startupScript != null)
				{
					return startupScript;
				}

				JavaeeParameters params = new JavaeeParameters();
				getShutdownParameters(params, getServerModel(config), isDebug(runner));
				return new CommandLineExecutableObject(params.get(), null);
			}
		};
	}

	@Override
	public EnvironmentHelper getEnvironmentHelper()
	{
		return new EnvironmentHelper()
		{
			@Override
			public String getDefaultJavaVmEnvVariableName(CommonModel config)
			{
				return "JAVA_OPTS";
			}

			@Override
			public List<EnvironmentVariable> getAdditionalEnvironmentVariables(CommonModel config)
			{
				return getEnvironmentVariables(getServerModel(config));
			}
		};
	}

	@NonNls
	protected abstract void getStartupParameters(JavaeeParameters params, T model, boolean debug);

	@NonNls
	protected abstract void getShutdownParameters(JavaeeParameters params, T model, boolean debug);

	@Nullable
	@NonNls
	protected List<EnvironmentVariable> getEnvironmentVariables(T model)
	{
		return null;
	}

	protected void initSettings(T model, DebuggingRunnerData data)
	{
	}

	protected void checkSettings(T model, DebuggingRunnerData data) throws RuntimeConfigurationException
	{
	}

	protected void add(List<String> list, @NonNls String... parameters)
	{
		for(String parameter : parameters)
		{
			if(StringUtil.isEmpty(parameter))
			{
				return;
			}
		}
		list.addAll(Arrays.asList(parameters));
	}

	@SuppressWarnings({"RawUseOfParameterizedType"})
	private boolean isDebug(ProgramRunner runner)
	{
		return DebuggingRunnerData.DEBUGGER_RUNNER_ID.equals(runner.getRunnerId());
	}

	@SuppressWarnings({"unchecked"})
	private T getServerModel(CommonModel config)
	{
		return (T) config.getServerModel();
	}
}
