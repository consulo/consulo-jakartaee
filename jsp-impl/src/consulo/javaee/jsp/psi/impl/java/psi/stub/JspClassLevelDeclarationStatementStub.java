package consulo.javaee.jsp.psi.impl.java.psi.stub;

import com.intellij.psi.PsiClassLevelDeclarationStatement;
import com.intellij.psi.stubs.IStubElementType;
import com.intellij.psi.stubs.StubBase;
import com.intellij.psi.stubs.StubElement;
import consulo.java.psi.impl.java.stub.PsiClassLevelDeclarationStatementStub;

/**
 * @author VISTALL
 * @since 15-Jul-17
 */
public class JspClassLevelDeclarationStatementStub extends StubBase<PsiClassLevelDeclarationStatement> implements PsiClassLevelDeclarationStatementStub
{
	public JspClassLevelDeclarationStatementStub(StubElement parent, IStubElementType elementType)
	{
		super(parent, elementType);
	}
}
