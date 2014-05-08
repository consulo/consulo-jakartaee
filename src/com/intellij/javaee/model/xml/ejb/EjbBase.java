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

package com.intellij.javaee.model.xml.ejb;

import com.intellij.javaee.model.common.ejb.EnterpriseBean;
import com.intellij.javaee.model.xml.DescriptionGroup;
import com.intellij.javaee.model.xml.JavaeeDomModelElement;
import com.intellij.javaee.model.xml.JndiEnvironmentRefsGroup;
import com.intellij.javaee.model.EjbNameConverter;
import com.intellij.psi.PsiClass;
import com.intellij.util.xml.GenericDomValue;
import com.intellij.util.xml.Convert;

/**
 * Created by IntelliJ IDEA.
 * User: Gregory.Shrag@o
 * Date: 03.11.2005
 * Time: 17:03:27
 */
public interface EjbBase extends JavaeeDomModelElement, DescriptionGroup, JndiEnvironmentRefsGroup, EnterpriseBean {

  @Convert(EjbNameConverter.class)
  GenericDomValue<String> getEjbName();

  SecurityIdentity getSecurityIdentity();

  GenericDomValue<PsiClass> getEjbClass();


}
