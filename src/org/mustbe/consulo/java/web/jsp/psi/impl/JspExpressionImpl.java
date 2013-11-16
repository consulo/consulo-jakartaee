package org.mustbe.consulo.java.web.jsp.psi.impl;

import org.jetbrains.annotations.NotNull;
import org.mustbe.consulo.java.web.jsp.psi.JspExpression;
import com.intellij.lang.ASTNode;

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
