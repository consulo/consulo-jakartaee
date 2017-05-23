package consulo.javaee.jsp.psi.impl;

import org.jetbrains.annotations.NotNull;
import com.intellij.lang.ASTNode;
import com.intellij.psi.impl.source.jsp.jspXml.JspDeclaration;

/**
 * @author VISTALL
 * @since 16.11.13.
 */
public class JspDeclarationImpl extends JspXmlTagBaseImpl implements JspDeclaration
{
	public JspDeclarationImpl(@NotNull ASTNode node)
	{
		super(node);
	}

	@Override
	public void accept(@NotNull JspElementVisitor visitor)
	{
		visitor.visitDeclaration(this);
	}
}
