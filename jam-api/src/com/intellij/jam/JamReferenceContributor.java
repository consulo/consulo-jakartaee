/*
 * Copyright 2000-2014 JetBrains s.r.o.
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

import com.intellij.codeInsight.completion.CompletionUtil;
import com.intellij.jam.reflect.JamAnnotationMeta;
import com.intellij.jam.reflect.JamAttributeMeta;
import com.intellij.jam.reflect.JamStringAttributeMeta;
import com.intellij.patterns.PsiJavaElementPattern;
import com.intellij.patterns.PsiJavaPatterns;
import com.intellij.patterns.PsiNameValuePairPattern;
import com.intellij.psi.*;
import com.intellij.psi.util.PsiTreeUtil;
import com.intellij.semantic.SemService;
import com.intellij.util.ProcessingContext;
import com.intellij.util.containers.ContainerUtil;
import org.jetbrains.annotations.NotNull;

import java.util.List;

import static com.intellij.patterns.PsiJavaPatterns.psiLiteral;
import static com.intellij.patterns.PsiJavaPatterns.psiNameValuePair;

/**
 * @author peter
 */
public class JamReferenceContributor extends PsiReferenceContributor {
  private static final PsiNameValuePairPattern NAME_VALUE_PAIR = psiNameValuePair().withParent(PsiAnnotationParameterList.class);
  public static final PsiJavaElementPattern.Capture<PsiLiteral> STRING_IN_ANNO = psiLiteral().withParent(
    PsiJavaPatterns.or(NAME_VALUE_PAIR,
                       PsiJavaPatterns.psiElement(PsiArrayInitializerMemberValue.class).withParent(NAME_VALUE_PAIR)
    ));

  public void registerReferenceProviders(@NotNull PsiReferenceRegistrar registrar) {
    registrar.registerReferenceProvider(STRING_IN_ANNO, new PsiReferenceProvider() {
      @NotNull
      @Override
      public PsiReference[] getReferencesByElement(@NotNull PsiElement element, @NotNull ProcessingContext context) {
        final PsiNameValuePair pair = PsiTreeUtil.getParentOfType(element, PsiNameValuePair.class);
        final PsiAnnotation anno = (PsiAnnotation)pair.getParent().getParent();
        final PsiAnnotation originalAnno = CompletionUtil.getOriginalOrSelf(anno);
        List<JamAnnotationMeta> metas = SemService.getSemService(anno.getProject()).getSemElements(JamService.ANNO_META_KEY, originalAnno);
        for (JamAnnotationMeta annotationMeta : metas) {
          if (annotationMeta != null) {
            final JamAttributeMeta<?> attribute = annotationMeta.findAttribute(pair.getName());
            if (attribute instanceof JamStringAttributeMeta) {
              final JamStringAttributeMeta<?, ?> meta = (JamStringAttributeMeta)attribute;
              final Object jam = attribute.getJam(PsiElementRef.real(anno));
              final JamConverter<?> converter = meta.getConverter();
              if (jam instanceof List) {
                List<PsiReference> refs = ContainerUtil.newArrayList();
                final List<JamStringAttributeElement> list = (List<JamStringAttributeElement>)jam;
                for (final JamStringAttributeElement attributeElement : list) {
                  if (element.equals(attributeElement.getPsiElement())) {
                    ContainerUtil.addAll(refs, converter.createReferences(attributeElement));
                  }
                }
                return refs.toArray(new PsiReference[refs.size()]);
              }
              return converter.createReferences((JamStringAttributeElement)jam);
            }
          }
        }
        return PsiReference.EMPTY_ARRAY;
      }
    }, PsiReferenceRegistrar.HIGHER_PRIORITY);
  }
}
