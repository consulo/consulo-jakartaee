package consulo.javaee.jsp.psi.impl;

import org.jetbrains.annotations.NotNull;
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
	public void accept(@NotNull JspElementVisitor visitor)
	{
		visitor.visitExpression(this);
	}
}
