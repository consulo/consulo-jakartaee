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

import org.jdom.Element;
import org.jetbrains.idea.tomcat.server.TomcatServerModel;
import com.intellij.javaee.context.DeploymentModelContext;
import com.intellij.javaee.run.configuration.CommonModel;
import com.intellij.javaee.ui.packaging.WebApplicationArtifactType;
import com.intellij.openapi.util.io.FileUtil;
import com.intellij.openapi.util.text.StringUtil;
import com.intellij.packaging.artifacts.Artifact;
import com.intellij.remoteServer.configuration.deployment.DeploymentSource;

public class TomcatModuleDeploymentModel extends TomcatDeploymentModelBase implements DeploymentModelContext {
  public String CONTEXT_PATH = "/";

  public TomcatModuleDeploymentModel(CommonModel parentConfiguration, DeploymentSource source) {
    super(parentConfiguration, source);
  }

  public boolean isEEArtifact() {
    Artifact artifact = getArtifact();
    return artifact != null && !(artifact.getArtifactType() instanceof WebApplicationArtifactType);
  }

  public String getContextPath() {
    if (isEEArtifact()) {
      return "/" + FileUtil.sanitizeFileName(getDeploymentSource().getPresentableName());
    }
    else {
      return getContextRoot();
    }
  }

  @Override
  public TomcatServerModel getServerModel() {
    return (TomcatServerModel)super.getServerModel();
  }

  @Override
  public boolean isDefaultContextRoot() {
    return isEEArtifact();
  }

  @Override
  public String getContextRoot() {
    String result = CONTEXT_PATH;
    Element contextRoot = TomcatUtil.findContextInContextXmlByFacet(this);
    if (StringUtil.isEmpty(result) && contextRoot != null && !getServerModel().isTomEE()) {
      result = contextRoot.getAttributeValue(TomcatContexts.PATH_ATTR);
    }
    if (!StringUtil.startsWithChar(result, '/')) {
      result = "/" + result;
    }
    if (result.equals("/")) {
      result = "";
    }
    return result;
  }
}
