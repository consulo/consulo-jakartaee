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

package consulo.jsp.impl.language.file;

import com.intellij.java.language.JavaLanguage;
import consulo.jsp.language.file.JspFileViewProvider;
import consulo.jsp.impl.language.psi.JspTemplateTokens;
import consulo.jsp.impl.language.psi.java.psi.JspJavaFileImpl;
import consulo.jsp.language.JspLanguage;
import consulo.language.Language;
import consulo.language.impl.file.MultiplePsiFilesPerDocumentFileViewProvider;
import consulo.language.impl.psi.PsiFileImpl;
import consulo.language.parser.ParserDefinition;
import consulo.language.psi.PsiFile;
import consulo.language.psi.PsiManager;
import consulo.virtualFileSystem.VirtualFile;
import consulo.xml.lang.html.HTMLLanguage;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * @author VISTALL
 * @since 08.11.13.
 */
public class JspFileViewProviderImpl extends MultiplePsiFilesPerDocumentFileViewProvider implements JspFileViewProvider
{
	public JspFileViewProviderImpl(PsiManager manager, VirtualFile virtualFile, boolean physical)
	{
		super(manager, virtualFile, physical);
	}

	@Nonnull
	@Override
	public Language getBaseLanguage()
	{
		return JspLanguage.INSTANCE;
	}

	@Nullable
	@Override
	protected PsiFile createFile(@Nonnull final Language lang)
	{
		if(lang == getBaseLanguage())
		{
			return ParserDefinition.forLanguage(lang).createFile(this);
		}

		if(lang == JavaLanguage.INSTANCE)
		{
			return new JspJavaFileImpl(this);
		}

		if(lang == getTemplateDataLanguage())
		{
			PsiFileImpl file = (PsiFileImpl) ParserDefinition.forLanguage(lang).createFile(this);
			file.setContentElementType(JspTemplateTokens.HTML_TEMPLATE_DATA);
			return file;
		}
		return null;
	}

	@Nonnull
	@Override
	public Set<Language> getLanguages()
	{
		return new HashSet<>(Arrays.asList(getBaseLanguage(), getTemplateDataLanguage(), JavaLanguage.INSTANCE));
	}

	@Nonnull
	@Override
	public Language getTemplateDataLanguage()
	{
		return HTMLLanguage.INSTANCE;
	}

	@Override
	protected MultiplePsiFilesPerDocumentFileViewProvider cloneInner(VirtualFile virtualFile)
	{
		return new JspFileViewProviderImpl(getManager(), virtualFile, false);
	}

	@Override
	public Set<String> getXmlNsPrefixes(CharSequence buffer)
	{
		return Collections.emptySet();
	}
}
