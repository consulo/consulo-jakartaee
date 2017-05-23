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

package consulo.javaee.jsp.highlight;

import java.util.HashMap;
import java.util.Map;

import org.jetbrains.annotations.NotNull;
import com.intellij.lexer.Lexer;
import com.intellij.openapi.editor.DefaultLanguageHighlighterColors;
import com.intellij.openapi.editor.HighlighterColors;
import com.intellij.openapi.editor.colors.TextAttributesKey;
import com.intellij.openapi.fileTypes.SyntaxHighlighterBase;
import com.intellij.psi.tree.IElementType;
import consulo.javaee.jsp.lexer.JspHtmlLexer;
import consulo.javaee.jsp.psi.JspTokens;

/**
 * @author VISTALL
 * @since 08.11.13.
 */
public class JspSyntaxHighlighter extends SyntaxHighlighterBase
{
	private static final Map<IElementType, TextAttributesKey> ourMap = new HashMap<IElementType, TextAttributesKey>();

	static
	{
		ourMap.put(JspTokens.COMMENT, DefaultLanguageHighlighterColors.BLOCK_COMMENT);
		ourMap.put(JspTokens.STRING_LITERAL, DefaultLanguageHighlighterColors.STRING);
		ourMap.put(JspTokens.BAD_CHARACTER, HighlighterColors.BAD_CHARACTER);
	}

	@NotNull
	@Override
	public Lexer getHighlightingLexer()
	{
		return new JspHtmlLexer();
	}

	@NotNull
	@Override
	public TextAttributesKey[] getTokenHighlights(IElementType elementType)
	{
		return pack(ourMap.get(elementType));
	}
}
