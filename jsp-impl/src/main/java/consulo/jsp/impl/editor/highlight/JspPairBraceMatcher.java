package consulo.jsp.impl.editor.highlight;

import consulo.annotation.component.ExtensionImpl;
import consulo.jsp.language.JspLanguage;
import consulo.jsp.language.ast.JspTokenType;
import consulo.language.BracePair;
import consulo.language.Language;
import consulo.language.PairedBraceMatcher;

import jakarta.annotation.Nonnull;

/**
 * @author VISTALL
 * @since 24-May-17
 */
@ExtensionImpl
public class JspPairBraceMatcher implements PairedBraceMatcher
{
	private static final BracePair[] ourBraces = new BracePair[]{
			new BracePair(JspTokenType.JSP_DIRECTIVE_START, JspTokenType.JSP_DIRECTIVE_END, true),
			new BracePair(JspTokenType.JSP_SCRIPTLET_START, JspTokenType.JSP_SCRIPTLET_END, true),
			new BracePair(JspTokenType.JSP_EXPRESSION_START, JspTokenType.JSP_EXPRESSION_END, true),
			new BracePair(JspTokenType.JSP_DECLARATION_START, JspTokenType.JSP_DECLARATION_END, true),
	};

	@Override
	public BracePair[] getPairs()
	{
		return ourBraces;
	}

	@Nonnull
	@Override
	public Language getLanguage()
	{
		return JspLanguage.INSTANCE;
	}
}
