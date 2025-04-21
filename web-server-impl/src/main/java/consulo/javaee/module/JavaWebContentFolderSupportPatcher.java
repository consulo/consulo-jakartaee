package consulo.javaee.module;

import consulo.annotation.component.ExtensionImpl;
import consulo.content.ContentFolderTypeProvider;
import consulo.content.base.WebResourcesFolderTypeProvider;
import consulo.jakartaee.web.module.extension.JavaWebModuleExtension;
import consulo.module.content.layer.ContentFolderSupportPatcher;
import consulo.module.content.layer.ModifiableRootModel;

import jakarta.annotation.Nonnull;

import java.util.Set;

/**
 * @author VISTALL
 * @since 2014-04-19
 */
@ExtensionImpl
public class JavaWebContentFolderSupportPatcher implements ContentFolderSupportPatcher {
    @Override
    public void patch(@Nonnull ModifiableRootModel model, @Nonnull Set<ContentFolderTypeProvider> set) {
        JavaWebModuleExtension extension = model.getExtension(JavaWebModuleExtension.class);
        if (extension != null) {
            set.add(WebResourcesFolderTypeProvider.getInstance());
        }
    }
}
