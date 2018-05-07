package consulo.javaee.jsp.psi.impl;

import javax.annotation.Nonnull;

import com.intellij.psi.impl.source.jsp.jspXml.JspExpression;
import consulo.javaee.jsp.psi.JspElements;

/**
 * @author VISTALL
 * @since 16.11.13.
 */
public class JspExpressionImpl extends JspXmlTagBaseImpl implements JspExpression
{
	public JspExpressionImpl()
	{
		super(JspElements.EXPRESSION);
	}

	@Override
	public void accept(@Nonnull JspElementVisitor visitor)
	{
		visitor.visitExpression(this);
	}
}
