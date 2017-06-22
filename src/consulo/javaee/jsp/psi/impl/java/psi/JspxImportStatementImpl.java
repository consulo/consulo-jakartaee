package consulo.javaee.jsp.psi.impl.java.psi;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import com.intellij.lang.java.JavaLanguage;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.JavaElementVisitor;
import com.intellij.psi.JavaPsiFacade;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.PsiFile;
import com.intellij.psi.PsiJavaCodeReferenceElement;
import com.intellij.psi.impl.light.LightClassReference;
import com.intellij.psi.impl.light.LightElement;
import com.intellij.psi.impl.light.LightPackageReference;
import com.intellij.psi.impl.source.jsp.jspJava.JspxImportStatement;
import consulo.annotations.RequiredReadAction;

/**
 * @author VISTALL
 * @since 25-May-17
 */
public class JspxImportStatementImpl extends LightElement implements JspxImportStatement
{
	private boolean myPackageReference;
	private String myQName;
	private TextRange myRange;
	private JspJavaFileImpl myJspJavaFile;

	public JspxImportStatementImpl(String qName, TextRange range, JspJavaFileImpl jspJavaFile)
	{
		super(jspJavaFile.getManager(), JavaLanguage.INSTANCE);
		myQName = qName;
		myRange = range;
		myJspJavaFile = jspJavaFile;

		myPackageReference = myQName.endsWith(".*");
		myQName = myQName.replace(".*", "");
	}

	@Override
	public void accept(@NotNull PsiElementVisitor visitor)
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

	@RequiredReadAction
	@NotNull
	@Override
	public PsiElement[] getChildren()
	{
		return new PsiElement[]{getImportReference()};
	}

	@Override
	public PsiFile getContainingFile()
	{
		return myJspJavaFile;
	}

	@NotNull
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
