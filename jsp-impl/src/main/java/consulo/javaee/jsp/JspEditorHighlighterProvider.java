package consulo.javaee.jsp;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.intellij.openapi.editor.colors.EditorColorsScheme;
import com.intellij.openapi.editor.highlighter.EditorHighlighter;
import com.intellij.openapi.fileTypes.EditorHighlighterProvider;
import com.intellij.openapi.fileTypes.FileType;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;
import consulo.javaee.jsp.highlight.JspEditorHighlighter;

/**
 * @author VISTALL
 * @since 2019-02-25
 */
public class JspEditorHighlighterProvider implements EditorHighlighterProvider
{
	@Override
	public EditorHighlighter getEditorHighlighter(@Nullable Project project, @Nonnull FileType fileType, @Nullable VirtualFile virtualFile, @Nonnull EditorColorsScheme colors)
	{
		return new JspEditorHighlighter(project, virtualFile, colors);
	}
}
