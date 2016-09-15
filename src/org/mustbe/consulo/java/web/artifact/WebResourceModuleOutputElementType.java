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
import com.intellij.packaging.impl.elements.moduleContent.ModuleOutputElementTypeBase;
import com.intellij.packaging.impl.elements.moduleContent.ModuleOutputPackagingElementImpl;
import consulo.roots.impl.WebResourcesFolderTypeProvider;
import consulo.util.pointers.NamedPointer;

/**
 * @author VISTALL
 * @since 07.11.13.
 */
public class WebResourceModuleOutputElementType extends ModuleOutputElementTypeBase
{
	public static WebResourceModuleOutputElementType getInstance()
	{
		return getInstance(WebResourceModuleOutputElementType.class);
	}

	public WebResourceModuleOutputElementType()
	{
		super("java-web-resource", WebResourcesFolderTypeProvider.getInstance());
	}

	public ModuleOutputPackagingElementImpl createElement(@NotNull com.intellij.openapi.project.Project project, @NotNull NamedPointer<Module> pointer)
	{
		return new WebResourceModuleOutputPackagingElement(this, project, pointer, myContentFolderTypeProvider);
	}

	@NotNull
	@Override
	public ModuleOutputPackagingElementImpl createEmpty(@NotNull Project project)
	{
		return new WebResourceModuleOutputPackagingElement(this, project, myContentFolderTypeProvider);
	}
}
