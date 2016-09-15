package consulo.javaee.jsp.psi.impl;

import org.jetbrains.annotations.NotNull;
import com.intellij.lang.ASTNode;
import consulo.javaee.jsp.psi.JspExpression;

/**
 * @author VISTALL
 * @since 16.11.13.
 */
public class JspExpressionImpl extends JspElementImpl implements JspExpression
{
	public JspExpressionImpl(@NotNull ASTNode node)
	{
		super(node);
	}

	@Override
	public void accept(@NotNull JspElementVisitor visitor)
	{
		visitor.visitExpression(this);
	}
}
