package consulo.jsp.impl.language.psi.java.psi;

import consulo.jsp.language.psi.java.JspClassLevelDeclarationStatement;
import consulo.language.ast.ASTNode;
import consulo.language.impl.psi.ASTWrapperPsiElement;
import consulo.language.psi.PsiElement;
import consulo.language.psi.util.PsiTreeUtil;

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
