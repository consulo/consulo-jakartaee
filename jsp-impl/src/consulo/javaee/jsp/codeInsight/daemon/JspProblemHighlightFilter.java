package consulo.javaee.jsp.codeInsight.daemon;

import javax.annotation.Nonnull;

import com.intellij.codeInsight.daemon.ProblemHighlightFilter;
import com.intellij.openapi.roots.ProjectRootManager;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.PsiFile;
import consulo.java.util.JavaProjectRootsUtil;
import consulo.javaee.jsp.JspFileType;

/**
 * @author VISTALL
 * @since 24-May-17
 */
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
