package consulo.javaee.run.configuration;

import java.util.ArrayList;
import java.util.List;

import org.jdom.Element;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import com.intellij.javaee.deployment.DeploymentModel;
import com.intellij.javaee.deployment.DeploymentSettings;
import com.intellij.openapi.project.Project;
import com.intellij.packaging.artifacts.Artifact;
import com.intellij.remoteServer.configuration.deployment.DeploymentSource;
import com.intellij.remoteServer.configuration.deployment.DeploymentSourceType;

/**
 * @author VISTALL
 * @since 15-Jul-17
 */
public class JavaEEDeploymentSettings implements DeploymentSettings
{
	private final List<DeploymentModel> myItems = new ArrayList<>();
	private final Project myProject;

	public JavaEEDeploymentSettings(Project project)
	{
		myProject = project;
	}

	@NotNull
	@Override
	public List<DeploymentModel> getDeploymentModels()
	{
		return myItems;
	}

	@Override
	public void removeModel(@NotNull DeploymentModel model)
	{
		myItems.remove(model);
	}

	@Nullable
	@Override
	public DeploymentModel getModel(@NotNull Artifact artifact)
	{
		return null;
	}

	@NotNull
	@Override
	public List<Artifact> getDeployedArtifacts()
	{
		return null;
	}

	@NotNull
	@Override
	public List<Artifact> getArtifacts2Build()
	{
		return null;
	}

	@NotNull
	@Override
	public DeploymentModel getOrCreateModel(@NotNull Artifact artifact)
	{
		return null;
	}

	@NotNull
	@Override
	public DeploymentModel getOrCreateModel(@NotNull DeploymentSource source)
	{
		return null;
	}

	public void readExternal(Element element)
	{
		for(Element child : element.getChildren("deploy-item"))
		{
			Element sourceElement = child.getChild("source");
			if(sourceElement == null)
			{
				continue;
			}

			DeploymentSourceType<?> deploymentSourceType = findDeploymentSourceType(sourceElement.getAttributeValue("id"));
			if(deploymentSourceType == null)
			{
				continue;
			}
			DeploymentSource deploymentSource = deploymentSourceType.load(element, myProject);


		}
	}

	@Nullable
	private static DeploymentSourceType<?> findDeploymentSourceType(@Nullable String id)
	{
		for(DeploymentSourceType<?> type : DeploymentSourceType.EP_NAME.getExtensions())
		{
			if(type.getId().equals(id))
			{
				return type;
			}
		}
		return null;
	}

	public void writeExternal(Element element)
	{

	}
}
