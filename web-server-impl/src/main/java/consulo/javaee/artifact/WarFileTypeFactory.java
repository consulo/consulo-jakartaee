package consulo.javaee.artifact;

import com.intellij.java.language.impl.JarArchiveFileType;
import consulo.annotation.component.ExtensionImpl;
import consulo.virtualFileSystem.fileType.FileTypeConsumer;
import consulo.virtualFileSystem.fileType.FileTypeFactory;

import jakarta.annotation.Nonnull;

/**
 * @author VISTALL
 * @since 2013-11-12
 */
@ExtensionImpl
public class WarFileTypeFactory extends FileTypeFactory {
    @Override
    public void createFileTypes(@Nonnull FileTypeConsumer fileTypeConsumer) {
        fileTypeConsumer.consume(JarArchiveFileType.INSTANCE, "war");
    }
}
