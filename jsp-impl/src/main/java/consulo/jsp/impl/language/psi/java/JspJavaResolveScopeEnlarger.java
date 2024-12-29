package consulo.jsp.impl.language.psi.java;

import consulo.annotation.component.ExtensionImpl;
import consulo.content.scope.SearchScope;
import consulo.jsp.impl.language.JspFileType;
import consulo.language.psi.ResolveScopeEnlarger;
import consulo.language.psi.scope.GlobalSearchScope;
import consulo.project.Project;
import consulo.virtualFileSystem.VirtualFile;

import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;

/**
 * @author VISTALL
 * @since 30-Jun-17
 */
@ExtensionImpl
public class JspJavaResolveScopeEnlarger extends ResolveScopeEnlarger
{
	@Nullable
	@Override
	public SearchScope getAdditionalResolveScope(@Nonnull VirtualFile file, Project project)
	{
		if(file.getFileType() == JspFileType.INSTANCE)
		{
			// since file outside source directory (web root) - allow resolve inside file
			return GlobalSearchScope.fileScope(project, file);
		}
		return null;
	}
}
