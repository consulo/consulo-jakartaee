package consulo.javaee.jsp.psi.impl;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import com.intellij.psi.impl.source.tree.CompositeElement;
import com.intellij.psi.tree.IElementType;
import consulo.javaee.jsp.psi.JspElements;
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
		throw new UnsupportedOperationException(type.toString());
	}

	@Override
	public boolean apply(@Nullable IElementType type)
	{
		return type == JspElements.JSP_DOCUMENT;
	}
}
