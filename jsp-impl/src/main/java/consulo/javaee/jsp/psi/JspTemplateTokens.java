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

import com.intellij.lang.html.HTMLLanguage;
import com.intellij.lexer.Lexer;
import com.intellij.psi.PsiFile;
import com.intellij.psi.templateLanguages.TemplateDataElementType;
import com.intellij.psi.templateLanguages.TemplateLanguageFileViewProvider;
import com.intellij.psi.tree.IElementType;
import consulo.javaee.jsp.JspLanguage;
import consulo.javaee.jsp.lexer.JspHtmlLexer;

/**
 * @author VISTALL
 * @since 08.11.13.
 */
public interface JspTemplateTokens
{
	TemplateDataElementType HTML_TEMPLATE_DATA = new TemplateDataElementType("HTML_TEMPLATE_DATA", HTMLLanguage.INSTANCE, JspTokens.HTML_FRAGMENT, new IElementType("HTML_OUTER_ELEMENT_TYPE",
			JspLanguage.INSTANCE))
	{
		@Override
		protected Lexer createBaseLexer(PsiFile file, TemplateLanguageFileViewProvider viewProvider)
		{
			return new JspHtmlLexer();
		}
	};
}
