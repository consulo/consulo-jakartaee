package consulo.javaee.jsp.roots.impl;

import javax.annotation.Nonnull;

import com.intellij.openapi.module.Module;
import com.intellij.openapi.module.ModuleUtilCore;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.roots.ProjectFileIndex;
import com.intellij.openapi.roots.impl.JavaLanguageLevelPusher;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.pom.java.LanguageLevel;
import consulo.javaee.module.extension.JavaWebModuleExtension;
import consulo.roots.impl.WebResourcesFolderTypeProvider;

/**
 * @author VISTALL
 * @since 15-Jul-17
 */
public class JspWebRooLanguageLevelPusher extends JavaLanguageLevelPusher
{
	@Override
	public LanguageLevel getImmediateValue(@Nonnull Module module)
	{
		LanguageLevel level = super.getImmediateValue(module);
		if(level == null)
		{
			return null;
		}
		JavaWebModuleExtension extension = ModuleUtilCore.getExtension(module, JavaWebModuleExtension.class);
		if(extension == null)
		{
			return null;
		}
		return level;
	}

	@Override
	public boolean acceptsDirectory(@Nonnull VirtualFile file, @Nonnull Project project)
	{
		return ProjectFileIndex.getInstance(project).getContentFolderTypeForFile(file) == WebResourcesFolderTypeProvider.getInstance();
	}
}
