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
package com.intellij.jam.reflect;

import javax.annotation.Nonnull;

import com.intellij.jam.JamBooleanAttributeElement;
import com.intellij.psi.PsiAnnotation;
import com.intellij.psi.PsiElementRef;

/**
 * @since 143
 */
public class JamBooleanAttributeMeta extends JamAttributeMeta<JamBooleanAttributeElement> {

  private final boolean myDefaultValue;

  public JamBooleanAttributeMeta(String attrName, boolean defaultValue) {
    super(attrName);
    myDefaultValue = defaultValue;
  }

  @Nonnull
  @Override
  public JamBooleanAttributeElement getJam(PsiElementRef<PsiAnnotation> anno) {
    return new JamBooleanAttributeElement(getAttributeLink().getAttributeName(), anno, myDefaultValue);
  }
}
