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

package consulo.javaee.jsp.lexer;

import com.intellij.lexer.MergingLexerAdapter;
import com.intellij.psi.jsp.JspTokenType;
import com.intellij.psi.tree.TokenSet;

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
