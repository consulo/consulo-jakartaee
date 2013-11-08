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

package org.mustbe.consulo.java.web.jsp.highlight;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.mustbe.consulo.java.web.jsp.psi.JspTokens;
import com.intellij.ide.highlighter.HtmlFileType;
import com.intellij.ide.highlighter.JavaFileType;
import com.intellij.lexer.Lexer;
import com.intellij.openapi.editor.colors.EditorColorsScheme;
import com.intellij.openapi.editor.colors.TextAttributesKey;
import com.intellij.openapi.editor.ex.util.LayerDescriptor;
import com.intellij.openapi.editor.ex.util.LayeredLexerEditorHighlighter;
import com.intellij.openapi.fileTypes.SyntaxHighlighter;
import com.intellij.openapi.fileTypes.SyntaxHighlighterFactory;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.tree.IElementType;
import lombok.val;

/**
 * @author VISTALL
 * @since 08.11.13.
 */
public class JspEditorHighlighter extends LayeredLexerEditorHighlighter
{
	public JspEditorHighlighter(@Nullable final Project project, @Nullable final VirtualFile virtualFile, @NotNull final EditorColorsScheme colors)
	{
		super(new JspSyntaxHighlighter(), colors);

		val htmlHighlight = SyntaxHighlighterFactory.getSyntaxHighlighter(HtmlFileType.INSTANCE, project, virtualFile);
		assert htmlHighlight != null;
		registerLayer(JspTokens.HTML_TEXT, new LayerDescriptor(new SyntaxHighlighter()
		{
			@NotNull
			public Lexer getHighlightingLexer()
			{
				return htmlHighlight.getHighlightingLexer();
			}

			@NotNull
			public TextAttributesKey[] getTokenHighlights(final IElementType tokenType)
			{
				return htmlHighlight.getTokenHighlights(tokenType);
			}
		}, ""));

		val javaHighlight = SyntaxHighlighterFactory.getSyntaxHighlighter(JavaFileType.INSTANCE, project, virtualFile);
		assert javaHighlight != null;
		registerLayer(JspTokens.JAVA_TEXT, new LayerDescriptor(new SyntaxHighlighter()
		{
			@NotNull
			public Lexer getHighlightingLexer()
			{
				return javaHighlight.getHighlightingLexer();
			}

			@NotNull
			public TextAttributesKey[] getTokenHighlights(final IElementType tokenType)
			{
				return javaHighlight.getTokenHighlights(tokenType);
			}
		}, ""));
	}

}
