package org.mustbe.consulo.java.web.module;

import java.util.Set;

import org.jetbrains.annotations.NotNull;
import org.mustbe.consulo.java.web.module.extension.JavaWebModuleExtension;
import org.mustbe.consulo.roots.ContentFolderSupportPatcher;
import org.mustbe.consulo.roots.ContentFolderTypeProvider;
import org.mustbe.consulo.roots.impl.WebResourcesFolderTypeProvider;
import com.intellij.openapi.roots.ModifiableRootModel;

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
