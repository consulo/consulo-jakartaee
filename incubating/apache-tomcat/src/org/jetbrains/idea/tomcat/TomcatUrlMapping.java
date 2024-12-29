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
import com.intellij.javaee.context.FacetContextProvider;
import com.intellij.javaee.run.configuration.CommonModel;
import com.intellij.javaee.serverInstances.J2EEServerInstance;
import com.intellij.javaee.web.WebFacetContextProvider;
import com.intellij.javaee.web.WebUtil;
import com.intellij.openapi.util.text.StringUtil;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.util.Url;
import jakarta.annotation.Nonnull;

import java.util.List;

/**
 * @author nik
 */
public class TomcatUrlMapping extends ApplicationServerUrlMapping {
  public static final TomcatUrlMapping INSTANCE = new TomcatUrlMapping();

  private TomcatUrlMapping() {
  }

  @Override
  protected void collectFacetContextProviders(List<FacetContextProvider> facetContextProvider) {
    facetContextProvider.add(new WebFacetContextProvider());
  }

  @Override
  public VirtualFile findSourceFile(@Nonnull final J2EEServerInstance serverInstance,
                                    @Nonnull final CommonModel commonModel, @Nonnull Url url) {
    String baseUrl = ApplicationServerUrlMapping.createUrl(commonModel, null, null);
    String urlString = url.trimParameters().toDecodedForm();
    if (!urlString.startsWith(baseUrl)) {
      return null;
    }
    String relative = StringUtil.trimStart(urlString.substring(baseUrl.length()), "/");
    return WebUtil.findSourceFile(relative, commonModel, model -> {
      TomcatModuleDeploymentModel tomcatModel = (TomcatModuleDeploymentModel)model;
      return tomcatModel.isEEArtifact() ? null : tomcatModel.CONTEXT_PATH;
    });
  }
}
