package consulo.javaee.jsp.psi.impl.java.psi;

import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.impl.source.jsp.jspJava.JspClassLevelDeclarationStatement;
import com.intellij.psi.util.PsiTreeUtil;

import javax.annotation.Nonnull;

/**
 * @author VISTALL
 * @since 25-May-17
 */
public class JspClassLevelDeclarationStatementImpl extends ASTWrapperPsiElement implements JspClassLevelDeclarationStatement
{
	public JspClassLevelDeclarationStatementImpl(@Nonnull ASTNode node)
	{
		super(node);
	}

	@Override
	public PsiElement getContext()
	{
		return PsiTreeUtil.getParentOfType(this, JspClassImpl.class);
	}
}
