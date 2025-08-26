package consulo.jsp.impl.action;

import com.intellij.javaee.J2EEFileTemplateNames;
import consulo.annotation.component.ActionImpl;
import consulo.annotation.component.ActionParentRef;
import consulo.annotation.component.ActionRef;
import consulo.annotation.component.ActionRefAnchor;
import consulo.application.ReadAction;
import consulo.application.dumb.DumbAware;
import consulo.content.ContentFolderTypeProvider;
import consulo.content.base.WebResourcesFolderTypeProvider;
import consulo.dataContext.DataContext;
import consulo.ide.IdeView;
import consulo.ide.action.CreateFileFromTemplateAction;
import consulo.ide.action.CreateFileFromTemplateDialog;
import consulo.jakarta.localize.JakartaLocalize;
import consulo.jakartaee.web.module.extension.JavaWebModuleExtension;
import consulo.javaee.icon.JavaEEApiIconGroup;
import consulo.language.psi.PsiDirectory;
import consulo.language.util.ModuleUtilCore;
import consulo.localize.LocalizeValue;
import consulo.module.Module;
import consulo.module.content.ProjectFileIndex;
import consulo.project.Project;
import jakarta.annotation.Nonnull;

/**
 * @author VISTALL
 * @since 28-Jun-17
 */
@ActionImpl(id = "CreateJSPAction", parents = @ActionParentRef(value = @ActionRef(id = "NewGroup1"), anchor = ActionRefAnchor.FIRST))
public class CreateJSPFileAction extends CreateFileFromTemplateAction implements DumbAware {
    public CreateJSPFileAction() {
        super(JakartaLocalize.actionCreatejspactionText(), JakartaLocalize.actionCreatefilteractionDescription(), JavaEEApiIconGroup.jsp());
    }

    @Override
    protected void buildDialog(Project project, PsiDirectory directory, CreateFileFromTemplateDialog.Builder builder) {
        builder.setTitle(JakartaLocalize.dialogTitleJspFile());

        builder.addKind(JakartaLocalize.actionCreatejspactionJspKind(), JavaEEApiIconGroup.jsp(), J2EEFileTemplateNames.JSP_FILE);
        builder.addKind(JakartaLocalize.actionCreatejspactionJspxKind(), JavaEEApiIconGroup.jspx(), J2EEFileTemplateNames.JSPX_FILE);
    }

    @Nonnull
    @Override
    protected LocalizeValue getActionName(PsiDirectory directory, String newName, String templateName) {
        return JakartaLocalize.actionCreatejspactionTitle();
    }

    @Override
    protected boolean isAvailable(DataContext dataContext) {
        Project project = dataContext.getData(Project.KEY);
        IdeView view = dataContext.getData(IdeView.KEY);
        PsiDirectory[] directories = view == null ? PsiDirectory.EMPTY_ARRAY : ReadAction.compute(view::getDirectories);
        if (project == null || directories.length == 0) {
            return false;
        }

        Module module = dataContext.getData(Module.KEY);
        if (module == null) {
            return false;
        }

        if (ModuleUtilCore.getExtension(module, JavaWebModuleExtension.class) == null) {
            return false;
        }

        ProjectFileIndex projectFileIndex = ProjectFileIndex.getInstance(project);
        for (PsiDirectory directory : directories) {
            ContentFolderTypeProvider provider = projectFileIndex.getContentFolderTypeForFile(directory.getVirtualFile());
            if (provider instanceof WebResourcesFolderTypeProvider) {
                return true;
            }
        }
        return false;
    }
}
