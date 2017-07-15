/*
 * Copyright 2000-2013 JetBrains s.r.o.
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

package com.intellij.javaee.web;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import com.intellij.javaee.artifact.JavaeeArtifactUtil;
import com.intellij.javaee.deployment.DeploymentModel;
import com.intellij.javaee.facet.JavaeeFacetUtil;
import com.intellij.javaee.model.common.JavaeeModelElement;
import com.intellij.javaee.model.xml.web.Filter;
import com.intellij.javaee.model.xml.web.JspConfig;
import com.intellij.javaee.model.xml.web.JspPropertyGroup;
import com.intellij.javaee.model.xml.web.Servlet;
import com.intellij.javaee.model.xml.web.WebApp;
import com.intellij.javaee.run.configuration.CommonModel;
import com.intellij.lang.jsp.JspxFileViewProvider;
import com.intellij.openapi.components.ServiceManager;
import com.intellij.openapi.module.Module;
import com.intellij.openapi.module.ModuleUtilCore;
import com.intellij.openapi.paths.PathReference;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.Pair;
import com.intellij.openapi.util.TextRange;
import com.intellij.openapi.vfs.VfsUtilCore;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.packaging.artifacts.Artifact;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.psi.PsiFileSystemItem;
import com.intellij.psi.jsp.BaseJspFile;
import com.intellij.psi.jsp.JspFile;
import com.intellij.psi.jsp.WebDirectoryElement;
import com.intellij.psi.xml.XmlFile;
import com.intellij.util.Consumer;
import com.intellij.util.Function;
import com.intellij.util.Processor;
import com.intellij.util.SmartList;
import com.intellij.util.UriUtil;
import com.intellij.util.containers.ContainerUtil;
import com.intellij.util.xml.DomUtil;
import com.intellij.util.xml.GenericDomValue;
import consulo.javaee.module.extension.JavaWebModuleExtension;


/**
 * @author mike
 */
public abstract class WebUtil
{
	@NonNls
	public static final String WEB_FRAMEWORK_GROUP_ID = "web";

	public static WebUtil getWebUtil()
	{
		return ServiceManager.getService(WebUtil.class);
	}

	@Nullable
	public static JavaWebModuleExtension getWebFacet(@NotNull JavaeeModelElement element)
	{
		return JavaeeFacetUtil.getInstance().getJavaeeFacet(element, JavaWebModuleExtension.class);
	}

	@Nullable
	public static JavaWebModuleExtension getWebFacet(@NotNull PsiElement element)
	{
		return JavaeeFacetUtil.getInstance().getJavaeeFacet(element, JavaWebModuleExtension.class);
	}

	@Nullable
	public static JavaWebModuleExtension getWebFacet(VirtualFile file, Project project)
	{
		return JavaeeFacetUtil.getInstance().getJavaeeFacet(file, JavaWebModuleExtension.class, project);
	}


	@Nullable
	public static WebRoot findParentWebRoot(final VirtualFile selectedFile, final List<WebRoot> webRoots)
	{
		if(selectedFile == null)
		{
			return null;
		}
		for(WebRoot webRoot : webRoots)
		{
			final VirtualFile file = webRoot.getFile();
			if(file == null)
			{
				continue;
			}
			if(VfsUtilCore.isAncestor(file, selectedFile, false))
			{
				return webRoot;
			}
		}
		return null;
	}

	public static boolean isInsideWebRoots(VirtualFile file, List<WebRoot> webRoots)
	{
		for(WebRoot webRoot : webRoots)
		{
			VirtualFile resDir = webRoot.getFile();
			if(resDir == null || !resDir.isValid())
			{
				continue;
			}
			if(VfsUtilCore.isAncestor(resDir, file, false))
			{
				return true;
			}
		}
		return false;
	}

	public static boolean isInsideWebRoots(VirtualFile file, Project project)
	{
		return findFacetByFileUnderWebRoot(file, project) != null;
	}

	@Nullable
	public static Servlet findServlet(@NotNull JavaWebModuleExtension webFacet, final String name)
	{
		final WebApp root = webFacet.getRoot();
		return root == null ? null : DomUtil.findByName(root.getServlets(), name);
	}

	@Nullable
	public static Filter findFilter(@NotNull JavaWebModuleExtension webFacet, final String name)
	{
		final WebApp root = webFacet.getRoot();
		return root == null ? null : DomUtil.findByName(root.getFilters(), name);
	}

	@NotNull
	public static TextRange trimRange(@NotNull String url, TextRange range)
	{
		for(int i = 0; i < url.length(); i++)
		{
			switch(url.charAt(i))
			{
				case '?':
				case '#':
					final int startOffset = range.getStartOffset();
					return new TextRange(startOffset, startOffset + i);
			}
		}
		return range;
	}


	@NotNull
	public static String trimURL(@NotNull String url)
	{
		return PathReference.trimPath(url);
	}


