package consulo.javaee.jsp.psi.impl.java;

import com.intellij.psi.tree.IElementType;
import consulo.javaee.jsp.psi.impl.java.psi.stub.elementType.JspClassLevelDeclarationStatementElementType;
import consulo.javaee.jsp.psi.impl.java.psi.stub.elementType.JspClassStubElementType;
import consulo.javaee.jsp.psi.impl.java.psi.stub.elementType.JspHolderMethodElementType;
import consulo.javaee.jsp.psi.impl.java.psi.stub.elementType.JspJavaFileElementType;

/**
 * @author VISTALL
 * @since 21-Jun-17
 */
public interface JspJavaStubElements
{
	IElementType JAVA_IN_JSP_FILE = new JspJavaFileElementType();

	JspClassStubElementType JSP_CLASS = new JspClassStubElementType();

	JspHolderMethodElementType JSP_HOLDER_METHOD = new JspHolderMethodElementType();

	JspClassLevelDeclarationStatementElementType JSP_CLASS_LEVEL_DECLARATION_STATEMENT = new JspClassLevelDeclarationStatementElementType();
}
