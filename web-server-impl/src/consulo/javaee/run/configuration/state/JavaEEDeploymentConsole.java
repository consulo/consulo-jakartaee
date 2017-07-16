package consulo.javaee.run.configuration.state;

import javax.swing.JPanel;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import com.intellij.execution.Executor;
import com.intellij.execution.executors.DefaultDebugExecutor;
import com.intellij.execution.impl.ConsoleViewImpl;
import com.intellij.execution.process.ProcessHandler;
import com.intellij.execution.runners.RunContentBuilder;
import com.intellij.execution.ui.ExecutionConsole;
import com.intellij.execution.ui.ExecutionConsoleEx;
import com.intellij.execution.ui.RunnerLayoutUi;
import com.intellij.execution.ui.layout.LayoutStateDefaults;
import com.intellij.execution.ui.layout.PlaceInGrid;
import com.intellij.icons.AllIcons;
import com.intellij.javaee.J2EEBundle;
import com.intellij.javaee.appServerIntegrations.AppServerIntegration;
import com.intellij.javaee.deployment.DeploymentView;
import com.intellij.javaee.run.execution.JavaeeConsoleView;
import com.intellij.javaee.serverInstances.J2EEServerInstance;
import com.intellij.openapi.actionSystem.ActionToolbarPosition;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.ToggleAction;
import com.intellij.openapi.project.Project;
import com.intellij.ui.ToolbarDecorator;
import com.intellij.ui.components.JBList;
import com.intellij.ui.content.Content;
import com.intellij.util.ui.JBUI;
import consulo.annotations.RequiredDispatchThread;
import consulo.javaee.JavaEEIcons;

/**
 * @author VISTALL
 * @since 16-Jul-17
 */
public class JavaEEDeploymentConsole extends ConsoleViewImpl implements ExecutionConsoleEx, JavaeeConsoleView
{
	private Executor myExecutor;

	public JavaEEDeploymentConsole(Executor executor, @NotNull Project project)
	{
		super(project, true);
		myExecutor = executor;
	}

	@Override
	public void buildUi(RunnerLayoutUi ui)
	{
		int index = myExecutor == DefaultDebugExecutor.getDebugExecutorInstance() ? 1 : 0;

		((LayoutStateDefaults) ui).initTabDefaults(index, "Server", null);

		final Content consoleContent = ui.createContent(ExecutionConsole.CONSOLE_CONTENT_ID, getComponent(), "Output", null, getPreferredFocusableComponent());
		consoleContent.setCloseable(false);

		RunContentBuilder.addAdditionalConsoleEditorActions(this, consoleContent);

		ui.addContent(consoleContent, index, PlaceInGrid.center, false);

		JBList<Object> deploymentList = new JBList<>("Deploy Artifact");

		ToolbarDecorator decorator = ToolbarDecorator.createDecorator(deploymentList);
		decorator.setToolbarPosition(ActionToolbarPosition.LEFT);
		decorator.setPanelBorder(JBUI.Borders.empty());
		decorator.disableUpDownActions();
		decorator.disableRemoveAction();
		decorator.addExtraAction(new AnAction(J2EEBundle.message("action.name.deploy.selected"), null, AllIcons.Nodes.Deploy)
		{
			@RequiredDispatchThread
			@Override
			public void actionPerformed(@NotNull AnActionEvent anActionEvent)
			{

			}
		});
		decorator.addExtraAction(new AnAction(J2EEBundle.message("action.name.undeploy"), null, AllIcons.Nodes.Undeploy)
		{
			@RequiredDispatchThread
			@Override
			public void actionPerformed(@NotNull AnActionEvent anActionEvent)
			{

			}
		});
		decorator.addExtraAction(new AnAction(J2EEBundle.message("action.name.refresh.deployment.status"), null, AllIcons.Actions.Refresh)
		{
			@RequiredDispatchThread
			@Override
			public void actionPerformed(@NotNull AnActionEvent anActionEvent)
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


		JPanel panel = decorator.createPanel();

		final Content deploymentContent = ui.createContent("JavaEEDeployment", panel, "Deployment", null, panel);
		deploymentContent.setCloseable(false);
		ui.addContent(deploymentContent, index, PlaceInGrid.left, false);
	}

	@Nullable
	@Override
	public String getExecutionConsoleId()
	{
		return "JavaEEDeploymentConsoleView";
	}

	@Override
	public AppServerIntegration getIntegration()
	{
		return null;
	}

	@Override
	public J2EEServerInstance getServerInstance()
	{
		return null;
	}

	@Override
	public ProcessHandler getProcessHandler()
	{
		return null;
	}

	@Nullable
	@Override
	public DeploymentView getDeploymentView()
	{
		return null;
	}
}
