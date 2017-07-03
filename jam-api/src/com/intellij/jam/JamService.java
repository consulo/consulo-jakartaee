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

import com.intellij.jam.reflect.JamAnnotationMeta;
import com.intellij.jam.reflect.JamMemberMeta;
import com.intellij.openapi.components.ServiceManager;
import com.intellij.openapi.project.Project;
import com.intellij.psi.*;
import com.intellij.psi.search.GlobalSearchScope;
import com.intellij.semantic.SemKey;
import com.intellij.semantic.SemService;
import com.intellij.util.Function;
import com.intellij.util.Processor;
import com.intellij.util.containers.ContainerUtil;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static com.intellij.jam.model.util.JamCommonUtil.findAnnotatedElements;
import static com.intellij.jam.model.util.JamCommonUtil.getSuperClassList;

/**
 * @author peter
 */
public class JamService {
  private static final Map<SemKey, SemKey<JamMemberMeta>> ourMetaKeys = ContainerUtil.newHashMap();

  public static final SemKey<JamMemberMeta> MEMBER_META_KEY = SemKey.createKey("JamMemberMeta");
  public static final SemKey<JamAnnotationMeta> ANNO_META_KEY = SemKey.createKey("JamAnnotationMeta");
  public static final SemKey<JamElement> JAM_ELEMENT_KEY = SemKey.createKey("JamElement");
  public static final SemKey<JamElement> JAM_ALIASING_ELEMENT_KEY = SemKey.createKey("JamAliasingElement");

  public static final SemKey<JamMemberMeta> ALIASING_MEMBER_META_KEY = getMetaKey(JAM_ALIASING_ELEMENT_KEY);
  private final PsiManager myPsiManager;
  private final SemService mySemService;

  protected JamService(PsiManager psiManager, SemService semService) {
    myPsiManager = psiManager;
    mySemService = semService;
  }

  public static JamService getJamService(Project p) {
    return ServiceManager.getService(p, JamService.class);
  }

  private static void processMembers(PsiClass psiClass,
                                     boolean checkClass,
                                     boolean checkMethods,
                                     boolean checkFields,
                                     boolean checkDeep,
                                     Processor<PsiMember> processor) {
    if (checkClass) {
      if (checkDeep) {
        for (final PsiClass curClass : getSuperClassList(psiClass)) {
          processor.process(curClass);
        }
      }
      else {
        processor.process(psiClass);
      }
    }
    if (checkMethods) {
      ContainerUtil.process(Arrays.asList(checkDeep ? psiClass.getAllMethods() : psiClass.getMethods()), processor);
    }
    if (checkFields) {
      ContainerUtil.process(Arrays.asList(checkDeep ? psiClass.getAllFields() : psiClass.getFields()), processor);
    }
  }

  @Nullable
  @Deprecated
  public <T extends JamElement> T getJamElement(Class<T> key, PsiElement psi) {
    for (JamElement element : mySemService.getSemElements(JAM_ELEMENT_KEY, psi)) {
      if (key.isInstance(element)) {
        //noinspection unchecked
        return (T)element;
      }
    }
    return null;
  }

  @Nullable
  public <T extends JamElement> T getJamElement(@NotNull PsiElement psi,
                                                JamMemberMeta<? extends PsiModifierListOwner, ? extends T>... metas) {
    for (JamMemberMeta<? extends PsiModifierListOwner, ? extends T> meta : metas) {
      final JamElement element = mySemService.getSemElement(meta.getJamKey(), psi);
      if (element != null) {
        //noinspection unchecked
        return (T)element;
      }
    }
    return null;
  }

  @Nullable
  public <T extends JamElement> T getJamElement(SemKey<T> key, @NotNull PsiElement psi) {
    if (!psi.isValid()) {
      throw new PsiInvalidElementAccessException(psi);
    }
    try {
      return mySemService.getSemElement(key, psi);
    }
    catch (PsiInvalidElementAccessException e) {
      throw new RuntimeException("Element invalidated: old=" + psi + "; new=" + e.getPsiElement(), e);
    }
  }

  @NotNull
  public <T extends PsiModifierListOwner> List<JamMemberMeta> getMetas(@NotNull T psi) {
    return mySemService.getSemElements(MEMBER_META_KEY, psi);
  }

  @Nullable
  public <T extends PsiModifierListOwner> JamMemberMeta<T, ?> getMeta(@NotNull T psi, SemKey<? extends JamMemberMeta<T, ?>> key) {
    return mySemService.getSemElement(key, psi);
  }

  @Nullable
  public JamAnnotationMeta getMeta(@NotNull PsiAnnotation anno) {
    return mySemService.getSemElement(ANNO_META_KEY, anno);
  }

