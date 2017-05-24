package consulo.javaee.jsp.psi.impl;

import com.intellij.psi.impl.source.xml.XmlDocumentImpl;
import com.intellij.psi.xml.XmlTag;
import consulo.javaee.jsp.psi.JspElements;

/**
 * @author VISTALL
 * @since 24-May-17
 */
public class JspDocumentImpl extends XmlDocumentImpl
{
	public JspDocumentImpl()
	{
		super(JspElements.JSP_DOCUMENT);
	}

	@Override
	public XmlTag getRootTag()
	{
		return (XmlTag) findPsiChildByType(JspElements.JSP_ROOT_TAG);
	}
}
