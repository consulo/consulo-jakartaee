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

import com.intellij.java.impl.util.xml.converters.AbstractMemberResolveConverter;
import com.intellij.java.language.psi.*;
import com.intellij.java.language.psi.util.PropertyMemberType;
import com.intellij.java.language.psi.util.PropertyUtil;
import com.intellij.javaee.model.common.EjbReference;
import com.intellij.javaee.model.common.Resource;
import com.intellij.javaee.model.xml.InjectionTarget;
import com.intellij.javaee.model.xml.ServiceRef;
import consulo.language.editor.CodeInsightBundle;
import consulo.util.lang.StringUtil;
import consulo.xml.util.xml.ConvertContext;
import consulo.xml.util.xml.DomUtil;

import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;

/**
 * @author Gregory.Shrago
 */
public class EjbInjectionTargetConverter extends AbstractMemberResolveConverter {
  @Nonnull
  protected PsiType getPsiType(final ConvertContext context) {
    final Resource resource;
    final EjbReference ejbReference;
    final ServiceRef serviceReference;
    final PsiClass aClass;
    if ((resource = DomUtil.getParentOfType(context.getInvocationElement(), Resource.class, false)) != null) {
      aClass = resource.getType().getValue();
    }
    else if ((ejbReference = DomUtil.getParentOfType(context.getInvocationElement(), EjbReference.class, false)) != null) {
      aClass = ejbReference.getBeanInterface().getValue();
    }
    else if ((serviceReference = DomUtil.getParentOfType(context.getInvocationElement(), ServiceRef.class, false)) != null) {
      aClass = serviceReference.getServiceInterface().getValue();
    }
    else {
      aClass = null;
    }
    return aClass == null? super.getPsiType(context) : JavaPsiFacade.getInstance(aClass.getProject()).getElementFactory().createType(aClass);
  }

  @Override
  protected String getPropertyName(final String s, final ConvertContext context) {
    return StringUtil.notNullize(PropertyUtil.getPropertyName(s));
  }

  protected boolean methodSuits(final PsiMethod psiMethod) {
    return AbstractMethodResolveConverter.methodSuits(psiMethod) && PropertyUtil.isSimplePropertySetter(psiMethod);
  }

  protected boolean fieldSuits(final PsiField psiField) {
    return super.fieldSuits(psiField);
  }

  @Nullable
  protected PsiClass getTargetClass(final ConvertContext context) {
    final InjectionTarget injectionTarget = context.getInvocationElement().getParentOfType(InjectionTarget.class, true);
    return injectionTarget == null? null : injectionTarget.getInjectionTargetClass().getValue();
  }

  @Nonnull
  protected PropertyMemberType[] getMemberTypes(final ConvertContext context) {
    return new PropertyMemberType[] { PropertyMemberType.SETTER, PropertyMemberType.FIELD};
  }

  @Override
  public String getErrorMessage(@Nullable final String s, final ConvertContext context) {
    return CodeInsightBundle.message("error.cannot.resolve.default.message", s);
  }
}
