package consulo.jsp.impl.language.lexer;

import consulo.jsp.language.ast.JspTokenType;
import consulo.language.ast.TokenSet;
import consulo.language.lexer.MergingLexerAdapter;
import consulo.xml.psi.xml.XmlTokenType;

/**
 * @author VISTALL
 * @since 23-May-17
 */
public class JspLexer extends MergingLexerAdapter
{
	private static final TokenSet ourMergeSet = TokenSet.create(XmlTokenType.XML_DATA_CHARACTERS, JspTokenType.JAVA_CODE, XmlTokenType.XML_ATTRIBUTE_VALUE_TOKEN, XmlTokenType
			.XML_COMMENT_CHARACTERS);

	public JspLexer()
	{
		super(new _JspLexer(), ourMergeSet);
	}
}
