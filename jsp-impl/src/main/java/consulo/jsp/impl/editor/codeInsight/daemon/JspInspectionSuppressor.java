package consulo.jsp.impl.editor.codeInsight.daemon;

import com.intellij.java.impl.codeInspection.JavaInspectionSuppressor;
import com.intellij.java.language.JavaLanguage;
import com.intellij.java.language.psi.util.PsiUtil;
import consulo.annotation.access.RequiredReadAction;
import consulo.annotation.component.ExtensionImpl;
import consulo.jsp.language.JspLanguage;
import consulo.jsp.language.ast.JspTokenType;
import consulo.language.Language;
import consulo.language.editor.inspection.InspectionSuppressor;
import consulo.language.editor.inspection.SuppressQuickFix;
import consulo.language.psi.PsiElement;
import consulo.language.psi.PsiFile;

import jakarta.annotation.Nonnull;

/**
 * @author VISTALL
 * @since 11/10/2021
 */
@ExtensionImpl
public class JspInspectionSuppressor implements InspectionSuppressor
{
	private JavaInspectionSuppressor myJavaInspectionSuppressor = new JavaInspectionSuppressor();

	@Override
	public boolean isSuppressedFor(@Nonnull PsiElement element, String toolId)
	{
		return false;
	}

	@Override
	@RequiredReadAction
	public SuppressQuickFix[] getSuppressActions(@Nonnull PsiElement element, String toolShortName)
	{
		if(PsiUtil.getElementType(element) == JspTokenType.JAVA_CODE)
		{
			PsiFile file = element.getContainingFile();
			if(file == null)
			{
				return SuppressQuickFix.EMPTY_ARRAY;
			}

			PsiFile javaFile = file.getViewProvider().getPsi(JavaLanguage.INSTANCE);

			if(javaFile == null)
			{
				return SuppressQuickFix.EMPTY_ARRAY;
			}

			PsiElement javaElement = javaFile.findElementAt(element.getTextOffset());
			return myJavaInspectionSuppressor.getSuppressActions(javaElement, toolShortName);
		}
		return SuppressQuickFix.EMPTY_ARRAY;
	}

	@Nonnull
	@Override
	public Language getLanguage()
	{
		return JspLanguage.INSTANCE;
	}
}
