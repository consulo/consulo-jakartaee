package consulo.jsp.impl.language.psi;

import consulo.language.ast.IElementType;
import consulo.language.impl.psi.PsiCommentImpl;
import consulo.language.psi.PsiComment;

/**
 * @author VISTALL
 * @since 22/10/2021
 */
public class JspCommentImpl extends PsiCommentImpl implements PsiComment
{
	public JspCommentImpl(IElementType type, CharSequence text)
	{
		super(type, text);
	}
}
