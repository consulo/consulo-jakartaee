package consulo.javaee.jsp.format;

import java.util.List;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.intellij.formatting.Alignment;
import com.intellij.formatting.Block;
import com.intellij.formatting.FormattingModel;
import com.intellij.formatting.FormattingModelBuilder;
import com.intellij.formatting.Wrap;
import com.intellij.lang.ASTNode;
import com.intellij.lang.LanguageFormatting;
import com.intellij.lang.java.JavaLanguage;
import com.intellij.psi.FileViewProvider;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.psi.codeStyle.CodeStyleSettings;
import com.intellij.psi.formatter.DocumentBasedFormattingModel;
import com.intellij.psi.templateLanguages.SimpleTemplateLanguageFormattingModelBuilder;
import com.intellij.psi.templateLanguages.TemplateLanguageFileViewProvider;
import com.intellij.psi.tree.IElementType;
import consulo.javaee.jsp.format.copy.BlockUtil2;
import consulo.javaee.jsp.format.copy.DataLanguageBlockWrapper2;
import consulo.javaee.jsp.format.copy.TemplateLanguageBlock2;
import consulo.javaee.jsp.format.copy.TemplateLanguageFormattingModelBuilder2;
import consulo.javaee.jsp.psi.JspTemplateTokens;

/**
 * @author VISTALL
 * @since 2018-08-26
 */
public class JspFormattingModelBuilder extends TemplateLanguageFormattingModelBuilder2
{
	@Override
	public TemplateLanguageBlock2 createTemplateLanguageBlock(@Nonnull ASTNode node,
															  @Nullable Wrap wrap,
															  @Nullable Alignment alignment,
															  @Nullable List<DataLanguageBlockWrapper2> foreignChildren,
															  @Nonnull CodeStyleSettings codeStyleSettings)
	{
		return new JspBlock(this, codeStyleSettings, node, foreignChildren);
	}

	@Override
	@Nonnull
	public FormattingModel createModel(PsiElement element, CodeStyleSettings settings)
	{
		final PsiFile file = element.getContainingFile();
		Block rootBlock;

		ASTNode node = element.getNode();

		IElementType elementType = node.getElementType();

		if(elementType == JspTemplateTokens.HTML_OUTER_ELEMENT_TYPE)
		{
			return new SimpleTemplateLanguageFormattingModelBuilder().createModel(element, settings);
		}
		else
		{
			rootBlock = getRootBlock(file, file.getViewProvider(), settings);
		}

		FileViewProvider viewProvider = file.getViewProvider();
		if(viewProvider instanceof TemplateLanguageFileViewProvider)
		{
			PsiFile javaFile = viewProvider.getPsi(JavaLanguage.INSTANCE);

			FormattingModelBuilder javaModelBuilder = LanguageFormatting.INSTANCE.forContext(javaFile);
			assert javaModelBuilder != null;
			FormattingModel javaModel = javaModelBuilder.createModel(javaFile, settings);
			Block javaRootBlock = javaModel.getRootBlock();
			if(rootBlock instanceof JspBlock)
			{
				List<DataLanguageBlockWrapper2> data = BlockUtil2.filterBlocksByRange(BlockUtil2.buildChildWrappers(javaRootBlock), javaFile.getTextRange());

				((JspBlock) rootBlock).setJavaOriginalWrappers(data);
			}
		}
		return new DocumentBasedFormattingModel(rootBlock, element.getProject(), settings, file.getFileType(), file);
	}

	@Override
	public boolean dontFormatMyModel()
	{
		return false;
	}
}
