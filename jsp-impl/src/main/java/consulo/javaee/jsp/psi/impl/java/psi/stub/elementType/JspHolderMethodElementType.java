package consulo.javaee.jsp.psi.impl.java.psi.stub.elementType;

import com.intellij.lang.ASTNode;
import com.intellij.lang.LighterAST;
import com.intellij.lang.LighterASTNode;
import com.intellij.lang.java.JavaLanguage;
import com.intellij.psi.PsiElement;
import com.intellij.psi.stubs.*;
import consulo.annotation.access.RequiredReadAction;
import consulo.javaee.jsp.psi.impl.java.psi.JspHolderMethodImpl;
import consulo.psi.tree.IElementTypeAsPsiFactory;

import javax.annotation.Nonnull;
import java.io.IOException;

/**
 * @author VISTALL
 * @since 2019-06-13
 */
public class JspHolderMethodElementType extends ILightStubElementType<EmptyStub<JspHolderMethodImpl>, JspHolderMethodImpl> implements IElementTypeAsPsiFactory
{
	public JspHolderMethodElementType()
	{
		super("JSP_HOLDER_METHOD", JavaLanguage.INSTANCE);
	}

	@Override
	public JspHolderMethodImpl createPsi(@Nonnull EmptyStub<JspHolderMethodImpl> stub)
	{
		return new JspHolderMethodImpl(stub, this);
	}

	@Nonnull
	@Override
	public PsiElement createElement(@Nonnull ASTNode astNode)
	{
		return new JspHolderMethodImpl(astNode);
	}

	@Nonnull
	@Override
	public String getExternalId()
	{
		return "jsp.holder.method";
	}

	@Override
	public void serialize(@Nonnull EmptyStub<JspHolderMethodImpl> jspHolderMethodEmptyStub, @Nonnull StubOutputStream stubOutputStream) throws IOException
	{
	}

	@RequiredReadAction
	@Override
	public EmptyStub<JspHolderMethodImpl> createStub(@Nonnull JspHolderMethodImpl jspHolderMethod, StubElement stubElement)
	{
		return new EmptyStub<>(stubElement, this);
	}

	@Nonnull
	@Override
	public EmptyStub<JspHolderMethodImpl> deserialize(@Nonnull StubInputStream stubInputStream, StubElement stubElement) throws IOException
	{
		return new EmptyStub<>(stubElement, this);
	}

	@Override
	public void indexStub(@Nonnull EmptyStub<JspHolderMethodImpl> jspHolderMethodEmptyStub, @Nonnull IndexSink indexSink)
	{

	}

	@Override
	public EmptyStub<JspHolderMethodImpl> createStub(LighterAST lighterAST, LighterASTNode lighterASTNode, StubElement stubElement)
	{
		return new EmptyStub<>(stubElement, this);
	}
}
