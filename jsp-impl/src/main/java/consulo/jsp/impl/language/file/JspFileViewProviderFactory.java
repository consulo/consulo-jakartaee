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

import consulo.annotation.component.ExtensionImpl;
import consulo.jsp.language.JspLanguage;
import consulo.language.Language;
import consulo.language.file.FileViewProvider;
import consulo.language.file.LanguageFileViewProviderFactory;
import consulo.language.psi.PsiManager;
import consulo.virtualFileSystem.VirtualFile;

import javax.annotation.Nonnull;

/**
 * @author VISTALL
 * @since 08.11.13.
 */
@ExtensionImpl
public class JspFileViewProviderFactory implements LanguageFileViewProviderFactory
{
	@Override
	public FileViewProvider createFileViewProvider(@Nonnull VirtualFile virtualFile, Language language, @Nonnull PsiManager psiManager, boolean b)
	{
		return new JspFileViewProviderImpl(psiManager, virtualFile, b);
	}

	@Nonnull
	@Override
	public Language getLanguage()
	{
		return JspLanguage.INSTANCE;
	}
}
