package consulo.javaee.jsp.lexer;

import com.intellij.lexer.MergingLexerAdapter;
import com.intellij.pom.java.LanguageLevel;
import com.intellij.psi.tree.TokenSet;

/**
 * @author VISTALL
 * @since 23-May-17
 */
public class JspJavaLexer extends MergingLexerAdapter
{
	private static final TokenSet ourMergetSet = TokenSet.create(_JspJavaLexer.JSP_IN_JAVA);

	public JspJavaLexer(LanguageLevel languageLevel)
	{
		super(new _JspJavaLexer(languageLevel), ourMergetSet);
	}
}
