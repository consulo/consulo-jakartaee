package consulo.javaee.jsp.psi;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import com.intellij.psi.impl.source.tree.CompositeElement;
import com.intellij.psi.tree.IElementType;
import consulo.javaee.jsp.psi.impl.JspExpressionImpl;
import consulo.psi.tree.ASTCompositeFactory;

/**
 * @author VISTALL
 * @since 24-May-17
 */
public class JspCompositeElementFactory implements ASTCompositeFactory
{
	@NotNull
	@Override
	public CompositeElement createComposite(IElementType type)
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
	public boolean apply(@Nullable IElementType type)
	{
		return type == JspElements.JSP_DOCUMENT || type == JspElements.JSP_ROOT_TAG || type == JspElements.SCRIPTLET || type == JspElements.EXPRESSION || type == JspElements.DIRECTIVE || type ==
				JspElements.DECLARATION;
	}
}
