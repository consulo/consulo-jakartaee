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
package com.intellij.javaee.model;

import java.util.Collection;

import javax.annotation.Nonnull;

import com.intellij.javaee.model.common.ejb.EjbCommonModelUtil;
import com.intellij.javaee.model.common.ejb.EnterpriseBean;
import com.intellij.util.xml.ConvertContext;

/**
 * @author peter
 */
public class EjbLinkResolveConverter extends JavaeeResolvingConverter<EnterpriseBean> {

  public EnterpriseBean fromString(final String s, final ConvertContext context) {
    return null;
    //return resolveEnterpriseBean(s, JavaeeFacetUtil.getInstance().getJavaeeFacet(context));
  }

 /* @Nullable
  public static EnterpriseBean resolveEnterpriseBean(final String s, @Nullable final JavaeeFacet contextFacet) {
    if (s == null || contextFacet == null) return null;
    final int index = s.indexOf('#');
    final Project project = contextFacet.getModule().getProject();
    if (index < 0) {
      EjbFacet facet = contextFacet instanceof EjbFacet ? (EjbFacet)contextFacet : null;
      final EnterpriseBean ejb = ElementPresentationManager.findByName(EjbCommonModelUtil.getAllEjbs(project, contextFacet.getModule(), facet), s);
      if (ejb != null) return ejb;
      return ElementPresentationManager.findByName(EjbCommonModelUtil.getAllEjbs(project), s);
    }

    final String relative = s.substring(0, index);
    for (final EjbFacet ejbFacet : JavaeeFacetUtil.getInstance().getJavaeeFacets(EjbFacet.ID, project)) {
      if (relative.equals(getRelativePath(contextFacet, ejbFacet))) {
        return ElementPresentationManager.findByName(EjbCommonModelUtil.getAllEjbs(project, null, ejbFacet), s.substring(index + 1));
      }
    }

    return null;
  }  */

 /* @Nullable
  private static String getRelativePath(@Nullable JavaeeFacet contextFacet, @Nullable EjbFacet ejbFacet) {
    if (contextFacet == null || ejbFacet == null) {
      return null;
    }
    return JavaeeArtifactUtil.getInstance().getRelativePath(contextFacet, ejbFacet);
  }   */
 /*
  public String toString(final EnterpriseBean t, final ConvertContext context) {
    if (t == null) return null;

    final EjbFacet ejbFacet = EjbModuleUtil.getEjbFacet(t);
    final JavaeeFacet contextFacet = JavaeeFacetUtil.getInstance().getJavaeeFacet(context);
    final String ejbName = t.getEjbName().getValue();
    if (ejbFacet == contextFacet) {
      return ejbName;
    }
    final String relativePath = getRelativePath(contextFacet, ejbFacet);
    return relativePath == null ? ejbName : relativePath + "#" + ejbName;
  }   */

  @Nonnull
  public Collection<? extends EnterpriseBean> getVariants(final ConvertContext context) {
    return EjbCommonModelUtil.getAllEjbs(context.getProject());
  }
}
