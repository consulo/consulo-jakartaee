package org.mustbe.consulo.java.web.jsp.psi.impl;

import org.jetbrains.annotations.NotNull;
import org.mustbe.consulo.java.web.jsp.psi.JspFragment;
import com.intellij.lang.ASTNode;

/**
 * @author VISTALL
 * @since 16.11.13.
 */
public class JspFragmentImpl extends JspElementImpl implements JspFragment
{
	public JspFragmentImpl(@NotNull ASTNode node)
	{
		super(node);
	}

	@Override
	public void accept(@NotNull JspElementVisitor visitor)
	{
		visitor.visitFragment(this);
	}
}
