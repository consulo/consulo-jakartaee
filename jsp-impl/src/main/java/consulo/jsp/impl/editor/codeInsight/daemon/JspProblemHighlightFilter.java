package consulo.jsp.impl.editor.codeInsight.daemon;

import consulo.annotation.component.ExtensionImpl;
import consulo.java.impl.util.JavaProjectRootsUtil;
import consulo.jsp.impl.language.JspFileType;
import consulo.language.editor.ProblemHighlightFilter;
import consulo.language.psi.PsiFile;
import consulo.module.content.ProjectRootManager;
import consulo.virtualFileSystem.VirtualFile;

import javax.annotation.Nonnull;

/**
 * @author VISTALL
 * @since 24-May-17
 */
@ExtensionImpl
public class JspProblemHighlightFilter extends ProblemHighlightFilter
{
	@Override
	public boolean shouldHighlight(@Nonnull PsiFile psiFile)
	{
		return psiFile.getFileType() != JspFileType.INSTANCE || !JavaProjectRootsUtil.isOutsideSourceRoot(psiFile);
	}

	@Override
	public boolean shouldProcessInBatch(@Nonnull PsiFile psiFile)
	{
		final boolean shouldHighlight = shouldHighlightFile(psiFile);
		if(shouldHighlight)
		{
			if(psiFile.getFileType() == JspFileType.INSTANCE)
			{
				final VirtualFile virtualFile = psiFile.getVirtualFile();
				if(virtualFile != null && ProjectRootManager.getInstance(psiFile.getProject()).getFileIndex().isInLibrarySource(virtualFile))
				{
					return false;
				}
			}
		}
		return shouldHighlight;
	}
}
