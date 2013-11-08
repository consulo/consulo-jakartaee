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

package org.mustbe.consulo.java.web.jsp.highlight;

import org.jetbrains.annotations.NotNull;
import org.mustbe.consulo.java.web.jsp.lexer.JspLexer;
import com.intellij.lexer.Lexer;
import com.intellij.openapi.editor.colors.TextAttributesKey;
import com.intellij.openapi.fileTypes.SyntaxHighlighterBase;
import com.intellij.psi.tree.IElementType;

/**
 * @author VISTALL
 * @since 08.11.13.
 */
public class JspSyntaxHighlighter extends SyntaxHighlighterBase
{
	@NotNull
	@Override
	public Lexer getHighlightingLexer()
	{
		return new JspLexer();
	}

	@NotNull
	@Override
	public TextAttributesKey[] getTokenHighlights(IElementType elementType)
	{
		return new TextAttributesKey[0];
	}
}
