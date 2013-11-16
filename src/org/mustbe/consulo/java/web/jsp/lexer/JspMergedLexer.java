package org.mustbe.consulo.java.web.jsp.lexer;

import org.mustbe.consulo.java.web.jsp.psi.JspTokens;
import com.intellij.lexer.MergingLexerAdapter;
import com.intellij.psi.tree.TokenSet;

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
