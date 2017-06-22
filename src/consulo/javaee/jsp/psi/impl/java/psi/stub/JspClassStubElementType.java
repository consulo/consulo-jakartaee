package consulo.javaee.jsp.psi.impl.java.psi.stub;

import java.io.IOException;

import org.jetbrains.annotations.NotNull;
import com.intellij.lang.ASTNode;
import com.intellij.lang.LighterAST;
import com.intellij.lang.LighterASTNode;
import com.intellij.lang.java.JavaLanguage;
import com.intellij.psi.PsiElement;
import com.intellij.psi.impl.java.stubs.PsiClassStub;
import com.intellij.psi.impl.source.jsp.jspJava.JspClass;
import com.intellij.psi.stubs.ILightStubElementType;
import com.intellij.psi.stubs.IndexSink;
import com.intellij.psi.stubs.StubElement;
import com.intellij.psi.stubs.StubInputStream;
import com.intellij.psi.stubs.StubOutputStream;
import consulo.annotations.RequiredReadAction;
import consulo.javaee.jsp.psi.impl.java.psi.JspClassImpl;
import consulo.psi.tree.IElementTypeAsPsiFactory;

/**
 * @author VISTALL
 * @since 22-Jun-17
 */
public class JspClassStubElementType extends ILightStubElementType<PsiClassStub<JspClass>, JspClass> implements IElementTypeAsPsiFactory
{
	public JspClassStubElementType()
	{
		super("JSP_CLASS", JavaLanguage.INSTANCE);
	}

	@Override
	public JspClass createPsi(@NotNull PsiClassStub<JspClass> stub)
	{
		return new JspClassImpl(stub, this);
	}

	@RequiredReadAction
	@Override
	public PsiClassStub<JspClass> createStub(@NotNull JspClass psi, StubElement parentStub)
	{
		return new JspClassStub(parentStub, this);
	}

	@NotNull
	@Override
	public String getExternalId()
	{
		return "jsp.class";
	}

	@Override
	public void serialize(@NotNull PsiClassStub<JspClass> stub, @NotNull StubOutputStream dataStream) throws IOException
	{
	}

	@NotNull
	@Override
	public PsiClassStub<JspClass> deserialize(@NotNull StubInputStream dataStream, StubElement parentStub) throws IOException
	{
		return new JspClassStub(parentStub, this);
	}

	@Override
	public void indexStub(@NotNull PsiClassStub<JspClass> stub, @NotNull IndexSink sink)
	{
	}

	@NotNull
	@Override
	public PsiElement createElement(@NotNull ASTNode astNode)
	{
		return new JspClassImpl(astNode);
	}

	@Override
	public PsiClassStub<JspClass> createStub(LighterAST tree, LighterASTNode node, StubElement parentStub)
	{
		return new JspClassStub(parentStub, this);
	}
}
