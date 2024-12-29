package consulo.jsp.impl.language.psi;

import consulo.annotation.component.ExtensionImpl;
import consulo.language.ast.IElementType;
import consulo.language.impl.ast.ASTLeafFactory;
import consulo.language.impl.ast.LeafElement;
import consulo.language.version.LanguageVersion;

import jakarta.annotation.Nonnull;

/**
 * @author VISTALL
 * @since 22/10/2021
 */
@ExtensionImpl
public class JspLeafElementFactory implements ASTLeafFactory
{
	@Nonnull
	@Override
	public LeafElement createLeaf(@Nonnull IElementType elementType, @Nonnull LanguageVersion languageVersion, @Nonnull CharSequence text)
	{
		return new JspCommentImpl(elementType, text);
	}

	@Override
	public boolean test(IElementType elementType)
	{
		return elementType == JspElements.COMMENT;
	}
}
