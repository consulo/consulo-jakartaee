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

import com.intellij.jam.JamConverter;
import com.intellij.jam.JamElement;
import com.intellij.psi.*;
import com.intellij.psi.ref.AnnotationAttributeChildLink;
import com.intellij.util.NullableFunction;
import com.intellij.util.containers.ContainerUtil;
import org.jetbrains.annotations.NonNls;
import javax.annotation.Nonnull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author peter
 */
public abstract class JamAttributeMeta<JamType> {
  private final AnnotationAttributeChildLink myAttributeLink;

  protected JamAttributeMeta(@NonNls String attrName) {
    myAttributeLink = new AnnotationAttributeChildLink(attrName);
  }

  public AnnotationAttributeChildLink getAttributeLink() {
    return myAttributeLink;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    JamAttributeMeta that = (JamAttributeMeta)o;

    if (!myAttributeLink.equals(that.myAttributeLink)) return false;

    return true;
  }

  @Override
  public int hashCode() {
    return myAttributeLink.hashCode();
  }

  @Nonnull
  protected <T> List<T> getCollectionJam(PsiElementRef<PsiAnnotation> annoRef, NullableFunction<PsiAnnotationMemberValue, T> producer) {
    final PsiAnnotationMemberValue attr = getAttributeLink().findLinkedChild(annoRef.getPsiElement());
    if (attr == null) {
      return Collections.emptyList();
    }

    final ArrayList<T> result = new ArrayList<>();
    if (attr instanceof PsiArrayInitializerMemberValue) {
      for (PsiAnnotationMemberValue value : ((PsiArrayInitializerMemberValue)attr).getInitializers()) {
        ContainerUtil.addIfNotNull(result, producer.fun(value));
      }
    } else {
      ContainerUtil.addIfNotNull(result, producer.fun(attr));
    }
    return result;
  }


  @Nonnull
  public abstract JamType getJam(PsiElementRef<PsiAnnotation> anno);

  public static JamStringAttributeMeta.Single<String> singleString(@Nonnull @NonNls String attrName) {
    return singleString(attrName, JamConverter.DUMMY_CONVERTER);
  }

  public static <T> JamStringAttributeMeta.Single<T> singleString(@Nonnull @NonNls String attrName, JamConverter<T> converter) {
    return new JamStringAttributeMeta.Single<>(attrName, converter);
  }

  public static <T extends Enum<T>> JamEnumAttributeMeta.Single<T> singleEnum(@Nonnull @NonNls String attrName, Class<T> modelEnum) {
    return new JamEnumAttributeMeta.Single<>(attrName, modelEnum);
  }

  public static JamStringAttributeMeta.Collection<String> collectionString(@Nonnull @NonNls String attrName) {
    return collectionString(attrName, JamConverter.DUMMY_CONVERTER);
  }

  public static <T> JamStringAttributeMeta.Collection<T> collectionString(String attrName, JamConverter<T> converter) {
    return new JamStringAttributeMeta.Collection<>(attrName, converter);
  }

  public static <T extends JamElement> JamAnnotationAttributeMeta.Single<T> singleAnno(@Nonnull @NonNls String attrName, JamAnnotationMeta annoMeta, Class<T> jamClass) {
    final JamInstantiator<PsiAnnotation, T> instantiator = JamInstantiator.proxied(jamClass);
    return new JamAnnotationAttributeMeta.Single<>(attrName, annoMeta, instantiator);
  }

  public static <T extends JamElement> JamAnnotationAttributeMeta.Collection<T> annoCollection(@Nonnull @NonNls String attrName, @Nonnull JamAnnotationMeta annoMeta, Class<T> jamClass) {
    final JamInstantiator<PsiAnnotation, T> instantiator = JamInstantiator.proxied(jamClass);
    return new JamAnnotationAttributeMeta.Collection<>(attrName, annoMeta, instantiator);
  }


  public static JamClassAttributeMeta.Single singleClass(String attrName) {
    return new JamClassAttributeMeta.Single(attrName);
  }

  public static JamClassAttributeMeta.Collection classCollection(String attrName) {
    return new JamClassAttributeMeta.Collection(attrName);
  }

  public static JamTypeAttributeMeta.Single singleType(String attrName) {
    return new JamTypeAttributeMeta.Single(attrName);
  }

  public static JamTypeAttributeMeta.Collection typeCollection(String attrName) {
    return new JamTypeAttributeMeta.Collection(attrName);
  }

  /**
   * @since 143
   */
  public static JamBooleanAttributeMeta singleBoolean(@Nonnull @NonNls String attrName, boolean defaultValue) {
    return new JamBooleanAttributeMeta(attrName, defaultValue);
  }

  /**
   * @since 2017.1
   */
  public static JamNumberAttributeMeta.Single<Integer> singleInteger(@Nonnull @NonNls String attrName) {
    return new JamNumberAttributeMeta.Single<>(attrName, Integer.class);
  }

  protected static PsiAnnotationMemberValue addAttribute(PsiElementRef<PsiAnnotation> annoRef, String valueText, final AnnotationAttributeChildLink link) {
    final PsiAnnotation annotation = annoRef.ensurePsiElementExists();
    final PsiElementFactory factory = JavaPsiFacade.getElementFactory(annotation.getProject());
    PsiAnnotationMemberValue literal = factory.createExpressionFromText(valueText, null);

    PsiAnnotationMemberValue attr = link.findLinkedChild(annotation);
    if (attr == null) {
      literal = (PsiAnnotationMemberValue)link.createChild(annotation).replace(literal);
    } else if (attr instanceof PsiArrayInitializerMemberValue) {
      literal = (PsiAnnotationMemberValue) attr.add(literal);
    } else {
      PsiAnnotationMemberValue arrayInit = factory.createAnnotationFromText("@Foo({})", null).findDeclaredAttributeValue(null);
      arrayInit.add(attr);
      arrayInit = annotation.setDeclaredAttributeValue(link.getAttributeName(), arrayInit);
      literal = (PsiAnnotationMemberValue)arrayInit.add(literal);
    }
    return literal;
  }
}
