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

import com.intellij.javaee.model.common.ejb.EntityBean;
import consulo.xml.util.xml.ConvertContext;
import consulo.xml.util.xml.DomElement;

/**
 * @author peter
 */
public class PrimkeyFieldResolvingConverter extends CmpFieldResolvingConverter{
  protected EntityBean getEntityBean(ConvertContext context) {
    final DomElement element = context.getInvocationElement();
    final DomElement domElement = element.getParent();
    return domElement instanceof EntityBean ? (EntityBean)domElement : null;
  }
}
