package consulo.javaee.jsp.psi.impl.java;

import com.intellij.psi.tree.IElementType;
import consulo.javaee.jsp.JspLanguage;
import consulo.javaee.jsp.psi.impl.java.psi.JspClassLevelDeclarationStatementImpl;
import consulo.javaee.jsp.psi.impl.java.psi.JspHolderMethodImpl;
import consulo.javaee.jsp.psi.impl.java.psi.stub.elementType.JspClassStubElementType;
import consulo.javaee.jsp.psi.impl.java.psi.stub.elementType.JspJavaFileElementType;
import consulo.psi.tree.ElementTypeAsPsiFactory;

/**
 * @author VISTALL
 * @since 21-Jun-17
 */
public interface JspJavaStubElements
{
	IElementType JAVA_IN_JSP_FILE = new JspJavaFileElementType();

	JspClassStubElementType JSP_CLASS = new JspClassStubElementType();

	IElementType JSP_HOLDER_METHOD = new ElementTypeAsPsiFactory("JSP_HOLDER_METHOD", JspLanguage.INSTANCE, JspHolderMethodImpl.class);

	IElementType JSP_CLASS_LEVEL_DECLARATION_STATEMENT = new ElementTypeAsPsiFactory("JSP_CLASS_LEVEL_DECLARATION_STATEMENT", JspLanguage.INSTANCE, JspClassLevelDeclarationStatementImpl.class);
}
