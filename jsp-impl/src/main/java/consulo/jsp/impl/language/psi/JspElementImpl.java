package consulo.jsp.impl.language.psi;

import consulo.language.ast.ASTNode;
import consulo.language.impl.psi.ASTWrapperPsiElement;
import consulo.language.psi.PsiElementVisitor;

import javax.annotation.Nonnull;

/**
 * @author VISTALL
 * @since 16.11.13.
 */
public abstract class JspElementImpl extends ASTWrapperPsiElement
{
	public JspElementImpl(@Nonnull ASTNode node)
	{
		super(node);
	}

	@Override
	public void accept(@Nonnull PsiElementVisitor visitor)
	{
		if(visitor instanceof JspElementVisitor)
		{
			accept((JspElementVisitor) visitor);
		}
		else
		{
			visitor.visitElement(this);
		}
	}

	public abstract void accept(@Nonnull JspElementVisitor visitor);
}
