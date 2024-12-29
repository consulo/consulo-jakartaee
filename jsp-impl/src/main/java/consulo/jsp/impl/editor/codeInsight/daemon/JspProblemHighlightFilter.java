package consulo.jsp.impl.editor.codeInsight.daemon;

import consulo.annotation.component.ExtensionImpl;
import consulo.java.impl.util.JavaProjectRootsUtil;
import consulo.jsp.impl.language.JspFileType;
import consulo.language.editor.ProblemHighlightFilter;
import consulo.language.psi.PsiFile;
import consulo.module.content.ProjectFileIndex;
import consulo.virtualFileSystem.VirtualFile;
import jakarta.inject.Inject;

import jakarta.annotation.Nonnull;

/**
 * @author VISTALL
 * @since 24-May-17
 */
@ExtensionImpl
public class JspProblemHighlightFilter extends ProblemHighlightFilter
{
	private final ProjectFileIndex myProjectFileIndex;

	@Inject
	public JspProblemHighlightFilter(ProjectFileIndex projectFileIndex)
	{
		myProjectFileIndex = projectFileIndex;
	}

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
				if(virtualFile != null && myProjectFileIndex.isInLibrarySource(virtualFile))
				{
					return false;
				}
			}
		}
		return shouldHighlight;
	}
}
