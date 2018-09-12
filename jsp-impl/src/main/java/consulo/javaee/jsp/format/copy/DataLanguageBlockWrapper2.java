/*
 * Copyright 2000-2012 JetBrains s.r.o.
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
package consulo.javaee.jsp.format.copy;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.intellij.formatting.ASTBlock;
import com.intellij.formatting.Alignment;
import com.intellij.formatting.Block;
import com.intellij.formatting.BlockEx;
import com.intellij.formatting.ChildAttributes;
import com.intellij.formatting.Indent;
import com.intellij.formatting.Spacing;
import com.intellij.formatting.Wrap;
import com.intellij.formatting.templateLanguages.BlockWithParent;
import com.intellij.lang.ASTNode;
import com.intellij.lang.Language;
import com.intellij.openapi.util.Pair;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.PsiElement;
import com.intellij.psi.formatter.common.AbstractBlock;
import com.intellij.psi.templateLanguages.OuterLanguageElement;
import com.intellij.util.containers.ContainerUtil;

/**
 * @author Alexey Chmutov
 *         Date: Jun 30, 2009
 *         Time: 7:18:37 PM
 */
public class DataLanguageBlockWrapper2 implements ASTBlock, BlockEx, BlockWithParent
{
	private final Block myOriginal;
	@Nullable
	private final Language myLanguage;
	private List<Block> myBlocks;
	private List<TemplateLanguageBlock2> myTlBlocks;
	private BlockWithParent myParent;
	private DataLanguageBlockWrapper2 myRightHandWrapper;
	private Spacing mySpacing;
	private Map<Pair<Block, Block>, Spacing> myChildDataBorderSpacings;

	private DataLanguageBlockWrapper2(@Nonnull final Block original)
	{
		assert !(original instanceof DataLanguageBlockWrapper2) && !(original instanceof TemplateLanguageBlock2) : original.getClass().getName();
		myOriginal = original;

		final ASTNode node = getNode();
		Language language = null;
		if(node != null)
		{
			final PsiElement psi = node.getPsi();
			if(psi != null)
			{
				language = psi.getContainingFile().getLanguage();
			}
		}
		myLanguage = language;
	}

	@Override
	@Nonnull
	public TextRange getTextRange()
	{
		return myOriginal.getTextRange();
	}

	@Override
	@Nonnull
	public List<Block> getSubBlocks()
	{
		if(myBlocks == null)
		{
			myBlocks = buildBlocks();
			initSpacings();
		}
		return myBlocks;
	}

	private void initSpacings()
	{
		for(int i = 1; i < myBlocks.size(); i++)
		{
			Block block1 = myBlocks.get(i - 1);
			Block block2 = myBlocks.get(i);
			if(block1 instanceof TemplateLanguageBlock2)
			{
				Spacing spacing = ((TemplateLanguageBlock2) block1).getRightNeighborSpacing(block2, this, i - 1);
				if(spacing != null)
				{
					if(myChildDataBorderSpacings == null)
					{
						myChildDataBorderSpacings = ContainerUtil.newTroveMap();
					}
					myChildDataBorderSpacings.put(Pair.create(block1, block2), spacing);
				}
			}
		}
	}

	@Nullable
	@Override
	public Language getLanguage()
	{
		// Use base language code style settings for the template blocks.
		return myLanguage;
	}

	private List<Block> buildBlocks()
	{
		assert myBlocks == null;
		if(isLeaf())
		{
			return AbstractBlock.EMPTY;
		}
		final List<DataLanguageBlockWrapper2> subWrappers = BlockUtil2.buildChildWrappers(myOriginal);
		final List<Block> children;
		if(myTlBlocks == null)
		{
			children = new ArrayList<>(subWrappers);
		}
		else if(subWrappers.size() == 0)
		{
			//noinspection unchecked
			children = (List<Block>) (subWrappers.size() > 0 ? myTlBlocks : BlockUtil2.splitBlockIntoFragments(myOriginal, myTlBlocks));
		}
		else
		{
			children = BlockUtil2.mergeBlocks(myTlBlocks, subWrappers);
		}
		//BlockUtil.printBlocks(getTextRange(), children);
		return BlockUtil2.setParent(children, this);
	}

	@Override
	public Wrap getWrap()
	{
		BlockWithParent parent = getParent();
		if(parent instanceof TemplateLanguageBlock2)
		{
			return ((TemplateLanguageBlock2) parent).substituteTemplateChildWrap(this, myOriginal.getWrap());
		}
		return myOriginal.getWrap();
	}

	@Override
	@Nonnull
	public ChildAttributes getChildAttributes(final int newChildIndex)
	{
		return myOriginal.getChildAttributes(newChildIndex);
	}

	@Override
	public Indent getIndent()
	{
		return myOriginal.getIndent();
	}

	@Override
	public Alignment getAlignment()
	{
		return myOriginal.getAlignment();
	}

	@Override
	@Nullable
	public Spacing getSpacing(Block child1, @Nonnull Block child2)
	{
		if(child1 instanceof DataLanguageBlockWrapper2 && child2 instanceof DataLanguageBlockWrapper2)
		{
			return myOriginal.getSpacing(((DataLanguageBlockWrapper2) child1).myOriginal, ((DataLanguageBlockWrapper2) child2).myOriginal);
		}
		if(child1 instanceof TemplateLanguageBlock2 && myChildDataBorderSpacings != null)
		{
			return myChildDataBorderSpacings.get(Pair.create(child1, child2));
		}
		return null;
	}

	@Override
	public boolean isIncomplete()
	{
		return myOriginal.isIncomplete();
	}

	@Override
	public boolean isLeaf()
	{
		return myTlBlocks == null && myOriginal.isLeaf();
	}

	void addTlChild(TemplateLanguageBlock2 tlBlock)
	{
		assert myBlocks == null;
		if(myTlBlocks == null)
		{
			myTlBlocks = new ArrayList<>(5);
		}
		myTlBlocks.add(tlBlock);
		tlBlock.setParent(this);
	}

	public Block getOriginal()
	{
		return myOriginal;
	}

	@Override
	public String toString()
	{
		String tlBlocksInfo = " TlBlocks " + (myTlBlocks == null ? "0" : myTlBlocks.size()) + "|" + getTextRange() + "|";
		return tlBlocksInfo + myOriginal.toString();
	}

	@Nullable
	public static DataLanguageBlockWrapper2 create(@Nonnull final Block original, @Nullable final Indent indent)
	{
		final boolean doesntNeedWrapper = original instanceof ASTBlock && ((ASTBlock) original).getNode() instanceof OuterLanguageElement;
		return doesntNeedWrapper ? null : new DataLanguageBlockWrapper2(original);
	}

	@Override
	@Nullable
	public ASTNode getNode()
	{
		return myOriginal instanceof ASTBlock ? ((ASTBlock) myOriginal).getNode() : null;
	}

	@Override
	public BlockWithParent getParent()
	{
		return myParent;
	}

	@Override
	public void setParent(BlockWithParent parent)
	{
		myParent = parent;
	}

	public void setRightHandSpacing(DataLanguageBlockWrapper2 rightHandWrapper, Spacing spacing)
	{
		myRightHandWrapper = rightHandWrapper;
		mySpacing = spacing;
	}

	@Nullable
	public Spacing getRightHandSpacing(DataLanguageBlockWrapper2 rightHandWrapper)
	{
		return myRightHandWrapper == rightHandWrapper ? mySpacing : null;
	}
}
