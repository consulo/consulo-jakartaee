package consulo.javaee.run.configuration.state.view;

import java.util.List;

import javax.swing.JComponent;
import javax.swing.JList;

import javax.annotation.Nonnull;
import com.intellij.icons.AllIcons;
import com.intellij.javaee.J2EEBundle;
import com.intellij.javaee.deployment.DeploymentManager;
import com.intellij.javaee.deployment.DeploymentModel;
import com.intellij.javaee.deployment.DeploymentStatus;
import com.intellij.javaee.deployment.DeploymentView;
import com.intellij.openapi.actionSystem.ActionToolbarPosition;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.ToggleAction;
import com.intellij.ui.CollectionListModel;
import com.intellij.ui.ColoredListCellRenderer;
import com.intellij.ui.ToolbarDecorator;
import com.intellij.ui.components.JBList;
import com.intellij.util.ui.JBUI;
import consulo.annotations.RequiredDispatchThread;
import consulo.javaee.JavaEEIcons;
import consulo.javaee.run.configuration.JavaEEConfigurationImpl;

/**
 * @author VISTALL
 * @since 16-Jul-17
 */
public class DeploymentViewImpl implements DeploymentView
{
	private JavaEEConfigurationImpl myConfiguration;
	private JBList<DeploymentModel> myDeploymentList;

	public DeploymentViewImpl(JavaEEConfigurationImpl configuration)
	{
		myConfiguration = configuration;
	}

	@Nonnull
	public JComponent getComponent()
	{
		CollectionListModel<DeploymentModel> model = new CollectionListModel<>(myConfiguration.getDeploymentSettings().getDeploymentModels());
		myDeploymentList = new JBList<>(model);
		myDeploymentList.setCellRenderer(new ColoredListCellRenderer<DeploymentModel>()
		{
			@Override
			protected void customizeCellRenderer(@Nonnull JList<? extends DeploymentModel> list, DeploymentModel value, int index, boolean selected, boolean hasFocus)
			{
				DeploymentStatus deploymentStatus = DeploymentManager.getInstance(myConfiguration.getProject()).getDeploymentStatus(value, myConfiguration);

				setIcon(deploymentStatus.getIcon(value.getDeploymentSource().getIcon()));
				append(value.getDeploymentSource().getPresentableName());
				setToolTipText(deploymentStatus.getDescription());
			}
		});

		ToolbarDecorator decorator = ToolbarDecorator.createDecorator(myDeploymentList);
		decorator.setToolbarPosition(ActionToolbarPosition.LEFT);
		decorator.setPanelBorder(JBUI.Borders.empty());
		decorator.disableUpDownActions();
		decorator.disableRemoveAction();
		decorator.addExtraAction(new AnAction(J2EEBundle.message("action.name.deploy.selected"), null, AllIcons.Nodes.Deploy)
		{
			@RequiredDispatchThread
			@Override
			public void actionPerformed(@Nonnull AnActionEvent anActionEvent)
			{

			}
		});
		decorator.addExtraAction(new AnAction(J2EEBundle.message("action.name.undeploy"), null, AllIcons.Nodes.Undeploy)
		{
			@RequiredDispatchThread
			@Override
			public void actionPerformed(@Nonnull AnActionEvent anActionEvent)
			{

			}
		});
		decorator.addExtraAction(new AnAction(J2EEBundle.message("action.name.refresh.deployment.status"), null, AllIcons.Actions.Refresh)
		{
			@RequiredDispatchThread
			@Override
			public void actionPerformed(@Nonnull AnActionEvent anActionEvent)
			{

			}
		});
		decorator.addExtraAction(new ToggleAction(J2EEBundle.message("action.name.build.on.frame.deactivation"), null, JavaEEIcons.BuildOnFrameDeactivation)
		{
			@Override
			public boolean isSelected(AnActionEvent e)
			{
				return false;
			}

			@Override
			public void setSelected(AnActionEvent e, boolean state)
			{

			}
		});

		return decorator.createPanel();
	}

	@Override
	public List<DeploymentModel> getSelectedModels()
	{
		return myDeploymentList.getSelectedValuesList();
	}
}
