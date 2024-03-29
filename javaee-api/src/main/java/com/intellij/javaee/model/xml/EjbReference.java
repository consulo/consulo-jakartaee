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
package com.intellij.javaee.model.xml;

import com.intellij.java.language.psi.PsiClass;
import com.intellij.javaee.model.EjbLinkResolveConverter;
import com.intellij.javaee.model.common.ejb.EnterpriseBean;
import com.intellij.javaee.model.enums.EjbRefType;
import consulo.xml.util.xml.Convert;
import consulo.xml.util.xml.GenericDomValue;
import consulo.xml.util.xml.NameValue;

/**
 * @author peter
 */
public interface EjbReference extends com.intellij.javaee.model.common.EjbReference, JavaeeDomModelElement {

  GenericDomValue<PsiClass> getComponentInterface();

  GenericDomValue<PsiClass> getHomeInterface();

  @NameValue
  GenericDomValue<String> getEjbRefName();

  GenericDomValue<PsiClass> getBeanInterface();

  GenericDomValue<String> getMappedName();

  @Convert(EjbLinkResolveConverter.class)
  GenericDomValue<EnterpriseBean> getEjbLink();

  GenericDomValue<EjbRefType> getEjbRefType();
}
