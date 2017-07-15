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
package com.intellij.jam.view;

import com.intellij.jam.model.common.CommonModelElement;
import com.intellij.openapi.ui.Messages;
import com.intellij.psi.PsiElement;
import com.intellij.util.IncorrectOperationException;

import javax.swing.*;
import java.util.Collection;

public interface JamUserResponse {
  JamUserResponse TEST = new JamUserResponse() {
    public Collection<PsiElement> askUserToDeletePsiElements(Collection<PsiElement> ownedReferences, String elementDisplayName) {
      return ownedReferences;
    }

    public void logErrorWhileDeletingModelElement(IncorrectOperationException e, CommonModelElement object) {
      throw new RuntimeException(e);
    }

    public void logErrorWhileDeletingPsiElement(IncorrectOperationException e, String xmlObjectDisplayString, PsiElement element) {
      throw new RuntimeException(e);
    }

    public int showYesNoDialog(String message, String title, Icon icon) {
      return Messages.YES;
    }

    public void onDeletingHasBeenFinished() {
    }
  };

  JamUserResponse QUIET = new JamUserResponse() {
    public Collection<PsiElement> askUserToDeletePsiElements(Collection<PsiElement> ownedReferences, String elementDisplayName) {
      return ownedReferences;
    }

    public void logErrorWhileDeletingModelElement(IncorrectOperationException e, CommonModelElement object) {
      throw new RuntimeException(e);
    }

    public void logErrorWhileDeletingPsiElement(IncorrectOperationException e, String xmlObjectDisplayString, PsiElement element) {
      throw new RuntimeException(e);
    }

    public int showYesNoDialog(String message, String title, Icon icon) {
      return Messages.YES;
    }

    public void onDeletingHasBeenFinished() {
    }
  };

  Collection<PsiElement> askUserToDeletePsiElements(Collection<PsiElement> ownedReferences, String elementDisplayName);

  void logErrorWhileDeletingModelElement(IncorrectOperationException e, CommonModelElement object);

  void logErrorWhileDeletingPsiElement(IncorrectOperationException e, String xmlObjectDisplayString, PsiElement element);

  @Messages.YesNoResult
  int showYesNoDialog(String message, String title, Icon icon);

  void onDeletingHasBeenFinished();
}
