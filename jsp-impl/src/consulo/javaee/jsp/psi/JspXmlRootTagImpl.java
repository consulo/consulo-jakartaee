package consulo.javaee.jsp.psi;

import org.jetbrains.annotations.NotNull;
import com.intellij.psi.impl.source.jsp.jspXml.JspXmlRootTag;
import consulo.annotations.RequiredReadAction;
import consulo.javaee.jsp.psi.impl.JspElementVisitor;
import consulo.javaee.jsp.psi.impl.JspXmlTagBaseImpl;

/**
 * @author VISTALL
 * @since 24-May-17
 */
public class JspXmlRootTagImpl extends JspXmlTagBaseImpl implements JspXmlRootTag
{
	public JspXmlRootTagImpl()
	{
		super(JspElements.JSP_ROOT_TAG);
	}

	@NotNull
	@RequiredReadAction
	@Override
	public String getName()
	{
		return "jsp:root";
	}

	@Override
	public void accept(@NotNull JspElementVisitor visitor)
	{
		visitor.visitElement(this);
	}
}
