package consulo.jsp.impl.action;

import com.intellij.javaee.J2EEBundle;
import com.intellij.javaee.J2EEFileTemplateNames;
import consulo.annotation.component.ActionImpl;
import consulo.annotation.component.ActionParentRef;
import consulo.annotation.component.ActionRef;
import consulo.annotation.component.ActionRefAnchor;
import consulo.application.dumb.DumbAware;
import consulo.content.ContentFolderTypeProvider;
import consulo.content.base.WebResourcesFolderTypeProvider;
import consulo.dataContext.DataContext;
import consulo.ide.IdeView;
import consulo.ide.action.CreateFileFromTemplateAction;
import consulo.ide.action.CreateFileFromTemplateDialog;
import consulo.jakartaee.web.module.extension.JavaWebModuleExtension;
import consulo.javaee.JavaEEIcons;
import consulo.language.editor.CommonDataKeys;
import consulo.language.editor.LangDataKeys;
import consulo.language.psi.PsiDirectory;
import consulo.language.util.ModuleUtilCore;
import consulo.module.Module;
import consulo.module.content.ProjectFileIndex;
import consulo.project.Project;

/**
 * @author VISTALL
 * @since 28-Jun-17
 */
@ActionImpl(id = "CreateJSPAction", parents = @ActionParentRef(value = @ActionRef(id = "NewGroup1"), anchor = ActionRefAnchor.FIRST))
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
		final Project project = dataContext.getData(CommonDataKeys.PROJECT);
		final IdeView view = dataContext.getData(IdeView.KEY);
		if(project == null || view == null || view.getDirectories().length == 0)
		{
			return false;
		}

		final Module module = dataContext.getData(LangDataKeys.MODULE);
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
			if(provider instanceof WebResourcesFolderTypeProvider)
			{
				return true;
			}
		}
		return false;
	}
}
