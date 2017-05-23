package consulo.javaee.jsp.lexer;

import com.intellij.lexer.MergingLexerAdapter;
import com.intellij.psi.tree.TokenSet;

/**
 * @author VISTALL
 * @since 23-May-17
 */
public class JspJavaLexer extends MergingLexerAdapter
{
	private static final TokenSet ourMergetSet = TokenSet.create(_JspJavaLexer.JSP_IN_JAVA);

	public JspJavaLexer()
	{
		super(new _JspJavaLexer(), ourMergetSet);
	}
}
