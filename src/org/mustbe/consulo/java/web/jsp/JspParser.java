package org.mustbe.consulo.java.web.jsp;

import org.jetbrains.annotations.NotNull;
import org.mustbe.consulo.java.web.jsp.psi.JspElements;
import org.mustbe.consulo.java.web.jsp.psi.JspTokens;
import com.intellij.lang.ASTNode;
import com.intellij.lang.LanguageVersion;
import com.intellij.lang.PsiBuilder;
import com.intellij.lang.PsiBuilderUtil;
import com.intellij.lang.PsiParser;
import com.intellij.psi.tree.IElementType;

/**
 * @author VISTALL
 * @since 16.11.13.
 */
public class JspParser implements PsiParser
{
	@NotNull
	@Override
	public ASTNode parse(@NotNull IElementType elementType, @NotNull PsiBuilder builder, @NotNull LanguageVersion languageVersion)
	{
		PsiBuilder.Marker marker = builder.mark();
		while(!builder.eof())
		{
			if(builder.getTokenType() == JspTokens.DIRECTIVE_OPEN)
			{
				parseDirective(builder);
			}
			else if(builder.getTokenType() == JspTokens.FRAGMENT_OPEN)
			{
				PsiBuilder.Marker mark = builder.mark();

				builder.advanceLexer();

				while(!builder.eof() && builder.getTokenType() != JspTokens.FRAGMENT_CLOSE)
				{
					builder.advanceLexer();
				}

				if(builder.getTokenType() == JspTokens.FRAGMENT_CLOSE)
				{
					builder.advanceLexer();
					mark.done(JspElements.FRAGMENT);
				}
				else
				{
					mark.error("%> expected");
				}
			}
			else if(builder.getTokenType() == JspTokens.LINE_FRAGMENT_OPEN)
			{
				PsiBuilder.Marker mark = builder.mark();

				builder.advanceLexer();

				while(!builder.eof() && builder.getTokenType() != JspTokens.LINE_FRAGMENT_CLOSE)
				{
					builder.advanceLexer();
				}

				if(builder.getTokenType() == JspTokens.LINE_FRAGMENT_CLOSE)
				{
					builder.advanceLexer();
					mark.done(JspElements.LINE_FRAGMENT);
				}
				else
				{
					mark.error("%> expected");
				}
			}
			else if(builder.getTokenType() == JspTokens.EXPRESSION_OPEN)
			{
				PsiBuilder.Marker mark = builder.mark();

				builder.advanceLexer();

				while(!builder.eof() && builder.getTokenType() != JspTokens.EXPRESSION_CLOSE)
				{
					builder.advanceLexer();
				}

				if(builder.getTokenType() == JspTokens.EXPRESSION_CLOSE)
				{
					builder.advanceLexer();
					mark.done(JspElements.EXPRESSION);
				}
				else
				{
					mark.error("} expected");
				}
			}
			else
			{
				builder.advanceLexer();
			}
		}
		marker.done(elementType);
		return builder.getTreeBuilt();
	}

	private void parseDirective(PsiBuilder builder)
	{
		PsiBuilder.Marker mark = builder.mark();

		builder.advanceLexer();

		if(!PsiBuilderUtil.expect(builder, JspTokens.IDENTIFIER))
		{
			builder.error("Identifier expected");
		}
		else
		{
			while(true)
			{
				if(builder.getTokenType() == JspTokens.IDENTIFIER)
				{
					PsiBuilder.Marker attMark = builder.mark();
					builder.advanceLexer();

					if(PsiBuilderUtil.expect(builder, JspTokens.EQ))
					{
						if(!PsiBuilderUtil.expect(builder, JspTokens.STRING_LITERAL))
						{
							builder.error("Value expected");
						}
					}
					else
					{
						builder.error("'=' expected");
					}

					attMark.done(JspElements.ATTRIBUTE);
				}
				else if(builder.getTokenType() == JspTokens.DIRECTIVE_CLOSE)
				{
					break;
				}
				else
				{
					builder.error("Identifier expected");
					break;
				}
			}
		}

		if(PsiBuilderUtil.expect(builder, JspTokens.DIRECTIVE_CLOSE))
		{
			mark.done(JspElements.DIRECTIVE);
		}
		else
		{
			mark.error("%> expected");
		}
	}
}
