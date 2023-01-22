package consulo.jsp.impl.language.psi;

import consulo.jsp.language.JspLanguage;
import consulo.jsp.impl.language.psi.stub.JspFileElementType;

/**
 * @author VISTALL
 * @since 15-Jul-17
 */
public interface JspStubElements
{
	JspFileElementType JSP_FILE = new JspFileElementType(JspLanguage.INSTANCE);
}
