package consulo.javaee.jsp.psi.impl.java.psi.stub;

import com.intellij.pom.java.LanguageLevel;
import com.intellij.psi.PsiClass;
import com.intellij.psi.PsiJavaFile;
import com.intellij.psi.PsiJavaModule;
import com.intellij.psi.impl.java.stubs.JavaStubElementTypes;
import com.intellij.psi.impl.java.stubs.PsiJavaFileStub;
import com.intellij.psi.impl.java.stubs.SourceStubPsiFactory;
import com.intellij.psi.impl.java.stubs.StubPsiFactory;
import com.intellij.psi.stubs.PsiFileStubImpl;
import com.intellij.psi.tree.IStubFileElementType;
import consulo.javaee.jsp.psi.impl.java.JspJavaStubElements;

import javax.annotation.Nonnull;

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