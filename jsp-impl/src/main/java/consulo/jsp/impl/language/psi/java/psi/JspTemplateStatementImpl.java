package consulo.jsp.impl.language.psi.java.psi;

import consulo.jsp.language.psi.java.JspTemplateStatement;
import consulo.language.ast.ASTNode;
import consulo.language.impl.psi.ASTWrapperPsiElement;

import javax.annotation.Nonnull;

/**
 * @author VISTALL
 * @since 25-May-17
 */
public class JspTemplateStatementImpl extends ASTWrapperPsiElement implements JspTemplateStatement
{
	public JspTemplateStatementImpl(@Nonnull ASTNode node)
	{
		super(node);
	}
}
