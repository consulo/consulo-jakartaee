package consulo.javaee.jsp;

import javax.annotation.Nonnull;

import com.intellij.lang.ASTNode;
import com.intellij.lang.PsiBuilder;
import com.intellij.lang.PsiBuilderUtil;
import com.intellij.lang.PsiParser;
import com.intellij.psi.jsp.JspTokenType;
import com.intellij.psi.tree.IElementType;
import com.intellij.psi.xml.XmlElementType;
import com.intellij.psi.xml.XmlTokenType;
import consulo.javaee.jsp.psi.JspElements;
import consulo.lang.LanguageVersion;

/**
 * @author VISTALL
 * @since 16.11.13.
 */
public class JspParser implements PsiParser
{
	@Nonnull
	@Override
	public ASTNode parse(@Nonnull IElementType elementType, @Nonnull PsiBuilder builder, @Nonnull LanguageVersion languageVersion)
	{
		PsiBuilder.Marker marker = builder.mark();
		PsiBuilder.Marker documentMarker = builder.mark();
		PsiBuilder.Marker rootMarker = builder.mark();
		while(!builder.eof())
		{
			if(builder.getTokenType() == XmlTokenType.XML_START_TAG_START)
			{
				parseDirective(builder);
			}
			else if(builder.getTokenType() == JspTokenType.JSP_DECLARATION_START)
			{
				parseSimpleTag(builder, JspTokenType.JSP_DECLARATION_END, JspElements.DECLARATION);
			}
			else if(builder.getTokenType() == JspTokenType.JSP_EXPRESSION_START)
			{
				parseSimpleTag(builder, JspTokenType.JSP_EXPRESSION_END, JspElements.EXPRESSION);
			}
			else if(builder.getTokenType() == JspTokenType.JSP_SCRIPTLET_START)
			{
				parseSimpleTag(builder, JspTokenType.JSP_SCRIPTLET_END, JspElements.SCRIPTLET);
			}
			else
			{
				builder.advanceLexer();
			}
		}
		rootMarker.done(JspElements.JSP_ROOT_TAG);
		documentMarker.done(JspElements.JSP_DOCUMENT);
		marker.done(elementType);
		return builder.getTreeBuilt();
	}

	private void parseSimpleTag(PsiBuilder builder, IElementType endElementType, IElementType to)
	{
		PsiBuilder.Marker mark = builder.mark();

		builder.advanceLexer();

		while(!builder.eof() && builder.getTokenType() != endElementType)
		{
			builder.advanceLexer();
		}

		if(!PsiBuilderUtil.expect(builder, endElementType))
		{
			builder.error("%> expected");
		}
		mark.done(to);
	}

	private void parseDirective(PsiBuilder builder)
	{
		PsiBuilder.Marker mark = builder.mark();

		builder.advanceLexer();

		if(!PsiBuilderUtil.expect(builder, XmlTokenType.XML_TAG_NAME))
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
							PsiBuilderUtil.expect(builder, XmlTokenType.XML_ATTRIBUTE_VALUE_TOKEN);

							if(!PsiBuilderUtil.expect(builder, XmlTokenType.XML_ATTRIBUTE_VALUE_END_DELIMITER))
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
				else if(builder.getTokenType() == XmlTokenType.XML_EMPTY_ELEMENT_END)
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

		if(!PsiBuilderUtil.expect(builder, XmlTokenType.XML_EMPTY_ELEMENT_END))
		{
			builder.error("'%>' expected");
		}
		mark.done(JspElements.DIRECTIVE);
	}
}
