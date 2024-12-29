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

import com.intellij.java.language.psi.PsiClass;
import com.intellij.javaee.model.xml.ejb.EntityBean;
import com.intellij.javaee.model.xml.ejb.MethodParams;
import com.intellij.javaee.model.xml.ejb.Query;
import com.intellij.javaee.model.xml.ejb.QueryMethod;
import consulo.xml.util.xml.ConvertContext;
import org.jetbrains.annotations.NonNls;

import jakarta.annotation.Nonnull;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Set;

/**
 * @author peter
 */
public class QueryMethodResolveConverter extends AbstractMethodResolveConverter<QueryMethod> {

  public QueryMethodResolveConverter() {
    super(QueryMethod.class);
  }

  @Nonnull
  protected Collection<PsiClass> getPsiClasses(final QueryMethod parent, final ConvertContext context) {
    final Query query = (Query)parent.getParent();
    if (query != null) {
      final EntityBean bean = (EntityBean)query.getParent();
      if (bean != null) {
        @NonNls final String value = parent.getMethodName().getStringValue();
        if (value != null && value.startsWith("find")) {
          return Arrays.asList(bean.getHome().getValue(), bean.getLocalHome().getValue());
        }
        return Arrays.asList(bean.getEjbClass().getValue());
      }
    }
    return Collections.emptyList();
  }

  public Set<String> getAdditionalVariants() {
    return Collections.emptySet();
  }

  @Nonnull
  protected MethodParams getMethodParams(@Nonnull final QueryMethod parent) {
    return parent.getMethodParams();
  }
}
