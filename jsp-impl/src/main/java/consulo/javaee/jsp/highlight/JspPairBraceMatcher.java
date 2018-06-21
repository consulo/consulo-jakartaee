package consulo.javaee.jsp.highlight;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import com.intellij.lang.BracePair;
import com.intellij.lang.PairedBraceMatcher;
import com.intellij.psi.PsiFile;
import com.intellij.psi.jsp.JspTokenType;
import com.intellij.psi.tree.IElementType;

/**
 * @author VISTALL
 * @since 24-May-17
 */
public class JspPairBraceMatcher implements PairedBraceMatcher
{
	private static final BracePair[] ourBraces = new BracePair[]{
			new BracePair(JspTokenType.JSP_DIRECTIVE_START, JspTokenType.JSP_DIRECTIVE_END, true),
			new BracePair(JspTokenType.JSP_SCRIPTLET_START, JspTokenType.JSP_SCRIPTLET_END, true),
			new BracePair(JspTokenType.JSP_EXPRESSION_START, JspTokenType.JSP_EXPRESSION_END, true),
			new BracePair(JspTokenType.JSP_DECLARATION_START, JspTokenType.JSP_DECLARATION_END, true),
	};

	@Override
	public BracePair[] getPairs()
	{
		return ourBraces;
	}

	@Override
	public boolean isPairedBracesAllowedBeforeType(@Nonnull IElementType iElementType, @Nullable IElementType iElementType1)
	{
		return false;
	}

	@Override
	public int getCodeConstructStart(PsiFile psiFile, int i)
	{
		return i;
	}
}
