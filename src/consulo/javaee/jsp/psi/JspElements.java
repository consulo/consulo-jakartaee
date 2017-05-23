package consulo.javaee.jsp.psi;

import com.intellij.psi.tree.IElementType;
import consulo.javaee.jsp.JspLanguage;
import consulo.javaee.jsp.psi.impl.JspDirectiveImpl;
import consulo.javaee.jsp.psi.impl.JspExpressionImpl;
import consulo.javaee.jsp.psi.impl.JspFragmentImpl;
import consulo.javaee.jsp.psi.impl.JspLineFragmentImpl;
import consulo.javaee.jsp.psi.impl.JspXmlRootTagImpl;
import consulo.psi.tree.ElementTypeAsPsiFactory;

/**
 * @author VISTALL
 * @since 15.11.13.
 */
public interface JspElements
{
	IElementType DIRECTIVE = new ElementTypeAsPsiFactory("DIRECTIVE", JspLanguage.INSTANCE, JspDirectiveImpl.class);

	IElementType JSP_ROOT_TAG = new ElementTypeAsPsiFactory("XML_ROOT_TAG", JspLanguage.INSTANCE, JspXmlRootTagImpl.class);

	IElementType FRAGMENT = new ElementTypeAsPsiFactory("FRAGMENT", JspLanguage.INSTANCE, JspFragmentImpl.class);

	IElementType LINE_FRAGMENT = new ElementTypeAsPsiFactory("LINE_FRAGMENT", JspLanguage.INSTANCE, JspLineFragmentImpl.class);

	IElementType EXPRESSION = new ElementTypeAsPsiFactory("EXPRESSION", JspLanguage.INSTANCE, JspExpressionImpl.class);
}
