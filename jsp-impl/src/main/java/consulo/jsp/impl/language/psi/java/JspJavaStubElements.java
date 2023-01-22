package consulo.jsp.impl.language.psi.java;

import consulo.jsp.impl.language.psi.java.psi.stub.elementType.JspClassStubElementType;
import consulo.jsp.impl.language.psi.java.psi.stub.elementType.JspJavaFileElementType;
import consulo.language.ast.IElementType;

/**
 * @author VISTALL
 * @since 21-Jun-17
 */
public interface JspJavaStubElements
{
	IElementType JAVA_IN_JSP_FILE = new JspJavaFileElementType();

	JspClassStubElementType JSP_CLASS = new JspClassStubElementType();
}
