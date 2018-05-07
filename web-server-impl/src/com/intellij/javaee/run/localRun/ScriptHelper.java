package com.intellij.javaee.run.localRun;

import com.intellij.execution.configurations.RunConfigurationBase;
import com.intellij.execution.configurations.RunnerSettings;
import com.intellij.execution.configurations.RuntimeConfigurationException;
import com.intellij.javaee.run.configuration.CommonModel;
import javax.annotation.Nullable;

/**
 * @author nik
 */
public abstract class ScriptHelper {

  @Nullable 
  public abstract ExecutableObject getDefaultScript(CommonModel commonModel);

  public void checkRunnerSettings(RunConfigurationBase runConfiguration, RunnerSettings runnerSettings) throws RuntimeConfigurationException {
  }

  public void initRunnerSettings(RunConfigurationBase runConfiguration, RunnerSettings runnerSettings) {
  }
}
