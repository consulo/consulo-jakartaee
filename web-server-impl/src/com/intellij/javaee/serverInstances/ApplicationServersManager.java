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
package com.intellij.javaee.serverInstances;

import java.io.File;
import java.util.List;

import javax.swing.JPanel;

import org.jetbrains.annotations.NonNls;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import com.intellij.javaee.appServerIntegrations.AppServerIntegration;
import com.intellij.javaee.appServerIntegrations.ApplicationServer;
import com.intellij.javaee.appServerIntegrations.ApplicationServerPersistentData;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.roots.libraries.Library;
import com.intellij.openapi.roots.libraries.LibraryTable;

/**
 * author: lesya
 */
public abstract class ApplicationServersManager {

  @NonNls public static final String APPLICATION_SERVER_MODULE_LIBRARIES = "application_server_libraries";

  public static ApplicationServersManager getInstance() {
    return ApplicationManager.getApplication().getComponent(ApplicationServersManager.class);
  }

  public abstract List<ApplicationServer> getApplicationServers();

  public abstract List<ApplicationServer> getApplicationServers(AppServerIntegration... integrations);

  @Nullable
  public abstract ApplicationServer findByName(@Nullable String name);

  @Nullable
  public abstract ApplicationServer findByName(@Nullable String name, @Nonnull AppServerIntegration integration);

  @Nullable
  public abstract ApplicationServer editApplicationServers(JPanel panel, AppServerIntegration integration, ApplicationServer current);

  public abstract ApplicationServersManagerModifiableModel createModifiableModel();

  @Deprecated
  @Nullable
  public abstract ApplicationServer getDefaultFor(AppServerIntegration integration);

  public abstract void addServersListener(ApplicationServersManagerListener listener);

  public abstract void removeServersListener(ApplicationServersManagerListener listener);

  public abstract LibraryTable getLibraryTable();

  public abstract ApplicationServer createServer(AppServerIntegration integration, ApplicationServerPersistentData serverData);

  /**
   * @see com.intellij.javaee.serverInstances.ApplicationServersManager#createModifiableModel()
   */
  public interface ApplicationServersManagerModifiableModel {
    List<ApplicationServer> getCurrentList(AppServerIntegration[] integrations);

    ApplicationServer createNewApplicationServer(String name, File[] defaultLibraries, ApplicationServerPersistentData persistentData);

    void deleteApplicationServer(ApplicationServer applicationServer);

    void commit();

    Library getLibrary(ApplicationServer applicationServer);

    void dispose();

    //@Nullable
    //Library addFrameworkLibrary(@NotNull ApplicationServer server, @NotNull FrameworkType type, @NotNull List<File> files);

    //void removeFrameworkLibrary(@NotNull ApplicationServer server, @NotNull FrameworkType type);
  }
}
