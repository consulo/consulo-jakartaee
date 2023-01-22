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

import com.intellij.java.language.impl.JavaFileType;
import consulo.jsp.language.ast.JspTokenType;
import consulo.codeEditor.DefaultLanguageHighlighterColors;
import consulo.colorScheme.EditorColorsScheme;
import consulo.colorScheme.TextAttributesKey;
import consulo.jsp.impl.language.psi.JspTokens;
import consulo.language.ast.IElementType;
import consulo.language.editor.highlight.*;
import consulo.language.lexer.Lexer;
import consulo.project.Project;
import consulo.virtualFileSystem.VirtualFile;
import consulo.xml.ide.highlighter.HtmlFileType;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/**
 * @author VISTALL
 * @since 08.11.13.
 */
public class JspEditorHighlighter extends LayeredLexerEditorHighlighter
{
	public JspEditorHighlighter(@Nullable final Project project, @Nullable final VirtualFile virtualFile, @Nonnull final EditorColorsScheme colors)
	{
		super(new JspSyntaxHighlighter(), colors);

		SyntaxHighlighter htmlHighlight = SyntaxHighlighterFactory.getSyntaxHighlighter(HtmlFileType.INSTANCE, project, virtualFile);
		assert htmlHighlight != null;
		registerLayer(JspTokens.HTML_FRAGMENT, new LayerDescriptor(htmlHighlight, ""));

		SyntaxHighlighter javaHighlight = SyntaxHighlighterFactory.getSyntaxHighlighter(JavaFileType.INSTANCE, project, virtualFile);
		assert javaHighlight != null;
		registerLayer(JspTokenType.JAVA_CODE, new LayerDescriptor(new SyntaxHighlighterBase()
		{
			@Nonnull
			@Override
			public Lexer getHighlightingLexer()
			{
				return javaHighlight.getHighlightingLexer();
			}

			@Nonnull
			@Override
			public TextAttributesKey[] getTokenHighlights(IElementType iElementType)
			{
				return pack(DefaultLanguageHighlighterColors.TEMPLATE_LANGUAGE_COLOR, javaHighlight.getTokenHighlights(iElementType));
			}
		}, ""));
	}

}
