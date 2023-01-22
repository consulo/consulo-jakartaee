package consulo.jsp.impl.language.psi.java;

import com.intellij.java.language.JavaLanguage;
import consulo.jsp.impl.language.psi.java.psi.*;
import consulo.jsp.language.JspLanguage;
import consulo.language.ast.ElementTypeAsPsiFactory;
import consulo.language.ast.IElementType;

/**
 * @author VISTALL
 * @since 24-May-17
 */
public interface JspJavaElements
{
	IElementType JSP_CLASS = JspJavaStubElements.JSP_CLASS;
	IElementType JSP_HOLDER_METHOD = new ElementTypeAsPsiFactory("JSP_HOLDER_METHOD", JspLanguage.INSTANCE, JspHolderMethodImpl::new);
	IElementType JSP_CODE_BLOCK = new ElementTypeAsPsiFactory("JSP_CODE_BLOCK", JavaLanguage.INSTANCE, JspCodeBlockImpl::new);

	IElementType JSP_TEMPLATE_STATEMENT = new ElementTypeAsPsiFactory("JSP_TEMPLATE_STATEMENT", JavaLanguage.INSTANCE, JspTemplateStatementImpl::new);
	IElementType JSP_CLASS_LEVEL_DECLARATION_STATEMENT = new ElementTypeAsPsiFactory("JSP_CLASS_LEVEL_DECLARATION_STATEMENT", JspLanguage.INSTANCE, JspClassLevelDeclarationStatementImpl::new);
	IElementType JSP_EXRESSION_STATEMENT = new ElementTypeAsPsiFactory("JSP_EXRESSION_STATEMENT", JavaLanguage.INSTANCE, JspExpressionStatementImpl::new);
}
