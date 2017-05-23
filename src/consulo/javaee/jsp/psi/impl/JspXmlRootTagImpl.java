package consulo.javaee.jsp.psi.impl;

import org.jetbrains.annotations.NotNull;
import com.intellij.lang.ASTNode;
import com.intellij.psi.impl.source.jsp.jspXml.JspXmlRootTag;

/**
 * @author VISTALL
 * @since 24-May-17
 */
public class JspXmlRootTagImpl extends JspXmlTagBaseImpl implements JspXmlRootTag
{
	public JspXmlRootTagImpl(@NotNull ASTNode node)
	{
		super(node);
	}

	@Override
	public void accept(@NotNull JspElementVisitor visitor)
	{

	}
}