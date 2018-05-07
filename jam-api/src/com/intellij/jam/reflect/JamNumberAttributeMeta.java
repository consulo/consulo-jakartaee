/*
 * Copyright 2000-2016 JetBrains s.r.o.
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

import com.intellij.jam.JamNumberAttributeElement;
import com.intellij.psi.PsiAnnotation;
import com.intellij.psi.PsiElementRef;
import javax.annotation.Nonnull;

import java.util.List;

/**
 * @param <T> Number class to use.
 * @since 2017.1
 */
public abstract class JamNumberAttributeMeta<T extends Number, JamType> extends JamAttributeMeta<JamType> {

  protected final Class<T> myClass;

  protected JamNumberAttributeMeta(String attrName, Class<T> numberClass) {
    super(attrName);
    myClass = numberClass;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    if (!super.equals(o)) return false;

    JamNumberAttributeMeta meta = (JamNumberAttributeMeta)o;

    if (!myClass.equals(meta.myClass)) return false;

    return true;
  }

  @Override
  public int hashCode() {
    int result = super.hashCode();
    result = 31 * result + myClass.hashCode();
    return result;
  }


  public static class Single<T extends Number> extends JamNumberAttributeMeta<T, JamNumberAttributeElement<T>> {

    public Single(String attrName, Class<T> numberClass) {
      super(attrName, numberClass);
    }

    @Nonnull
    @Override
    public JamNumberAttributeElement<T> getJam(PsiElementRef<PsiAnnotation> anno) {
      return new JamNumberAttributeElement<>(getAttributeLink().getAttributeName(), anno, myClass);
    }
  }

  public static class Collection<T extends Number> extends JamNumberAttributeMeta<T, List<JamNumberAttributeElement<T>>> {
    public Collection(String attrName, Class<T> numberClass) {
      super(attrName, numberClass);
    }

    @Nonnull
    @Override
    public List<JamNumberAttributeElement<T>> getJam(PsiElementRef<PsiAnnotation> anno) {
      return getCollectionJam(anno, psiAnnotationMemberValue -> new JamNumberAttributeElement<>(psiAnnotationMemberValue, myClass));
    }
  }
}
