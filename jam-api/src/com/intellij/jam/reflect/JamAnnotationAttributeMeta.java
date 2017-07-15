/*
 * Copyright 2000-2012 JetBrains s.r.o.
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

import com.intellij.jam.JamElement;
import com.intellij.jam.JamService;
import com.intellij.patterns.ElementPattern;
import com.intellij.patterns.PsiAnnotationPattern;
import com.intellij.pom.PomTarget;
import com.intellij.psi.PsiAnnotation;
import com.intellij.psi.PsiAnnotationMemberValue;
import com.intellij.psi.PsiElementRef;
import com.intellij.psi.util.PsiTreeUtil;
import com.intellij.semantic.SemKey;
import com.intellij.semantic.SemRegistrar;
import com.intellij.semantic.SemService;
import com.intellij.util.Consumer;
import com.intellij.util.NullableFunction;
import com.intellij.util.ObjectUtils;
import com.intellij.util.PairConsumer;
import com.intellij.util.containers.ContainerUtil;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

import static com.intellij.patterns.PsiJavaPatterns.psiAnnotation;

/**
 * @author peter
 */
public abstract class JamAnnotationAttributeMeta<T extends JamElement, JamType> extends JamAttributeMeta<JamType>{
  protected final JamAnnotationMeta myAnnoMeta;
  protected final SemKey<T> myJamKey;
  protected final JamInstantiator<PsiAnnotation, T> myInstantiator;
  private final List<PairConsumer<T, Consumer<PomTarget>>> myPomTargetProducers = ContainerUtil.newArrayList();
  private final PsiAnnotationPattern myAnnoPattern;

  private JamAnnotationAttributeMeta(String attrName, JamAnnotationMeta annoMeta, JamInstantiator<PsiAnnotation, T> instantiator) {
    super(attrName);
    myAnnoMeta = annoMeta;
    myInstantiator = instantiator;
    myJamKey = JamService.JAM_ELEMENT_KEY.subKey(attrName);
    myAnnoPattern = psiAnnotation().qName(myAnnoMeta.getAnnoName());
  }

  public JamAnnotationAttributeMeta<T, JamType> addPomTargetProducer(@NotNull PairConsumer<T, Consumer<PomTarget>> producer) {
    myPomTargetProducers.add(producer);
    return this;
  }

  public List<PomTarget> getAssociatedTargets(@NotNull T element) {
    final ArrayList<PomTarget> list = ContainerUtil.newArrayList();
    final Consumer<PomTarget> targetConsumer = target -> list.add(target);
    for (final PairConsumer<T, Consumer<PomTarget>> function : myPomTargetProducers) {
      function.consume(element, targetConsumer);
    }
    return list;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    JamAnnotationAttributeMeta that = (JamAnnotationAttributeMeta)o;

    if (myAnnoMeta != null ? !myAnnoMeta.equals(that.myAnnoMeta) : that.myAnnoMeta != null) return false;
    if (myInstantiator != null ? !myInstantiator.equals(that.myInstantiator) : that.myInstantiator != null) return false;

    return true;
  }

  @Override
  public int hashCode() {
    int result = myAnnoMeta != null ? myAnnoMeta.hashCode() : 0;
    result = 31 * result + (myInstantiator != null ? myInstantiator.hashCode() : 0);
    return result;
  }

  public JamAnnotationMeta getAnnotationMeta() {
    return myAnnoMeta;
  }

  public JamInstantiator<PsiAnnotation, T> getInstantiator() {
    return myInstantiator;
  }

  public void registerSem(SemRegistrar registrar, ElementPattern<PsiAnnotation> annotationPattern, JamAnnotationMeta parentMeta) {
    PsiAnnotationPattern annoPattern = myAnnoPattern.insideAnnotationAttribute(getAttributeLink().getAttributeName(), annotationPattern);
    registrar.registerSemElementProvider(myJamKey, annoPattern, new JamCreator(parentMeta));
    myAnnoMeta.registerNestedSem(registrar, annoPattern, parentMeta);
  }

  public static final class Single<T extends JamElement> extends JamAnnotationAttributeMeta<T, T> {

    public Single(String attrName, JamAnnotationMeta annoMeta, JamInstantiator<PsiAnnotation, T> instantiator) {
      super(attrName, annoMeta, instantiator);
    }

    @NotNull
    public T getJam(PsiElementRef<PsiAnnotation> anno) {
      final PsiAnnotation psiElement = anno.getPsiElement();
      assert psiElement != null;
      return ObjectUtils.assertNotNull(JamService.getJamService(anno.getPsiManager().getProject()).getJamElement(myJamKey, psiElement));
    }
  }
  public static final class Collection<T extends JamElement> extends JamAnnotationAttributeMeta<T, List<T>> {

    public Collection(String attrName, @NotNull JamAnnotationMeta annoMeta, JamInstantiator<PsiAnnotation, T> instantiator) {
      super(attrName, annoMeta, instantiator);
    }

    @NotNull
    public List<T> getJam(final PsiElementRef<PsiAnnotation> anno) {
      return getCollectionJam(anno, psiAnnotationMemberValue -> getJam(psiAnnotationMemberValue));
    }

    @Nullable
    private T getJam(PsiAnnotationMemberValue element) {
      if (element instanceof PsiAnnotation) {
        return JamService.getJamService(element.getProject()).getJamElement(myJamKey, element);
      }
      return null;
    }

    @NotNull
    public T addAttribute(PsiElementRef<PsiAnnotation> annoRef) {
      return ObjectUtils.assertNotNull(getJam(addAttribute(annoRef, "@" + myAnnoMeta.getAnnoName(), getAttributeLink())));
    }

    @Override
    public JamAnnotationAttributeMeta.Collection<T> addPomTargetProducer(@NotNull PairConsumer<T, Consumer<PomTarget>> producer) {
      super.addPomTargetProducer(producer);
      return this;
    }

  }

  protected class JamCreator implements NullableFunction<PsiAnnotation, T> {
    private final JamAnnotationMeta myParentMeta;

    private JamCreator(JamAnnotationMeta parentMeta) {
      myParentMeta = parentMeta;
    }

    public T fun(PsiAnnotation annotation) {
      final PsiAnnotation parentAnno = PsiTreeUtil.getParentOfType(annotation, PsiAnnotation.class, true);
      assert parentAnno != null;
      final JamAnnotationMeta annotationMeta = SemService.getSemService(parentAnno.getProject()).getSemElement(myParentMeta.getMetaKey(), parentAnno);
      if (annotationMeta == myParentMeta) {
        return myInstantiator.instantiate(PsiElementRef.real(annotation));
      }
      return null;
    }
  }

}
