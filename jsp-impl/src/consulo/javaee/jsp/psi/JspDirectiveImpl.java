package consulo.javaee.jsp.psi;

import org.jetbrains.annotations.NotNull;
import com.intellij.psi.impl.source.jsp.jspXml.JspDirective;
import consulo.javaee.jsp.psi.impl.JspElementVisitor;
import consulo.javaee.jsp.psi.impl.JspXmlTagBaseImpl;

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
	public void accept(@NotNull JspElementVisitor visitor)
	{
		visitor.visitDirective(this);
	}
}
