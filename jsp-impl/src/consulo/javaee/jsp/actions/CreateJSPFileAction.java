package consulo.javaee.jsp.actions;

import com.intellij.ide.IdeView;
import com.intellij.ide.actions.CreateFileFromTemplateAction;
import com.intellij.ide.actions.CreateFileFromTemplateDialog;
import com.intellij.javaee.J2EEBundle;
import com.intellij.javaee.J2EEFileTemplateNames;
import com.intellij.openapi.actionSystem.CommonDataKeys;
import com.intellij.openapi.actionSystem.DataContext;
import com.intellij.openapi.actionSystem.LangDataKeys;
import com.intellij.openapi.module.Module;
import com.intellij.openapi.module.ModuleUtilCore;
import com.intellij.openapi.project.DumbAware;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.roots.ProjectFileIndex;
import com.intellij.psi.PsiDirectory;
import consulo.javaee.JavaEEIcons;
import consulo.javaee.module.extension.JavaWebModuleExtension;
import consulo.roots.ContentFolderTypeProvider;
import consulo.roots.impl.WebResourcesFolderTypeProvider;

/**
 * @author VISTALL
 * @since 28-Jun-17
 */
public class CreateJSPFileAction extends CreateFileFromTemplateAction implements DumbAware
{
	public CreateJSPFileAction()
	{
		super("JSP", null, JavaEEIcons.Jsp);
	}

	@Override
	protected void buildDialog(Project project, PsiDirectory directory, CreateFileFromTemplateDialog.Builder builder)
	{
		builder.setTitle(J2EEBundle.message("dialog.title.jsp.file"));

		builder.addKind("JSP", JavaEEIcons.Jsp, J2EEFileTemplateNames.JSP_FILE);
		builder.addKind("JSPX", JavaEEIcons.Jspx, J2EEFileTemplateNames.JSPX_FILE);
	}

	@Override
	protected String getActionName(PsiDirectory directory, String newName, String templateName)
	{
		return "Create File";
	}

	@Override
	protected boolean isAvailable(DataContext dataContext)
	{
		final Project project = CommonDataKeys.PROJECT.getData(dataContext);
		final IdeView view = LangDataKeys.IDE_VIEW.getData(dataContext);
		if(project == null || view == null || view.getDirectories().length == 0)
		{
			return false;
		}

		final Module module = LangDataKeys.MODULE.getData(dataContext);
		if(module == null)
		{
			return false;
		}

		if(ModuleUtilCore.getExtension(module, JavaWebModuleExtension.class) == null)
		{
			return false;
		}

		ProjectFileIndex projectFileIndex = ProjectFileIndex.getInstance(project);
		for(PsiDirectory directory : view.getDirectories())
		{
			ContentFolderTypeProvider provider = projectFileIndex.getContentFolderTypeForFile(directory.getVirtualFile());
			if(WebResourcesFolderTypeProvider.getInstance() == provider)
			{
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean startInWriteAction()
	{
		return false;
	}
}
