package consulo.javaee.jsp.psi;

import javax.annotation.Nonnull;
import com.intellij.psi.impl.source.jsp.jspXml.JspScriptlet;
import consulo.javaee.jsp.psi.impl.JspElementVisitor;
import consulo.javaee.jsp.psi.impl.JspXmlTagBaseImpl;

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
