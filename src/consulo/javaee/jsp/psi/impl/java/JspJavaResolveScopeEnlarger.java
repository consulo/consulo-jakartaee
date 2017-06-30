package consulo.javaee.jsp.psi.impl.java;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.ResolveScopeEnlarger;
import com.intellij.psi.search.GlobalSearchScope;
import com.intellij.psi.search.SearchScope;
import consulo.javaee.jsp.JspFileType;

/**
 * @author VISTALL
 * @since 30-Jun-17
 */
public class JspJavaResolveScopeEnlarger extends ResolveScopeEnlarger
{
	@Nullable
	@Override
	public SearchScope getAdditionalResolveScope(@NotNull VirtualFile file, Project project)
	{
		if(file.getFileType() == JspFileType.INSTANCE)
		{
			// since file outside source directory (web root) - allow resolve inside file
			return GlobalSearchScope.fileScope(project, file);
		}
		return null;
	}
}
