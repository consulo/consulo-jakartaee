package consulo.jsp.impl.language.psi;

import consulo.jsp.language.psi.xml.JspDirective;

import jakarta.annotation.Nonnull;

/**
 * @author VISTALL
 * @since 15.11.13.
 */
public class JspDirectiveImpl extends JspXmlTagBaseImpl implements JspDirective
{
	public JspDirectiveImpl()
	{
		super(JspElements.DIRECTIVE);
	}

	@Override
	public boolean isEmpty()
	{
		return true;
	}

	@Override
	public void accept(@Nonnull JspElementVisitor visitor)
	{
		visitor.visitDirective(this);
	}
}
