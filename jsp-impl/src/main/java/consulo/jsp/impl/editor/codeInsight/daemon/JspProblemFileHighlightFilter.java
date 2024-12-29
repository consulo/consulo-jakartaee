package consulo.jsp.impl.editor.codeInsight.daemon;

import consulo.annotation.component.ExtensionImpl;
import consulo.jsp.impl.language.JspFileType;
import consulo.language.editor.wolfAnalyzer.WolfFileProblemFilter;
import consulo.virtualFileSystem.VirtualFile;

import jakarta.annotation.Nonnull;

/**
 * @author VISTALL
 * @since 24-May-17
 */
@ExtensionImpl
public class JspProblemFileHighlightFilter implements WolfFileProblemFilter
{
	@Override
	public boolean isToBeHighlighted(@Nonnull VirtualFile virtualFile)
	{
		return virtualFile.getFileType() == JspFileType.INSTANCE;
	}
}
