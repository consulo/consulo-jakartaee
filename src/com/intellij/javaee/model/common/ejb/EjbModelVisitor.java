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

import com.intellij.psi.PsiClass;
import com.intellij.javaee.model.common.ejb.EnterpriseBean;
import com.intellij.javaee.model.common.ejb.EntityBean;

/**
 * @author peter
 */
public abstract class EjbModelVisitor<T> {
  public T visitEjb(EnterpriseBean ejb){
    return null;
  }

  public T visitEntityBean(EntityBean ejb){
    return visitEjb(ejb);
  }

  public T visitSessionBean(SessionBean ejb){
    return visitEjb(ejb);
  }

  public T visitMessageDrivenBean(MessageDrivenBean ejb){
    return visitEjb(ejb);
  }

  public boolean visitInterface(PsiClass aClass){
    return true;
  }
}
