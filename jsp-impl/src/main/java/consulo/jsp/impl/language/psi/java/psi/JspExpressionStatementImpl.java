package consulo.jsp.impl.language.psi.java.psi;

import com.intellij.java.language.psi.JavaElementVisitor;
import com.intellij.java.language.psi.PsiStatement;
import consulo.language.ast.ASTNode;
import consulo.language.impl.psi.ASTWrapperPsiElement;
import consulo.language.psi.PsiElementVisitor;

import jakarta.annotation.Nonnull;

/**
 * @author VISTALL
 * @since 25-May-17
 */
public class JspExpressionStatementImpl extends ASTWrapperPsiElement implements PsiStatement
{
	public JspExpressionStatementImpl(@Nonnull ASTNode node)
	{
		super(node);
	}

	@Override
	public void accept(@Nonnull PsiElementVisitor visitor)
	{
		if(visitor instanceof JavaElementVisitor)
		{
			((JavaElementVisitor) visitor).visitStatement(this);
		}
		else
		{
			visitor.visitElement(this);
		}
	}
}
