/*
 * Copyright 2000-2014 JetBrains s.r.o.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package consulo.jakartaee.webServer.impl.web;

import com.intellij.javaee.artifact.JavaeeArtifactUtil;
import consulo.jakartaee.webServer.impl.context.FacetContextProvider;
import consulo.jakartaee.webServer.impl.context.WebModuleContextProvider;
import consulo.jakartaee.webServer.impl.deployment.DeploymentModel;
import consulo.jakartaee.webServer.impl.web.artifact.WebArtifactUtil;
import consulo.compiler.artifact.Artifact;
import consulo.jakartaee.web.module.extension.JavaWebModuleExtension;
import consulo.javaee.module.extension.JavaEEApplicationModuleExtension;
import consulo.javaee.module.extension.JavaEEModuleExtension;
import consulo.project.Project;
import consulo.util.io.FileUtil;
import consulo.util.lang.StringUtil;

import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import java.io.File;
import java.util.Collection;

/**
 * Created by IntelliJ IDEA.
 * User: michael.golubev
 */
public class WebFacetContextProvider implements FacetContextProvider
{
	@Override
	public Class<? extends JavaEEModuleExtension> getFacetId()
	{
		return JavaWebModuleExtension.class;
	}

	@Override
	public String getDeploymentContext(@Nonnull WebModuleContextProvider webModuleContextProvider, @Nonnull DeploymentModel deploymentModel, @Nonnull JavaEEModuleExtension facet)
	{
		JavaWebModuleExtension webFacet = (JavaWebModuleExtension) facet;

		Artifact artifact = deploymentModel.getArtifact();
		if(artifact != null && JavaeeArtifactUtil.getInstance().isJavaeeApplication(artifact.getArtifactType()))
		{
			return getModuleContext(webModuleContextProvider, artifact, webFacet);
		}

		String serverSpecificContext = StringUtil.nullize(getContextServerSpecific(deploymentModel, webFacet));
		if(serverSpecificContext != null)
		{
			return serverSpecificContext;
		}

		File source = deploymentModel.getDeploymentSource().getFile();
		return source == null ? null : FileUtil.getNameWithoutExtension(source);
	}

	protected String getContextServerSpecific(@Nonnull DeploymentModel deploymentModel, @Nonnull JavaWebModuleExtension facet)
	{
		return getContextServerSpecific(facet);
	}

	protected String getContextServerSpecific(@Nonnull JavaWebModuleExtension facet)
	{
		return null;
	}

	@Nullable
	public static String getModuleContext(@Nonnull WebModuleContextProvider webModuleContextProvider, @Nonnull Artifact earArtifact, @Nonnull final JavaWebModuleExtension webFacet)
	{
		final Project project = webFacet.getModule().getProject();
		final String moduleWebUri = WebArtifactUtil.getInstance().getModuleWebUri(earArtifact, webFacet);
		if(moduleWebUri == null)
		{
			return null;
		}

		final Collection<JavaEEApplicationModuleExtension> earFacets = JavaeeArtifactUtil.getInstance().getFacetsIncludedInArtifact(project, earArtifact, JavaEEApplicationModuleExtension.class);
		for(JavaEEApplicationModuleExtension earFacet : earFacets)
		{
			String contextRoot = webModuleContextProvider.getContext(earFacet, moduleWebUri);
			if(contextRoot != null)
			{
				return contextRoot;
			}
		}
		return null;
	}
}
