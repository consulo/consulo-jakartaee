package consulo.javaee.jsp.psi.impl.java.psi.stub.elementType;

import com.intellij.lang.ASTNode;
import com.intellij.lang.LighterAST;
import com.intellij.lang.LighterASTNode;
import com.intellij.lang.java.JavaLanguage;
import com.intellij.psi.PsiClassLevelDeclarationStatement;
import com.intellij.psi.PsiElement;
import com.intellij.psi.stubs.*;
import consulo.annotation.access.RequiredReadAction;
import consulo.java.psi.impl.java.stub.PsiClassLevelDeclarationStatementStub;
import consulo.javaee.jsp.psi.impl.java.psi.JspClassLevelDeclarationStatementImpl;
import consulo.javaee.jsp.psi.impl.java.psi.stub.JspClassLevelDeclarationStatementStub;
import consulo.psi.tree.IElementTypeAsPsiFactory;

import javax.annotation.Nonnull;
import java.io.IOException;

/**
 * @author VISTALL
 * @since 15-Jul-17
 */
public class JspClassLevelDeclarationStatementElementType extends ILightStubElementType<PsiClassLevelDeclarationStatementStub, PsiClassLevelDeclarationStatement> implements IElementTypeAsPsiFactory
{
	public JspClassLevelDeclarationStatementElementType()
	{
		super("JSP_CLASS_LEVEL_DECLARATION_STATEMENT", JavaLanguage.INSTANCE);
	}

	@Override
	public PsiClassLevelDeclarationStatementStub createStub(LighterAST tree, LighterASTNode node, StubElement parentStub)
	{
		return new JspClassLevelDeclarationStatementStub(parentStub, this);
	}

	@Override
	public PsiClassLevelDeclarationStatement createPsi(@Nonnull PsiClassLevelDeclarationStatementStub stub)
	{
		return new JspClassLevelDeclarationStatementImpl(stub, this);
	}

	@RequiredReadAction
	@Override
	public PsiClassLevelDeclarationStatementStub createStub(@Nonnull PsiClassLevelDeclarationStatement psi, StubElement parentStub)
	{
		return new JspClassLevelDeclarationStatementStub(parentStub, this);
	}

	@Nonnull
	@Override
	public String getExternalId()
	{
		return "jsp.class.level.declaration.statement";
	}

	@Override
	public void serialize(@Nonnull PsiClassLevelDeclarationStatementStub stub, @Nonnull StubOutputStream dataStream) throws IOException
	{
	}

	@Nonnull
	@Override
	public PsiClassLevelDeclarationStatementStub deserialize(@Nonnull StubInputStream dataStream, StubElement parentStub) throws IOException
	{
		return new JspClassLevelDeclarationStatementStub(parentStub, this);
	}

	@Override
	public void indexStub(@Nonnull PsiClassLevelDeclarationStatementStub stub, @Nonnull IndexSink sink)
	{

	}

	@Nonnull
	@Override
	public PsiElement createElement(@Nonnull ASTNode astNode)
	{
		return new JspClassLevelDeclarationStatementImpl(astNode);
	}
}
