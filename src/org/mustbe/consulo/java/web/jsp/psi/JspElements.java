package org.mustbe.consulo.java.web.jsp.psi;

import org.mustbe.consulo.java.web.jsp.JspLanguage;
import org.mustbe.consulo.java.web.jsp.psi.impl.JspAttributeImpl;
import org.mustbe.consulo.java.web.jsp.psi.impl.JspDirectiveImpl;
import org.mustbe.consulo.java.web.jsp.psi.impl.JspExpressionImpl;
import org.mustbe.consulo.java.web.jsp.psi.impl.JspFragmentImpl;
import org.mustbe.consulo.java.web.jsp.psi.impl.JspLineFragmentImpl;
import com.intellij.psi.tree.IElementType;
import com.intellij.psi.tree.IElementTypeAsPsiFactory;

/**
 * @author VISTALL
 * @since 15.11.13.
 */
public interface JspElements
{
	IElementType DIRECTIVE = new IElementTypeAsPsiFactory("DIRECTIVE", JspLanguage.INSTANCE, JspDirectiveImpl.class);

	IElementType ATTRIBUTE = new IElementTypeAsPsiFactory("ATTRIBUTE", JspLanguage.INSTANCE, JspAttributeImpl.class);

	IElementType FRAGMENT = new IElementTypeAsPsiFactory("FRAGMENT", JspLanguage.INSTANCE, JspFragmentImpl.class);

	IElementType LINE_FRAGMENT = new IElementTypeAsPsiFactory("LINE_FRAGMENT", JspLanguage.INSTANCE, JspLineFragmentImpl.class);

	IElementType EXPRESSION = new IElementTypeAsPsiFactory("EXPRESSION", JspLanguage.INSTANCE, JspExpressionImpl.class);
}
