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
package consulo.jakartaee.webServer.impl;

import consulo.language.psi.PsiElement;
import consulo.language.psi.PsiFile;
import consulo.language.psi.PsiFileSystemItem;
import consulo.language.util.IncorrectOperationException;
import consulo.navigation.Navigatable;
import consulo.virtualFileSystem.VirtualFile;
import org.jetbrains.annotations.NonNls;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;

public interface WebDirectoryElement extends PsiFileSystemItem, Navigatable
{
  WebDirectoryElement[] EMPTY_ARRAY = new WebDirectoryElement[0];

  @Nullable WebDirectoryElement getParentDirectory();

  @Nullable WebDirectoryElement getParent();

  boolean isDirectory();

  @Nonnull
  String getPath();
  @Nonnull
  List<VirtualFile> getOriginalVirtualFiles();
  @Nonnull
  WebDirectoryElement[] getChildren();

  boolean processChildren(@Nonnull final WebDirectoryProcessor processor);
  @Nullable WebDirectoryElement createElement(final String name, final boolean isDirectory) throws IncorrectOperationException;

  interface WebDirectoryProcessor {
    boolean execute(String name, boolean isDirectory) throws Exception;
  }

  @Nullable
  VirtualFile getOriginalVirtualFile();

  @Nullable
  PsiFile getOriginalFile();

  @Nullable
  PsiElement resolveRelative(@NonNls String path);
}
