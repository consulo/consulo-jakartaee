package consulo.javaee.artifact;

import com.intellij.java.language.impl.JarArchiveFileType;
import consulo.annotation.component.ExtensionImpl;
import consulo.virtualFileSystem.fileType.FileTypeConsumer;
import consulo.virtualFileSystem.fileType.FileTypeFactory;

import javax.annotation.Nonnull;

/**
 * @author VISTALL
 * @since 12.11.13.
 */
@ExtensionImpl
public class WarFileTypeFactory extends FileTypeFactory
{
	@Override
	public void createFileTypes(@Nonnull FileTypeConsumer fileTypeConsumer)
	{
		fileTypeConsumer.consume(JarArchiveFileType.INSTANCE, "war");
	}
}
