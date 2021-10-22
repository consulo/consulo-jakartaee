package consulo.javaee.jsp.psi;

import com.intellij.psi.impl.source.tree.LeafElement;
import com.intellij.psi.tree.IElementType;
import consulo.lang.LanguageVersion;
import consulo.psi.tree.ASTLeafFactory;

import javax.annotation.Nonnull;

/**
 * @author VISTALL
 * @since 22/10/2021
 */
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
