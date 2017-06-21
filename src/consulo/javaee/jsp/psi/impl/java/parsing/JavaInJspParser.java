package consulo.javaee.jsp.psi.impl.java.parsing;

import org.jetbrains.annotations.NotNull;
import com.intellij.codeInsight.daemon.JavaErrorMessages;
import com.intellij.lang.ASTNode;
import com.intellij.lang.PsiBuilder;
import com.intellij.lang.PsiParser;
import com.intellij.lang.java.parser.DeclarationParser;
import com.intellij.lang.java.parser.JavaParser;
import com.intellij.lang.java.parser.StatementParser;
import com.intellij.psi.JavaTokenType;
import com.intellij.psi.jsp.JspTokenType;
import com.intellij.psi.tree.IElementType;
import consulo.javaee.jsp.psi.impl.java.JspJavaElements;
import consulo.lang.LanguageVersion;

/**
 * @author VISTALL
 * @since 24-May-17
 */
public class JavaInJspParser implements PsiParser
{
	@NotNull
	@Override
	public ASTNode parse(@NotNull IElementType root, @NotNull PsiBuilder builder, @NotNull LanguageVersion languageVersion)
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

				marker.done(JspJavaElements.JSP_CLASS_DECLARATION_STATEMENT);

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

	private void doneError(@NotNull PsiBuilder builder)
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
				error.error(JavaErrorMessages.message("else.without.if"));
			}
			else if(tokenType == JavaTokenType.CATCH_KEYWORD)
			{
				error.error(JavaErrorMessages.message("catch.without.try"));
			}
			else if(tokenType == JavaTokenType.FINALLY_KEYWORD)
			{
				error.error(JavaErrorMessages.message("finally.without.try"));
			}
			else
			{
				error.error(JavaErrorMessages.message("unexpected.token"));
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
