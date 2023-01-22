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

import consulo.jsp.language.ast.JspTokenType;
import consulo.codeEditor.DefaultLanguageHighlighterColors;
import consulo.codeEditor.HighlighterColors;
import consulo.colorScheme.TextAttributesKey;
import consulo.jsp.impl.language.lexer.JspHighlightLexer;
import consulo.jsp.impl.language.psi.JspTokens;
import consulo.language.ast.IElementType;
import consulo.language.editor.highlight.SyntaxHighlighterBase;
import consulo.language.lexer.Lexer;
import consulo.xml.editor.XmlHighlighterColors;
import consulo.xml.psi.xml.XmlTokenType;

import javax.annotation.Nonnull;
import java.util.HashMap;
import java.util.Map;

/**
 * @author VISTALL
 * @since 08.11.13.
 */
public class JspSyntaxHighlighter extends SyntaxHighlighterBase
{
	private static final Map<IElementType, TextAttributesKey> ourMap1 = new HashMap<>();

	static
	{
		ourMap1.put(JspTokenType.JSP_COMMENT, DefaultLanguageHighlighterColors.BLOCK_COMMENT);
		ourMap1.put(JspTokens.BAD_CHARACTER, HighlighterColors.BAD_CHARACTER);

		ourMap1.put(JspTokenType.JSP_DIRECTIVE_START, DefaultLanguageHighlighterColors.KEYWORD);
		ourMap1.put(JspTokenType.JSP_DIRECTIVE_END, DefaultLanguageHighlighterColors.KEYWORD);
		ourMap1.put(JspTokenType.JSP_EXPRESSION_START, DefaultLanguageHighlighterColors.KEYWORD);
		ourMap1.put(JspTokenType.JSP_EXPRESSION_END, DefaultLanguageHighlighterColors.KEYWORD);
		ourMap1.put(JspTokenType.JSP_DECLARATION_START, DefaultLanguageHighlighterColors.KEYWORD);
		ourMap1.put(JspTokenType.JSP_DECLARATION_END, DefaultLanguageHighlighterColors.KEYWORD);
		ourMap1.put(JspTokenType.JSP_SCRIPTLET_START, DefaultLanguageHighlighterColors.KEYWORD);
		ourMap1.put(JspTokenType.JSP_SCRIPTLET_END, DefaultLanguageHighlighterColors.KEYWORD);
		ourMap1.put(XmlTokenType.XML_ATTRIBUTE_VALUE_TOKEN, XmlHighlighterColors.XML_ATTRIBUTE_VALUE);
		ourMap1.put(XmlTokenType.XML_ATTRIBUTE_VALUE_START_DELIMITER, XmlHighlighterColors.XML_ATTRIBUTE_VALUE);
		ourMap1.put(XmlTokenType.XML_ATTRIBUTE_VALUE_END_DELIMITER, XmlHighlighterColors.XML_ATTRIBUTE_VALUE);

		ourMap1.put(XmlTokenType.XML_TAG_NAME, XmlHighlighterColors.XML_TAG_NAME);

		ourMap1.put(XmlTokenType.XML_NAME, XmlHighlighterColors.XML_ATTRIBUTE_NAME);
	}

	@Nonnull
	@Override
	public Lexer getHighlightingLexer()
	{
		return new JspHighlightLexer();
	}

	@Nonnull
	@Override
	public TextAttributesKey[] getTokenHighlights(IElementType elementType)
	{
		return pack(DefaultLanguageHighlighterColors.TEMPLATE_LANGUAGE_COLOR, ourMap1.get(elementType));
	}
}
