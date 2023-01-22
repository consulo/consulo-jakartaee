package consulo.jsp.impl.editor;

import consulo.annotation.component.ExtensionImpl;
import consulo.codeEditor.EditorHighlighter;
import consulo.colorScheme.EditorColorsScheme;
import consulo.jsp.impl.editor.highlight.JspEditorHighlighter;
import consulo.jsp.impl.language.JspFileType;
import consulo.language.editor.highlight.EditorHighlighterProvider;
import consulo.project.Project;
import consulo.virtualFileSystem.VirtualFile;
import consulo.virtualFileSystem.fileType.FileType;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/**
 * @author VISTALL
 * @since 2019-02-25
 */
@ExtensionImpl
public class JspEditorHighlighterProvider implements EditorHighlighterProvider
{
	@Override
	public EditorHighlighter getEditorHighlighter(@Nullable Project project, @Nonnull FileType fileType, @Nullable VirtualFile virtualFile, @Nonnull EditorColorsScheme colors)
	{
		return new JspEditorHighlighter(project, virtualFile, colors);
	}

	@Nonnull
	@Override
	public FileType getFileType()
	{
		return JspFileType.INSTANCE;
	}
}
