package consulo.javaee.jsp.lexer;

import com.intellij.lexer.MergingLexerAdapter;
import com.intellij.psi.tree.TokenSet;
import com.intellij.psi.xml.XmlTokenType;

/**
 * @author VISTALL
 * @since 23-May-17
 */
public class JspLexer extends MergingLexerAdapter
{
	private static final TokenSet ourMergeSet = TokenSet.create(XmlTokenType.XML_DATA_CHARACTERS);

	public JspLexer()
	{
		super(new _JspLexer(), ourMergeSet);
	}
}
