package consulo.javaee.run.configuration.state;

import consulo.jakartaee.webServer.impl.appServerIntegrations.AppServerIntegration;
import consulo.jakartaee.webServer.impl.deployment.DeploymentView;
import consulo.jakartaee.webServer.impl.run.execution.JavaeeConsoleView;
import consulo.jakartaee.webServer.impl.serverInstances.J2EEServerInstance;
import consulo.execution.debug.DefaultDebugExecutor;
import consulo.execution.executor.Executor;
import consulo.execution.runner.RunContentBuilder;
import consulo.execution.ui.ExecutionConsole;
import consulo.execution.ui.ExecutionConsoleEx;
import consulo.execution.ui.layout.LayoutStateDefaults;
import consulo.execution.ui.layout.PlaceInGrid;
import consulo.execution.ui.layout.RunnerLayoutUi;
import consulo.ide.impl.idea.execution.impl.ConsoleViewImpl;
import consulo.javaee.run.configuration.JavaEEConfigurationImpl;
import consulo.javaee.run.configuration.state.view.DeploymentViewImpl;
import consulo.process.ProcessHandler;
import consulo.project.Project;
import consulo.ui.ex.content.Content;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/**
 * @author VISTALL
 * @since 16-Jul-17
 */
public class JavaEEDeploymentConsole extends ConsoleViewImpl implements ExecutionConsoleEx, JavaeeConsoleView
{
	private final Executor myExecutor;
	private final JavaEEConfigurationImpl myConfiguration;

	private final DeploymentViewImpl myDeploymentView;

	public JavaEEDeploymentConsole(Executor executor, JavaEEConfigurationImpl configuration, @Nonnull Project project)
	{
		super(project, true);
		myExecutor = executor;
		myConfiguration = configuration;
		myDeploymentView = new DeploymentViewImpl(myConfiguration);
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


		final Content deploymentContent = ui.createContent("JavaEEDeployment", myDeploymentView.getComponent(), "Deployment", null, null);
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
		return myDeploymentView;
	}
}
