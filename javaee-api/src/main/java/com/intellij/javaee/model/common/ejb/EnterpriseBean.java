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
package com.intellij.javaee.model.common.ejb;

import com.intellij.java.language.psi.PsiClass;
import com.intellij.java.language.psi.PsiMethod;
import com.intellij.javaee.ejb.EjbMethod;
import com.intellij.javaee.model.common.EnvironmentGroup;
import com.intellij.javaee.model.enums.TransAttribute;
import consulo.xml.util.xml.GenericValue;
import consulo.xml.util.xml.NameValue;
import consulo.xml.util.xml.PrimaryKey;

import javax.annotation.Nonnull;
import java.util.List;

/**
 * @author peter
 */
//@DeleteHandler("com.intellij.openapi.module.EjbDeleteHandler")
public interface EnterpriseBean extends EnvironmentGroup, SecurityGroup, InterceptorMethodContainer {

  @Nonnull
  EjbDescriptorVersion getEjbVersion();

  @PrimaryKey
  @NameValue
  GenericValue<String> getEjbName();

  GenericValue<PsiClass> getEjbClass();

  GenericValue<TransAttribute> getTransactionAttribute();

  GenericValue<? extends SecurityRole> getRunAs();

  List<? extends GenericValue<? extends SecurityRole>> getDeclaredRoles();

  GenericValue<Boolean> isExcludeDefaultInterceptors();
  List<? extends GenericValue<PsiClass>> getClassInterceptors();

  GenericValue<PsiMethod> getTimeoutMethodValue();

  List<? extends GenericValue<PsiClass>> getBusinessInterfaces();

  List<? extends EjbMethod> getEjbMethods();
}
