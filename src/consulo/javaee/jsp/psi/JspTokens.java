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
	IElementType JSP_FRAGMENT = new IElementType("JSP_FRAGMENT", JspLanguage.INSTANCE);
	IElementType JAVA_FRAGMENT = new IElementType("JAVA_FRAGMENT", JspLanguage.INSTANCE);
	IElementType HTML_FRAGMENT = new IElementType("HTML_FRAGMENT", JspLanguage.INSTANCE);

	@Deprecated
	IElementType TAG_CLOSER = new IElementType("TAG_CLOSER", JspLanguage.INSTANCE);
}
