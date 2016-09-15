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
import java.util.Collections;

import org.jetbrains.annotations.Nullable;
import org.mustbe.consulo.java.web.module.extension.JavaWebModuleExtension;
import com.intellij.openapi.module.Module;
import com.intellij.openapi.roots.ModuleRootModel;
import com.intellij.packaging.artifacts.Artifact;
import com.intellij.packaging.artifacts.ArtifactManager;
import com.intellij.packaging.artifacts.ArtifactTemplate;
import com.intellij.packaging.elements.PackagingElementResolvingContext;
import com.intellij.packaging.impl.artifacts.ArtifactUtil;
import com.intellij.packaging.impl.elements.ArtifactElementType;
import com.intellij.packaging.impl.ui.ChooseArtifactsDialog;
import consulo.packaging.artifacts.ArtifactPointerUtil;
import lombok.val;

/**
 * @author VISTALL
 * @since 07.11.13.
 */
public class WarArtifactTemplate extends ArtifactTemplate
{
	private PackagingElementResolvingContext myContext;

	public WarArtifactTemplate(PackagingElementResolvingContext context)
	{
		myContext = context;
	}

	public static NewArtifactConfiguration doCreateArtifactTemplate(Artifact artifact, PackagingElementResolvingContext context)
	{
		val modulesIncludedInArtifacts = ArtifactUtil.getModulesIncludedInArtifacts(Collections.singletonList(artifact), context.getProject());

		String moduleName = artifact.getName();

		for(Module modulesIncludedInArtifact : modulesIncludedInArtifacts)
		{
			ModuleRootModel rootModel = context.getModulesProvider().getRootModel(modulesIncludedInArtifact);

			JavaWebModuleExtension extension = rootModel.getExtension(JavaWebModuleExtension.class);
			if(extension != null)
			{
				moduleName = modulesIncludedInArtifact.getName();
				break;
			}
		}

		val root = WarArtifactType.getInstance().createRootElement(moduleName);

		val artifactPackagingElement = ArtifactElementType.getInstance().createEmpty(context.getProject());
		artifactPackagingElement.setArtifactPointer(ArtifactPointerUtil.getPointerManager(context.getProject()).create(artifact));

		root.addFirstChild(artifactPackagingElement);

		return new NewArtifactConfiguration(root, WarArtifactType.getInstance().getPresentableName() + ": " + moduleName,
				WarArtifactType.getInstance());
	}

	@Nullable
	@Override
	public NewArtifactConfiguration createArtifact()
	{
		val artifacts = new ArrayList<Artifact>();
		for(val artifact : ArtifactManager.getInstance(myContext.getProject()).getArtifacts())
		{
			if(artifact.getArtifactType() == ExplodedWarArtifactType.getInstance())
			{
				artifacts.add(artifact);
			}
		}

		val dialog = new ChooseArtifactsDialog(myContext.getProject(), artifacts, "Choose Artifact", "Choose Exploded War Artifact");

		val artifactList = dialog.showAndGetResult();
		if(artifactList.size() != 1)
		{
			return null;
		}
		return doCreateArtifactTemplate(artifactList.get(0), myContext);
	}

	@Override
	public String getPresentableName()
	{
		return "From Exploded Artifact";
	}
}
