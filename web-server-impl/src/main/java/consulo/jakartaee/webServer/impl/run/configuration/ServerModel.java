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
package consulo.jakartaee.webServer.impl.run.configuration;

import consulo.jakartaee.webServer.impl.deployment.DeploymentProvider;
import consulo.jakartaee.webServer.impl.run.execution.OutputProcessor;
import consulo.jakartaee.webServer.impl.serverInstances.J2EEServerInstance;
import consulo.execution.RuntimeConfigurationException;
import consulo.execution.configuration.ui.SettingsEditor;
import consulo.jakartaee.webServer.impl.appServerIntegrations.AppServerIntegration;
import consulo.process.ExecutionException;
import consulo.process.ProcessHandler;
import consulo.util.lang.Pair;
import consulo.util.xml.serializer.JDOMExternalizable;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;

public interface ServerModel extends JDOMExternalizable, Cloneable {

  J2EEServerInstance createServerInstance() throws ExecutionException;

  /**
   * @deprecated override {@link AppServerIntegration#getDeploymentProvider(boolean)} instead
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
