package consulo.javaee.artifact;

import org.jetbrains.annotations.NotNull;
import com.intellij.ide.highlighter.JarArchiveFileType;
import com.intellij.openapi.fileTypes.FileTypeConsumer;
import com.intellij.openapi.fileTypes.FileTypeFactory;

/**
 * @author VISTALL
 * @since 12.11.13.
 */
public class WarFileTypeFactory extends FileTypeFactory
{
	@Override
	public void createFileTypes(@NotNull FileTypeConsumer fileTypeConsumer)
	{
		fileTypeConsumer.consume(JarArchiveFileType.INSTANCE, "war");
	}
}
