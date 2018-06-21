package consulo.javaee.deployment.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nonnull;

import org.jdom.Element;

import javax.annotation.Nullable;
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

	public void addModel(@Nonnull DeploymentModel model)
	{
		myItems.add(model);
	}

	public void removeAll()
	{
		myItems.clear();
	}

	@Nonnull
	@Override
	public List<DeploymentModel> getDeploymentModels()
	{
		return myItems;
	}

	@Override
	public void removeModel(@Nonnull DeploymentModel model)
	{
		myItems.remove(model);
	}

	@Nullable
	@Override
	public DeploymentModel getModel(@Nonnull Artifact artifact)
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

	@Nonnull
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

	@Nonnull
	@Override
	public List<Artifact> getArtifacts2Build()
	{
		return getDeployedArtifacts();
	}

	@Nonnull
	@Override
	public DeploymentModel getOrCreateModel(@Nonnull Artifact artifact)
	{
		return getOrCreateModel(new ArtifactDeploymentSourceImpl(ArtifactPointerUtil.getPointerManager(myProject).create(artifact)));
	}

	@Nonnull
	@Override
	public DeploymentModel getOrCreateModel(@Nonnull DeploymentSource source)
	{
		DeploymentModel newDeploymentModel = myBundleType.createNewDeploymentModel(myCommonModel, source);
		myItems.add(newDeploymentModel);
		return newDeploymentModel;
	}

	public void readExternal(Element element)
	{
		myItems.clear();

		Element deploymentElement = element.getChild("deployment");
		if(deploymentElement == null)
		{
			return;
		}

		for(Element childElement : deploymentElement.getChildren())
		{
			DeploymentSourceType<?> deploymentSourceType = findDeploymentSourceType(childElement.getName());
			if(deploymentSourceType == null)
			{
				continue;
			}

			DeploymentSource deploymentSource = deploymentSourceType.load(childElement, myProject);

			DeploymentModel model = myBundleType.createNewDeploymentModel(myCommonModel, deploymentSource);

			Element settingsElement = childElement.getChild("settings");
			if(settingsElement != null)
			{
				model.readExternal(settingsElement);
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
		if(myItems.isEmpty())
		{
			return;
		}

		Element deploymentElement = new Element("deployment");
		rootElement.addContent(deploymentElement);

		for(DeploymentModel item : myItems)
		{
			DeploymentSource deploymentSource = item.getDeploymentSource();
			DeploymentSourceType type = deploymentSource.getType();

			Element childElement = new Element(type.getId());
			deploymentElement.addContent(childElement);

			type.save(deploymentSource, childElement);

			Element settingsElement = new Element("settings");
			item.writeExternal(settingsElement);

			childElement.addContent(settingsElement);
		}
	}
}
