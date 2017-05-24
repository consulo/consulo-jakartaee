package consulo.javaee.jsp.codeInsigh.daemon.analysis;

import org.jetbrains.annotations.NotNull;
import com.intellij.codeInsight.daemon.impl.analysis.HighlightVisitorImpl;
import com.intellij.psi.PsiFile;
import com.intellij.psi.PsiResolveHelper;
import com.intellij.psi.jsp.JspFile;

/**
 * @author VISTALL
 * @since 24-May-17
 */
public class JspJavaHighlightVisitorImpl extends HighlightVisitorImpl
{
	private PsiResolveHelper myResolveHelper;

	public JspJavaHighlightVisitorImpl(@NotNull PsiResolveHelper resolveHelper)
	{
		super(resolveHelper);
		myResolveHelper = resolveHelper;
	}

	@NotNull
	@Override
	public HighlightVisitorImpl clone()
	{
		return new JspJavaHighlightVisitorImpl(myResolveHelper);
	}

	@Override
	public boolean suitableForFile(@NotNull PsiFile file)
	{
		return file instanceof JspFile;
	}
}
