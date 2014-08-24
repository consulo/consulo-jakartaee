package org.mustbe.consulo.java.web.jsp.psi;

import org.mustbe.consulo.java.web.jsp.JspLanguage;
import org.mustbe.consulo.java.web.jsp.psi.impl.JspAttributeImpl;
import org.mustbe.consulo.java.web.jsp.psi.impl.JspDirectiveImpl;
import org.mustbe.consulo.java.web.jsp.psi.impl.JspExpressionImpl;
import org.mustbe.consulo.java.web.jsp.psi.impl.JspFragmentImpl;
import org.mustbe.consulo.java.web.jsp.psi.impl.JspLineFragmentImpl;
import com.intellij.psi.tree.ElementTypeAsPsiFactory;
import com.intellij.psi.tree.IElementType;

/**
 * @author VISTALL
 * @since 15.11.13.
 */
public interface JspElements
{
	IElementType DIRECTIVE = new ElementTypeAsPsiFactory("DIRECTIVE", JspLanguage.INSTANCE, JspDirectiveImpl.class);

	IElementType ATTRIBUTE = new ElementTypeAsPsiFactory("ATTRIBUTE", JspLanguage.INSTANCE, JspAttributeImpl.class);

	IElementType FRAGMENT = new ElementTypeAsPsiFactory("FRAGMENT", JspLanguage.INSTANCE, JspFragmentImpl.class);

	IElementType LINE_FRAGMENT = new ElementTypeAsPsiFactory("LINE_FRAGMENT", JspLanguage.INSTANCE, JspLineFragmentImpl.class);

	IElementType EXPRESSION = new ElementTypeAsPsiFactory("EXPRESSION", JspLanguage.INSTANCE, JspExpressionImpl.class);
}
