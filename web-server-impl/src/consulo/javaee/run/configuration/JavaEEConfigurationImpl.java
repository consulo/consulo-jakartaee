package consulo.javaee.run.configuration;

import java.util.Collection;
import java.util.List;

import org.jdom.Element;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import com.intellij.diagnostic.logging.LogConfigurationPanel;
import com.intellij.execution.ExecutionBundle;
import com.intellij.execution.ExecutionException;
import com.intellij.execution.Executor;
import com.intellij.execution.JavaRunConfigurationExtensionManager;
import com.intellij.execution.configurations.ConfigurationFactory;
import com.intellij.execution.configurations.LocatableConfigurationBase;
import com.intellij.execution.configurations.RunConfiguration;
import com.intellij.execution.configurations.RunProfileState;
import com.intellij.execution.runners.ExecutionEnvironment;
import com.intellij.javaee.J2EEBundle;
import com.intellij.javaee.appServerIntegrations.AppServerIntegration;
import com.intellij.javaee.deployment.DeploymentModel;
import com.intellij.javaee.deployment.DeploymentSettings;
import com.intellij.javaee.run.configuration.CommonStrategy;
import com.intellij.javaee.run.configuration.ServerModel;
import com.intellij.openapi.compiler.CompileScope;
import com.intellij.openapi.module.Module;
import com.intellij.openapi.options.SettingsEditor;
import com.intellij.openapi.options.SettingsEditorGroup;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.projectRoots.Sdk;
import com.intellij.openapi.util.InvalidDataException;
import com.intellij.openapi.util.WriteExternalException;
import com.intellij.packaging.artifacts.Artifact;
import consulo.javaee.bundle.JavaEEServerBundleType;
import consulo.javaee.deployment.impl.JavaEEDeploymentSettingsImpl;
import consulo.javaee.run.configuration.editor.JavaEEDeploymentConfigurationEditor;
import consulo.javaee.run.configuration.editor.JavaEEServerConfigurationEditor;
import consulo.javaee.run.configuration.editor.JavaEEStartupConfigurationEditor;

/**
 * @author VISTALL
 * @since 09-Jul-17
 */
public class JavaEEConfigurationImpl extends LocatableConfigurationBase implements CommonStrategy
{
	private final JavaEEServerBundleType myBundleType;
	private final ServerModel myServerModel;

	private SettingsBean mySettingsBean = new SettingsBean();

	private JavaEEDeploymentSettingsImpl myDeploymentSettings;


	public JavaEEConfigurationImpl(Project project, ConfigurationFactory factory, String name, JavaEEServerBundleType bundleType, ServerModel serverModel)
	{
		super(project, factory, name);
		myBundleType = bundleType;
		myServerModel = serverModel;
		myDeploymentSettings = new JavaEEDeploymentSettingsImpl(project, bundleType, this);

		myServerModel.setCommonModel(this);
	}

	@Override
	public SettingsBean getSettingsBean()
	{
		return mySettingsBean;
	}

	@Override
	public AppServerIntegration getIntegration()
	{
		return myBundleType.getIntegration();
	}

	@Override
	public boolean isLocal()
	{
		return false;
	}

	@Nullable
	@Override
	public Sdk getServerBundle()
	{
		return null;
	}

	@Override
	public String getHost()
	{
		return null;
	}

	@Override
	public int getPort()
	{
		return 0;
	}

	@Override
	public ServerModel getServerModel()
	{
		return myServerModel;
	}

	@Override
	public void initialize()
	{

	}

	@Override
	public Collection<? extends DeploymentModel> getDeploymentModels()
	{
		return myDeploymentSettings.getDeploymentModels();
	}

	@Override
	public List<Artifact> getDeployedArtifacts()
	{
		return myDeploymentSettings.getDeployedArtifacts();
	}

	@Override
	public List<Artifact> getArtifactsToBuild()
	{
		return myDeploymentSettings.getArtifacts2Build();
	}

	@Nullable
	@Override
	public DeploymentModel getDeploymentModel(Artifact artifact)
	{
		return myDeploymentSettings.getOrCreateModel(artifact);
	}

	@Override
	public void setUrlToOpenInBrowser(@Nullable String newUrl)
	{

	}

	@NotNull
	@Override
	public DeploymentSettings getDeploymentSettings()
	{
		return myDeploymentSettings;
	}

	@Override
	public String getUrlToOpenInBrowser()
	{
		return null;
	}

	@Override
	public CompileScope getCompileScope()
	{
		return null;
	}

	@Override
	public void readExternal(Element element) throws InvalidDataException
	{
		super.readExternal(element);
		myDeploymentSettings.readExternal(element);
	}

	@Override
	public void writeExternal(Element element) throws WriteExternalException
	{
		super.writeExternal(element);
		myDeploymentSettings.writeExternal(element);
	}

	@NotNull
	@Override
	@SuppressWarnings("unchecked")
	public SettingsEditor<? extends RunConfiguration> getConfigurationEditor()
	{
		SettingsEditorGroup group = new SettingsEditorGroup<>();
		group.addEditor(J2EEBundle.message("title.run.configuration.editor.server"), new JavaEEServerConfigurationEditor(myBundleType, myServerModel));
		group.addEditor(J2EEBundle.message("title.run.configuration.editor.deployment"), new JavaEEDeploymentConfigurationEditor(getProject(), myBundleType, this));
		group.addEditor(ExecutionBundle.message("logs.tab.title"), new LogConfigurationPanel<>());
		JavaRunConfigurationExtensionManager.getInstance().appendEditors(this, group);
		group.addEditor("Startup/Connection", new JavaEEStartupConfigurationEditor());
		return group;
	}

	@NotNull
	@Override
	public Module[] getModules()
	{
		return new Module[0];
	}

	@Nullable
	@Override
	public RunProfileState getState(@NotNull Executor executor, @NotNull ExecutionEnvironment environment) throws ExecutionException
	{
		return null;
	}
}
