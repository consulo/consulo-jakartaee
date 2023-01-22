package consulo.jsp.impl.language.psi.java.psi.stub;

import com.intellij.java.language.psi.PsiClassLevelDeclarationStatement;
import consulo.java.language.impl.psi.stub.PsiClassLevelDeclarationStatementStub;
import consulo.language.psi.stub.IStubElementType;
import consulo.language.psi.stub.StubBase;
import consulo.language.psi.stub.StubElement;

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
