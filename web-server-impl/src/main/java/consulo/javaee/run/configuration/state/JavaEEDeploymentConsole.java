package consulo.javaee.run.configuration.state;

import consulo.disposer.Disposer;
import consulo.execution.debug.DefaultDebugExecutor;
import consulo.execution.executor.Executor;
import consulo.execution.runner.RunContentBuilder;
import consulo.execution.ui.ExecutionConsole;
import consulo.execution.ui.ExecutionConsoleEx;
import consulo.execution.ui.console.ConsoleView;
import consulo.execution.ui.console.TextConsoleBuilder;
import consulo.execution.ui.console.TextConsoleBuilderFactory;
import consulo.execution.ui.layout.LayoutStateDefaults;
import consulo.execution.ui.layout.PlaceInGrid;
import consulo.execution.ui.layout.RunnerLayoutUi;
import consulo.jakartaee.webServer.impl.appServerIntegrations.AppServerIntegration;
import consulo.jakartaee.webServer.impl.deployment.DeploymentView;
import consulo.jakartaee.webServer.impl.run.execution.JavaeeConsoleView;
import consulo.jakartaee.webServer.impl.serverInstances.J2EEServerInstance;
import consulo.javaee.run.configuration.JavaEEConfigurationImpl;
import consulo.javaee.run.configuration.state.view.DeploymentViewImpl;
import consulo.process.ProcessHandler;
import consulo.project.Project;
import consulo.ui.ex.content.Content;
import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;

import javax.swing.*;

/**
 * @author VISTALL
 * @since 2017-07-16
 */
public class JavaEEDeploymentConsole implements ExecutionConsoleEx, JavaeeConsoleView {
    private final Executor myExecutor;
    @Nonnull
    private final Project myProject;

    private final DeploymentViewImpl myDeploymentView;

    private ConsoleView myConsoleView;

    public JavaEEDeploymentConsole(Executor executor, JavaEEConfigurationImpl configuration, @Nonnull Project project) {
        myExecutor = executor;
        myProject = project;
        myDeploymentView = new DeploymentViewImpl(configuration);
    }

    public ConsoleView getConsoleView() {
        return myConsoleView;
    }

    @Override
    public void buildUi(RunnerLayoutUi ui) {
        TextConsoleBuilder builder = TextConsoleBuilderFactory.getInstance().createBuilder(myProject);
        builder.setViewer(true);

        myConsoleView = builder.getConsole();

        Disposer.register(this, myConsoleView);

        int index = myExecutor == DefaultDebugExecutor.getDebugExecutorInstance() ? 1 : 0;

        ((LayoutStateDefaults)ui).initTabDefaults(index, "Server", null);

        Content consoleContent =
            ui.createContent(ExecutionConsole.CONSOLE_CONTENT_ID, getComponent(), "Output", null, getPreferredFocusableComponent());
        consoleContent.setCloseable(false);

        RunContentBuilder.addAdditionalConsoleEditorActions(this, consoleContent);

        ui.addContent(consoleContent, index, PlaceInGrid.center, false);


        Content deploymentContent = ui.createContent("JavaEEDeployment", myDeploymentView.getComponent(), "Deployment", null, null);
        deploymentContent.setCloseable(false);
        ui.addContent(deploymentContent, index, PlaceInGrid.left, false);
    }

    @Override
    public JComponent getComponent() {
        return myConsoleView.getComponent();
    }

    @Override
    public JComponent getPreferredFocusableComponent() {
        return myConsoleView.getPreferredFocusableComponent();
    }

    @Nullable
    @Override
    public String getExecutionConsoleId() {
        return "JavaEEDeploymentConsoleView";
    }

    @Override
    public AppServerIntegration getIntegration() {
        return null;
    }

    @Override
    public J2EEServerInstance getServerInstance() {
        return null;
    }

    @Override
    public ProcessHandler getProcessHandler() {
        return null;
    }

    @Nullable
    @Override
    public DeploymentView getDeploymentView() {
        return myDeploymentView;
    }

    @Override
    public void dispose() {

    }
}
