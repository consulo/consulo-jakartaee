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

import com.intellij.javaee.appServerIntegrations.ApplicationServerUrlMapping;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.diagnostic.Logger;
import com.intellij.openapi.util.JDOMUtil;
import com.intellij.openapi.util.io.StreamUtil;
import com.intellij.util.SystemProperties;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.idea.tomcat.server.TomcatLocalModel;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

/**
 * @author nik
 */
public class Tomcat4Deployer {
  private static final Logger LOG = Logger.getInstance("#org.jetbrains.idea.tomcat.Tomcat4Deployer");
  private final TomcatLocalModel myModel;
  private static final int TIMEOUT = 5000;
  @NonNls private static final String IDEA_MANAGER_USERNAME = "idea_manager";
  @NonNls private static final String IDEA_MANAGER_PASSWORD = "idea_manager";
  @NonNls private static final String AUTHORIZATION_STRING = IDEA_MANAGER_USERNAME + ":" + IDEA_MANAGER_PASSWORD;
  @NonNls private static final String USER_ELEMENT = "user";
  @NonNls private static final String USERNAME_ATTRIBUTE = "name";
  @NonNls private static final String PASSWORD_ATTRIBUTE = "password";
  @NonNls private static final String ROLES_ATTRIBUTE = "roles";
  @NonNls private static final String MANAGER_ROLE = "manager";
  @NonNls private static final String AUTHORIZATION_PROPERTY = "Authorization";
  @NonNls private static final String BASIC_AUTHORIZATION_METHOD = "Basic ";
  @NonNls private static final String FAIL_MESSAGE_PREFIX = "FAIL -";

  public Tomcat4Deployer(final TomcatLocalModel model) {
    myModel = model;
  }

  public void deploy(final String contextPath) {
    startCommand("manager/start?path=" + adjustContextPath(contextPath));
  }

  public void undeploy(String contextPath) {
    startCommand("manager/stop?path=" + adjustContextPath(contextPath));
  }

  private static String adjustContextPath(final String contextPath) {
    if (contextPath.length() == 0) {
      return "/";
    }
    return contextPath;
  }

  private void startCommand(@NonNls final String commandUrl) {
    final String managerUrl = ApplicationServerUrlMapping.createUrl(myModel.getCommonModel(), null, null) + commandUrl;
    ApplicationManager.getApplication().executeOnPooledThread((Runnable)() -> runManagerCommand(managerUrl));
  }

  private static boolean runManagerCommand(final String managerUrl) {
    try {
      URL url = new URL(managerUrl);
      long time = System.currentTimeMillis();
      while (System.currentTimeMillis() - time < TIMEOUT) {
        LOG.debug("Running server command: " + managerUrl);
        URLConnection urlConnection = url.openConnection();
        urlConnection.setDoInput(true);
        urlConnection.setDoOutput(false);
        String encoded = Base64.getEncoder().encodeToString(AUTHORIZATION_STRING.getBytes(StandardCharsets.UTF_8));
        urlConnection.setRequestProperty(AUTHORIZATION_PROPERTY, BASIC_AUTHORIZATION_METHOD + encoded);
        urlConnection.connect();
        String msg = StreamUtil.readText(urlConnection.getInputStream());
        LOG.debug("Server returned: " + msg);
        if (msg != null) {
          return !msg.startsWith(FAIL_MESSAGE_PREFIX);
        }
      }
      return false;
    } catch (IOException e) {
      LOG.debug(e);
      return false;
    }
  }

  public static void addManagerUser(File tomcatUsersFile) {
    try {
      final Document document = JDOMUtil.loadDocument(tomcatUsersFile);
      final Element root = document.getRootElement();
      final Element userElement = new Element(USER_ELEMENT);
      userElement.setAttribute(USERNAME_ATTRIBUTE, IDEA_MANAGER_USERNAME);
      userElement.setAttribute(PASSWORD_ATTRIBUTE, IDEA_MANAGER_PASSWORD);
      userElement.setAttribute(ROLES_ATTRIBUTE, MANAGER_ROLE);
      root.addContent(userElement);

      JDOMUtil.writeDocument(document, tomcatUsersFile, SystemProperties.getLineSeparator());
    }
    catch (JDOMException e) {
      LOG.debug(e);
    }
    catch (IOException e) {
      LOG.debug(e);
    }
  }
}
