/*
 * Copyright 2000-2009 JetBrains s.r.o.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package consulo.jsp.impl.editor.formatting.copy;

import consulo.document.util.TextRange;
import consulo.language.ast.ASTNode;
import consulo.language.ast.IElementType;
import consulo.language.codeStyle.*;
import consulo.language.codeStyle.template.BlockWithParent;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Alexey Chmutov
 *         Date: Jun 26, 2009
 *         Time: 4:05:40 PM
 */
public abstract class TemplateLanguageBlock2 extends AbstractBlock implements BlockWithParent
{
	private final TemplateLanguageBlockFactory2 myBlockFactory;
	private final CodeStyleSettings mySettings;
	private List<DataLanguageBlockWrapper2> myForeignChildren;
	protected boolean myChildrenBuilt = false;
	private BlockWithParent myParent;

	protected TemplateLanguageBlock2(@Nonnull TemplateLanguageBlockFactory2 blockFactory,
									 @Nonnull CodeStyleSettings settings,
									 @Nonnull ASTNode node, @Nullable List<DataLanguageBlockWrapper2> foreignChildren)
	{
		this(node, null, null, blockFactory, settings, foreignChildren);
	}

	protected TemplateLanguageBlock2(@Nonnull ASTNode node,
									 @Nullable Wrap wrap,
									 @Nullable Alignment alignment,
									 @Nonnull TemplateLanguageBlockFactory2 blockFactory,
									 @Nonnull CodeStyleSettings settings,
									 @Nullable List<DataLanguageBlockWrapper2> foreignChildren)
	{
		super(node, wrap, alignment);
		myBlockFactory = blockFactory;
		myForeignChildren = foreignChildren;
		mySettings = settings;
	}

	@Override
	protected List<Block> buildChildren()
	{
		myChildrenBuilt = true;
		if(isLeaf())
		{
			return EMPTY;
		}
		final ArrayList<TemplateLanguageBlock2> tlChildren = new ArrayList<>(5);
		for(ASTNode childNode = getNode().getFirstChildNode(); childNode != null; childNode = childNode.getTreeNext())
		{
			if(FormatterUtil.containsWhiteSpacesOnly(childNode))
			{
				continue;
			}
			if(shouldBuildBlockFor(childNode))
			{
				final TemplateLanguageBlock2 childBlock = myBlockFactory
						.createTemplateLanguageBlock(childNode, createChildWrap(childNode), createChildAlignment(childNode), null, mySettings);
				childBlock.setParent(this);
				tlChildren.add(childBlock);
			}
		}
		final List<Block> children = (List<Block>) (myForeignChildren == null ? tlChildren : BlockUtil2.mergeBlocks(tlChildren, myForeignChildren));
		//BlockUtil.printBlocks(getTextRange(), children);
		return BlockUtil2.setParent(children, this);
	}

	protected boolean shouldBuildBlockFor(ASTNode childNode)
	{
		return childNode.getElementType() != getTemplateTextElementType() || noForeignChildren();
	}

	private boolean noForeignChildren()
	{
		return (myForeignChildren == null || myForeignChildren.isEmpty());
	}

	void addForeignChild(@Nonnull DataLanguageBlockWrapper2 foreignChild)
	{
		initForeignChildren();
		myForeignChildren.add(foreignChild);
	}

	public void addForeignChildren(List<DataLanguageBlockWrapper2> foreignChildren)
	{
		initForeignChildren();
		myForeignChildren.addAll(foreignChildren);
	}

	private void initForeignChildren()
	{
		assert !myChildrenBuilt;
		if(myForeignChildren == null)
		{
			myForeignChildren = new ArrayList<>(5);
		}
	}

	@Override
	@Nullable
	public Spacing getSpacing(@Nullable Block child1, @Nonnull Block child2)
	{
		if(child1 instanceof DataLanguageBlockWrapper2 && child2 instanceof DataLanguageBlockWrapper2)
		{
			return ((DataLanguageBlockWrapper2) child1).getRightHandSpacing((DataLanguageBlockWrapper2) child2);
		}
		return null;
	}

	/**
	 * Invoked when the current base language block is located inside a template data language block to determine the spacing after the current block.
	 *
	 * @param rightNeighbor  the block to the right of the current one
	 * @param parent         the parent block
	 * @param thisBlockIndex the index of the current block in the parent block subblocks
	 * @return the spacing between the current block and its right neighbor
	 */
	@Nullable
	public Spacing getRightNeighborSpacing(@Nonnull Block rightNeighbor, @Nonnull DataLanguageBlockWrapper2 parent, int thisBlockIndex)
	{
		return null;
	}

	@Override
	public boolean isLeaf()
	{
		return noForeignChildren() && getNode().getFirstChildNode() == null;
	}

	protected abstract IElementType getTemplateTextElementType();

	@Override
	public BlockWithParent getParent()
	{
		return myParent;
	}

	@Override
	public void setParent(BlockWithParent newParent)
	{
		myParent = newParent;
	}

	/**
	 * Checks if DataLanguageBlockFragmentWrapper must be created for the given text range.
	 *
	 * @param range The range to check.
	 * @return True by default.
	 */
	public boolean isRequiredRange(TextRange range)
	{
		return true;
	}

	protected Wrap createChildWrap(ASTNode child)
	{
		return Wrap.createWrap(WrapType.NONE, false);
	}

	protected Alignment createChildAlignment(ASTNode child)
	{
		return null;
	}

	public CodeStyleSettings getSettings()
	{
		return mySettings;
	}

	public List<DataLanguageBlockWrapper2> getForeignChildren()
	{
		return myForeignChildren;
	}

	@Nullable
	public Wrap substituteTemplateChildWrap(@Nonnull DataLanguageBlockWrapper2 child, @Nullable Wrap childWrap)
	{
		return childWrap;
	}
}

