package consulo.javaee.run.configuration.state.view;

import com.intellij.javaee.J2EEBundle;
import consulo.application.AllIcons;
import consulo.jakartaee.webServer.impl.deployment.DeploymentManager;
import consulo.jakartaee.webServer.impl.deployment.DeploymentModel;
import consulo.jakartaee.webServer.impl.deployment.DeploymentStatus;
import consulo.jakartaee.webServer.impl.deployment.DeploymentView;
import consulo.javaee.run.configuration.JavaEEConfigurationImpl;
import consulo.platform.base.icon.PlatformIconGroup;
import consulo.ui.annotation.RequiredUIAccess;
import consulo.ui.ex.action.ActionToolbarPosition;
import consulo.ui.ex.action.AnAction;
import consulo.ui.ex.action.AnActionEvent;
import consulo.ui.ex.action.ToggleAction;
import consulo.ui.ex.awt.*;

import jakarta.annotation.Nonnull;

import javax.swing.*;
import java.util.List;

/**
 * @author VISTALL
 * @since 16-Jul-17
 */
public class DeploymentViewImpl implements DeploymentView {
    private JavaEEConfigurationImpl myConfiguration;
    private JBList<DeploymentModel> myDeploymentList;

    public DeploymentViewImpl(JavaEEConfigurationImpl configuration) {
        myConfiguration = configuration;
    }

    @Nonnull
    public JComponent getComponent() {
        CollectionListModel<DeploymentModel> model =
            new CollectionListModel<>(myConfiguration.getDeploymentSettings().getDeploymentModels());
        myDeploymentList = new JBList<>(model);
        myDeploymentList.setCellRenderer(new ColoredListCellRenderer<DeploymentModel>() {
            @Override
            protected void customizeCellRenderer(
                @Nonnull JList<? extends DeploymentModel> list,
                DeploymentModel value,
                int index,
                boolean selected,
                boolean hasFocus
            ) {
                DeploymentStatus deploymentStatus =
                    DeploymentManager.getInstance(myConfiguration.getProject()).getDeploymentStatus(value, myConfiguration);

                setIcon(deploymentStatus.getIcon(value.getDeploymentSource().getIcon()));
                append(value.getDeploymentSource().getPresentableName().get());
                setToolTipText(deploymentStatus.getDescription());
            }
        });

        ToolbarDecorator decorator = ToolbarDecorator.createDecorator(myDeploymentList);
        decorator.setToolbarPosition(ActionToolbarPosition.LEFT);
        decorator.setPanelBorder(JBUI.Borders.empty());
        decorator.disableUpDownActions();
        decorator.disableRemoveAction();
        decorator.addExtraAction(new AnAction(J2EEBundle.message("action.name.deploy.selected"), null, AllIcons.Nodes.Deploy) {
            @RequiredUIAccess
            @Override
            public void actionPerformed(@Nonnull AnActionEvent anActionEvent) {
            }
        });
        decorator.addExtraAction(new AnAction(J2EEBundle.message("action.name.undeploy"), null, AllIcons.Nodes.Undeploy) {
            @RequiredUIAccess
            @Override
            public void actionPerformed(@Nonnull AnActionEvent anActionEvent) {
            }
        });
        decorator.addExtraAction(new AnAction(J2EEBundle.message("action.name.refresh.deployment.status"), null, AllIcons.Actions.Refresh) {
            @RequiredUIAccess
            @Override
            public void actionPerformed(@Nonnull AnActionEvent anActionEvent) {
            }
        });
        decorator.addExtraAction(new ToggleAction(
            J2EEBundle.message("action.name.build.on.frame.deactivation"),
            null,
            PlatformIconGroup.actionsCompile()
        ) {
            @Override
            public boolean isSelected(AnActionEvent e) {
                return false;
            }

            @Override
            public void setSelected(AnActionEvent e, boolean state) {
            }
        });

        return decorator.createPanel();
    }

    @Override
    public List<DeploymentModel> getSelectedModels() {
        return myDeploymentList.getSelectedValuesList();
    }
}
