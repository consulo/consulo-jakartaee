package consulo.jsp.impl.language.psi.java.psi;

import com.intellij.java.language.JavaLanguage;
import com.intellij.java.language.impl.psi.impl.light.LightClassReference;
import com.intellij.java.language.impl.psi.impl.light.LightPackageReference;
import com.intellij.java.language.psi.JavaElementVisitor;
import com.intellij.java.language.psi.JavaPsiFacade;
import com.intellij.java.language.psi.PsiJavaCodeReferenceElement;
import consulo.annotation.access.RequiredReadAction;
import consulo.document.util.TextRange;
import consulo.jsp.language.psi.java.JspxImportStatement;
import consulo.language.impl.psi.LightElement;
import consulo.language.impl.psi.PsiErrorElementImpl;
import consulo.language.psi.PsiElement;
import consulo.language.psi.PsiElementVisitor;
import consulo.language.psi.PsiErrorElement;
import consulo.language.psi.PsiFile;
import consulo.localize.LocalizeValue;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

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
			myErrorElement = new PsiErrorElementImpl(LocalizeValue.of(imporText));
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
