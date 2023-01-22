package consulo.jsp.impl.language.lexer;

import com.intellij.java.language.LanguageLevel;
import consulo.jsp.language.ast.JspTokenType;
import consulo.language.ast.TokenSet;
import consulo.language.lexer.MergingLexerAdapter;

/**
 * @author VISTALL
 * @since 23-May-17
 */
public class JspJavaLexer extends MergingLexerAdapter
{
	private static final TokenSet ourMergetSet = TokenSet.create(JspTokenType.JSP_FRAGMENT);

	public JspJavaLexer(LanguageLevel languageLevel)
	{
		super(new _JspJavaLexer(languageLevel), ourMergetSet);
	}
}
