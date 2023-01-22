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

package consulo.jakartaee.webServer.impl.appServerIntegrations;

import consulo.jakartaee.webServer.impl.deployment.DeploymentModel;
import consulo.jakartaee.webServer.impl.run.configuration.ServerModel;
import consulo.jakartaee.webServer.impl.serverInstances.J2EEServerInstance;
import consulo.compiler.artifact.ArtifactUtil;

import javax.annotation.Nullable;

/**
 * @author nik
 */
public interface AppServerDeployedFileUrlProvider {

  AppServerDeployedFileUrlProvider DEFAULT = new AppServerDeployedFileUrlProvider() {
    public @Nullable String getUrlForDeployedFile(J2EEServerInstance serverInstance, DeploymentModel deploymentModel, String relativePath) {
      final ServerModel serverModel = serverInstance.getCommonModel().getServerModel();
      return ArtifactUtil.concatPaths(serverModel.getDefaultUrlForBrowser(), relativePath);
    }
  };

  @Nullable String getUrlForDeployedFile(J2EEServerInstance serverInstance, DeploymentModel deploymentModel, String relativePath);

}
