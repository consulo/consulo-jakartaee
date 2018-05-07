/*
 * Copyright 2000-2013 JetBrains s.r.o.
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

import com.intellij.jam.JamClassAttributeElement;
import com.intellij.openapi.util.Factory;
import com.intellij.psi.PsiAnnotation;
import com.intellij.psi.PsiClass;
import com.intellij.psi.PsiElementRef;

import javax.annotation.Nonnull;

import java.util.List;

/**
 * @author peter
 */
public abstract class JamClassAttributeMeta<JamType> extends JamAttributeMeta<JamType> {

  public JamClassAttributeMeta(String attrName) {
    super(attrName);
  }

  public static class Collection extends JamClassAttributeMeta<List<JamClassAttributeElement>> {
    public static final Collection CLASS_COLLECTION_VALUE_META = classCollection(PsiAnnotation.DEFAULT_REFERENCED_METHOD_NAME);

    public Collection(String attrName) {
      super(attrName);
    }

    @Nonnull
    public List<JamClassAttributeElement> getJam(PsiElementRef<PsiAnnotation> anno) {
      return getCollectionJam(anno, psiAnnotationMemberValue -> new JamClassAttributeElement(psiAnnotationMemberValue));
    }
  }

  public static class Single extends JamClassAttributeMeta<JamClassAttributeElement> {
    public static final Single CLASS_VALUE_META = singleClass(PsiAnnotation.DEFAULT_REFERENCED_METHOD_NAME);

    public Single(String attrName) {
      super(attrName);
    }

    @Nonnull
    public JamClassAttributeElement getJam(PsiElementRef<PsiAnnotation> anno) {
      return new JamClassAttributeElement(anno, getAttributeLink().getAttributeName());
    }

    @Nonnull
    public JamClassAttributeElement getJam(PsiElementRef<PsiAnnotation> anno, final Factory<PsiClass> defaultValue) {
      return new JamClassAttributeElement(anno, getAttributeLink().getAttributeName()) {
        @Override
        public PsiClass getValue() {
          final PsiClass psiClass = super.getValue();
          return psiClass == null ? defaultValue.create() : psiClass;
        }
      };
    }
  }

}
