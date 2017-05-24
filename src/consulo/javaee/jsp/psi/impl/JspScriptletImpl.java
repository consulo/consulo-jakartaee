package consulo.javaee.jsp.psi.impl;

import org.jetbrains.annotations.NotNull;
import com.intellij.psi.impl.source.jsp.jspXml.JspScriptlet;
import consulo.javaee.jsp.psi.JspElements;

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
	public void accept(@NotNull JspElementVisitor visitor)
	{
		visitor.visitScriptlet(this);
	}
}
