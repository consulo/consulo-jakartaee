package consulo.jsp.impl.language.psi;


import consulo.annotation.component.ExtensionImpl;
import consulo.language.ast.IElementType;
import consulo.language.impl.ast.ASTCompositeFactory;
import consulo.language.impl.ast.CompositeElement;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/**
 * @author VISTALL
 * @since 24-May-17
 */
@ExtensionImpl
public class JspCompositeElementFactory implements ASTCompositeFactory
{
	@Nonnull
	@Override
	public CompositeElement createComposite(@Nonnull IElementType type)
	{
		if(type == JspElements.JSP_DOCUMENT)
		{
			return new JspDocumentImpl();
		}
		if(type == JspElements.JSP_ROOT_TAG)
		{
			return new JspXmlRootTagImpl();
		}
		if(type == JspElements.SCRIPTLET)
		{
			return new JspScriptletImpl();
		}
		if(type == JspElements.EXPRESSION)
		{
			return new JspExpressionImpl();
		}
		if(type == JspElements.DECLARATION)
		{
			return new JspDeclarationImpl();
		}
		if(type == JspElements.DIRECTIVE)
		{
			return new JspDirectiveImpl();
		}
		throw new UnsupportedOperationException(type.toString());
	}

	@Override
	public boolean test(@Nullable IElementType type)
	{
		return type == JspElements.JSP_DOCUMENT || type == JspElements.JSP_ROOT_TAG || type == JspElements.SCRIPTLET || type == JspElements.EXPRESSION || type == JspElements.DIRECTIVE || type ==
				JspElements.DECLARATION;
	}
}
