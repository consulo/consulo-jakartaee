package consulo.javaee.jsp.lexer;

import static consulo.javaee.jsp.psi.JspTokens.JAVA_FRAGMENT;

import com.intellij.lexer.JavaLexer;
import com.intellij.lexer.Lexer;
import com.intellij.lexer.LookAheadLexer;
import com.intellij.pom.java.LanguageLevel;
import com.intellij.psi.tree.IElementType;
import com.intellij.psi.tree.java.IJavaElementType;

/**
 * @author VISTALL
 * @since 23-May-17
 */
public class _JspJavaLexer extends LookAheadLexer
{
	public static final IElementType JSP_IN_JAVA = new IJavaElementType("JSP_IN_JAVA");

	private JavaLexer myJavaLexer;

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

		if(baseLexer.getTokenType() == JAVA_FRAGMENT)
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
			advanceAs(baseLexer, JSP_IN_JAVA);
		}
	}
}
