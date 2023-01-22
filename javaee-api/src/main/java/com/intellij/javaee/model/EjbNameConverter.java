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
import com.intellij.java.language.psi.PsiNameHelper;
import com.intellij.javaee.model.xml.ejb.EjbBase;
import consulo.xml.util.xml.ConvertContext;
import consulo.xml.util.xml.Converter;
import consulo.xml.util.xml.DomElement;

/**
 * @author Gregory.Shrago
 */
public class EjbNameConverter extends Converter<String> {
  public String fromString(final String s, final ConvertContext context) {
    if (s == null) {
      final DomElement domElement = context.getInvocationElement();
      final EjbBase ejb = ((EjbBase)domElement.getParent());
      final PsiClass aClass = ejb.getEjbClass().getValue();
      if (aClass != null) {
        return aClass.getName();
      }
      else {
        final String aClassString = ejb.getEjbClass().getStringValue();
        if (aClassString != null) {
          return PsiNameHelper.getShortClassName(aClassString);
        }
      }
      return "unknown";
    }
    return s;
  }

  public String toString(final String t, final ConvertContext context) {
    return t;
  }
}
