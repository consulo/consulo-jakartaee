package consulo.javaee.jsp.psi.impl.java;

import com.intellij.lang.java.JavaLanguage;
import com.intellij.psi.tree.IElementType;
import consulo.javaee.jsp.psi.impl.java.psi.JspCodeBlockImpl;
import consulo.javaee.jsp.psi.impl.java.psi.JspExpressionStatementImpl;
import consulo.javaee.jsp.psi.impl.java.psi.JspTemplateStatementImpl;
import consulo.psi.tree.ElementTypeAsPsiFactory;

/**
 * @author VISTALL
 * @since 24-May-17
 */
public interface JspJavaElements
{
	IElementType JSP_CLASS = JspJavaStubElements.JSP_CLASS;
	IElementType JSP_HOLDER_METHOD = JspJavaStubElements.JSP_HOLDER_METHOD;
	IElementType JSP_CODE_BLOCK = new ElementTypeAsPsiFactory("JSP_CODE_BLOCK", JavaLanguage.INSTANCE, JspCodeBlockImpl.class);

	IElementType JSP_TEMPLATE_STATEMENT = new ElementTypeAsPsiFactory("JSP_TEMPLATE_STATEMENT", JavaLanguage.INSTANCE, JspTemplateStatementImpl.class);
	IElementType JSP_CLASS_DECLARATION_STATEMENT = JspJavaStubElements.JSP_CLASS_LEVEL_DECLARATION_STATEMENT;
	IElementType JSP_EXRESSION_STATEMENT = new ElementTypeAsPsiFactory("JSP_EXRESSION_STATEMENT", JavaLanguage.INSTANCE, JspExpressionStatementImpl.class);
}
