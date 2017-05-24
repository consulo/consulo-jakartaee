package consulo.javaee.jsp.psi;

import com.intellij.psi.tree.IElementType;
import consulo.javaee.jsp.JspLanguage;

/**
 * @author VISTALL
 * @since 15.11.13.
 */
public interface JspElements
{
	IElementType JSP_ROOT_TAG = new IElementType("JSP_ROOT_TAG", JspLanguage.INSTANCE);

	IElementType JSP_DOCUMENT = new IElementType("JSP_DOCUMENT", JspLanguage.INSTANCE);

	IElementType DIRECTIVE = new IElementType("DIRECTIVE", JspLanguage.INSTANCE);

	IElementType DECLARATION = new IElementType("DECLARATION", JspLanguage.INSTANCE);

	IElementType SCRIPTLET = new IElementType("SCRIPTLET", JspLanguage.INSTANCE);

	IElementType EXPRESSION = new IElementType("EXPRESSION", JspLanguage.INSTANCE);
}
