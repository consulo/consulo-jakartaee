package consulo.jsp.impl.editor.formatting;

import consulo.jsp.impl.editor.formatting.copy.BlockUtil2;
import consulo.jsp.impl.editor.formatting.copy.DataLanguageBlockWrapper2;
import consulo.jsp.impl.editor.formatting.copy.TemplateLanguageBlock2;
import consulo.jsp.impl.editor.formatting.copy.TemplateLanguageBlockFactory2;
import consulo.jsp.impl.language.psi.JspTokens;
import consulo.jsp.language.ast.JspTokenType;
import consulo.language.ast.ASTNode;
import consulo.language.ast.IElementType;
import consulo.language.codeStyle.Block;
import consulo.language.codeStyle.CodeStyleSettings;
import consulo.language.codeStyle.Indent;
import consulo.language.codeStyle.template.BlockWithParent;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;

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
