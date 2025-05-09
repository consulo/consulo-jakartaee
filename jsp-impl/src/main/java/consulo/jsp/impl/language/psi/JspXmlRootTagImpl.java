package consulo.jsp.impl.language.psi;

import consulo.jsp.language.psi.xml.JspXmlRootTag;
import consulo.annotation.access.RequiredReadAction;

import jakarta.annotation.Nonnull;

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
