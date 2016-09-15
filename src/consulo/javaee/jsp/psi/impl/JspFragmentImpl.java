package consulo.javaee.jsp.psi.impl;

import org.jetbrains.annotations.NotNull;
import com.intellij.lang.ASTNode;
import consulo.javaee.jsp.psi.JspFragment;

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
