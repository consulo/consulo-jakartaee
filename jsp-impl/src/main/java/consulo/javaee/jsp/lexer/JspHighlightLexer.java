package consulo.javaee.jsp.lexer;

import com.intellij.lexer.MergingLexerAdapter;
import com.intellij.psi.tree.TokenSet;
import consulo.javaee.jsp.psi.JspTokens;

/**
 * @author VISTALL
 * @since 21-Jun-17
 */
public class JspHighlightLexer extends MergingLexerAdapter
{
	public JspHighlightLexer()
	{
		super(new _JspHighlightLexer(), TokenSet.create(JspTokens.HTML_FRAGMENT, JspTokens.JAVA_CODE, JspTokens.HTML_FRAGMENT));
	}
}
