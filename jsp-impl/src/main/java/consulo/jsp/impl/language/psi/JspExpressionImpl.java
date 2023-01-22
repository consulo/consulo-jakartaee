package consulo.jsp.impl.language.psi;

import javax.annotation.Nonnull;

import consulo.jsp.language.psi.xml.JspExpression;

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
