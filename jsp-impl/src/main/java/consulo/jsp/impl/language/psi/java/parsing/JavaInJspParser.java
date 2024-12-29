package consulo.jsp.impl.language.psi.java.parsing;

import com.intellij.java.language.impl.codeInsight.daemon.JavaErrorBundle;
import com.intellij.java.language.impl.parser.DeclarationParser;
import com.intellij.java.language.impl.parser.JavaParser;
import com.intellij.java.language.impl.parser.StatementParser;
import com.intellij.java.language.psi.JavaTokenType;
import consulo.jsp.impl.language.psi.java.JspJavaElements;
import consulo.jsp.language.ast.JspTokenType;
import consulo.language.ast.ASTNode;
import consulo.language.ast.IElementType;
import consulo.language.parser.PsiBuilder;
import consulo.language.parser.PsiParser;
import consulo.language.version.LanguageVersion;

import jakarta.annotation.Nonnull;

/**
 * @author VISTALL
 * @since 24-May-17
 */
public class JavaInJspParser implements PsiParser
{
	@Nonnull
	@Override
	public ASTNode parse(@Nonnull IElementType root, @Nonnull PsiBuilder builder, @Nonnull LanguageVersion languageVersion)
	{
		PsiBuilder.Marker mark = builder.mark();

		PsiBuilder.Marker classMarker = builder.mark();
		PsiBuilder.Marker methodMarker = builder.mark();

		JavaParser parser = new JavaParser();

		PsiBuilder.Marker bodyMarker = builder.mark();

		while(!builder.eof())
		{
			if(builder.getTokenType() == JspTokenType.JSP_FRAGMENT)
			{
				doneTemplateStatement(builder);
			}
			else if(builder.getTokenType() == JspTokenType.JSP_SCRIPTLET_START)
			{
				doneTemplateStatement(builder);

				parseStatements(builder, parser.getStatementParser());

				if(builder.getTokenType() == JspTokenType.JSP_SCRIPTLET_END)
				{
					doneTemplateStatement(builder);
				}
			}
			else if(builder.getTokenType() == JspTokenType.JSP_EXPRESSION_START)
			{
				doneTemplateStatement(builder);

				PsiBuilder.Marker marker = builder.mark();
				if(parser.getExpressionParser().parse(builder) == null)
				{
					marker.error("Expression expected");
				}
				else
				{
					marker.done(JspJavaElements.JSP_EXRESSION_STATEMENT);
				}

				while(!builder.eof() && builder.getTokenType() != JspTokenType.JSP_EXPRESSION_END)
				{
					doneError(builder);
				}

				if(builder.getTokenType() == JspTokenType.JSP_EXPRESSION_END)
				{
					doneTemplateStatement(builder);
				}
			}
			else if(builder.getTokenType() == JspTokenType.JSP_DECLARATION_START)
			{
				doneTemplateStatement(builder);

				PsiBuilder.Marker marker = builder.mark();

				while(!builder.eof() && builder.getTokenType() != JspTokenType.JSP_DECLARATION_END)
				{
					PsiBuilder.Marker declarationMarker = parser.getDeclarationParser().parse(builder, DeclarationParser.Context.CLASS);
					if(declarationMarker != null && builder.getTokenType() == JavaTokenType.SEMICOLON)
					{
						builder.advanceLexer();
						continue;
					}

					if(declarationMarker == null && builder.getTokenType() != null)
					{
						PsiBuilder.Marker errorMarker = builder.mark();
						builder.advanceLexer();
						errorMarker.error("Unexpected symbol");
					}
				}

				marker.done(JspJavaElements.JSP_CLASS_LEVEL_DECLARATION_STATEMENT);

				if(builder.getTokenType() == JspTokenType.JSP_DECLARATION_END)
				{
					doneTemplateStatement(builder);
				}
			}
			else
			{
				doneError(builder);
			}
		}

		bodyMarker.done(JspJavaElements.JSP_CODE_BLOCK);

		methodMarker.done(JspJavaElements.JSP_HOLDER_METHOD);
		classMarker.done(JspJavaElements.JSP_CLASS);

		mark.done(root);
		return builder.getTreeBuilt();
	}

	private void doneError(@Nonnull PsiBuilder builder)
	{
		PsiBuilder.Marker err = builder.mark();
		builder.advanceLexer();
		err.error("Unexpected token");
	}

	private void parseStatements(final PsiBuilder builder, StatementParser parser)
	{
		while(builder.getTokenType() != JspTokenType.JSP_SCRIPTLET_END && !builder.eof())
		{
			final PsiBuilder.Marker statement = parser.parseStatement(builder);
			if(statement != null)
			{
				continue;
			}

			final IElementType tokenType = builder.getTokenType();

			final PsiBuilder.Marker error = builder.mark();
			builder.advanceLexer();
			if(tokenType == JavaTokenType.ELSE_KEYWORD)
			{
				error.error(JavaErrorBundle.message("else.without.if"));
			}
			else if(tokenType == JavaTokenType.CATCH_KEYWORD)
			{
				error.error(JavaErrorBundle.message("catch.without.try"));
			}
			else if(tokenType == JavaTokenType.FINALLY_KEYWORD)
			{
				error.error(JavaErrorBundle.message("finally.without.try"));
			}
			else
			{
				error.error(JavaErrorBundle.message("unexpected.token"));
			}
		}
	}

	private void doneTemplateStatement(PsiBuilder builder)
	{
		PsiBuilder.Marker mark = builder.mark();
		builder.advanceLexer();
		mark.done(JspJavaElements.JSP_TEMPLATE_STATEMENT);
	}
}
