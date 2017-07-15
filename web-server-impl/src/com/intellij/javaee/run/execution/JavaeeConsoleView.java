/*
 * Copyright 2000-2010 JetBrains s.r.o.
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
package com.intellij.javaee.run.execution;

import com.intellij.execution.process.ProcessHandler;
import com.intellij.javaee.appServerIntegrations.AppServerIntegration;
import com.intellij.javaee.deployment.DeploymentView;
import com.intellij.javaee.serverInstances.J2EEServerInstance;
import com.intellij.openapi.actionSystem.DataKey;
import org.jetbrains.annotations.Nullable;

/**
 * @author nik
 */
public interface JavaeeConsoleView {
  DataKey<JavaeeConsoleView> KEY = DataKey.create("JAVAEE_CONSOLE_VIEW");

  AppServerIntegration getIntegration();

  J2EEServerInstance getServerInstance();

  ProcessHandler getProcessHandler();

  @Nullable
  DeploymentView getDeploymentView();
}
