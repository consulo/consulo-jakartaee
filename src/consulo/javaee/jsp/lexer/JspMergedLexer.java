package consulo.javaee.jsp.lexer;

import com.intellij.lexer.MergingLexerAdapter;
import com.intellij.psi.tree.TokenSet;
import consulo.javaee.jsp.psi.JspTokens;

/**
 * @author VISTALL
 * @since 16.11.13.
 */
public class JspMergedLexer extends MergingLexerAdapter
{
	public JspMergedLexer()
	{
		super(new JspLexer(), TokenSet.create(JspTokens.JAVA_TEXT, JspTokens.HTML_TEXT));
	}
}
