package consulo.javaee.jsp.psi.impl.java.psi;

import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import com.intellij.lang.java.JavaLanguage;
import com.intellij.psi.FileViewProvider;
import com.intellij.psi.PsiImportList;
import com.intellij.psi.PsiImportStatement;
import com.intellij.psi.PsiImportStatementBase;
import com.intellij.psi.PsiImportStaticStatement;
import com.intellij.psi.impl.light.LightElement;
import com.intellij.psi.impl.source.jsp.jspJava.JspxImportList;
import com.intellij.psi.jsp.JspDirectiveKind;
import com.intellij.psi.jsp.JspFile;
import com.intellij.psi.xml.XmlTag;
import consulo.javaee.jsp.JspLanguage;
import consulo.javaee.jsp.psi.impl.JspJavaFileImpl;

/**
 * @author VISTALL
 * @since 24-May-17
 */
public class JspxImportListImpl extends LightElement implements JspxImportList
{
	private JspJavaFileImpl myJspJavaFile;

	public JspxImportListImpl(JspJavaFileImpl jspJavaFile)
	{
		super(jspJavaFile.getManager(), JavaLanguage.INSTANCE);
		myJspJavaFile = jspJavaFile;
	}

	@NotNull
	@Override
	public PsiImportStatement[] getImportStatements()
	{
		FileViewProvider viewProvider = myJspJavaFile.getContainingFile().getViewProvider();

		JspFile psi = (JspFile) viewProvider.getPsi(JspLanguage.INSTANCE);

		XmlTag[] directiveTags = psi.getDirectiveTags(JspDirectiveKind.PAGE, false);
		return new PsiImportStatement[0];
	}

	@NotNull
	@Override
	public PsiImportStaticStatement[] getImportStaticStatements()
	{
		return new PsiImportStaticStatement[0];
	}

	@NotNull
	@Override
	public PsiImportStatementBase[] getAllImportStatements()
	{
		return new PsiImportStatementBase[0];
	}

	@Nullable
	@Override
	public PsiImportStatement findSingleClassImportStatement(String qName)
	{
		return null;
	}

	@Nullable
	@Override
	public PsiImportStatement findOnDemandImportStatement(@NonNls String packageName)
	{
		return null;
	}

	@Nullable
	@Override
	public PsiImportStatementBase findSingleImportStatement(String name)
	{
		return null;
	}

	@Override
	public boolean isReplaceEquivalent(PsiImportList otherList)
	{
		return false;
	}

	@Override
	public String toString()
	{
		return "JspxImportListImpl";
	}
}
