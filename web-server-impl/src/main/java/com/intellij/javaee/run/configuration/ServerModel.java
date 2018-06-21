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

import com.intellij.execution.ExecutionException;
import com.intellij.execution.configurations.RuntimeConfigurationException;
import com.intellij.execution.process.ProcessHandler;
import com.intellij.javaee.deployment.DeploymentProvider;
import com.intellij.javaee.run.execution.OutputProcessor;
import com.intellij.javaee.serverInstances.J2EEServerInstance;
import com.intellij.openapi.options.SettingsEditor;
import com.intellij.openapi.util.JDOMExternalizable;
import com.intellij.openapi.util.Pair;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import java.util.List;

public interface ServerModel extends JDOMExternalizable, Cloneable {

  J2EEServerInstance createServerInstance() throws ExecutionException;

  /**
   * @deprecated override {@link com.intellij.javaee.appServerIntegrations.AppServerIntegration#getDeploymentProvider(boolean)} instead
   */
  @Nullable
  DeploymentProvider getDeploymentProvider();

  @Nonnull
  String getDefaultUrlForBrowser();

  SettingsEditor getEditor();

  OutputProcessor createOutputProcessor(ProcessHandler processHandler, J2EEServerInstance serverInstance);

  List<Pair<String, Integer>> getAddressesToCheck();

  void checkConfiguration() throws RuntimeConfigurationException;

  int getDefaultPort();

  void setCommonModel(CommonModel commonModel);

  Object clone() throws CloneNotSupportedException;

  int getLocalPort();
}
