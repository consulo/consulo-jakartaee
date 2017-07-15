package consulo.javaee.jsp.psi.impl.java.psi;

import org.jetbrains.annotations.NotNull;
import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.intellij.lang.ASTNode;
import com.intellij.psi.JavaElementVisitor;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.PsiStatement;

/**
 * @author VISTALL
 * @since 25-May-17
 */
public class JspExpressionStatementImpl extends ASTWrapperPsiElement implements PsiStatement
{
	public JspExpressionStatementImpl(@NotNull ASTNode node)
	{
		super(node);
	}

	@Override
	public void accept(@NotNull PsiElementVisitor visitor)
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
