package consulo.jsp.impl.language.psi;

import consulo.jsp.language.psi.xml.JspScriptlet;

import javax.annotation.Nonnull;

/**
 * @author VISTALL
 * @since 16.11.13.
 */
public class JspScriptletImpl extends JspXmlTagBaseImpl implements JspScriptlet
{
	public JspScriptletImpl()
	{
		super(JspElements.SCRIPTLET);
	}

	@Override
	public void accept(@Nonnull JspElementVisitor visitor)
	{
		visitor.visitScriptlet(this);
	}
}
