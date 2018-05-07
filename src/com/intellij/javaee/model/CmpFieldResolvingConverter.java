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

import com.intellij.javaee.model.common.ejb.CmpField;
import com.intellij.javaee.model.common.ejb.EntityBean;
import com.intellij.javaee.J2EEBundle;
import com.intellij.util.xml.ConvertContext;
import com.intellij.util.xml.ElementPresentationManager;
import com.intellij.codeInsight.CodeInsightBundle;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import java.util.Collection;
import java.util.Collections;

/**
 * @author peter
 */
public abstract class CmpFieldResolvingConverter extends JavaeeResolvingConverter<CmpField> {
  @Nullable
  protected abstract EntityBean getEntityBean(ConvertContext context);

  public CmpField fromString(final String s, final ConvertContext context) {
    final EntityBean entityBean = getEntityBean(context);
    return entityBean != null ? ElementPresentationManager.findByName(entityBean.getCmpFields(), s) : null;
  }

  public String getErrorMessage(@Nullable String s, final ConvertContext context) {
    return CodeInsightBundle.message("error.cannot.resolve.0.1", J2EEBundle.message("model.object.type.cmp.field"), s);
  }

  @Nonnull
  public Collection<? extends CmpField> getVariants(final ConvertContext context) {
    final EntityBean entityBean = getEntityBean(context);
    return entityBean == null ? Collections.<CmpField>emptyList() : entityBean.getCmpFields();
  }
}
