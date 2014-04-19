package org.mustbe.consulo.java.web.maven;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.jetbrains.idea.maven.importing.MavenImporterFromBuildPlugin;
import org.jetbrains.idea.maven.importing.MavenModifiableModelsProvider;
import org.jetbrains.idea.maven.importing.MavenRootModelAdapter;
import org.jetbrains.idea.maven.project.MavenProject;
import org.jetbrains.idea.maven.project.MavenProjectChanges;
import org.jetbrains.idea.maven.project.MavenProjectsProcessorTask;
import org.jetbrains.idea.maven.project.MavenProjectsTree;
import org.mustbe.consulo.java.web.module.extension.JavaWebModuleExtension;
import org.mustbe.consulo.roots.ContentFolderTypeProvider;
import org.mustbe.consulo.roots.impl.WebResourcesFolderTypeProvider;
import com.intellij.openapi.module.Module;
import com.intellij.util.containers.MultiMap;

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
	public void preProcess(
			Module module,
			MavenProject mavenProject,
			MavenProjectChanges mavenProjectChanges,
			MavenModifiableModelsProvider mavenModifiableModelsProvider)
	{

	}

	@Override
	public void process(
			MavenModifiableModelsProvider mavenModifiableModelsProvider,
			Module module,
			MavenRootModelAdapter mavenRootModelAdapter,
			MavenProjectsTree mavenProjectsTree,
			MavenProject mavenProject,
			MavenProjectChanges mavenProjectChanges,
			Map<MavenProject, String> mavenProjectStringMap,
			List<MavenProjectsProcessorTask> mavenProjectsProcessorTasks)
	{
		enableModuleExtension(module, mavenModifiableModelsProvider, JavaWebModuleExtension.class);
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
