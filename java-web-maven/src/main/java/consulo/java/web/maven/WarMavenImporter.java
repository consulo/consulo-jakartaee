package consulo.java.web.maven;

import consulo.compiler.artifact.Artifact;
import consulo.compiler.artifact.ArtifactTemplate;
import consulo.compiler.artifact.ModifiableArtifactModel;
import consulo.content.ContentFolderTypeProvider;
import consulo.content.base.WebResourcesFolderTypeProvider;
import consulo.jakartaee.web.module.extension.JavaWebModuleExtension;
import consulo.javaee.artifact.ExplodedWarArtifactTemplate;
import consulo.javaee.artifact.WarArtifactTemplate;
import consulo.maven.importing.MavenImporterFromBuildPlugin;
import consulo.module.Module;
import consulo.util.collection.MultiMap;
import org.jetbrains.idea.maven.importing.MavenModifiableModelsProvider;
import org.jetbrains.idea.maven.importing.MavenRootModelAdapter;
import org.jetbrains.idea.maven.project.MavenProject;
import org.jetbrains.idea.maven.project.MavenProjectChanges;
import org.jetbrains.idea.maven.project.MavenProjectsProcessorTask;
import org.jetbrains.idea.maven.project.MavenProjectsTree;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * @author VISTALL
 * @since 19.04.14
 */
public class WarMavenImporter extends MavenImporterFromBuildPlugin
{
	public WarMavenImporter()
	{
		super("org.apache.maven.plugins", "maven-war-plugin");
	}

	@Override
	public void preProcess(Module module, MavenProject mavenProject, MavenProjectChanges mavenProjectChanges, MavenModifiableModelsProvider mavenModifiableModelsProvider)
	{
	}

	@Override
	public void process(final MavenModifiableModelsProvider mavenModifiableModelsProvider, Module module, MavenRootModelAdapter mavenRootModelAdapter, MavenProjectsTree mavenProjectsTree, MavenProject mavenProject, MavenProjectChanges mavenProjectChanges, Map<MavenProject, String> mavenProjectStringMap, List<MavenProjectsProcessorTask> mavenProjectsProcessorTasks)
	{
		enableModuleExtension(module, mavenModifiableModelsProvider, JavaWebModuleExtension.class);


		ArtifactTemplate.NewArtifactConfiguration explodedArtifactConfiguration = ExplodedWarArtifactTemplate.doCreateArtifactTemplate(module, mavenModifiableModelsProvider.getPackagingElementResolvingContext());

		ModifiableArtifactModel artifactModel = mavenModifiableModelsProvider.getArtifactModel();

		Artifact artifact = artifactModel.findArtifact(explodedArtifactConfiguration.getArtifactName());
		if(artifact != null)
		{
			artifactModel.removeArtifact(artifact);
		}

		artifact = artifactModel.addArtifact(explodedArtifactConfiguration.getArtifactName(), explodedArtifactConfiguration.getArtifactType(), explodedArtifactConfiguration.getRootElement());

		ArtifactTemplate.NewArtifactConfiguration warArtifactConfiguration = WarArtifactTemplate.doCreateArtifactTemplate(artifact, mavenModifiableModelsProvider.getPackagingElementResolvingContext());

		Artifact warArtifact = artifactModel.findArtifact(warArtifactConfiguration.getArtifactName());
		if(warArtifact != null)
		{
			artifactModel.removeArtifact(warArtifact);
		}

		artifactModel.addArtifact(warArtifactConfiguration.getArtifactName(), warArtifactConfiguration.getArtifactType(), warArtifactConfiguration.getRootElement());
	}

	@Override
	public void getSupportedPackagings(Collection<String> result)
	{
		result.add("war");
	}

	@Override
	public boolean isApplicable(MavenProject mavenProject)
	{
		return super.isApplicable(mavenProject) && mavenProject.getPackaging().equalsIgnoreCase("war");
	}

	@Override
	public void collectContentFolders(MavenProject mavenProject, MultiMap<ContentFolderTypeProvider, String> result)
	{
		result.putValue(WebResourcesFolderTypeProvider.getInstance(), "src/main/webapp");
	}
}
