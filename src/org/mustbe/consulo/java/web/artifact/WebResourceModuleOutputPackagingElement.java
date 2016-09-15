/*
 * Copyright 2013 must-be.org
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

package org.mustbe.consulo.java.web.artifact;

import org.jetbrains.annotations.NotNull;
import com.intellij.openapi.module.Module;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.roots.ModuleRootManager;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.packaging.artifacts.ArtifactType;
import com.intellij.packaging.elements.ArtifactIncrementalCompilerContext;
import com.intellij.packaging.elements.IncrementalCompilerInstructionCreator;
import com.intellij.packaging.elements.PackagingElementResolvingContext;
import com.intellij.packaging.elements.PackagingElementType;
import com.intellij.packaging.impl.elements.moduleContent.ModuleOutputPackagingElementImpl;
import consulo.roots.ContentFolderScopes;
import consulo.roots.ContentFolderTypeProvider;
import consulo.util.pointers.NamedPointer;

/**
 * @author VISTALL
 * @since 07.11.13.
 */
public class WebResourceModuleOutputPackagingElement extends ModuleOutputPackagingElementImpl
{
	public WebResourceModuleOutputPackagingElement(PackagingElementType type, Project project, NamedPointer<Module> modulePointer, ContentFolderTypeProvider contentFolderType)
	{
		super(type, project, modulePointer, contentFolderType);
	}

	public WebResourceModuleOutputPackagingElement(PackagingElementType type, Project project, ContentFolderTypeProvider contentFolderType)
	{
		super(type, project, contentFolderType);
	}

	@Override
	public void computeIncrementalCompilerInstructions(@NotNull IncrementalCompilerInstructionCreator creator, @NotNull PackagingElementResolvingContext resolvingContext, @NotNull ArtifactIncrementalCompilerContext compilerContext, @NotNull ArtifactType artifactType)
	{
		Module module = findModule(resolvingContext);
		if(module != null)
		{
			VirtualFile[] virtualFiles = ModuleRootManager.getInstance(module).getContentFolderFiles(ContentFolderScopes.of(myContentFolderType));

			for(VirtualFile virtualFile : virtualFiles)
			{
				creator.addDirectoryCopyInstructions(virtualFile, null);
			}
		}
	}
}
