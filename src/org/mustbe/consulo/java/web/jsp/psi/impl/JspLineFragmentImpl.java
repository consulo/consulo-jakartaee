package org.mustbe.consulo.java.web.jsp.psi.impl;

import org.jetbrains.annotations.NotNull;
import org.mustbe.consulo.java.web.jsp.psi.JspLineFragment;
import com.intellij.lang.ASTNode;

/**
 * @author VISTALL
 * @since 16.11.13.
 */
public class JspLineFragmentImpl extends JspFragmentImpl implements JspLineFragment
{
	public JspLineFragmentImpl(@NotNull ASTNode node)
	{
		super(node);
	}

	@Override
	public void accept(@NotNull JspElementVisitor visitor)
	{
		visitor.visitLineFragment(this);
	}
}
