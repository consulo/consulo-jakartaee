package consulo.javaee.module;

import java.util.Set;

import javax.annotation.Nonnull;
import com.intellij.openapi.roots.ModifiableRootModel;
import consulo.javaee.module.extension.JavaWebModuleExtension;
import consulo.roots.ContentFolderSupportPatcher;
import consulo.roots.ContentFolderTypeProvider;
import consulo.roots.impl.WebResourcesFolderTypeProvider;

/**
 * @author VISTALL
 * @since 19.04.14
 */
public class JavaWebContentFolderSupportPatcher implements ContentFolderSupportPatcher
{
	@Override
	public void patch(@Nonnull ModifiableRootModel model, @Nonnull Set<ContentFolderTypeProvider> set)
	{
		JavaWebModuleExtension extension = model.getExtension(JavaWebModuleExtension.class);
		if(extension != null)
		{
			set.add(WebResourcesFolderTypeProvider.getInstance());
		}
	}
}
