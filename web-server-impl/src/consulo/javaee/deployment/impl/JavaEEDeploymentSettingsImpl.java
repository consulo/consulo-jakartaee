package consulo.javaee.deployment.impl;

import java.util.ArrayList;
import java.util.List;

import org.jdom.Element;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import com.intellij.javaee.deployment.DeploymentModel;
import com.intellij.javaee.deployment.DeploymentSettings;
import com.intellij.javaee.run.configuration.CommonModel;
import com.intellij.openapi.project.Project;
import com.intellij.packaging.artifacts.Artifact;
import com.intellij.remoteServer.configuration.deployment.DeploymentSource;
import com.intellij.remoteServer.configuration.deployment.DeploymentSourceType;
import com.intellij.remoteServer.impl.configuration.deploySource.impl.ArtifactDeploymentSourceImpl;
import consulo.javaee.bundle.JavaEEServerBundleType;
import consulo.packaging.artifacts.ArtifactPointerUtil;

/**
 * @author VISTALL
 * @since 15-Jul-17
 */
public class JavaEEDeploymentSettingsImpl implements DeploymentSettings
{
	private final List<DeploymentModel> myItems = new ArrayList<>();
	private final Project myProject;
	private final JavaEEServerBundleType myBundleType;
	private final CommonModel myCommonModel;

	public JavaEEDeploymentSettingsImpl(Project project, JavaEEServerBundleType bundleType, CommonModel commonModel)
	{
		myProject = project;
		myBundleType = bundleType;
		myCommonModel = commonModel;
	}

	public void addModel(@NotNull DeploymentModel model)
	{
		myItems.add(model);
	}

	public void removeAll()
	{
		myItems.clear();
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
		for(DeploymentModel item : myItems)
		{
			Artifact other = item.getArtifact();
			if(artifact.equals(other))
			{
				return item;
			}
		}
		return null;
	}

	@NotNull
	@Override
	public List<Artifact> getDeployedArtifacts()
	{
		List<Artifact> artifacts = new ArrayList<>();
		for(DeploymentModel item : myItems)
		{
			Artifact artifact = item.getArtifact();
			if(artifact != null)
			{
				artifacts.add(artifact);
			}
		}
		return artifacts;
	}

	@NotNull
	@Override
	public List<Artifact> getArtifacts2Build()
	{
		return getDeployedArtifacts();
	}

	@NotNull
	@Override
	public DeploymentModel getOrCreateModel(@NotNull Artifact artifact)
	{
		return getOrCreateModel(new ArtifactDeploymentSourceImpl(ArtifactPointerUtil.getPointerManager(myProject).create(artifact)));
	}

	@NotNull
	@Override
	public DeploymentModel getOrCreateModel(@NotNull DeploymentSource source)
	{
		DeploymentModel newDeploymentModel = myBundleType.createNewDeploymentModel(myCommonModel, source);
		myItems.add(newDeploymentModel);
		return newDeploymentModel;
	}

	public void readExternal(Element element)
	{
		myItems.clear();

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
			DeploymentSource deploymentSource = deploymentSourceType.load(sourceElement, myProject);

			DeploymentModel model = myBundleType.createNewDeploymentModel(myCommonModel, deploymentSource);

			Element modelElement = child.getChild("model");
			if(modelElement != null)
			{
				model.readExternal(modelElement);
			}

			myItems.add(model);
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

	@SuppressWarnings("unchecked")
	public void writeExternal(Element rootElement)
	{
		for(DeploymentModel item : myItems)
		{
			Element element = new Element("deploy-item");
			rootElement.addContent(element);

			DeploymentSource deploymentSource = item.getDeploymentSource();
			DeploymentSourceType type = deploymentSource.getType();

			Element sourceElement = new Element("source");
			element.addContent(sourceElement);
			sourceElement.setAttribute("id", type.getId());

			Element modelElement = new Element("model");
			item.writeExternal(modelElement);

			element.addContent(modelElement);

			type.save(deploymentSource, sourceElement);
		}
	}
}
