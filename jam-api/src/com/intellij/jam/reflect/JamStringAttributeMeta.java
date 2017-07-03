/*
 * Copyright 2000-2009 JetBrains s.r.o.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package com.intellij.jam.reflect;

import com.intellij.jam.JamConverter;
import com.intellij.jam.JamStringAttributeElement;
import com.intellij.openapi.util.Factory;
import com.intellij.psi.PsiAnnotation;
import com.intellij.psi.PsiAnnotationMemberValue;
import com.intellij.psi.PsiElementRef;
import com.intellij.util.NullableFunction;
import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * @author peter
 */
public abstract class JamStringAttributeMeta<T, JamType> extends JamAttributeMeta<JamType> {
  protected final JamConverter<T> myConverter;

  public JamStringAttributeMeta(String attrName, JamConverter<T> converter) {
    super(attrName);
    myConverter = converter;
  }

  public JamConverter<T> getConverter() {
    return myConverter;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    if (!super.equals(o)) return false;

    JamStringAttributeMeta that = (JamStringAttributeMeta)o;

    if (!myConverter.equals(that.myConverter)) return false;

    return true;
  }

  @Override
  public int hashCode() {
    int result = super.hashCode();
    result = 31 * result + myConverter.hashCode();
    return result;
  }

  public static class Collection<T> extends JamStringAttributeMeta<T, List<JamStringAttributeElement<T>>> {
    public Collection(String attrName, JamConverter<T> converter) {
      super(attrName, converter);
    }

    @NotNull
    public List<JamStringAttributeElement<T>> getJam(PsiElementRef<PsiAnnotation> anno) {
      return getCollectionJam(anno, psiAnnotationMemberValue -> new JamStringAttributeElement<>(psiAnnotationMemberValue, myConverter));
    }

    public JamStringAttributeElement<T> addAttribute(PsiElementRef<PsiAnnotation> annoRef, String stringValue) {
      return new JamStringAttributeElement<>(addAttribute(annoRef, "\"" + stringValue + "\"", getAttributeLink()), myConverter);
    }

  }

  public static class Single<T> extends JamStringAttributeMeta<T, JamStringAttributeElement<T>> {
    public static final Single<String> NAME_STRING_VALUE_META = singleString("name");

    public Single(String attrName, JamConverter<T> converter) {
      super(attrName, converter);
    }

    @NotNull
    public JamStringAttributeElement<T> getJam(PsiElementRef<PsiAnnotation> anno, @NotNull final Factory<T> defaultValue) {
      return new JamStringAttributeElement<T>(anno, getAttributeLink().getAttributeName(), myConverter) {
        @Override
        public T getValue() {
          final T value = super.getValue();
          return value == null ? defaultValue.create() : value;
        }
      };
    }

    @NotNull
    public JamStringAttributeElement<T> getJam(PsiElementRef<PsiAnnotation> anno) {
      return new JamStringAttributeElement<>(anno, getAttributeLink().getAttributeName(), myConverter);
    }
  }

}
