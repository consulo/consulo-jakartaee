package consulo.jsp.impl.editor.codeInsight.daemon.analysis;

import com.intellij.java.analysis.impl.codeInsight.daemon.impl.analysis.HighlightVisitorImpl;
import com.intellij.java.language.JavaLanguage;
import com.intellij.java.language.psi.PsiResolveHelper;
import consulo.language.editor.rawHighlight.HighlightInfoHolder;
import consulo.language.psi.PsiFile;

import jakarta.annotation.Nonnull;

/**
 * @author VISTALL
 * @since 24-May-17
 */
public class JspJavaHighlightVisitorImpl extends HighlightVisitorImpl
{
	public JspJavaHighlightVisitorImpl(@Nonnull PsiResolveHelper resolveHelper)
	{
		super(resolveHelper);
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
}
