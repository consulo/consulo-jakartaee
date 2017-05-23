package consulo.javaee.jsp.lexer;

import com.intellij.lexer.JavaLexer;
import com.intellij.lexer.Lexer;
import com.intellij.lexer.LexerPosition;
import com.intellij.lexer.LookAheadLexer;
import com.intellij.pom.java.LanguageLevel;
import com.intellij.psi.JavaTokenType;
import com.intellij.psi.tree.IElementType;
import com.intellij.psi.tree.java.IJavaElementType;

/**
 * @author VISTALL
 * @since 23-May-17
 */
public class _JspJavaLexer extends LookAheadLexer
{
	public static final IElementType JSP_IN_JAVA = new IJavaElementType("JSP_IN_JAVA");

	private boolean myEnabledJavaTokens;

	public _JspJavaLexer()
	{
		super(new JavaLexer(LanguageLevel.HIGHEST));
	}

	@Override
	protected void lookAhead(Lexer baseLexer)
	{
		IElementType tokenType = baseLexer.getTokenType();
		if(tokenType == null)
		{
			super.lookAhead(baseLexer);
			return;
		}

		if(myEnabledJavaTokens)
		{
			if(tokenType == JavaTokenType.PERC)
			{
				LexerPosition currentPosition = baseLexer.getCurrentPosition();

				boolean tagClosed = false;
				baseLexer.advance();

				if(baseLexer.getTokenType() == JavaTokenType.GT)
				{
					tagClosed = true;
				}
				baseLexer.restore(currentPosition);

				if(tagClosed)
				{
					myEnabledJavaTokens = false;
					baseLexer.advance();
					baseLexer.advance();
					addToken(JSP_IN_JAVA);
					return;
				}
			}

			super.lookAhead(baseLexer);
			return;
		}

		if(tokenType == JavaTokenType.LT)
		{
			LexerPosition currentPosition = baseLexer.getCurrentPosition();

			// this <
			baseLexer.advance();

			// <%
			if(baseLexer.getTokenType() == JavaTokenType.PERC)
			{
				baseLexer.advance();

				// simple <%
				if(baseLexer.getTokenType() == JavaTokenType.WHITE_SPACE)
				{
					baseLexer.restore(currentPosition);

					baseLexer.advance(); // <
					baseLexer.advance(); // %
					addToken(JSP_IN_JAVA);
					myEnabledJavaTokens = true;
					return;
				}
				// <%!
				else if(baseLexer.getTokenType() == JavaTokenType.EXCL || baseLexer.getTokenType() == JavaTokenType.EQ)
				{
					baseLexer.restore(currentPosition);

					baseLexer.advance(); // <
					baseLexer.advance(); // %
					baseLexer.advance(); // ! or =
					addToken(JSP_IN_JAVA);
					myEnabledJavaTokens = true;
					return;
				}
				else
				{
					baseLexer.restore(currentPosition);
				}
			}
			else
			{
				baseLexer.restore(currentPosition);
			}
		}

		advanceAs(baseLexer, JSP_IN_JAVA);
	}
}