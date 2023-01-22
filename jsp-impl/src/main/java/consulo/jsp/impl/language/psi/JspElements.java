package consulo.jsp.impl.language.psi;

import consulo.jsp.language.JspLanguage;
import consulo.language.ast.IElementType;

/**
 * @author VISTALL
 * @since 15.11.13.
 */
public interface JspElements
{
	IElementType JSP_ROOT_TAG = new IElementType("JSP_ROOT_TAG", JspLanguage.INSTANCE);

	IElementType JSP_DOCUMENT = new IElementType("JSP_DOCUMENT", JspLanguage.INSTANCE);

	IElementType DIRECTIVE = new IElementType("JSP_DIRECTIVE", JspLanguage.INSTANCE);

	IElementType DECLARATION = new IElementType("JSP_DECLARATION", JspLanguage.INSTANCE);

	IElementType COMMENT = new IElementType("JSP_COMMENT", JspLanguage.INSTANCE);

	IElementType SCRIPTLET = new IElementType("JSP_SCRIPTLET", JspLanguage.INSTANCE);

	IElementType EXPRESSION = new IElementType("JSP_EXPRESSION", JspLanguage.INSTANCE);
}