	public static int indexOfDynamicJSP(String text)
	{
		final int len = text.length();
		for(int i = 0; i < len; i++)
		{
			switch(text.charAt(i))
			{
				case '$':
					if(i + 1 < len && text.charAt(i + 1) == '{')
					{
						return i;
					}
					break;
				case '<':
					if(i + 1 < len && text.charAt(i + 1) == '%')
					{
						return i;
					}
					break;
			}
		}
		return -1;

	}

	public static int getLastPosOfURL(@NotNull String url)
	{
		for(int i = 0; i < url.length(); i++)
		{
			switch(url.charAt(i))
			{
				case '?':
				case '#':
					return i;
			}
		}
		return -1;
	}

	@Nullable
	public abstract JspFile getJspFile(Servlet servlet);

	public abstract WebDirectoryElement createWebDirectoryElement(JavaWebModuleExtension webFacet, @NotNull String path, boolean isDirectory);

	@Nullable
	public abstract WebModel getLocalWebModel(@NotNull XmlFile file);

	@Nullable
	public static String getRelativePath(WebDirectoryElement src, WebDirectoryElement dst)
	{
		final WebDirectoryElement commonAncestor = getCommonAncestor(src, dst);

		if(commonAncestor != null)
		{
			StringBuilder buffer = new StringBuilder();
			if(src != commonAncestor)
			{
				while(src.getParent() != commonAncestor)
				{
					buffer.append("").append('/');
					src = src.getParentDirectory();
					assert src != null;
				}
			}
			buffer.append(getRelativePathFromAncestor(dst, commonAncestor));
			return buffer.toString();
		}

		return null;
	}

	public static String getRelativePathFromAncestor(WebDirectoryElement file, WebDirectoryElement ancestor)
	{

		int length = 0;
		WebDirectoryElement parent = file;

		while(true)
		{
			if(parent == null)
			{
				return null;
			}
			if(parent.equals(ancestor))
			{
				break;
			}
			if(length > 0)
			{
				length++;
			}
			length += parent.getName().length();
			parent = parent.getParentDirectory();
		}

		char[] chars = new char[length];
		int index = chars.length;
		parent = file;

		while(true)
		{
			if(parent.equals(ancestor))
			{
				break;
			}
			if(index < length)
			{
				chars[--index] = '/';
			}
			String name = parent.getName();
			for(int i = name.length() - 1; i >= 0; i--)
			{
				chars[--index] = name.charAt(i);
			}
			parent = parent.getParentDirectory();
		}
		return new String(chars);
	}

	public static WebDirectoryElement getCommonAncestor(WebDirectoryElement file1, WebDirectoryElement file2)
	{
		WebDirectoryElement[] path1 = getPathComponents(file1);
		WebDirectoryElement[] path2 = getPathComponents(file2);

		WebDirectoryElement[] minLengthPath;
		WebDirectoryElement[] maxLengthPath;
		if(path1.length < path2.length)
		{
			minLengthPath = path1;
			maxLengthPath = path2;
		}
		else
		{
			minLengthPath = path2;
			maxLengthPath = path1;
		}

		int lastEqualIdx = -1;
		for(int i = 0; i < minLengthPath.length; i++)
		{
			if(minLengthPath[i].equals(maxLengthPath[i]))
			{
				lastEqualIdx = i;
			}
			else
			{
				break;
			}
		}
		return lastEqualIdx != -1 ? minLengthPath[lastEqualIdx] : null;
	}

	/**
	 * Gets an array of files representing paths from root to the passed file.
	 *
	 * @param file the file
	 * @return virtual files which represents paths from root to the passed file
	 */
	private static WebDirectoryElement[] getPathComponents(WebDirectoryElement file)
	{
		ArrayList<WebDirectoryElement> componentsList = new ArrayList<>();
		while(file != null)
		{
			componentsList.add(file);
			file = file.getParentDirectory();
		}

		int size = componentsList.size();
		WebDirectoryElement[] components = new WebDirectoryElement[size];

		for(int i = 0; i < size; i++)
		{
			components[i] = componentsList.get(size - i - 1);
		}
		return components;
	}

	@Nullable
	public abstract WebDirectoryElement findWebDirectoryByFile(@NotNull final VirtualFile file, @NotNull final JavaWebModuleExtension webFacet);

	@Nullable
	public static WebDirectoryElement findWebDirectoryByFile(@NotNull final VirtualFile file, Project project)
	{
		final JavaWebModuleExtension webFacet = getWebFacet(file, project);
		return webFacet == null ? null : getWebUtil().findWebDirectoryByFile(file, webFacet);
	}

	@Nullable
	public static WebDirectoryElement findWebDirectoryByFile(@NotNull final PsiFileSystemItem file)
	{
		VirtualFile virtualFile = file.getVirtualFile();
		final JavaWebModuleExtension webFacet = getWebFacet(file);
		if(webFacet != null && virtualFile != null)
		{
			return getWebUtil().findWebDirectoryByFile(virtualFile, webFacet);
		}
		return null;
	}

	@Nullable
	public abstract WebDirectoryElement getParentWebDirectory(@NotNull PsiFile file);

