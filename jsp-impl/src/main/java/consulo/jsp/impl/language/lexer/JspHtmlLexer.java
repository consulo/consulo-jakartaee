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

package consulo.jsp.impl.language.lexer;

import consulo.jsp.language.ast.JspTokenType;
import consulo.language.ast.TokenSet;
import consulo.language.lexer.MergingLexerAdapter;

/**
 * @author VISTALL
 * @since 08.11.13.
 */
public class JspHtmlLexer extends MergingLexerAdapter
{
	private static final TokenSet ourSet = TokenSet.create(JspTokenType.JSP_FRAGMENT);

	public JspHtmlLexer()
	{
		super(new _JspHtmlLexer(), ourSet);
	}
}
