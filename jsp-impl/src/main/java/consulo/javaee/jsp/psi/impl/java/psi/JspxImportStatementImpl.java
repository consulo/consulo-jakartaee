package consulo.javaee.jsp.psi.impl.java.psi;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.intellij.lang.java.JavaLanguage;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.JavaElementVisitor;
import com.intellij.psi.JavaPsiFacade;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.PsiErrorElement;
import com.intellij.psi.PsiFile;
import com.intellij.psi.PsiJavaCodeReferenceElement;
import com.intellij.psi.impl.light.LightClassReference;
import com.intellij.psi.impl.light.LightElement;
import com.intellij.psi.impl.light.LightPackageReference;
import com.intellij.psi.impl.source.jsp.jspJava.JspxImportStatement;
import com.intellij.psi.impl.source.tree.PsiErrorElementImpl;
import consulo.annotations.RequiredReadAction;

/**
 * @author VISTALL
 * @since 25-May-17
 */
public class JspxImportStatementImpl extends LightElement implements JspxImportStatement
{
	private final TextRange myRange;
	private final JspJavaFileImpl myJspJavaFile;

	private String myQName;
	private boolean myPackageReference;

	private PsiErrorElement myErrorElement;

	public JspxImportStatementImpl(String imporText, TextRange range, JspJavaFileImpl jspJavaFile, boolean error)
	{
		super(jspJavaFile.getManager(), JavaLanguage.INSTANCE);
		myRange = range;
		myJspJavaFile = jspJavaFile;

		if(error)
		{
			myErrorElement = new PsiErrorElementImpl(imporText);
		}
		else
		{
			myQName = imporText;
			myPackageReference = myQName.endsWith(".*");
			myQName = myQName.replace(".*", "");
		}
	}

	@Override
	public void accept(@Nonnull PsiElementVisitor visitor)
	{
		if(visitor instanceof JavaElementVisitor)
		{
			((JavaElementVisitor) visitor).visitImportStatement(this);
		}
		else
		{
			super.accept(visitor);
		}
	}

	@Override
	public PsiElement getFirstChild()
	{
		return null;
	}

	@RequiredReadAction
	@Nonnull
	@Override
	public PsiElement[] getChildren()
	{
		if(myErrorElement != null)
		{
			return new PsiElement[]{myErrorElement};
		}
		else
		{
			return new PsiElement[]{getImportReference()};
		}
	}

	@Override
	public PsiFile getContainingFile()
	{
		return myJspJavaFile;
	}

	@Nonnull
	@RequiredReadAction
	@Override
	public TextRange getTextRange()
	{
		return myRange;
	}

	@Override
	public PsiFile getDeclarationFile()
	{
		return myJspJavaFile;
	}

	@Nullable
	@Override
	public String getQualifiedName()
	{
		return myQName;
	}

	@Override
	public boolean isOnDemand()
	{
		return myPackageReference;
	}

	@Nullable
	@Override
	public PsiJavaCodeReferenceElement getImportReference()
	{
		if(myErrorElement != null)
		{
			return null;
		}

		if(isOnDemand())
		{
			return new LightPackageReference(getManager(), myQName);
		}
		else
		{
			return new LightClassReference(getManager(), myQName, myQName, getResolveScope());
		}
	}

	@Nullable
	@Override
	public PsiElement resolve()
	{
		if(myErrorElement != null)
		{
			return null;
		}

		if(isOnDemand())
		{
			return JavaPsiFacade.getInstance(myJspJavaFile.getProject()).findPackage(myQName);
		}
		else
		{
			return JavaPsiFacade.getInstance(myJspJavaFile.getProject()).findClass(myQName, getResolveScope());
		}
	}

	@Override
	public boolean isForeignFileImport()
	{
		return false;
	}

	@Override
	public String toString()
	{
		return myQName;
	}
}
