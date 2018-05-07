/*
 * Copyright 2000-2005 JetBrains s.r.o.
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
package org.jetbrains.idea.tomcat;

import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JPanel;

import javax.annotation.Nonnull;
import org.jetbrains.idea.tomcat.server.TomcatLocalModel;
import org.jetbrains.idea.tomcat.server.TomcatServerModel;
import com.intellij.execution.ExecutionException;
import com.intellij.execution.configurations.RuntimeConfigurationException;
import com.intellij.javaee.deployment.DeploymentModel;
import com.intellij.javaee.run.configuration.CommonModel;
import com.intellij.openapi.options.ConfigurationException;
import com.intellij.openapi.options.SettingsEditor;
import com.intellij.remoteServer.configuration.deployment.DeploymentSource;

public class TomcatDeploymentSettingsEditor extends SettingsEditor<DeploymentModel> {
  private JPanel myPanel;
  private JComboBox myContextPath;
  private JPanel myInnerPanel;

  private boolean myEEArtifact = false;

  public TomcatDeploymentSettingsEditor(final CommonModel configuration, final DeploymentSource source) {
    super(() -> new TomcatModuleDeploymentModel(configuration, source));
  }

  public void resetEditorFrom(@Nonnull DeploymentModel settings) {
    TomcatModuleDeploymentModel deploymentModel = (TomcatModuleDeploymentModel)settings;
    myEEArtifact = deploymentModel.isEEArtifact();
    myInnerPanel.setVisible(!myEEArtifact);
    if (myEEArtifact) {
      return;
    }
    TomcatServerModel configuration = (TomcatServerModel)settings.getServerModel();
    if (configuration instanceof TomcatLocalModel) {
      updateContextPaths((TomcatLocalModel)configuration);
    }
    setSelectedContextPath(deploymentModel.CONTEXT_PATH, true);
  }

  public void applyEditorTo(@Nonnull DeploymentModel settings) throws ConfigurationException {
    if (myEEArtifact) {
      return;
    }
    ((TomcatModuleDeploymentModel)settings).CONTEXT_PATH = getSelectedContextPath();
  }

  @Nonnull
  public JComponent createEditor() {
    return myPanel;
  }

  private String getSelectedContextPath() {
    final String item = (String)myContextPath.getEditor().getItem();
    return (item != null) ? item.trim() : "";
  }

  private void setSelectedContextPath(String contextPath, boolean addIfNotFound) {
    int itemCount = myContextPath.getItemCount();
    for (int idx = 0; idx < itemCount; idx++) {
      String path = (String)myContextPath.getItemAt(idx);
      if (contextPath.equals(path)) {
        myContextPath.setSelectedIndex(idx);
        return;
      }
    }
    if (addIfNotFound) {
      myContextPath.addItem(contextPath);
      myContextPath.setSelectedItem(contextPath);
    }
  }

  private void updateContextPaths(TomcatLocalModel configuration) {
    final String selectedContextPath = getSelectedContextPath();
    myContextPath.removeAllItems();
    try {
      TomcatContexts tomcatContexts = new TomcatContexts(configuration, configuration.getSourceBaseDirectoryPath());
      for (String path : tomcatContexts.getContextPaths()) {
        myContextPath.addItem(path);
      }
    }
    catch (RuntimeConfigurationException e) {
      // ignore
    }
    catch (ExecutionException e) {
      // ignore
    }
    setSelectedContextPath(selectedContextPath, true);
  }
}
