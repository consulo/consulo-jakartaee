package consulo.javaee.jsp.psi.impl;

import org.jetbrains.annotations.NotNull;
import com.intellij.psi.impl.source.jsp.jspXml.JspDeclaration;
import consulo.javaee.jsp.psi.JspElements;

/**
 * @author VISTALL
 * @since 16.11.13.
 */
public class JspDeclarationImpl extends JspXmlTagBaseImpl implements JspDeclaration
{
	public JspDeclarationImpl()
	{
		super(JspElements.DECLARATION);
	}

	@Override
	public void accept(@NotNull JspElementVisitor visitor)
	{
		visitor.visitDeclaration(this);
	}
}
