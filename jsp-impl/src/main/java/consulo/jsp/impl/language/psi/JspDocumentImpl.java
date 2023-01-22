package consulo.jsp.impl.language.psi;

import consulo.xml.psi.impl.source.xml.XmlDocumentImpl;
import consulo.xml.psi.xml.XmlTag;

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
