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

package consulo.jsp.impl.language.parser;

import consulo.annotation.component.ExtensionImpl;
import consulo.jsp.impl.language.lexer.JspLexer;
import consulo.jsp.impl.language.psi.JspTokens;
import consulo.jsp.impl.language.psi.JspFileImpl;
import consulo.jsp.impl.language.psi.JspStubElements;
import consulo.jsp.language.JspLanguage;
import consulo.language.Language;
import consulo.language.ast.ASTNode;
import consulo.language.ast.IFileElementType;
import consulo.language.ast.TokenSet;
import consulo.language.file.FileViewProvider;
import consulo.language.impl.psi.ASTWrapperPsiElement;
import consulo.language.lexer.Lexer;
import consulo.language.parser.ParserDefinition;
import consulo.language.parser.PsiParser;
import consulo.language.psi.PsiElement;
import consulo.language.psi.PsiFile;
import consulo.language.version.LanguageVersion;

import javax.annotation.Nonnull;

/**
 * @author VISTALL
 * @since 07.11.13.
 */
@ExtensionImpl
public class JspParserDefinition implements ParserDefinition
{
	public static final IFileElementType FILE_ELEMENT_TYPE = new IFileElementType("JSP_FILE_ELEMENT_TYPE", JspLanguage.INSTANCE);

	@Nonnull
	@Override
	public Language getLanguage()
	{
		return JspLanguage.INSTANCE;
	}

	@Nonnull
	@Override
	public Lexer createLexer(@Nonnull LanguageVersion languageVersion)
	{
		return new JspLexer();
	}

	@Nonnull
	@Override
	public PsiParser createParser(@Nonnull LanguageVersion languageVersion)
	{
		return new JspParser();
	}

	@Nonnull
	@Override
	public IFileElementType getFileNodeType()
	{
		return JspStubElements.JSP_FILE;
	}

	@Nonnull
	@Override
	public TokenSet getWhitespaceTokens(@Nonnull LanguageVersion languageVersion)
	{
		return TokenSet.create(JspTokens.WHITE_SPACE);
	}

	@Nonnull
	@Override
	public TokenSet getCommentTokens(@Nonnull LanguageVersion languageVersion)
	{
		return TokenSet.EMPTY;
	}

	@Nonnull
	@Override
	public TokenSet getStringLiteralElements(@Nonnull LanguageVersion languageVersion)
	{
		return TokenSet.EMPTY;
	}

	@Nonnull
	@Override
	public PsiElement createElement(ASTNode astNode)
	{
		return new ASTWrapperPsiElement(astNode);
	}

	@Override
	public PsiFile createFile(FileViewProvider fileViewProvider)
	{
		return new JspFileImpl(fileViewProvider);
	}

	@Override
	public SpaceRequirements spaceExistanceTypeBetweenTokens(ASTNode astNode, ASTNode astNode2)
	{
		return SpaceRequirements.MAY;
	}
}
