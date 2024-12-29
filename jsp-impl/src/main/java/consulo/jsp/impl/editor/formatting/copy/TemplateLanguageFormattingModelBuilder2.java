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
import consulo.language.Language;
import consulo.language.ast.ASTNode;
import consulo.language.codeStyle.*;
import consulo.language.file.FileViewProvider;
import consulo.language.psi.PsiElement;
import consulo.language.psi.PsiFile;
import consulo.language.template.TemplateLanguageFileViewProvider;
import consulo.util.collection.ContainerUtil;

import jakarta.annotation.Nonnull;
import java.util.Collections;
import java.util.List;

/**
 * @author Alexey Chmutov
 *         Date: Jun 26, 2009
 *         Time: 4:07:09 PM
 */
public abstract class TemplateLanguageFormattingModelBuilder2 implements DelegatingFormattingModelBuilder, TemplateLanguageBlockFactory2
{
	@Nonnull
	@Override
	public FormattingModel createModel(@Nonnull FormattingContext context)
	{
		PsiElement element = context.getPsiElement();
		CodeStyleSettings settings = context.getCodeStyleSettings();
		
		final PsiFile file = element.getContainingFile();
		Block rootBlock = getRootBlock(file, file.getViewProvider(), settings);
		return new DocumentBasedFormattingModel(rootBlock, element.getProject(), settings, file.getFileType(), file);
	}

	protected Block getRootBlock(PsiElement element, FileViewProvider viewProvider, CodeStyleSettings settings)
	{
		ASTNode node = element.getNode();
		if(node == null)
		{
			return createDummyBlock(node);
		}
		if(viewProvider instanceof TemplateLanguageFileViewProvider)
		{
			final Language dataLanguage = ((TemplateLanguageFileViewProvider) viewProvider).getTemplateDataLanguage();
			final FormattingModelBuilder builder = ContainerUtil.getFirstItem(FormattingModelBuilder.forLanguage(dataLanguage));
			if(builder instanceof DelegatingFormattingModelBuilder && ((DelegatingFormattingModelBuilder) builder).dontFormatMyModel())
			{
				return createDummyBlock(node);
			}
			if(builder != null)
			{
				final FormattingModel model = builder.createModel(FormattingContext.create(viewProvider.getPsi(dataLanguage), settings));
				List<DataLanguageBlockWrapper2> childWrappers = BlockUtil2.buildChildWrappers(model.getRootBlock());
				if(childWrappers.size() == 1)
				{
					childWrappers = BlockUtil2.buildChildWrappers(childWrappers.get(0).getOriginal());
				}
				return createTemplateLanguageBlock(node, Wrap.createWrap(WrapType.NONE, false), null,
						BlockUtil2.filterBlocksByRange(childWrappers, node.getTextRange()), settings);
			}
		}
		return createTemplateLanguageBlock(node, Wrap.createWrap(WrapType.NONE, false), null, Collections.<DataLanguageBlockWrapper2>emptyList(), settings);
	}

	protected AbstractBlock createDummyBlock(final ASTNode node)
	{
		return new AbstractBlock(node, Wrap.createWrap(WrapType.NONE, false), Alignment.createAlignment())
		{
			@Override
			protected List<Block> buildChildren()
			{
				return Collections.emptyList();
			}

			@Override
			public Spacing getSpacing(final Block child1, @Nonnull final Block child2)
			{
				return Spacing.getReadOnlySpacing();
			}

			@Override
			public boolean isLeaf()
			{
				return true;
			}
		};
	}

	@Override
	public TextRange getRangeAffectingIndent(PsiFile file, int offset, ASTNode elementAtOffset)
	{
		return null;
	}

	@Override
	public boolean dontFormatMyModel()
	{
		return true;
	}
}
