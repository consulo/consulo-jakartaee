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

import com.intellij.jam.JamElement;
import com.intellij.pom.PomTarget;
import com.intellij.psi.PsiParameter;
import com.intellij.util.Consumer;
import com.intellij.util.PairConsumer;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * @author peter
 */
public class JamParameterMeta<Jam extends JamElement> extends JamMemberMeta<PsiParameter, Jam> {

  public JamParameterMeta(@Nullable JamMemberArchetype<? super PsiParameter, ? super Jam> parent, Class<Jam> jamClass) {
    super(parent, jamClass);
  }

  public JamParameterMeta(Class<Jam> jamClass) {
    super(jamClass);
  }

  @Override
  public JamParameterMeta<Jam> addAnnotation(JamAnnotationMeta meta) {
    super.addAnnotation(meta);
    return this;
  }

  @Override
  public JamParameterMeta<Jam> addPomTargetProducer(@NotNull PairConsumer<Jam, Consumer<PomTarget>> producer) {
    super.addPomTargetProducer(producer);
    return this;
  }
}
