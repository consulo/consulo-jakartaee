package consulo.javaee.run.configuration.state;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
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
import com.intellij.javaee.appServerIntegrations.AppServerIntegration;
import com.intellij.javaee.deployment.DeploymentView;
import com.intellij.javaee.run.execution.JavaeeConsoleView;
import com.intellij.javaee.serverInstances.J2EEServerInstance;
import com.intellij.openapi.project.Project;
import com.intellij.ui.content.Content;
import consulo.javaee.run.configuration.JavaEEConfigurationImpl;
import consulo.javaee.run.configuration.state.view.DeploymentViewImpl;

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
