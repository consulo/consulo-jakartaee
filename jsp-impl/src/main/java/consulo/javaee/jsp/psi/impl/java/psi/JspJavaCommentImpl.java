package consulo.javaee.jsp.psi.impl.java.psi;

import com.intellij.psi.impl.source.jsp.jspJava.JspJavaComment;
import com.intellij.psi.impl.source.tree.PsiCommentImpl;
import com.intellij.psi.tree.IElementType;

/**
 * @author VISTALL
 * @since 25-May-17
 */
public class JspJavaCommentImpl extends PsiCommentImpl implements JspJavaComment
{
	public JspJavaCommentImpl(IElementType type, CharSequence text)
	{
		super(type, text);
	}
}
