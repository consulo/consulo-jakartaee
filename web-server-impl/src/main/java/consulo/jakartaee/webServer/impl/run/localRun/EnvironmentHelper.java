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

import consulo.jakartaee.webServer.impl.run.configuration.CommonModel;
import consulo.execution.configuration.EnvironmentVariable;
import org.jetbrains.annotations.NonNls;

import java.util.List;

public class EnvironmentHelper {

  @NonNls
  public String getDefaultJavaVmEnvVariableName(CommonModel model) {
    return null;
  }

  public List<EnvironmentVariable> getAdditionalEnvironmentVariables(CommonModel model) {
    return null;
  }

  public boolean defaultVmVariableNameCanBeChangedByUser() {
    return false;
  }

  public boolean isVmVariableVisible() {
    return true;
  }
}
