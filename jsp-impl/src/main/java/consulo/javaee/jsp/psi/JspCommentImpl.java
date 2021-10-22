package consulo.javaee.jsp.psi;

import com.intellij.psi.PsiComment;
import com.intellij.psi.impl.source.tree.PsiCommentImpl;
import com.intellij.psi.tree.IElementType;

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
