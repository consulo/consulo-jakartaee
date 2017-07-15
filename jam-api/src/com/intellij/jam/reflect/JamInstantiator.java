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

import com.intellij.jam.JamClassGenerator;
import com.intellij.jam.JamElement;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementRef;
import com.intellij.util.NotNullFunction;
import org.jetbrains.annotations.NotNull;

/**
 * @author peter
 */
public abstract class JamInstantiator<Psi extends PsiElement, Jam extends JamElement> {

  @NotNull
  public abstract Jam instantiate(@NotNull PsiElementRef<Psi> ref);

  public static <Psi extends PsiElement, Jam extends JamElement> JamInstantiator<Psi, Jam> proxied(final Class<Jam> jamClass) {
    final NotNullFunction<PsiElementRef,Jam> function = JamClassGenerator.getInstance().generateJamElementFactory(jamClass);
    return new JamInstantiator<Psi, Jam>() {
      @NotNull
      @Override
      public Jam instantiate(@NotNull PsiElementRef<Psi> psiPsiRef) {
        return function.fun(psiPsiRef);
      }
    };
  }

}
