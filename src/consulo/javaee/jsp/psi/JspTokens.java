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

package consulo.javaee.jsp.psi;

import com.intellij.psi.TokenType;
import com.intellij.psi.tree.IElementType;
import consulo.javaee.jsp.JspLanguage;

/**
 * @author VISTALL
 * @since 08.11.13.
 */
public interface JspTokens extends TokenType
{
	IElementType HTML_TEXT = new IElementType("HTML_TEXT", JspLanguage.INSTANCE);
	IElementType JAVA_TEXT = new IElementType("JAVA_TEXT", JspLanguage.INSTANCE);

	IElementType STRING_LITERAL = new IElementType("STRING_LITERAL", JspLanguage.INSTANCE);

	IElementType EQ = new IElementType("EQ", JspLanguage.INSTANCE);

	IElementType IDENTIFIER = new IElementType("IDENTIFIER", JspLanguage.INSTANCE);

	IElementType COMMENT = new IElementType("COMMENT", JspLanguage.INSTANCE);

	IElementType DIRECTIVE_OPEN = new IElementType("DIRECTIVE_OPEN", JspLanguage.INSTANCE); // <%@
	IElementType DIRECTIVE_CLOSE = new IElementType("DIRECTIVE_CLOSE", JspLanguage.INSTANCE); // %>

	IElementType FRAGMENT_OPEN = new IElementType("FRAGMENT_OPEN", JspLanguage.INSTANCE); // <%
	IElementType FRAGMENT_CLOSE = new IElementType("FRAGMENT_CLOSE", JspLanguage.INSTANCE); // %>

	IElementType LINE_FRAGMENT_OPEN = new IElementType("LINE_FRAGMENT_OPEN", JspLanguage.INSTANCE); // <%=
	IElementType LINE_FRAGMENT_CLOSE = new IElementType("FRAGMENT_CLOSE", JspLanguage.INSTANCE); // %>

	IElementType EXPRESSION_OPEN = new IElementType("LINE_FRAGMENT_OPEN", JspLanguage.INSTANCE); // ${
	IElementType EXPRESSION_CLOSE = new IElementType("FRAGMENT_CLOSE", JspLanguage.INSTANCE); //  }
}
