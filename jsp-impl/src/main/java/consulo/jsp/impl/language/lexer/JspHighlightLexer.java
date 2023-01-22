package consulo.jsp.impl.language.lexer;

import consulo.jsp.impl.language.psi.JspTokens;
import consulo.language.ast.TokenSet;
import consulo.language.lexer.MergingLexerAdapter;

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
