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

import java.util.ArrayList;

import org.jetbrains.annotations.Nullable;
import org.mustbe.consulo.java.web.JavaWebConstants;
import org.mustbe.consulo.java.web.module.extension.JavaWebModuleExtension;
import com.intellij.openapi.module.Module;
import com.intellij.openapi.module.ModuleManager;
import com.intellij.openapi.module.ModuleUtilCore;
import com.intellij.openapi.roots.ui.configuration.ChooseModulesDialog;
import com.intellij.packaging.artifacts.ArtifactTemplate;
import com.intellij.packaging.elements.PackagingElementResolvingContext;
import com.intellij.packaging.impl.elements.DirectoryElementType;
import com.intellij.packaging.impl.elements.moduleContent.ProductionModuleOutputElementType;
import lombok.val;

/**
 * @author VISTALL
 * @since 07.11.13.
 */
public class ExplodedWarArtifactTemplate extends ArtifactTemplate
{
	private PackagingElementResolvingContext myContext;

	public ExplodedWarArtifactTemplate(PackagingElementResolvingContext context)
	{
		myContext = context;
	}

	public static NewArtifactConfiguration doCreateArtifactTemplate(Module module)
	{
		val root = ExplodedWarArtifactType.getInstance().createRootElement(module.getName());

		val webInfDir = DirectoryElementType.getInstance().createEmpty(module.getProject());
		webInfDir.setDirectoryName(JavaWebConstants.WEB_INF);
		root.addFirstChild(webInfDir);

		val classesDir = DirectoryElementType.getInstance().createEmpty(module.getProject());
		classesDir.setDirectoryName("classes");
		webInfDir.addFirstChild(classesDir);

		val pointer = ModuleUtilCore.createPointer(module);

		classesDir.addFirstChild(ProductionModuleOutputElementType.getInstance().createElement(module.getProject(), pointer));

		root.addFirstChild(WebResourceModuleOutputElementType.getInstance().createElement(module.getProject(), pointer));

		return new NewArtifactConfiguration(root, ExplodedWarArtifactType.getInstance().getPresentableName() + ": " + module.getName(), ExplodedWarArtifactType.getInstance());
	}

	@Nullable
	@Override
	public NewArtifactConfiguration createArtifact()
	{
		val modules = new ArrayList<Module>();
		for(Module module : ModuleManager.getInstance(myContext.getProject()).getModules())
		{
			if(ModuleUtilCore.getExtension(module, JavaWebModuleExtension.class) != null)
			{
				modules.add(module);
			}
		}

		val dialog = new ChooseModulesDialog(myContext.getProject(), modules, "Choose Module", "Choose module for artifact creation");
		dialog.setSingleSelectionMode();
		val selectedModules = dialog.showAndGetResult();
		if(selectedModules.size() != 1)
		{
			return null;
		}
		return doCreateArtifactTemplate(modules.get(0));
	}

	@Override
	public String getPresentableName()
	{
		return "By module";
	}
}
