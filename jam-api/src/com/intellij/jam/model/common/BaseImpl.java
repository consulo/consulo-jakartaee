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
package com.intellij.jam.model.common;

import com.intellij.openapi.module.Module;
import com.intellij.openapi.module.ModuleUtil;
import com.intellij.psi.*;
import com.intellij.psi.search.GlobalSearchScope;
import com.intellij.util.xml.DomElement;
import com.intellij.util.xml.DomManager;
import com.intellij.util.xml.DomUtil;
import com.intellij.util.xml.DomTarget;
import com.intellij.pom.references.PomService;
import org.jetbrains.annotations.Nullable;

/**
 * @author peter
 */
public abstract class BaseImpl implements CommonDomModelElement {

  public PsiManager getPsiManager() {
    return PsiManager.getInstance(getManager().getProject());
  }

  public PsiElement getIdentifyingPsiElement() {
    final DomTarget target = DomTarget.getTarget(this);
    return target == null? getXmlElement() : PomService.convertToPsi(target);
  }

  @Nullable
  public PsiFile getContainingFile() {
    return DomUtil.getFile(this);
  }

  @Nullable
  protected final PsiClass findPsiClass(String className) {
    if (className == null) return null;
    final Module module = getModule();
    GlobalSearchScope scope = module != null?
                                     GlobalSearchScope.moduleWithDependenciesAndLibrariesScope(module):
                                     GlobalSearchScope.projectScope(getManager().getProject());
    return JavaPsiFacade.getInstance(getManager().getProject()).findClass(className, scope);
  }

  @Nullable
  public Module getModule() {
    if (!isValid()) {
      return null;
    }
    if (getManager().isMockElement(this)) {
      return DomUtil.getFile(this).getUserData(DomManager.MOCK_ELEMENT_MODULE);
    }
    final DomElement root = DomUtil.getRoot(this);
    if (equals(root)) {
      final PsiElement element = getIdentifyingPsiElement();
      return element == null ? null : ModuleUtil.findModuleForPsiElement(element);
    }
    else {
      return root.getModule();
    }
  }
}
