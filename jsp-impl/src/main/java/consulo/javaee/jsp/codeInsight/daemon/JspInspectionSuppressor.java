package consulo.javaee.jsp.codeInsight.daemon;

import com.intellij.codeInspection.InspectionSuppressor;
import com.intellij.codeInspection.SuppressManager;
import com.intellij.codeInspection.SuppressQuickFix;
import com.intellij.lang.java.JavaLanguage;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.psi.jsp.JspTokenType;
import com.intellij.psi.util.PsiUtil;
import consulo.annotation.access.RequiredReadAction;

import javax.annotation.Nonnull;

/**
 * @author VISTALL
 * @since 11/10/2021
 */
public class JspInspectionSuppressor implements InspectionSuppressor
{
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
			return SuppressManager.getInstance().getSuppressActions(javaElement, toolShortName);
		}
		return SuppressQuickFix.EMPTY_ARRAY;
	}
}
