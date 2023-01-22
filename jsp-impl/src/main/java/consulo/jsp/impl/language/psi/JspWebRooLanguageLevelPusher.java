package consulo.jsp.impl.language.psi;

import com.intellij.java.analysis.impl.JavaLanguageLevelPusher;
import com.intellij.java.language.LanguageLevel;
import consulo.annotation.component.ExtensionImpl;
import consulo.content.base.WebResourcesFolderTypeProvider;
import consulo.jakartaee.web.module.extension.JavaWebModuleExtension;
import consulo.language.util.ModuleUtilCore;
import consulo.module.Module;
import consulo.module.content.ProjectFileIndex;
import consulo.project.Project;
import consulo.virtualFileSystem.VirtualFile;

import javax.annotation.Nonnull;

/**
 * @author VISTALL
 * @since 15-Jul-17
 */
@ExtensionImpl
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
		return ProjectFileIndex.getInstance(project).getContentFolderTypeForFile(file) instanceof WebResourcesFolderTypeProvider;
	}
}
