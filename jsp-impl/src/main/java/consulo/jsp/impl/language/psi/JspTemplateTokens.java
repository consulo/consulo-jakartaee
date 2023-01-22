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

package consulo.jsp.impl.language.psi;

import consulo.jsp.impl.language.lexer.JspHtmlLexer;
import consulo.jsp.language.JspLanguage;
import consulo.language.ast.IElementType;
import consulo.language.impl.psi.template.TemplateDataElementType;
import consulo.language.lexer.Lexer;
import consulo.language.psi.PsiFile;
import consulo.language.template.TemplateLanguageFileViewProvider;
import consulo.xml.lang.html.HTMLLanguage;

/**
 * @author VISTALL
 * @since 08.11.13.
 */
public interface JspTemplateTokens
{
	IElementType HTML_OUTER_ELEMENT_TYPE = new IElementType("HTML_OUTER_ELEMENT_TYPE", JspLanguage.INSTANCE);

	TemplateDataElementType HTML_TEMPLATE_DATA = new TemplateDataElementType("HTML_TEMPLATE_DATA", HTMLLanguage.INSTANCE, JspTokens.HTML_FRAGMENT, HTML_OUTER_ELEMENT_TYPE)
	{
		@Override
		protected Lexer createBaseLexer(PsiFile file, TemplateLanguageFileViewProvider viewProvider)
		{
			return new JspHtmlLexer();
		}
	};
}
