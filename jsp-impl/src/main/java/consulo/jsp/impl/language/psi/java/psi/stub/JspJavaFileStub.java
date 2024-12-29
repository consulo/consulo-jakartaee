package consulo.jsp.impl.language.psi.java.psi.stub;

import com.intellij.java.language.LanguageLevel;
import com.intellij.java.language.impl.psi.impl.java.stubs.JavaStubElementTypes;
import com.intellij.java.language.impl.psi.impl.java.stubs.PsiJavaFileStub;
import com.intellij.java.language.impl.psi.impl.java.stubs.SourceStubPsiFactory;
import com.intellij.java.language.impl.psi.impl.java.stubs.StubPsiFactory;
import com.intellij.java.language.psi.PsiClass;
import com.intellij.java.language.psi.PsiJavaFile;
import com.intellij.java.language.psi.PsiJavaModule;
import consulo.jsp.impl.language.psi.java.JspJavaStubElements;
import consulo.language.psi.stub.IStubFileElementType;
import consulo.language.psi.stub.PsiFileStubImpl;

import jakarta.annotation.Nonnull;

/**
 * @author VISTALL
 * @since 15-Jul-17
 */
public class JspJavaFileStub extends PsiFileStubImpl<PsiJavaFile> implements PsiJavaFileStub
{
	private final LanguageLevel myLanguageLevel;

	public JspJavaFileStub(PsiJavaFile file, LanguageLevel languageLevel)
	{
		super(file);
		myLanguageLevel = languageLevel;
	}

	@Nonnull
	@Override
	public IStubFileElementType getType()
	{
		return (IStubFileElementType) JspJavaStubElements.JAVA_IN_JSP_FILE;
	}

	@Nonnull
	@Override
	public PsiClass[] getClasses()
	{
		return getChildrenByType(JavaStubElementTypes.CLASS, PsiClass.ARRAY_FACTORY);
	}

	@Override
	public PsiJavaModule getModule()
	{
		return null;
	}

	@Override
	public String getPackageName()
	{
		return null;
	}

	@Override
	public LanguageLevel getLanguageLevel()
	{
		return myLanguageLevel;
	}

	@Override
	public boolean isCompiled()
	{
		return false;
	}

	@Nonnull
	@Override
	public StubPsiFactory getPsiFactory()
	{
		return SourceStubPsiFactory.INSTANCE;
	}

	@Override
	public String toString()
	{
		return "JspFileStub";
	}
}