/*
 * Copyright 2000-2006 JetBrains s.r.o.
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

import com.intellij.javaee.appServerIntegrations.ApplicationServer;
import com.intellij.javaee.oss.server.JavaeeServerHomeProvider;
import com.intellij.javaee.oss.server.JavaeeServerVersionProvider;
import com.intellij.javaee.oss.util.Version;
import com.intellij.javaee.run.configuration.ApplicationServerSelectionListener;
import com.intellij.openapi.options.ConfigurationException;
import com.intellij.openapi.util.text.StringUtil;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.idea.tomcat.server.TomcatIntegration;
import org.jetbrains.idea.tomcat.server.TomcatLocalModel;
import org.jetbrains.idea.tomcat.server.TomcatRunSettingsEditor;

import javax.swing.*;

/**
 * @author nik
 */
public class TomcatLocalRunConfigurationEditor extends TomcatRunSettingsEditor<TomcatLocalModel>
  implements ApplicationServerSelectionListener {

  private JCheckBox myDeployTomcatApplicationsCheckBox;
  private JPanel myMainPanel;
  private JTextField myHttpPortTextField;
  private JTextField myHttpsPortTextField;
  private JCheckBox myPreserveSessionsCheckBox;
  private JTextField myJndiPortField;
  private JLabel myJndiPortLabel;
  private JTextField myAjpPortTextField;

  private boolean myOldLocalPortIsSource;
  private String myOldLocalPort;

  @Override
  public void serverSelected(@Nullable ApplicationServer server) {
    updateSessionsCheckboxLabel(server);
    if (server != null && myOldLocalPortIsSource) {
      if (myOldLocalPort.equals(myHttpPortTextField.getText())) {
        TomcatPersistentDataWrapper persistentDataWrapper = new TomcatPersistentDataWrapper(server);
        String newSourceLocalPort = persistentDataWrapper.hasSourceLocalPort()
                                    ? String.valueOf(persistentDataWrapper.getSourceLocalPort())
                                    : "";
        resetLocalPort(newSourceLocalPort);
      }
      else {
        myOldLocalPortIsSource = false;
      }
    }
  }

  @Override
  public void serverProbablyEdited(@Nullable ApplicationServer server) {
    updateSessionsCheckboxLabel(server);
  }

  @Override
  protected void applyEditorTo(TomcatLocalModel serverModel) throws ConfigurationException {
    serverModel.DEPLOY_TOMCAT_APPS = myDeployTomcatApplicationsCheckBox.isSelected();
    serverModel.setHttpPort(getTomcatPort(myHttpPortTextField, TomcatBundle.message("error.message.invalid.http.port.number")));
    serverModel.HTTPS_PORT = getTomcatPort(myHttpsPortTextField, TomcatBundle.message("error.message.invalid.secured.http.port.number"));
    serverModel.AJP_PORT = getTomcatPort(myAjpPortTextField, TomcatBundle.message("error.message.invalid.ajp.port.number"));
    serverModel.PRESERVE_SESSIONS = myPreserveSessionsCheckBox.isSelected();
    serverModel.JNDI_PORT = getJndiPort(myJndiPortField, serverModel);
  }

  private static int getTomcatPort(JTextField portTextField, String errorMessage) throws ConfigurationException {
    return StringUtil.isEmpty(portTextField.getText()) ? TomcatLocalModel.UNDEFINED_PORT : getPort(portTextField, errorMessage);
  }

  @Override
  protected void resetEditorFrom(TomcatLocalModel serverModel) {
    updateSessionsCheckboxLabel(serverModel.getApplicationServer());
    myDeployTomcatApplicationsCheckBox.setSelected(serverModel.DEPLOY_TOMCAT_APPS);

    myOldLocalPortIsSource = serverModel.isSourceLocalPort();
    Integer httpPort = serverModel.getHttpPort();
    resetLocalPort(httpPort == null ? "" : String.valueOf(httpPort));

    int securedLocalPort = serverModel.HTTPS_PORT;
    myHttpsPortTextField.setText(securedLocalPort == TomcatLocalModel.UNDEFINED_PORT ? "" : String.valueOf(securedLocalPort));

    int ajpPort = serverModel.AJP_PORT;
    myAjpPortTextField.setText(ajpPort == TomcatLocalModel.UNDEFINED_PORT ? "" : String.valueOf(ajpPort));

    myPreserveSessionsCheckBox.setSelected(serverModel.PRESERVE_SESSIONS);

    boolean useJmx = serverModel.isUseJmx();
    myJndiPortLabel.setVisible(useJmx);
    myJndiPortField.setVisible(useJmx);
    myJndiPortField.setText(String.valueOf(serverModel.JNDI_PORT));
  }

  private void resetLocalPort(String localPort) {
    myOldLocalPort = localPort;
    myHttpPortTextField.setText(myOldLocalPort);
  }

  private void updateSessionsCheckboxLabel(ApplicationServer server) {
    String sessionsCheckboxName = TomcatBundle.message("checkbox.preserve.sessions.across.restarts.and.redeploys");
    if (server != null) {
      boolean isTomEE = TomcatIntegration.isTomEE(new JavaeeServerHomeProvider(server).getValue());
      boolean isSpecificVersion = new Version(new JavaeeServerVersionProvider(server).getValue()).getMajor() < 7;
      if (isTomEE || isSpecificVersion) {
        sessionsCheckboxName = TomcatBundle.message("checkbox.preserve.sessions.across.restarts");
      }
    }
    myPreserveSessionsCheckBox.setText(sessionsCheckboxName);
  }

  @NotNull
  @Override
  protected JComponent getEditor() {
    return myMainPanel;
  }
}
