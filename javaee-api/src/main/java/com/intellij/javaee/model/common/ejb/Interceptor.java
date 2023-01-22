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
import com.intellij.javaee.model.common.EnvironmentGroup;
import consulo.application.util.function.Processor;
import consulo.xml.util.xml.GenericValue;
import consulo.xml.util.xml.NameValue;
import consulo.xml.util.xml.PrimaryKey;

/**
 * Created by IntelliJ IDEA.
 * User: Gregory.Shrago
 * Date: 07.02.2006
 * Time: 16:24:44
 */
//@DeleteHandler("com.intellij.openapi.module.EjbDeleteHandler")
public interface Interceptor extends EnvironmentGroup, InterceptorMethodContainer {

  @PrimaryKey
  @NameValue
  GenericValue<PsiClass> getInterceptorClass();

  void processInterceptorBindings(Processor<InterceptorBinding> processor);

}

