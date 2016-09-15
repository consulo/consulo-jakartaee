package org.mustbe.consulo.java.web.module;

import java.util.Set;

import org.jetbrains.annotations.NotNull;
import org.mustbe.consulo.java.web.module.extension.JavaWebModuleExtension;
import com.intellij.openapi.roots.ModifiableRootModel;
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
	public void patch(@NotNull ModifiableRootModel model, @NotNull Set<ContentFolderTypeProvider> set)
	{
		JavaWebModuleExtension extension = model.getExtension(JavaWebModuleExtension.class);
		if(extension != null)
		{
			set.add(WebResourcesFolderTypeProvider.getInstance());
		}
	}
}
