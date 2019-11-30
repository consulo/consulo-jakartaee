package consulo.javaee.jsp.psi;

import com.intellij.psi.impl.source.jsp.jspXml.JspXmlRootTag;
import consulo.annotation.access.RequiredReadAction;
import consulo.javaee.jsp.psi.impl.JspElementVisitor;
import consulo.javaee.jsp.psi.impl.JspXmlTagBaseImpl;

import javax.annotation.Nonnull;

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

	@Nonnull
	@RequiredReadAction
	@Override
	public String getName()
	{
		return "jsp:root";
	}

	@Override
	public void accept(@Nonnull JspElementVisitor visitor)
	{
		visitor.visitElement(this);
	}
}
