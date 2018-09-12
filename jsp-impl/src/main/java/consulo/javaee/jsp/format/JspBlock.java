package consulo.javaee.jsp.format;

import java.util.List;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.intellij.formatting.Block;
import com.intellij.formatting.Indent;
import com.intellij.formatting.templateLanguages.BlockWithParent;
import com.intellij.lang.ASTNode;
import com.intellij.psi.codeStyle.CodeStyleSettings;
import com.intellij.psi.jsp.JspTokenType;
import com.intellij.psi.tree.IElementType;
import consulo.javaee.jsp.format.copy.BlockUtil2;
import consulo.javaee.jsp.format.copy.DataLanguageBlockWrapper2;
import consulo.javaee.jsp.format.copy.TemplateLanguageBlock2;
import consulo.javaee.jsp.format.copy.TemplateLanguageBlockFactory2;
import consulo.javaee.jsp.psi.JspTokens;

/**
 * @author VISTALL
 * @since 2018-08-26
 */
public class JspBlock extends TemplateLanguageBlock2
{
	private List<DataLanguageBlockWrapper2> myJavaOriginalWrappers;

	public JspBlock(@Nonnull TemplateLanguageBlockFactory2 blockFactory,
					@Nonnull CodeStyleSettings settings,
					@Nonnull ASTNode node,
					@Nullable List<DataLanguageBlockWrapper2> foreignChildren)
	{
		super(blockFactory, settings, node, foreignChildren);
	}

	public void setJavaOriginalWrappers(List<DataLanguageBlockWrapper2> javaOriginalWrappers)
	{
		myJavaOriginalWrappers = javaOriginalWrappers;
	}

	@Override
	protected List<Block> buildChildren()
	{
		if(getNode().getElementType() == JspTokenType.JAVA_CODE && myJavaOriginalWrappers != null)
		{
			addForeignChildren(BlockUtil2.filterBlocksByRange(myJavaOriginalWrappers, getTextRange()));
		}
		return super.buildChildren();
	}

	@Override
	public void setParent(BlockWithParent newParent)
	{
		super.setParent(newParent);
		if(newParent instanceof JspBlock)
		{
			myJavaOriginalWrappers = ((JspBlock) newParent).myJavaOriginalWrappers;
		}
	}

	@Override
	protected IElementType getTemplateTextElementType()
	{
		return JspTokens.HTML_FRAGMENT;
	}

	@Override
	public Indent getIndent()
	{
		return Indent.getNoneIndent();
	}
}