	@Nullable
	public abstract WebDirectoryElement getContainingWebDirectory(JspxFileViewProvider viewProvider);

	@Nullable
	public abstract String getWebPath(PsiFile file);

	@Nullable
	public abstract WebDirectoryElement findWebDirectoryElement(@NotNull @NonNls String path, @NotNull JavaWebModuleExtension facet);

	public static boolean processMatchingJspGroups(PsiFile file, Processor<JspPropertyGroup> processor)
	{

		JavaWebModuleExtension facet = getWebFacet(file);
		if(facet == null)
		{
			return false;
		}
		WebApp app = facet.getRoot();
		if(app == null)
		{
			return false;
		}
		String path = getWebUtil().getWebPath(file);
		if(path == null)
		{
			return false;
		}

		for(JspConfig config : app.getJspConfigs())
		{
			group:
			for(JspPropertyGroup group : config.getJspPropertyGroups())
			{
				for(GenericDomValue<String> domValue : group.getUrlPatterns())
				{
					String pattern = domValue.getValue();
					if(pattern != null && pathMatchesUrlPattern(path, pattern))
					{
						if(!processor.process(group))
						{
							return true;
						}
						continue group;
					}
				}
			}
		}
		return false;
	}

	@NotNull
	public static List<PathReference> getPreludes(BaseJspFile file)
	{

		final List<PathReference> result = new SmartList<>();
		processMatchingJspGroups(file, group ->
		{
			for(GenericDomValue<PathReference> value : group.getIncludePreludes())
			{
				ContainerUtil.addIfNotNull(result, value.getValue());
			}
			return true;
		});
		return result;
	}

	public static List<PathReference> getCodas(BaseJspFile file)
	{

		final List<PathReference> result = new SmartList<>();
		processMatchingJspGroups(file, group ->
		{
			for(GenericDomValue<PathReference> value : group.getIncludeCodas())
			{
				ContainerUtil.addIfNotNull(result, value.getValue());
			}
			return true;
		});
		return result;
	}

	public static boolean pathMatchesUrlPattern(@NotNull String path, @NotNull String pattern)
	{
		if(pattern.endsWith("/*"))
		{
			return path.startsWith(pattern.substring(0, pattern.length() - 1));
		}

		if(pattern.startsWith("*."))
		{
			return path.endsWith(pattern.substring(1));
		}

		return path.equals(pattern);
	}

	@Nullable
	public static JavaWebModuleExtension findFacetByFileUnderWebRoot(@NotNull VirtualFile file, @NotNull Project project)
	{
		final Module module = ModuleUtilCore.findModuleForFile(file, project);
		if(module == null)
		{
			return null;
		}

		final Collection<JavaWebModuleExtension> facets = JavaWebModuleExtension.getInstances(module);
		for(JavaWebModuleExtension facet : facets)
		{
			if(isInsideWebRoots(file, facet.getWebRoots()))
			{
				return facet;
			}
		}
		return null;
	}

	/**
	 * @return List of WebDirectoryElements
	 */
	public abstract Collection<PsiFileSystemItem> getContextsByPath(final String path, final @NotNull Module module);

	public abstract void visitAllFacets(final @NotNull Module module, final Consumer<JavaWebModuleExtension> visitor);

	public static VirtualFile findSourceFile(@NotNull String relative, @NotNull CommonModel commonModel, @NotNull Function<DeploymentModel, String> fun)
	{
		Pair<DeploymentModel, String> deploymentModel = findDeploymentModel(UriUtil.trimTrailingSlashes(relative), commonModel, fun);
		if(deploymentModel == null)
		{
			return null;
		}

		Artifact artifact = deploymentModel.getFirst().getArtifact();
		if(artifact == null)
		{
			return null;
		}

		for(JavaWebModuleExtension facet : JavaeeArtifactUtil.getInstance().getFacetsIncludedInArtifact(commonModel.getProject(), artifact, JavaWebModuleExtension.class))
		{
			VirtualFile file = getWebUtil().createWebDirectoryElement(facet, deploymentModel.getSecond(), false).getOriginalVirtualFile();
			if(file != null)
			{
				return file;
			}
		}
		return null;
	}

	@Nullable
	private static Pair<DeploymentModel, String> findDeploymentModel(String relative, CommonModel commonModel, Function<DeploymentModel, String> fun)
	{
		DeploymentModel defaultModel = null;
		for(DeploymentModel deploymentModel : commonModel.getDeploymentModels())
		{
			String contextPath = fun.fun(deploymentModel);
			if(contextPath == null)
			{
				continue;
			}

			contextPath = com.intellij.javaee.UriUtil.trimSlashFrom(contextPath);
			if(contextPath.isEmpty())
			{
				defaultModel = deploymentModel;
			}
			else if(relative.startsWith(contextPath))
			{
				return Pair.create(deploymentModel, relative.substring(contextPath.length()));
			}
		}
		return defaultModel != null ? Pair.create(defaultModel, relative) : null;
	}
}
