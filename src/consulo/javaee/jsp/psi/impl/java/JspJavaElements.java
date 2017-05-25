package consulo.javaee.jsp.psi.impl.java;

import com.intellij.lang.java.JavaLanguage;
import com.intellij.psi.tree.IElementType;
import consulo.javaee.jsp.psi.impl.java.psi.JspClassImpl;
import consulo.javaee.jsp.psi.impl.java.psi.JspClassLevelDeclarationStatementImpl;
import consulo.javaee.jsp.psi.impl.java.psi.JspCodeBlockImpl;
import consulo.javaee.jsp.psi.impl.java.psi.JspHolderMethodImpl;
import consulo.javaee.jsp.psi.impl.java.psi.JspTemplateStatementImpl;
import consulo.psi.tree.ElementTypeAsPsiFactory;

/**
 * @author VISTALL
 * @since 24-May-17
 */
public interface JspJavaElements
{
	IElementType JSP_CLASS = new ElementTypeAsPsiFactory("JSP_CLASS", JavaLanguage.INSTANCE, JspClassImpl.class);
	IElementType JSP_HOLDER_METHOD = new ElementTypeAsPsiFactory("JSP_HOLDER_METHOD", JavaLanguage.INSTANCE, JspHolderMethodImpl.class);
	IElementType JSP_CODE_BLOCK = new ElementTypeAsPsiFactory("JSP_CODE_BLOCK", JavaLanguage.INSTANCE, JspCodeBlockImpl.class);

	IElementType JSP_TEMPLATE_STATEMENT = new ElementTypeAsPsiFactory("JSP_TEMPLATE_STATEMENT", JavaLanguage.INSTANCE, JspTemplateStatementImpl.class);
	IElementType JSP_CLASS_DECLARATION_STATEMENT = new ElementTypeAsPsiFactory("JSP_CLASS_DECLARATION_STATEMENT", JavaLanguage.INSTANCE, JspClassLevelDeclarationStatementImpl.class);
}
