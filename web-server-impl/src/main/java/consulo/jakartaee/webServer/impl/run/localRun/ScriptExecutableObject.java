/*
 * Copyright 2000-2007 JetBrains s.r.o.
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

import com.intellij.javaee.J2EEBundle;
import consulo.application.Application;
import consulo.application.util.TempFileService;
import consulo.util.io.FileUtil;
import consulo.process.ExecutionException;
import consulo.process.ProcessHandler;
import consulo.process.event.ProcessAdapter;
import consulo.process.event.ProcessEvent;
import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import org.jetbrains.annotations.NonNls;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Map;

/**
 * @deprecated Use ColoredScriptExecutableObject
 */
public class ScriptExecutableObject implements ExecutableObject {
  private final String myScript;
  private final @Nullable File myDirectoryForScript;
  private final String myFileName;

  public ScriptExecutableObject(final String script, final @Nullable File directoryForScript, final @NonNls String fileName) {
    myScript = script;
    myDirectoryForScript = directoryForScript;
    myFileName = fileName;
  }

  public ScriptExecutableObject(String script, @NonNls String fileName) {
    this(script, null, fileName);
  }

  public String getDisplayString() {
    return myScript;
  }

  public ProcessHandler createProcessHandler(String workingDirectory, Map<String,String> envVariables) throws ExecutionException
  {
    final File executableFile;
    try {
      executableFile = createNewExecutableFile();
      FileOutputStream outputStream = new FileOutputStream(executableFile);
      try {
        FileUtil.copy(new ByteArrayInputStream(getScript().getBytes()), outputStream);
      }
      finally {
        outputStream.close();
      }
    }
    catch (IOException e) {
      throw new ExecutionException(J2EEBundle.message("message.text.error.while.creating.temp.file", e.getLocalizedMessage()));
    }
    final File toDelete = myDirectoryForScript == null ? executableFile.getParentFile() : executableFile;
    toDelete.deleteOnExit();

    ProcessHandler result = createExecutable(executableFile).createProcessHandler(workingDirectory, envVariables);
    result.addProcessListener(new ProcessAdapter() {
      public void processTerminated(ProcessEvent event) {
        FileUtil.delete(toDelete);
      }
    });
    return result;
  }

  @Nonnull
  protected ExecutableObject createExecutable(@Nonnull File executableFile) {
    //noinspection deprecation
    return new CommandLineExecutableObject(new String[]{executableFile.getAbsolutePath()}, null);
  }

  protected String getScript() throws ExecutionException {
    return myScript;
  }

  private File createNewExecutableFile() throws IOException {
    if (myDirectoryForScript == null) {
      File tempDirectory = Application.get().getInstance(TempFileService.class).createTempDirectory("exec", "script").toFile();
      return ScriptUtil.createScriptFile(tempDirectory, myFileName);
    }
    else {
      myDirectoryForScript.mkdirs();
      final File file = FileUtil.findSequentNonexistentFile(myDirectoryForScript, myFileName, ScriptUtil.getScriptExtension());
      FileUtil.createIfDoesntExist(file);
      ScriptUtil.makeExecutable(file);
      return file;
    }
  }

}
