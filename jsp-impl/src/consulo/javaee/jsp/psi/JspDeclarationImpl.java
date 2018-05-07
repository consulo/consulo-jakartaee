package consulo.javaee.jsp.psi;

import javax.annotation.Nonnull;
import com.intellij.psi.impl.source.jsp.jspXml.JspDeclaration;
import consulo.javaee.jsp.psi.impl.JspElementVisitor;
import consulo.javaee.jsp.psi.impl.JspXmlTagBaseImpl;

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
	public void accept(@Nonnull JspElementVisitor visitor)
	{
		visitor.visitDeclaration(this);
	}
}
