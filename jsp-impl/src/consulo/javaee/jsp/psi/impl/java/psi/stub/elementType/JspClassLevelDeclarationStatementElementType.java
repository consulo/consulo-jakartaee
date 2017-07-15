package consulo.javaee.jsp.psi.impl.java.psi.stub.elementType;

import java.io.IOException;

import org.jetbrains.annotations.NotNull;
import com.intellij.lang.ASTNode;
import com.intellij.lang.LighterAST;
import com.intellij.lang.LighterASTNode;
import com.intellij.lang.java.JavaLanguage;
import com.intellij.psi.PsiClassLevelDeclarationStatement;
import com.intellij.psi.PsiElement;
import com.intellij.psi.stubs.ILightStubElementType;
import com.intellij.psi.stubs.IndexSink;
import com.intellij.psi.stubs.StubElement;
import com.intellij.psi.stubs.StubInputStream;
import com.intellij.psi.stubs.StubOutputStream;
import consulo.annotations.RequiredReadAction;
import consulo.java.psi.impl.java.stub.PsiClassLevelDeclarationStatementStub;
import consulo.javaee.jsp.psi.impl.java.psi.JspClassLevelDeclarationStatementImpl;
import consulo.javaee.jsp.psi.impl.java.psi.stub.JspClassLevelDeclarationStatementStub;
import consulo.psi.tree.IElementTypeAsPsiFactory;

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
	public PsiClassLevelDeclarationStatement createPsi(@NotNull PsiClassLevelDeclarationStatementStub stub)
	{
		return new JspClassLevelDeclarationStatementImpl(stub, this);
	}

	@RequiredReadAction
	@Override
	public PsiClassLevelDeclarationStatementStub createStub(@NotNull PsiClassLevelDeclarationStatement psi, StubElement parentStub)
	{
		return new JspClassLevelDeclarationStatementStub(parentStub, this);
	}

	@NotNull
	@Override
	public String getExternalId()
	{
		return "jsp.class.level.declaration.statement";
	}

	@Override
	public void serialize(@NotNull PsiClassLevelDeclarationStatementStub stub, @NotNull StubOutputStream dataStream) throws IOException
	{
	}

	@NotNull
	@Override
	public PsiClassLevelDeclarationStatementStub deserialize(@NotNull StubInputStream dataStream, StubElement parentStub) throws IOException
	{
		return new JspClassLevelDeclarationStatementStub(parentStub, this);
	}

	@Override
	public void indexStub(@NotNull PsiClassLevelDeclarationStatementStub stub, @NotNull IndexSink sink)
	{

	}

	@NotNull
	@Override
	public PsiElement createElement(@NotNull ASTNode astNode)
	{
		return new JspClassLevelDeclarationStatementImpl(astNode);
	}
}
