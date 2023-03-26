package consulo.jsp.impl.editor.codeInsight.daemon.analysis;

import com.intellij.java.language.psi.PsiResolveHelper;
import consulo.annotation.component.ExtensionImpl;
import consulo.jsp.language.psi.JspFile;
import consulo.language.editor.rawHighlight.HighlightVisitor;
import consulo.language.editor.rawHighlight.HighlightVisitorFactory;
import consulo.language.psi.PsiFile;
import jakarta.inject.Inject;

import javax.annotation.Nonnull;

/**
 * @author VISTALL
 * @since 26/03/2023
 */
@ExtensionImpl
public class JspJavaHighlightVisitorFactoryImpl implements HighlightVisitorFactory
{
	private final PsiResolveHelper myResolveHelper;

	@Inject
	public JspJavaHighlightVisitorFactoryImpl(PsiResolveHelper resolveHelper)
	{
		myResolveHelper = resolveHelper;
	}

	@Override
	public boolean suitableForFile(@Nonnull PsiFile file)
	{
		return file instanceof JspFile;
	}

	@Nonnull
	@Override
	public HighlightVisitor createVisitor()
	{
		return new JspJavaHighlightVisitorImpl(myResolveHelper);
	}
}