  public <T extends JamElement> List<T> getJamClassElements(final JamMemberMeta<? super PsiClass, T> meta,
                                                            @NonNls final String anno,
                                                            final GlobalSearchScope scope) {
    return getJamClassElements(meta.getJamKey(), anno, scope);
  }

  public <T extends JamElement> List<T> getJamMethodElements(final JamMemberMeta<? super PsiMethod, T> meta,
                                                             @NonNls final String anno,
                                                             final GlobalSearchScope scope) {
    return getJamMethodElements(meta.getJamKey(), anno, scope);
  }

  public <T extends JamElement> List<T> getJamFieldElements(final JamMemberMeta<? super PsiField, T> meta,
                                                            @NonNls final String anno,
                                                            final GlobalSearchScope scope) {
    return getJamFieldElements(meta.getJamKey(), anno, scope);
  }

  public <T extends JamElement> List<T> getAnnotatedMembersList(@NotNull final PsiClass psiClass,
                                                                final boolean checkClass,
                                                                final boolean checkMethods,
                                                                final boolean checkFields,
                                                                final boolean checkDeep,
                                                                final JamMemberMeta<? extends PsiMember, ? extends T>... metas) {
    final List<T> result = ContainerUtil.newArrayList();
    processMembers(psiClass, checkClass, checkMethods, checkFields, checkDeep, member -> {
      for (JamMemberMeta<? extends PsiMember, ? extends T> meta : metas) {
        ContainerUtil.addIfNotNull(result, mySemService.getSemElement(meta.getJamKey(), member));
      }
      return true;
    });
    return result;
  }

  public <T extends JamElement> List<T> getJamClassElements(final SemKey<T> key,
                                                            @NonNls final String anno,
                                                            final GlobalSearchScope scope) {
    final List<T> result = ContainerUtil.newArrayList();
    findAnnotatedElements(PsiClass.class, anno, myPsiManager, scope, psiMember -> {
      ContainerUtil.addIfNotNull(result, getJamElement(key, psiMember));
      return true;
    });
    return result;
  }

  public <T extends JamElement> List<T> getJamMethodElements(final SemKey<T> key,
                                                             @NonNls final String anno,
                                                             final GlobalSearchScope scope) {
    final List<T> result = ContainerUtil.newArrayList();
    findAnnotatedElements(PsiMethod.class, anno, myPsiManager, scope, psiMember -> {
      ContainerUtil.addIfNotNull(result, getJamElement(key, psiMember));
      return true;
    });
    return result;
  }

  public <T extends JamElement> List<T> getJamFieldElements(final SemKey<T> key,
                                                            @NonNls final String anno,
                                                            final GlobalSearchScope scope) {
    final List<T> result = ContainerUtil.newArrayList();
    findAnnotatedElements(PsiField.class, anno, myPsiManager, scope, psiMember -> {
      ContainerUtil.addIfNotNull(result, getJamElement(key, psiMember));
      return true;
    });
    return result;
  }

  public <T extends JamElement> List<T> getJamParameterElements(final SemKey<T> key,
                                                                @NonNls final String anno,
                                                                final GlobalSearchScope scope) {
    final List<T> result = ContainerUtil.newArrayList();
    findAnnotatedElements(PsiParameter.class, anno, myPsiManager, scope, psiParameter -> {
      ContainerUtil.addIfNotNull(result, getJamElement(key, psiParameter));
      return true;
    });
    return result;
  }

  public <T extends JamElement> List<T> getAnnotatedMembersList(@NotNull final PsiClass psiClass,
                                                                final SemKey<T> clazz,
                                                                final boolean checkClass,
                                                                final boolean checkMethods,
                                                                final boolean checkFields,
                                                                final boolean checkDeep) {
    final List<T> result = ContainerUtil.newArrayList();
    processMembers(psiClass, checkClass, checkMethods, checkFields, checkDeep, member -> {
      ContainerUtil.addIfNotNull(result, getJamElement(clazz, member));
      return true;
    });
    return result;
  }

  public static SemKey<JamMemberMeta> getMetaKey(final SemKey<? extends JamElement> jamKey) {
    synchronized (ourMetaKeys) {
      SemKey<JamMemberMeta> result = ourMetaKeys.get(jamKey);
      if (result == null) {
        SemKey<?>[] jamSupers = jamKey.getSupers();
        SemKey[] metaSupers = ContainerUtil.map2Array(jamSupers, SemKey.class, new Function<SemKey, SemKey<?>>() {
          @Override
          public SemKey<?> fun(SemKey key) {
            //noinspection unchecked
            return getMetaKey(key);
          }
        });
        //noinspection unchecked
        ourMetaKeys.put(jamKey, result = MEMBER_META_KEY.subKey(jamKey + "Meta", metaSupers));
      }
      return result;
    }
  }
}
