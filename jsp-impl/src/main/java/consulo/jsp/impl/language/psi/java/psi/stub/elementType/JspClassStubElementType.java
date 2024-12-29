package consulo.jsp.impl.language.psi.java.psi.stub.elementType;

import com.intellij.java.language.impl.psi.impl.java.stubs.JavaClassElementType;
import com.intellij.java.language.impl.psi.impl.java.stubs.PsiClassStub;
import com.intellij.java.language.impl.psi.impl.java.stubs.PsiJavaFileStub;
import com.intellij.java.language.impl.psi.impl.java.stubs.impl.PsiClassStubImpl;
import com.intellij.java.language.impl.psi.impl.source.tree.java.ClassElement;
import com.intellij.java.language.psi.PsiClass;
import consulo.index.io.StringRef;
import consulo.jsp.impl.language.psi.java.psi.JspClassImpl;
import consulo.jsp.impl.language.psi.java.psi.JspJavaFileImpl;
import consulo.language.ast.ASTNode;
import consulo.language.ast.IElementTypeAsPsiFactory;
import consulo.language.ast.LighterAST;
import consulo.language.ast.LighterASTNode;
import consulo.language.psi.PsiElement;
import consulo.language.psi.PsiFile;
import consulo.language.psi.stub.PsiFileStub;
import consulo.language.psi.stub.StubElement;
import consulo.language.psi.stub.StubInputStream;
import consulo.util.lang.StringUtil;

import jakarta.annotation.Nonnull;
import java.io.IOException;

/**
 * @author VISTALL
 * @since 22-Jun-17
 */
public class JspClassStubElementType extends JavaClassElementType implements IElementTypeAsPsiFactory
{
	public JspClassStubElementType()
	{
		super("JSP_CLASS");
	}

	@Override
	public PsiClassStub createStub(final LighterAST tree, final LighterASTNode node, final StubElement parentStub)
	{
		String qualifiedName = null;
		String name = null;

		if(parentStub instanceof PsiFileStub)
		{
			PsiElement psi = parentStub.getPsi();
			if(psi instanceof PsiFile)
			{
				name = JspClassImpl.buildName((PsiFile) psi);
			}
		}

		if(name != null)
		{
			if(parentStub instanceof PsiJavaFileStub)
			{
				JspJavaFileImpl file = (JspJavaFileImpl) ((PsiJavaFileStub) parentStub).getPsi();
				qualifiedName = StringUtil.getQualifiedName(file.getPackageName(), name);
			}
		}

		return new PsiClassStubImpl(this, parentStub, qualifiedName, name, null, (byte) 0);
	}

	@Nonnull
	@Override
	public PsiClassStub deserialize(@Nonnull StubInputStream dataStream, StubElement parentStub) throws IOException
	{
		short flags = dataStream.readShort();
		String name = StringRef.toString(dataStream.readName());
		String qname = StringRef.toString(dataStream.readName());
		String sourceFileName = StringRef.toString(dataStream.readName());
		PsiClassStubImpl classStub = new PsiClassStubImpl(this, parentStub, qname, name, null, flags);
		classStub.setSourceFileName(sourceFileName);
		return classStub;
	}

	@Override
	public PsiClass createPsi(@Nonnull PsiClassStub stub)
	{
		return new JspClassImpl(stub, this);
	}

	@Override
	public PsiClass createPsi(@Nonnull ASTNode node)
	{
		return createElement(node);
	}

	@Nonnull
	@Override
	public PsiClass createElement(@Nonnull ASTNode astNode)
	{
		return new JspClassImpl(astNode);
	}

	@Nonnull
	@Override
	public ASTNode createCompositeNode()
	{
		return new ClassElement(this);
	}
}
