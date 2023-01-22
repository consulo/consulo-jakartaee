package consulo.jsp.impl.language.psi;

import javax.annotation.Nonnull;

import consulo.jsp.language.psi.xml.JspDeclaration;

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
