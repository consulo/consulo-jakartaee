package consulo.javaee.artifact;

import javax.annotation.Nonnull;

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
	public void createFileTypes(@Nonnull FileTypeConsumer fileTypeConsumer)
	{
		fileTypeConsumer.consume(JarArchiveFileType.INSTANCE, "war");
	}
}
