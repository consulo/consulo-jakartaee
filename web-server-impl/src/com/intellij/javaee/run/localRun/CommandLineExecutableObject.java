/*
 * Copyright 2000-2013 JetBrains s.r.o.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.intellij.javaee.run.localRun;

import com.intellij.execution.ExecutionException;
import com.intellij.execution.configurations.GeneralCommandLine;
import com.intellij.execution.configurations.ParametersList;
import com.intellij.execution.process.OSProcessHandler;
import com.intellij.util.ArrayUtil;
import org.jetbrains.annotations.NonNls;
import javax.annotation.Nonnull;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @deprecated Use ColoredCommandLineExecutableObject
 */
public class CommandLineExecutableObject implements ExecutableObject {
  private final String[] myParameters;
  @NonNls private static final String CLASSPATH_VAR_NAME = "CLASSPATH";

  public CommandLineExecutableObject(@NonNls String[] parameters, @NonNls String programParameters) {
    myParameters = getParameters(parameters, programParameters);
  }

  public String getDisplayString() {
    StringBuilder result = new StringBuilder();
    for (int i = 0; i < myParameters.length; i++) {
      if (i > 0) result.append(" ");
      String parameter = myParameters[i];
      result.append(parameter);
    }
    return result.toString();
  }

  protected GeneralCommandLine createCommandLine(final String[] parameters, final Map<String, String> envVariables) {
    return ScriptUtil.createCommandLine(parameters);
  }

  public OSProcessHandler createProcessHandler(String workingDirectory, Map<String, String> envVariables) throws ExecutionException {
    GeneralCommandLine commandLine = createCommandLine(myParameters, envVariables);
    if (workingDirectory == null && myParameters.length > 0) {
      File parentFile = new File(myParameters[0]).getParentFile();
      if (parentFile != null) {
        workingDirectory = parentFile.getAbsolutePath();
      }
    }
    commandLine.withWorkDirectory(workingDirectory);
    commandLine.withEnvironment(CLASSPATH_VAR_NAME, "");
    commandLine.withEnvironment(envVariables);
    return createProcessHandler(commandLine);
  }

  @Nonnull
  protected OSProcessHandler createProcessHandler(GeneralCommandLine commandLine) throws ExecutionException {
    return new OSProcessHandler(commandLine);
  }

  public String[] getParameters() {
    return myParameters;
  }

  private static String[] getParameters(String[] parameters, String programParameters) {
    List<String> result = new ArrayList<>();
    for (String parameter : parameters) {
      if (parameter != null && (parameter.trim().length() > 0)) {
        result.add(parameter.trim());
      }
    }
    if (programParameters != null && (programParameters.trim().length() > 0)) {
      String[] programParametersArray = ParametersList.parse(programParameters);
      for (String s : programParametersArray) {
        if (s != null && s.trim().length() > 0) {
          result.add(s.trim());
        }
      }

    }
    return ArrayUtil.toStringArray(result);
  }
}
