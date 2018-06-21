package consulo.javaee.jsp.codeInsight.daemon.analysis;

import javax.annotation.Nonnull;

import com.intellij.codeInsight.daemon.impl.analysis.HighlightInfoHolder;
import com.intellij.codeInsight.daemon.impl.analysis.HighlightVisitorImpl;
import com.intellij.lang.java.JavaLanguage;
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

	public JspJavaHighlightVisitorImpl(@Nonnull PsiResolveHelper resolveHelper)
	{
		super(resolveHelper);
		myResolveHelper = resolveHelper;
	}

	@Override
	public boolean analyze(@Nonnull PsiFile file, boolean updateWholeFile, @Nonnull HighlightInfoHolder holder, @Nonnull Runnable highlight)
	{
		PsiFile javaFile = file.getViewProvider().getPsi(JavaLanguage.INSTANCE);
		if(javaFile == null)
		{
			javaFile = file;
		}

		return super.analyze(javaFile, updateWholeFile, holder, highlight);
	}

	@Nonnull
	@Override
	public HighlightVisitorImpl clone()
	{
		return new JspJavaHighlightVisitorImpl(myResolveHelper);
	}

	@Override
	public boolean suitableForFile(@Nonnull PsiFile file)
	{
		return file instanceof JspFile;
	}
}
