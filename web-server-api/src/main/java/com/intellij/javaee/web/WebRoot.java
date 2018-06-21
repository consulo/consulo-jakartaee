/*
 * Copyright 2000-2007 JetBrains s.r.o.
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
package com.intellij.javaee.web;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import org.jetbrains.annotations.NonNls;
import com.intellij.openapi.Disposable;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.util.Comparing;
import com.intellij.openapi.util.Computable;
import com.intellij.openapi.util.text.StringUtil;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.openapi.vfs.pointers.VirtualFilePointer;
import com.intellij.openapi.vfs.pointers.VirtualFilePointerManager;
import com.intellij.util.PathUtil;

public class WebRoot {
  private final String myRelativePath;
  private final VirtualFilePointer myVirtualFilePointer;

  public WebRoot(@NonNls String url, @NonNls String relativePath, @Nonnull Disposable parentDisposable) {
    myRelativePath = normalize(relativePath);
    myVirtualFilePointer = VirtualFilePointerManager.getInstance().create(url, parentDisposable, null);
  }

  public WebRoot(@Nonnull VirtualFile file, @NonNls String relativePath, @Nonnull Disposable parentDisposable) {
    this(file.getUrl(), relativePath, parentDisposable);
  }

  public String getRelativePath() {
    return myRelativePath;
  }

  public String getDirectoryUrl() {
    return myVirtualFilePointer.getUrl();
  }

  public String getPresentableUrl() {
    return myVirtualFilePointer.getPresentableUrl();
  }

  @Nullable
  public VirtualFile getFile() {
    return ApplicationManager.getApplication().runReadAction(new Computable<VirtualFile>() {
      @Nullable
      public VirtualFile compute() {
        return myVirtualFilePointer.getFile();
      }
    });
  }

  public String getURI() {
    return myRelativePath;
  }

  @Nullable
  private static String normalize(String relativePath) {
    relativePath = PathUtil.getCanonicalPath(relativePath);
    if (relativePath == null) relativePath = "";
    while (StringUtil.endsWithChar(relativePath, '/')) {
      relativePath = relativePath.substring(0, relativePath.length() - 1);
    }

    if (!StringUtil.startsWithChar(relativePath, '/')) {
      relativePath = "/" + relativePath;
    }
    return relativePath;
  }

  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof WebRoot)) return false;

    final WebRoot webRoot = (WebRoot)o;

    if (!Comparing.equal(getFile(), webRoot.getFile())) return false;
    if (myRelativePath != null ? !myRelativePath.equals(webRoot.myRelativePath) : webRoot.myRelativePath != null) return false;

    return true;
  }

  public int hashCode() {
    Object myFile = getFile();
    int result = myFile != null ? myFile.hashCode() : 0;
    result = 29 * result + (myRelativePath != null ? myRelativePath.hashCode() : 0);
    return result;
  }

  @NonNls public String toString() {
    return "WebRoot: ('"+(myVirtualFilePointer == null ? "" : myVirtualFilePointer.getPresentableUrl())+"' -> '"+myRelativePath+"')";
  }
}
