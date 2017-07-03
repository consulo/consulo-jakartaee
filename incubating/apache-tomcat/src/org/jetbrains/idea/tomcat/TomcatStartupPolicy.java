/*
 * Copyright 2000-2005 JetBrains s.r.o.
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
package org.jetbrains.idea.tomcat;

import com.intellij.execution.ExecutionException;
import com.intellij.execution.configurations.GeneralCommandLine;
import com.intellij.execution.configurations.RuntimeConfigurationException;
import com.intellij.execution.process.OSProcessHandler;
import com.intellij.execution.util.EnvironmentVariable;
import com.intellij.javaee.oss.server.JavaeeParameters;
import com.intellij.javaee.oss.server.JavaeeStartupPolicy;
import com.intellij.javaee.run.localRun.ColoredCommandLineExecutableObject;
import com.intellij.javaee.run.localRun.ExecutableObject;
import com.intellij.openapi.diagnostic.Logger;
import com.intellij.openapi.projectRoots.JavaSdkType;
import com.intellij.openapi.projectRoots.Sdk;
import com.intellij.openapi.util.SystemInfo;
import com.intellij.openapi.util.io.FileUtil;
import com.intellij.openapi.util.text.StringUtil;
import com.intellij.util.ArrayUtil;
import com.intellij.util.EnvironmentUtil;
import com.intellij.util.containers.HashMap;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.idea.tomcat.server.TomcatLocalModel;

import java.io.File;
import java.util.*;

public class TomcatStartupPolicy extends JavaeeStartupPolicy<TomcatLocalModel> {
  private static final Logger LOG = Logger.getInstance("#org.jetbrains.idea.tomcat.TomcatStartupPolicy");
  @NonNls protected static final String TEMP_FILE_NAME = "temp";
  @NonNls protected static final String BIN_DIR = "bin";
  @NonNls private static final String CATALINA_TMPDIR_ENV_PROPERTY = "CATALINA_TMPDIR";
  @NonNls private static final String JAVA_HOME_ENV_PROPERTY = "JAVA_HOME";
  @NonNls private static final String JRE_HOME_ENV_PROPERTY = "JRE_HOME";
  @NonNls private static final String JAVA_VM_ENV_VARIABLE = "JAVA_OPTS";
  @NonNls private static final String JAR_PARAMETER = "-jar";
  @NonNls public static final String CLASSPATH_PARAMETER = "-cp";
  @NonNls public static final String RMI_HOST_JAVA_OPT = "java.rmi.server.hostname";

  @Override
  protected ExecutableObject getDefaultStartupScript(TomcatLocalModel serverModel, boolean debug) {
    try {
      return new ExecutableCreator(serverModel, "run", "start") {

        @Override
        protected List<String> getCustomJavaOptions() {
          TomcatLocalModel tomcatModel = getTomcatModel();
          if (tomcatModel.isUseJmx()) {
            List<String> result = new ArrayList<>(Arrays.asList("-Dcom.sun.management.jmxremote=",
                                                                "-Dcom.sun.management.jmxremote.port=" + tomcatModel.JNDI_PORT,
                                                                "-Dcom.sun.management.jmxremote.ssl=false",
                                                                "-Dcom.sun.management.jmxremote.authenticate=false"));
            if (tomcatModel.getVmArgument(RMI_HOST_JAVA_OPT) == null) {
              result.add("-D" + RMI_HOST_JAVA_OPT + "=127.0.0.1");
            }
            if (tomcatModel.isTomEE()) {
              if (tomcatModel.versionHigher(7, 0, 68)) {
                result.add("-Dtomee.serialization.class.whitelist=");
                result.add("-Dtomee.serialization.class.blacklist=-");
              }
              if (tomcatModel.versionHigher(8, 0, 28)) {
                result.add("-Dtomee.remote.support=true");
                result.add("-Dopenejb.system.apps=true");
              }
            }
            return result;
          }
          else {
            return super.getCustomJavaOptions();
          }
        }
      }.createExecutable();
    }
    catch (RuntimeConfigurationException e) {
      return null;
    }
  }

  @Override
  protected ExecutableObject getDefaultShutdownScript(TomcatLocalModel serverModel, boolean debug) {
    try {
      return new ExecutableCreator(serverModel, "stop", "stop").createExecutable();
    }
    catch (RuntimeConfigurationException e) {
      return null;
    }
  }

  @Override
  protected void getStartupParameters(JavaeeParameters params, TomcatLocalModel model, boolean debug) {
    throw new UnsupportedOperationException();
  }

  @Override
  protected void getShutdownParameters(JavaeeParameters params, TomcatLocalModel model, boolean debug) {
    throw new UnsupportedOperationException();
  }

  @Override
  protected List<EnvironmentVariable> getEnvironmentVariables(TomcatLocalModel tomcatModel) {
    try {
      ArrayList<EnvironmentVariable> vars = new ArrayList<>();
      vars.add(new EnvironmentVariable("CATALINA_HOME", tomcatModel.getHomeDirectory(), true));
      vars.add(new EnvironmentVariable("CATALINA_BASE", tomcatModel.getBaseDirectoryPath(), true));
      String tmpDir = EnvironmentUtil.getValue(CATALINA_TMPDIR_ENV_PROPERTY);
      if (tmpDir == null) {
        vars.add(new EnvironmentVariable(CATALINA_TMPDIR_ENV_PROPERTY, getCatalinaTempDirectory(tomcatModel), true));
      }
      String[] javaEnvVars = {JAVA_HOME_ENV_PROPERTY, JRE_HOME_ENV_PROPERTY};
      String jrePath = tomcatModel.getJrePath();
      for (String varName : javaEnvVars) {
        setupJavaPath(vars, varName, jrePath);
      }

      return vars;
    }
    catch (RuntimeConfigurationException e) {
      LOG.error(e);
      return null;
    }
  }

  private static String getCatalinaTempDirectory(final TomcatLocalModel tomcatModel) throws RuntimeConfigurationException {
    File tempDir = new File(tomcatModel.getSourceBaseDirectoryPath(), TEMP_FILE_NAME);
    if (!tempDir.exists()) {
      tempDir = new File(tomcatModel.getBaseDirectoryPath(), TEMP_FILE_NAME);
      if (!tempDir.exists()) {
        FileUtil.createDirectory(tempDir);
      }
    }
    return tempDir.getAbsolutePath();
  }

  @NonNls
  public static String getDefaultCatalinaFileName() {
    return SystemInfo.isWindows ? "catalina.bat" : "catalina.sh";
  }

  private static void setupJavaPath(List<EnvironmentVariable> vars, String varName, String jrePath) {
    if (jrePath != null) {
      vars.add(new EnvironmentVariable(varName, jrePath, true));
    }
    else {
      String envValue = EnvironmentUtil.getValue(varName);

      if (envValue != null) {
        vars.add(new EnvironmentVariable(varName, envValue, true));
      }
    }
  }

  private static class ExecutableCreator {

    private final TomcatLocalModel myTomcatModel;
    private final File myBinDir;
    private final File myCatalinaScriptFile;

    private final String myScriptCommand;
    private final String myJavaCommand;

    public ExecutableCreator(TomcatLocalModel model, @NonNls String scriptCommand, @NonNls String javaCommand)
      throws RuntimeConfigurationException {
      myScriptCommand = scriptCommand;
      myJavaCommand = javaCommand;

      myTomcatModel = model;

      myBinDir = new File(new File(myTomcatModel.getHomeDirectory()), BIN_DIR);
      myCatalinaScriptFile = new File(myBinDir, getDefaultCatalinaFileName());
    }

    protected final TomcatLocalModel getTomcatModel() {
      return myTomcatModel;
    }

    public ExecutableObject createExecutable() throws RuntimeConfigurationException {
      if (myCatalinaScriptFile.exists()) {
        return createScriptExecutable();
      }
      else {
        return createJavaExecutable();
      }
    }

    private ExecutableObject createScriptExecutable() {
      return new ColoredCommandLineExecutableObject(new String[]{myCatalinaScriptFile.getAbsolutePath(), myScriptCommand}, null) {
        @Override
        public OSProcessHandler createProcessHandler(String workingDirectory, Map<String, String> envVariables) throws ExecutionException {
          List<String> customJavaOptions = getCustomJavaOptions();
          if (!customJavaOptions.isEmpty()) {
            envVariables = new HashMap<>(envVariables);
            String javaOptions = StringUtil.notNullize(envVariables.get(JAVA_VM_ENV_VARIABLE))
                                 + " "
                                 + StringUtil.join(customJavaOptions, " ");
            envVariables.put(JAVA_VM_ENV_VARIABLE, javaOptions);
          }
          return super.createProcessHandler(workingDirectory, envVariables);
        }
      };
    }

    private ExecutableObject createJavaExecutable() throws RuntimeConfigurationException {
      final Sdk jre = getTomcatModel().getJre();
      final @NonNls String vmExecutablePath = jre == null ? "java" : ((JavaSdkType)jre.getSdkType()).getVMExecutablePath(jre);
      List<String> args = new ArrayList<>();
      args.addAll(Arrays.asList(
        vmExecutablePath,
        "-Dcatalina.base=" + myTomcatModel.getBaseDirectoryPath(),
        "-Dcatalina.home=" + myTomcatModel.getHomeDirectory(),
        "-Djava.io.tmpdir=" + getCatalinaTempDirectory(myTomcatModel)
      ));

      args.addAll(getCustomJavaOptions());

      final String bootstrapJarPath = new File(myBinDir, "bootstrap.jar").getAbsolutePath();
      if (myTomcatModel.isVersion7OrHigher()) {
        args.addAll(Arrays.asList(
          CLASSPATH_PARAMETER,
          bootstrapJarPath + File.pathSeparator + new File(myBinDir, "tomcat-juli.jar").getAbsolutePath(),
          "org.apache.catalina.startup.Bootstrap"
        ));
      }
      else {
        args.addAll(Arrays.asList(
          JAR_PARAMETER,
          bootstrapJarPath
        ));
      }
      return new ColoredCommandLineExecutableObject(ArrayUtil.toStringArray(args), myJavaCommand) {
        protected GeneralCommandLine createCommandLine(String[] parameters, final Map<String, String> envVariables) {
          final String javaOptions = envVariables.get(JAVA_VM_ENV_VARIABLE);
          if (javaOptions != null) {
            List<String> newParameters = new ArrayList<>();
            for (String parameter : parameters) {
              if (JAR_PARAMETER.equals(parameter) || CLASSPATH_PARAMETER.equals(parameter)) {
                newParameters.addAll(StringUtil.splitHonorQuotes(javaOptions, ' '));
              }
              newParameters.add(parameter);
            }
            parameters = ArrayUtil.toStringArray(newParameters);
          }
          return super.createCommandLine(parameters, envVariables);
        }
      };
    }

    protected List<String> getCustomJavaOptions() {
      return Collections.emptyList();
    }
  }
}
