package consulo.javaee.jsp.psi.impl.java.psi;

import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import com.intellij.lang.java.JavaLanguage;
import com.intellij.psi.PsiImportList;
import com.intellij.psi.PsiImportStatement;
import com.intellij.psi.PsiImportStatementBase;
import com.intellij.psi.PsiImportStaticStatement;
import com.intellij.psi.PsiManager;
import com.intellij.psi.impl.light.LightElement;
import com.intellij.psi.impl.source.jsp.jspJava.JspxImportList;

/**
 * @author VISTALL
 * @since 24-May-17
 */
public class JspxImportListImpl extends LightElement implements JspxImportList
{
	public JspxImportListImpl(PsiManager manager)
	{
		super(manager, JavaLanguage.INSTANCE);
	}

	@NotNull
	@Override
	public PsiImportStatement[] getImportStatements()
	{
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
