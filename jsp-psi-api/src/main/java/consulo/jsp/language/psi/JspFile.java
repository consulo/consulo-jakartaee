/*
 * Copyright 2000-2009 JetBrains s.r.o.
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
package consulo.jsp.language.psi;

import com.intellij.java.language.psi.PsiClass;
import com.intellij.java.language.psi.ServerPageFile;
import consulo.xml.psi.xml.XmlTag;

import jakarta.annotation.Nullable;

public interface JspFile extends BaseJspFile, ServerPageFile
{
  JspFile[] EMPTY_ARRAY = new JspFile[0];

  XmlTag[] getDirectiveTagsInContext(JspDirectiveKind directiveKind);

  @Nullable
  PsiClass getJavaClass();
}
