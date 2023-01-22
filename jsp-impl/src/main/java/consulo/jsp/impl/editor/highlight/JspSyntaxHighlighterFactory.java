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

package consulo.jsp.impl.editor.highlight;

import consulo.annotation.component.ExtensionImpl;
import consulo.jsp.language.JspLanguage;
import consulo.language.Language;
import consulo.language.editor.highlight.SyntaxHighlighter;
import consulo.language.editor.highlight.SyntaxHighlighterFactory;
import consulo.project.Project;
import consulo.virtualFileSystem.VirtualFile;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/**
 * @author VISTALL
 * @since 08.11.13.
 */
@ExtensionImpl
public class JspSyntaxHighlighterFactory extends SyntaxHighlighterFactory
{
	@Nonnull
	@Override
	public SyntaxHighlighter getSyntaxHighlighter(@Nullable Project project, @Nullable VirtualFile virtualFile)
	{
		return new JspSyntaxHighlighter();
	}

	@Nonnull
	@Override
	public Language getLanguage()
	{
		return JspLanguage.INSTANCE;
	}
}
