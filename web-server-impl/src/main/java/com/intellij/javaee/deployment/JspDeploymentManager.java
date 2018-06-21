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
package com.intellij.javaee.deployment;

import javax.annotation.Nullable;
import com.intellij.openapi.components.ServiceManager;
import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiFile;
import consulo.javaee.module.extension.JavaEEModuleExtension;
import consulo.javaee.module.extension.JavaWebModuleExtension;


public abstract class JspDeploymentManager {

  public static JspDeploymentManager getInstance() {
    return ServiceManager.getService(JspDeploymentManager.class);
  }

  /**
   * Searches for deployed JSP source within all project modules
   * However, it is strongly recommended to use another version of this method instead
   * and specify only modules that are really deployed to the server in order to limit the search scope to the 'right' modules.
   * @param project
   * @param relPath
   */
  @Nullable
  public abstract PsiFile getDeployedJspSource(String relPath, Project project);

  /**
   * Searches for deployed JSP source within the deployedModules
   * @param relPath
   * @param project
   * @param scopeFacets
   */
  @Nullable
  public abstract PsiFile getDeployedJspSource(String relPath, Project project, JavaEEModuleExtension[] scopeFacets);

  public abstract String getSourceJspDeployment(PsiFile file);

  @Nullable
  public abstract String computeRelativeTargetPath(PsiFile file, JavaWebModuleExtension webFacet);
}
