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
import com.intellij.openapi.editor.XmlHighlighterColors;
import com.intellij.openapi.editor.colors.TextAttributesKey;
import com.intellij.openapi.fileTypes.SyntaxHighlighterBase;
import com.intellij.psi.jsp.JspTokenType;
import com.intellij.psi.tree.IElementType;
import com.intellij.psi.xml.XmlTokenType;
import consulo.javaee.jsp.lexer._JspHighlightLexer;
import consulo.javaee.jsp.psi.JspTokens;

/**
 * @author VISTALL
 * @since 08.11.13.
 */
public class JspSyntaxHighlighter extends SyntaxHighlighterBase
{
	private static final Map<IElementType, TextAttributesKey> ourMap = new HashMap<>();

	static
	{
		ourMap.put(JspTokenType.JSP_COMMENT, DefaultLanguageHighlighterColors.BLOCK_COMMENT);
		ourMap.put(JspTokens.BAD_CHARACTER, HighlighterColors.BAD_CHARACTER);

		ourMap.put(JspTokenType.JSP_DIRECTIVE_START, DefaultLanguageHighlighterColors.KEYWORD);
		ourMap.put(JspTokenType.JSP_DIRECTIVE_END, DefaultLanguageHighlighterColors.KEYWORD);
		ourMap.put(JspTokenType.JSP_EXPRESSION_START, DefaultLanguageHighlighterColors.KEYWORD);
		ourMap.put(JspTokenType.JSP_EXPRESSION_END, DefaultLanguageHighlighterColors.KEYWORD);
		ourMap.put(JspTokenType.JSP_DECLARATION_START, DefaultLanguageHighlighterColors.KEYWORD);
		ourMap.put(JspTokenType.JSP_DECLARATION_END, DefaultLanguageHighlighterColors.KEYWORD);
		ourMap.put(JspTokenType.JSP_SCRIPTLET_START, DefaultLanguageHighlighterColors.KEYWORD);
		ourMap.put(JspTokenType.JSP_SCRIPTLET_END, DefaultLanguageHighlighterColors.KEYWORD);

		ourMap.put(XmlTokenType.XML_ATTRIBUTE_VALUE_TOKEN, XmlHighlighterColors.XML_ATTRIBUTE_VALUE);
		ourMap.put(XmlTokenType.XML_ATTRIBUTE_VALUE_START_DELIMITER, XmlHighlighterColors.XML_ATTRIBUTE_VALUE);
		ourMap.put(XmlTokenType.XML_ATTRIBUTE_VALUE_END_DELIMITER, XmlHighlighterColors.XML_ATTRIBUTE_VALUE);
	}

	@NotNull
	@Override
	public Lexer getHighlightingLexer()
	{
		return new _JspHighlightLexer();
	}

	@NotNull
	@Override
	public TextAttributesKey[] getTokenHighlights(IElementType elementType)
	{
		return pack(ourMap.get(elementType));
	}
}
