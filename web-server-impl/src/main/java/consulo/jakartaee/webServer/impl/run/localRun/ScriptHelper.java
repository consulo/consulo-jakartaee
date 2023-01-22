package consulo.jakartaee.webServer.impl.run.localRun;

import consulo.jakartaee.webServer.impl.run.configuration.CommonModel;
import consulo.execution.RuntimeConfigurationException;
import consulo.execution.configuration.RunConfigurationBase;
import consulo.execution.configuration.RunnerSettings;

import javax.annotation.Nullable;

/**
 * @author nik
 */
public abstract class ScriptHelper {

  @Nullable 
  public abstract ExecutableObject getDefaultScript(CommonModel commonModel);

  public void checkRunnerSettings(RunConfigurationBase runConfiguration, RunnerSettings runnerSettings) throws RuntimeConfigurationException
  {
  }

  public void initRunnerSettings(RunConfigurationBase runConfiguration, RunnerSettings runnerSettings) {
  }
}
