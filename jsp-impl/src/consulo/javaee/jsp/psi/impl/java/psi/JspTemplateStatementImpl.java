package consulo.javaee.jsp.psi.impl.java.psi;

import javax.annotation.Nonnull;
import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.intellij.lang.ASTNode;
import com.intellij.psi.impl.source.jsp.jspJava.JspTemplateStatement;

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
