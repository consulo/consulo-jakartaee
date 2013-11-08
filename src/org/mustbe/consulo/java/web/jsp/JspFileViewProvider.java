/*
 * Copyright 2013 must-be.org
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

package org.mustbe.consulo.java.web.jsp;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.mustbe.consulo.java.web.jsp.psi.JspTemplateTokens;
import org.mustbe.consulo.java.web.jsp.psi.impl.JspJavaFileImpl;
import com.intellij.lang.Language;
import com.intellij.lang.LanguageParserDefinitions;
import com.intellij.lang.html.HTMLLanguage;
import com.intellij.lang.java.JavaLanguage;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.MultiplePsiFilesPerDocumentFileViewProvider;
import com.intellij.psi.PsiFile;
import com.intellij.psi.PsiManager;
import com.intellij.psi.impl.source.PsiFileImpl;
import com.intellij.psi.templateLanguages.TemplateLanguageFileViewProvider;
import lombok.val;

/**
 * @author VISTALL
 * @since 08.11.13.
 */
public class JspFileViewProvider extends MultiplePsiFilesPerDocumentFileViewProvider implements TemplateLanguageFileViewProvider
{
	public JspFileViewProvider(PsiManager manager, VirtualFile virtualFile, boolean physical)
	{
		super(manager, virtualFile, physical);
	}

	@NotNull
	@Override
	public Language getBaseLanguage()
	{
		return JspLanguage.INSTANCE;
	}

	@Nullable
	@Override
	protected PsiFile createFile(@NotNull final Language lang)
	{
		if(lang == getBaseLanguage())
		{
			return LanguageParserDefinitions.INSTANCE.forLanguage(lang).createFile(this);
		}

		if(lang == JavaLanguage.INSTANCE)
		{
			val file = new JspJavaFileImpl(this);
			file.setContentElementType(JspTemplateTokens.JAVA_TEMPLATE_DATA);
			return file;
		}

		if(lang == getTemplateDataLanguage())
		{
			val file = (PsiFileImpl) LanguageParserDefinitions.INSTANCE.forLanguage(lang).createFile(this);
			file.setContentElementType(JspTemplateTokens.HTML_TEMPLATE_DATA);
			return file;
		}
		return null;
	}

	@NotNull
	@Override
	public Set<Language> getLanguages()
	{
		return new HashSet<Language>(Arrays.asList(getBaseLanguage(), getTemplateDataLanguage(), JavaLanguage.INSTANCE));
	}

	@NotNull
	@Override
	public Language getTemplateDataLanguage()
	{
		return HTMLLanguage.INSTANCE;
	}

	@Override
	protected MultiplePsiFilesPerDocumentFileViewProvider cloneInner(VirtualFile virtualFile)
	{
		return new JspFileViewProvider(getManager(), virtualFile, isPhysical());
	}
}
