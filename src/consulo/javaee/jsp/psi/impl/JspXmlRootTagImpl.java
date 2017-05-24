package consulo.javaee.jsp.psi.impl;

import org.jetbrains.annotations.NotNull;
import com.intellij.psi.impl.source.jsp.jspXml.JspXmlRootTag;
import consulo.annotations.RequiredReadAction;
import consulo.javaee.jsp.psi.JspElements;

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
