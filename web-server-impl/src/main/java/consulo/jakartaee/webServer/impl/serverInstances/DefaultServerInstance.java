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
package consulo.jakartaee.webServer.impl.serverInstances;

import com.intellij.javaee.artifact.JavaeeArtifactUtil;
import consulo.application.ApplicationManager;
import consulo.application.util.function.Computable;
import consulo.compiler.artifact.Artifact;
import consulo.execution.ui.layout.RunnerLayoutUi;
import consulo.jakartaee.webServer.impl.appServerIntegrations.AppServerIntegration;
import consulo.jakartaee.webServer.impl.deployment.DeploymentModel;
import consulo.jakartaee.webServer.impl.run.configuration.CommonModel;
import consulo.jakartaee.webServer.impl.run.configuration.ServerModel;
import consulo.jakartaee.webServer.impl.run.localRun.ExecutableObject;
import consulo.javaee.module.extension.JavaEEModuleExtension;
import consulo.logging.Logger;
import consulo.process.ExecutionException;
import consulo.process.ProcessHandler;
import consulo.util.collection.Lists;
import org.jetbrains.annotations.NonNls;

import jakarta.annotation.Nonnull;
import java.io.IOException;
import java.net.BindException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class DefaultServerInstance implements J2EEServerInstance {
  private static final Logger LOG = Logger.getInstance(DefaultServerInstance.class);
  private final List<J2EEServerStateListener> myListeners = Lists.newLockFreeCopyOnWriteList();
  private final CommonModel myCommonModel;
  private final String myHost;
  private final int myPort;
  private ProcessHandler myProcessHandler;
  @NonNls protected static final String LOCALHOST = "localhost";

  public DefaultServerInstance(CommonModel runConfiguration) {
    myCommonModel = runConfiguration;
    myHost = myCommonModel.getHost();
    myPort = myCommonModel.getPort();
  }

  protected String getHost() {
    return myHost;
  }

  public String getName() {
    return myCommonModel.getName();
  }

  public AppServerIntegration getIntegration() {
    return myCommonModel.getIntegration();
  }

  public CommonModel getCommonModel() {
    return myCommonModel;
  }

  public ServerModel getServerModel() {
    return myCommonModel.getServerModel();
  }

  public void start(ProcessHandler processHandler) {
    myProcessHandler = processHandler;
  }

  public boolean isStopped() {
    return myProcessHandler != null && myProcessHandler.isProcessTerminated();
  }

  public boolean isStarting() {
    return myProcessHandler == null;
  }

  public boolean isStartupScriptTerminatesAfterServerStartup(@Nonnull ExecutableObject startupScript) {
    return isStartupScriptTerminatesAfterServerStartup();
  }

  /**
   * @deprecated override {@link #isStartupScriptTerminatesAfterServerStartup(ExecutableObject)} instead
   */
  @Deprecated
  public boolean isStartupScriptTerminatesAfterServerStartup() {
    return false;
  }

  public void updateChangedFiles(Set<String> changedFilesPaths) {
  }

  public boolean isConnected() {
    return isConnected(myPort);
  }

  protected boolean isConnected(int port) {
    if(LOCALHOST.equals(myHost) || myCommonModel.isLocal()) {
      try {
        ServerSocket serverSocket = new ServerSocket(port);
        serverSocket.close();
      } catch (BindException e) {
        return true;
      } catch (IOException ignored) {
      }
    }

    try {
      Socket socket = new Socket(myHost, port);
      socket.setSoTimeout(30000);
      try {
        socket.close();
      }
      catch (IOException ignored) {
      }
      return true;
    }
    catch (SocketTimeoutException ex) {
      return true;
    }
    catch (Exception e) {
      return false;
    }


  }

  public void shutdown() {
  }

  public boolean connect() throws Exception {
    return true;
  }

  public void registerServerError(Throwable e1) {
    LOG.error(e1);
  }

  public void addServerListener(J2EEServerStateListener j2EEServerStateListener) {
    myListeners.add(j2EEServerStateListener);
  }

  public void removeServerListener(J2EEServerStateListener j2EEServerStateListener) {
    myListeners.remove(j2EEServerStateListener);
  }

  public void fireServerListeners(final J2EEServerEvent event) {
    for (J2EEServerStateListener listener : myListeners) {
      listener.serverStateChanged(event);
    }
  }

  protected final void notifyBeforeDisconnect() {
    fireServerListeners(new DefaultJ2EEServerEvent(false, false, true));
  }

  public ProcessHandler getProcessHandler() {
    return myProcessHandler;
  }

  public static JavaEEModuleExtension[] getScopeFacets(final CommonModel commonModel) {
    return ApplicationManager.getApplication().runReadAction(new Computable<JavaEEModuleExtension[]>() {
      public JavaEEModuleExtension[] compute() {
        final List<JavaEEModuleExtension> facetsToDeploy = new ArrayList<>();
        for (DeploymentModel model : commonModel.getDeploymentModels()) {
          final Artifact artifact = model.getArtifact();
          if (artifact != null) {
            facetsToDeploy.addAll(JavaeeArtifactUtil.getInstance().getFacetsIncludedInArtifact(commonModel.getProject(), artifact, null));
          }
        }
        return facetsToDeploy.toArray(new JavaEEModuleExtension[facetsToDeploy.size()]);
      }
    });
  }

  public static JavaEEModuleExtension[] getScopeFacetsWithIncluded(final CommonModel commonModel) {
    return getScopeFacets(commonModel);
  }

  public void registerAdditionalContent(RunnerLayoutUi layoutUi) {
  }

  public void prepare() throws ExecutionException
  {

  }
}
