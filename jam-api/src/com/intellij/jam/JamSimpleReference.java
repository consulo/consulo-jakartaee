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
package com.intellij.jam;

import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiLiteral;
import com.intellij.psi.PsiReferenceBase;
import com.intellij.util.IncorrectOperationException;
import com.intellij.util.ObjectUtils;
import org.jetbrains.annotations.NotNull;

/**
 * @author peter
*/
public class JamSimpleReference<T> extends PsiReferenceBase<PsiLiteral> {
  private final JamStringAttributeElement<T> myContext;
  private final JamSimpleReferenceConverter<T> myConverter;

  public JamSimpleReference(JamStringAttributeElement<T> context) {
    super(ObjectUtils.assertNotNull(context.getPsiLiteral()));
    myContext = context;
    myConverter = (JamSimpleReferenceConverter<T>)context.getConverter();
  }

  public PsiElement resolve() {
    final T result = myConverter.fromString(getValue(), myContext);
    if (result == null) {
      return null;
    }

    final PsiElement element = myConverter.getPsiElementFor(result);
    return element == null? myContext.getPsiLiteral() : element;
  }

  @NotNull
  public Object[] getVariants() {
    return myConverter.getLookupVariants(myContext);
  }

  @Override
  public PsiElement bindToElement(@NotNull PsiElement element) throws IncorrectOperationException {
    return myConverter.bindReference(myContext, element);
  }
}
