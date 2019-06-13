package consulo.javaee.jsp.psi.impl.java.psi.stub.elementType;

import com.intellij.lang.ASTNode;
import com.intellij.lang.LighterAST;
import com.intellij.lang.LighterASTNode;
import com.intellij.psi.PsiClass;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.psi.impl.java.stubs.JavaClassElementType;
import com.intellij.psi.impl.java.stubs.PsiClassStub;
import com.intellij.psi.impl.java.stubs.PsiJavaFileStub;
import com.intellij.psi.impl.java.stubs.impl.PsiClassStubImpl;
import com.intellij.psi.impl.source.tree.java.ClassElement;
import com.intellij.psi.stubs.PsiFileStub;
import com.intellij.psi.stubs.StubElement;
import com.intellij.psi.stubs.StubInputStream;
import com.intellij.util.io.StringRef;
import consulo.java.psi.impl.java.stub.PsiClassLevelDeclarationStatementStub;
import consulo.javaee.jsp.psi.impl.java.psi.JspClassImpl;
import consulo.psi.tree.IElementTypeAsPsiFactory;

import javax.annotation.Nonnull;
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
				final String pkg = ((PsiJavaFileStub) parentStub).getPackageName();
				if(!pkg.isEmpty())
				{
					qualifiedName = pkg + '.' + name;
				}
				else
				{
					qualifiedName = name;
				}
			}
			else if(parentStub instanceof PsiClassStub)
			{
				final String parentFqn = ((PsiClassStub) parentStub).getQualifiedName();
				qualifiedName = parentFqn != null ? parentFqn + '.' + name : null;
			}
			else if(parentStub instanceof PsiClassLevelDeclarationStatementStub)
			{
				StubElement parent = parentStub;
				while(parent != null)
				{
					if(parent instanceof PsiClassStub)
					{
						qualifiedName = ((PsiClassStub) parent).getQualifiedName();
						break;
					}
					parent = parent.getParentStub();
				}
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
