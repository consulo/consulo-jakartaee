package consulo.jsp.impl.editor.formatting;

import com.intellij.java.language.JavaLanguage;
import consulo.annotation.component.ExtensionImpl;
import consulo.jsp.impl.editor.formatting.copy.BlockUtil2;
import consulo.jsp.impl.editor.formatting.copy.DataLanguageBlockWrapper2;
import consulo.jsp.impl.editor.formatting.copy.TemplateLanguageBlock2;
import consulo.jsp.impl.editor.formatting.copy.TemplateLanguageFormattingModelBuilder2;
import consulo.jsp.impl.language.psi.JspTemplateTokens;
import consulo.jsp.language.JspLanguage;
import consulo.language.Language;
import consulo.language.ast.ASTNode;
import consulo.language.ast.IElementType;
import consulo.language.codeStyle.*;
import consulo.language.codeStyle.template.SimpleTemplateLanguageFormattingModelBuilder;
import consulo.language.file.FileViewProvider;
import consulo.language.psi.PsiElement;
import consulo.language.psi.PsiFile;
import consulo.language.template.TemplateLanguageFileViewProvider;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;

/**
 * @author VISTALL
 * @since 2018-08-26
 */
@ExtensionImpl
public class JspFormattingModelBuilder extends TemplateLanguageFormattingModelBuilder2
{
	private SimpleTemplateLanguageFormattingModelBuilder mySimpleBuilder = new SimpleTemplateLanguageFormattingModelBuilder()
	{
		@Nonnull
		@Override
		public Language getLanguage()
		{
			return JspLanguage.INSTANCE;
		}
	};

	@Override
	public TemplateLanguageBlock2 createTemplateLanguageBlock(@Nonnull ASTNode node,
															  @Nullable Wrap wrap,
															  @Nullable Alignment alignment,
															  @Nullable List<DataLanguageBlockWrapper2> foreignChildren,
															  @Nonnull CodeStyleSettings codeStyleSettings)
	{
		return new JspBlock(this, codeStyleSettings, node, foreignChildren);
	}

	@Nonnull
	@Override
	public FormattingModel createModel(@Nonnull FormattingContext context)
	{
		PsiElement element = context.getPsiElement();
		CodeStyleSettings settings = context.getCodeStyleSettings();

		final PsiFile file = element.getContainingFile();
		Block rootBlock;

		ASTNode node = element.getNode();

		IElementType elementType = node.getElementType();

		if(elementType == JspTemplateTokens.HTML_OUTER_ELEMENT_TYPE)
		{
			return mySimpleBuilder.createModel(FormattingContext.create(element, settings));
		}
		else
		{
			rootBlock = getRootBlock(file, file.getViewProvider(), settings);
		}

		FileViewProvider viewProvider = file.getViewProvider();
		if(viewProvider instanceof TemplateLanguageFileViewProvider)
		{
			PsiFile javaFile = viewProvider.getPsi(JavaLanguage.INSTANCE);

			FormattingModelBuilder javaModelBuilder = FormattingModelBuilder.forContext(javaFile);
			assert javaModelBuilder != null;
			FormattingModel javaModel = javaModelBuilder.createModel(FormattingContext.create(javaFile, settings));
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

	@Nonnull
	@Override
	public Language getLanguage()
	{
		return JspLanguage.INSTANCE;
	}
}
