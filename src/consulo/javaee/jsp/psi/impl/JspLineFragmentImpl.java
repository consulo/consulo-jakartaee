package consulo.javaee.jsp.psi.impl;

import org.jetbrains.annotations.NotNull;
import com.intellij.lang.ASTNode;
import consulo.javaee.jsp.psi.JspLineFragment;

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
