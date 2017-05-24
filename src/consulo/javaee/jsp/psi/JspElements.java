package consulo.javaee.jsp.psi;

import com.intellij.psi.tree.IElementType;
import consulo.javaee.jsp.JspLanguage;
import consulo.javaee.jsp.psi.impl.JspDirectiveImpl;
import consulo.javaee.jsp.psi.impl.JspExpressionImpl;
import consulo.javaee.jsp.psi.impl.JspDeclarationImpl;
import consulo.javaee.jsp.psi.impl.JspScriptletImpl;
import consulo.javaee.jsp.psi.impl.JspXmlRootTagImpl;
import consulo.psi.tree.ElementTypeAsPsiFactory;

/**
 * @author VISTALL
 * @since 15.11.13.
 */
public interface JspElements
{
	IElementType JSP_ROOT_TAG = new ElementTypeAsPsiFactory("JSP_ROOT_TAG", JspLanguage.INSTANCE, JspXmlRootTagImpl.class);

	IElementType JSP_DOCUMENT = new IElementType("JSP_DOCUMENT", JspLanguage.INSTANCE);

	IElementType DIRECTIVE = new ElementTypeAsPsiFactory("DIRECTIVE", JspLanguage.INSTANCE, JspDirectiveImpl.class);

	IElementType DECLARATION = new ElementTypeAsPsiFactory("DECLARATION", JspLanguage.INSTANCE, JspDeclarationImpl.class);

	IElementType SCRIPTLET = new ElementTypeAsPsiFactory("SCRIPTLET", JspLanguage.INSTANCE, JspScriptletImpl.class);

	IElementType EXPRESSION = new ElementTypeAsPsiFactory("EXPRESSION", JspLanguage.INSTANCE, JspExpressionImpl.class);
}
