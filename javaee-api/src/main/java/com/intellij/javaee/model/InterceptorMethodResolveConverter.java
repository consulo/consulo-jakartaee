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
import com.intellij.java.language.psi.PsiMethod;
import com.intellij.javaee.model.common.ejb.EjbCommonModelUtil;
import com.intellij.javaee.model.xml.JavaeeDomModelElement;
import com.intellij.javaee.model.xml.ejb.EjbBase;
import com.intellij.javaee.model.xml.ejb.Interceptor;
import com.intellij.javaee.model.xml.ejb.MethodParams;
import consulo.util.collection.ContainerUtil;
import consulo.util.lang.function.Condition;
import consulo.xml.util.xml.ConvertContext;
import consulo.xml.util.xml.GenericDomValue;

import jakarta.annotation.Nonnull;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Set;

/**
 * @author peter
 */
public abstract class InterceptorMethodResolveConverter<T extends JavaeeDomModelElement> extends AbstractMethodResolveConverter<T> {
  public InterceptorMethodResolveConverter(final Class<T> domMethodClass) {
    super(domMethodClass);
  }

  protected MethodParams getMethodParams(@Nonnull final T parent) {
    return null;
  }

  public Set<String> getAdditionalVariants() {
    return Collections.emptySet();
  }

  protected Collection<PsiMethod> getVariants(final PsiClass s) {
    return ContainerUtil.findAll(s.getAllMethods(), new Condition<PsiMethod>() {
      public boolean value(final PsiMethod object) {
        return object.getParameterList().getParametersCount() == 0;
      }
    });
  }

  @Nonnull
  protected Collection<PsiClass> getPsiClasses(final T parent, final ConvertContext context) {
    final GenericDomValue<PsiClass> lifecycleCallbackClass = getPsiClassValue(parent);
    if (lifecycleCallbackClass.getXmlTag() == null) {
      Interceptor interceptor = parent.getParentOfType(Interceptor.class, true);
      if (interceptor != null) {
        return Arrays.asList(interceptor.getInterceptorClass().getValue());
      }
      final EjbBase base = parent.getParentOfType(EjbBase.class, true);
      if (base != null) {
        return Arrays.asList(EjbCommonModelUtil.getMergedEnterpriseBean(base).getEjbClass().getValue());
      }
    }

    return Arrays.asList(lifecycleCallbackClass.getValue());
  }

  protected abstract GenericDomValue<PsiClass> getPsiClassValue(T parent);
}
