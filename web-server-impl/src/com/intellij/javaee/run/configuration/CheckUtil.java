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
package com.intellij.javaee.run.configuration;

import com.intellij.execution.configurations.RuntimeConfigurationException;
import com.intellij.execution.configurations.RuntimeConfigurationError;
import com.intellij.javaee.J2EEBundle;

import java.io.File;

public class CheckUtil {
  private CheckUtil() {}

  public static void checkExists(String description, String fName, boolean optional) throws RuntimeConfigurationException {
    if ("".equals(fName)) {
      if (optional) {
        return;
      }
      else {
        throw new RuntimeConfigurationError(J2EEBundle.message("runtime.configuration.error.something.not.specified", description)) ;
      }
    }

    if (!new File(fName).exists()) {
      throw new RuntimeConfigurationError(J2EEBundle.message("runtime.configuration.error.description.file.does.not.exist", description, fName)) ;
    }
  }

  public static void checkExists(String description, String fName) throws RuntimeConfigurationException {
    checkExists(description, fName, false);
  }
}
