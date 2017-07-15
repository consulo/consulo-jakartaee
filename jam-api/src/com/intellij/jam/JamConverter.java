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

import com.intellij.psi.PsiReference;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * @author peter
 */
public abstract class JamConverter<T> {

  public static final JamConverter<String> DUMMY_CONVERTER = new JamConverter<String>() {
    @Override
    public String fromString(@Nullable String s, JamStringAttributeElement<String> context) {
      return s;
    }

    @Override
    public String toString(@Nullable String s, JamElement context) {
      return s;
    }
  };

  /**
   * @since 15
   */
  public static final JamConverter<Boolean> BOOLEAN_CONVERTER = new JamConverter<Boolean>() {
    @Nullable
    @Override
    public Boolean fromString(@Nullable String s, JamStringAttributeElement<Boolean> context) {
      return s == null ? null : Boolean.valueOf(s);
    }
  };

  @Nullable
  public abstract T fromString(@Nullable String s, JamStringAttributeElement<T> context);

  @Nullable
  public String toString(@Nullable T s, JamElement context) {
    throw new UnsupportedOperationException("toString() not supported for " + getClass());
  }

  @NotNull
  public PsiReference[] createReferences(JamStringAttributeElement<T> context) {
    return PsiReference.EMPTY_ARRAY;
  }
}
