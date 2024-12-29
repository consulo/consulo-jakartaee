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

import consulo.annotation.access.RequiredReadAction;
import consulo.jsp.language.file.JspxFileViewProvider;
import consulo.language.psi.PsiElement;
import consulo.language.psi.PsiFile;
import consulo.xml.psi.xml.XmlFile;
import consulo.xml.psi.xml.XmlTag;

import jakarta.annotation.Nonnull;

public interface BaseJspFile extends XmlFile
{
	BaseJspFile[] EMPTY_ARRAY = new BaseJspFile[0];

	PsiElement[] getContentsElements();

	@RequiredReadAction
	boolean isErrorPage();

	boolean isSessionPage();

	boolean isTagPage();

	@RequiredReadAction
	@Nonnull
	XmlTag[] getDirectiveTags(JspDirectiveKind directiveKind, final boolean searchInIncludes);

	XmlTag createDirective(XmlTag context, JspDirectiveKind directiveKind);

	XmlTag createDirective(JspDirectiveKind directiveKind);

	/**
	 * Method with a bad name. Returns file corresponding to getTemplateDataLanguage() method of ViewProvider
	 *
	 * @see consulo.language.template.TemplateLanguageFileViewProvider#getTemplateDataLanguage()
	 */
	PsiFile getBaseLanguageRoot();

	/**
	 * @return file which the errorPage directive references,
	 * or null, if there is no errorPage directive or directive references invalid file
	 */
	PsiFile getErrorPage();

	@Nonnull
	JspxFileViewProvider getViewProvider();

	@Nonnull
	XmlTag getRootTag();
}
