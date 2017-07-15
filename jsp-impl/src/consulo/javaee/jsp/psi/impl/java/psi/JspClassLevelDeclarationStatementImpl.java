package consulo.javaee.jsp.psi.impl.java.psi;

import org.jetbrains.annotations.NotNull;
import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.impl.source.jsp.jspJava.JspClassLevelDeclarationStatement;
import com.intellij.psi.util.PsiTreeUtil;

/**
 * @author VISTALL
 * @since 25-May-17
 */
public class JspClassLevelDeclarationStatementImpl extends ASTWrapperPsiElement implements JspClassLevelDeclarationStatement
{
	public JspClassLevelDeclarationStatementImpl(@NotNull ASTNode node)
	{
		super(node);
	}

	@Override
	public PsiElement getContext()
	{
		return PsiTreeUtil.getParentOfType(this, JspClassImpl.class);
	}
}
