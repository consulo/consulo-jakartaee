package consulo.javaee.jsp.lexer;

import com.intellij.lexer.JavaLexer;
import com.intellij.lexer.Lexer;
import com.intellij.lexer.LookAheadLexer;
import com.intellij.pom.java.LanguageLevel;
import com.intellij.psi.jsp.JspTokenType;
import com.intellij.psi.tree.IElementType;
import com.intellij.psi.tree.TokenSet;

/**
 * @author VISTALL
 * @since 23-May-17
 */
public class _JspJavaLexer extends LookAheadLexer
{
	private static final TokenSet ourPassTokens = TokenSet.create(JspTokenType.JSP_SCRIPTLET_START, JspTokenType.JSP_EXPRESSION_START, JspTokenType.JSP_DECLARATION_START, JspTokenType
			.JSP_SCRIPTLET_END, JspTokenType.JSP_EXPRESSION_END, JspTokenType.JSP_DECLARATION_END);

	private final JavaLexer myJavaLexer;

	public _JspJavaLexer(LanguageLevel languageLevel)
	{
		super(new JspLexer());
		myJavaLexer = new JavaLexer(languageLevel);
	}

	@Override
	protected void lookAhead(Lexer baseLexer)
	{
		if(baseLexer.getTokenType() == null)
		{
			super.lookAhead(baseLexer);
			return;
		}

		if(baseLexer.getTokenType() == JspTokenType.JAVA_CODE)
		{
			int start = baseLexer.getTokenStart();

			CharSequence bufferSequence = getBufferSequence();
			myJavaLexer.start(bufferSequence, start, baseLexer.getTokenEnd());

			IElementType elementType;
			while((elementType = myJavaLexer.getTokenType()) != null)
			{
				addToken(myJavaLexer.getTokenEnd(), elementType);
				myJavaLexer.advance();
			}

			baseLexer.advance();
		}
		else
		{
			advanceAs(baseLexer, ourPassTokens.contains(baseLexer.getTokenType()) ? baseLexer.getTokenType() : JspTokenType.JSP_FRAGMENT);
		}
	}
}
