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

package org.mustbe.consulo.java.web.jsp.psi;

import org.mustbe.consulo.java.web.jsp.JspLanguage;
import com.intellij.psi.templateLanguages.TemplateDataElementType;
import com.intellij.psi.tree.IElementType;

/**
 * @author VISTALL
 * @since 08.11.13.
 */
public interface JspTemplateTokens
{
	TemplateDataElementType HTML_TEMPLATE_DATA = new TemplateDataElementType("HTML_TEMPLATE_DATA", JspLanguage.INSTANCE, JspTokens.HTML_TEXT, new IElementType("HTML_OUTER_ELEMENT_TYPE", JspLanguage.INSTANCE));

	TemplateDataElementType JAVA_TEMPLATE_DATA = new TemplateDataElementType("JAVA_TEMPLATE_DATA", JspLanguage.INSTANCE, JspTokens.JAVA_TEXT, new IElementType("JAVA_OUTER_ELEMENT_TYPE", JspLanguage.INSTANCE));
}
