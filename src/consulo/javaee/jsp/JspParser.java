package consulo.javaee.jsp;

import org.jetbrains.annotations.NotNull;
import com.intellij.lang.ASTNode;
import com.intellij.lang.PsiBuilder;
import com.intellij.lang.PsiBuilderUtil;
import com.intellij.lang.PsiParser;
import com.intellij.psi.tree.IElementType;
import com.intellij.psi.xml.XmlElementType;
import com.intellij.psi.xml.XmlTokenType;
import consulo.javaee.jsp.psi.JspElements;
import consulo.javaee.jsp.psi.JspTokens;
import consulo.lang.LanguageVersion;

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
		PsiBuilder.Marker rootMarker = builder.mark();
		while(!builder.eof())
		{
			if(builder.getTokenType() == JspTokens.TAG_OPENER)
			{
				parseTag(builder);
			}
			/*else if(builder.getTokenType() == JspTokens.FRAGMENT_OPEN)
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
			}  */
			else
			{
				builder.advanceLexer();
			}
		}
		rootMarker.done(JspElements.JSP_ROOT_TAG);
		marker.done(elementType);
		return builder.getTreeBuilt();
	}

	private void parseTag(PsiBuilder builder)
	{
		PsiBuilder.Marker mark = builder.mark();

		builder.advanceLexer();

		if(!PsiBuilderUtil.expect(builder, JspTokens.TAG_NAME))
		{
			builder.error("Identifier expected");
		}
		else
		{
			while(true)
			{
				if(builder.getTokenType() == XmlTokenType.XML_NAME)
				{
					PsiBuilder.Marker attMark = builder.mark();
					builder.advanceLexer();

					if(PsiBuilderUtil.expect(builder, XmlTokenType.XML_EQ))
					{
						PsiBuilder.Marker attributeValueMarker = builder.mark();
						if(PsiBuilderUtil.expect(builder, XmlTokenType.XML_ATTRIBUTE_VALUE_START_DELIMITER))
						{
							if(!PsiBuilderUtil.expect(builder, XmlTokenType.XML_ATTRIBUTE_VALUE_TOKEN))
							{
								builder.error("Value expected");
							}
							else if(!PsiBuilderUtil.expect(builder, XmlTokenType.XML_ATTRIBUTE_VALUE_END_DELIMITER))
							{
								builder.error("Excted attribute value stopper");
							}

							attributeValueMarker.done(XmlElementType.XML_ATTRIBUTE_VALUE);
						}
						else
						{
							attributeValueMarker.error("Expected attribute start");
						}
					}
					else
					{
						builder.error("'=' expected");
					}

					attMark.done(XmlElementType.XML_ATTRIBUTE);
				}
				else if(builder.getTokenType() == JspTokens.TAG_CLOSER)
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

		if(!PsiBuilderUtil.expect(builder, JspTokens.TAG_CLOSER))
		{
			builder.error("'%>' expected");
		}
		mark.done(JspElements.DIRECTIVE);
	}
}
