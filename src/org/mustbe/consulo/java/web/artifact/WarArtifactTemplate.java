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
import com.intellij.packaging.artifacts.Artifact;
import com.intellij.packaging.artifacts.ArtifactManager;
import com.intellij.packaging.artifacts.ArtifactPointerUtil;
import com.intellij.packaging.artifacts.ArtifactTemplate;
import com.intellij.packaging.elements.PackagingElementResolvingContext;
import com.intellij.packaging.impl.artifacts.ArtifactUtil;
import com.intellij.packaging.impl.elements.ArtifactElementType;
import com.intellij.packaging.impl.ui.ChooseArtifactsDialog;
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

	public NewArtifactConfiguration doCreateArtifactTemplate(Artifact artifact)
	{
		val modulesIncludedInArtifacts = ArtifactUtil.getModulesIncludedInArtifacts(Collections.singletonList(artifact), myContext.getProject());

		if(modulesIncludedInArtifacts.size() != 1)
		{
			return null;
		}

		val module = modulesIncludedInArtifacts.iterator().next();
		val root = WarArtifactType.getInstance().createRootElement(module.getName());


		val artifactPackagingElement = ArtifactElementType.getInstance().createEmpty(myContext.getProject());
		artifactPackagingElement.setArtifactPointer(ArtifactPointerUtil.getPointerManager(myContext.getProject()).create(artifact));

		root.addFirstChild(artifactPackagingElement);

		return new NewArtifactConfiguration(root, WarArtifactType.getInstance().getPresentableName() + ": " + module.getName(), WarArtifactType.getInstance());
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

		val dialog = new ChooseArtifactsDialog(myContext.getProject(), artifacts, "Choose Artifact", "Choose exploded war artifact");

		val artifactList = dialog.showAndGetResult();
		if(artifactList.size() != 1)
		{
			return null;
		}
		return doCreateArtifactTemplate(artifactList.get(0));
	}

	@Override
	public String getPresentableName()
	{
		return "By artifact";
	}
}
