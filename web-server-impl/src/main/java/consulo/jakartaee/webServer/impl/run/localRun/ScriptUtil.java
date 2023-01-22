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
package consulo.jakartaee.webServer.impl.run.localRun;

import consulo.application.util.SystemInfo;
import consulo.process.cmd.GeneralCommandLine;
import consulo.util.io.FileUtil;

import javax.annotation.Nonnull;
import java.io.File;
import java.io.IOException;

public class ScriptUtil {
  private ScriptUtil() { }

  public static void appendEnvVariableDeclaration(@Nonnull String name, @Nonnull String value, @Nonnull StringBuilder buffer) {
    if (SystemInfo.isWindows) {
      buffer.append("SET ");
    }

    buffer.append(name);
    buffer.append("=");
    buffer.append(value);
    buffer.append("\n");

    if (SystemInfo.isUnix) {
      buffer.append("export ");
      buffer.append(name);
      buffer.append("\n");
    }
  }

  public static void appendEnvVariableReference(@Nonnull String name, @Nonnull StringBuilder buffer) {
    if (SystemInfo.isWindows) {
      buffer.append("%").append(name).append("%");
    }
    else {
      buffer.append("${").append(name).append("}");
    }
  }

  public static File createScriptFile(final File directory, final String fileName) throws IOException {
    File result = new File(directory, fileName + getScriptExtension());
    FileUtil.createIfDoesntExist(result);
    makeExecutable(result);
    return result;
  }

  public static void makeExecutable(final File result) throws IOException {
    if (SystemInfo.isUnix) {
      //noinspection ResultOfMethodCallIgnored
      result.setExecutable(true);
    }
  }

  public static String getScriptExtension() {
    return SystemInfo.isWindows ? "cmd" : "sh";
  }

  public static GeneralCommandLine createCommandLine(@Nonnull String... parameters) {
    return new GeneralCommandLine(parameters);
  }
}
