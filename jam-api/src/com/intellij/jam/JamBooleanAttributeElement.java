/*
 * Copyright 2000-2015 JetBrains s.r.o.
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
package com.intellij.jam;

import com.intellij.jam.model.util.JamCommonUtil;
import com.intellij.psi.PsiAnnotation;
import com.intellij.psi.PsiElementRef;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * @since 143
 */
public class JamBooleanAttributeElement extends JamAttributeElement<Boolean> {

  private final boolean myDefaultValue;

  public JamBooleanAttributeElement(String attributeName, @NotNull PsiElementRef<PsiAnnotation> parent, boolean defaultValue) {
    super(attributeName, parent);
    myDefaultValue = defaultValue;
  }

  @Nullable
  @Override
  public String getStringValue() {
    return Boolean.toString(getValue());
  }

  @NotNull
  @Override
  public Boolean getValue() {
    final Boolean value = JamCommonUtil.getObjectValue(getPsiElement(), Boolean.class);
    return value != null ? value : myDefaultValue;
  }
}
