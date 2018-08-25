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

package consulo.javaee.artifact;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import javax.annotation.Nullable;

import com.intellij.openapi.module.Module;
import com.intellij.openapi.roots.ModuleRootModel;
import com.intellij.packaging.artifacts.Artifact;
import com.intellij.packaging.artifacts.ArtifactManager;
import com.intellij.packaging.artifacts.ArtifactPointerManager;
import com.intellij.packaging.artifacts.ArtifactTemplate;
import com.intellij.packaging.elements.CompositePackagingElement;
import com.intellij.packaging.elements.PackagingElementFactory;
import com.intellij.packaging.elements.PackagingElementResolvingContext;
import com.intellij.packaging.impl.artifacts.ArtifactUtil;
import com.intellij.packaging.impl.elements.ArtifactElementType;
import com.intellij.packaging.impl.elements.ArtifactPackagingElement;
import com.intellij.packaging.impl.ui.ChooseArtifactsDialog;
import consulo.javaee.module.extension.JavaWebModuleExtension;

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
		Set<Module> modulesIncludedInArtifacts = ArtifactUtil.getModulesIncludedInArtifacts(Collections.singletonList(artifact), context.getProject());

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

		PackagingElementFactory elementFactory = PackagingElementFactory.getInstance(context.getProject());

		CompositePackagingElement<?> root = WarArtifactType.getInstance().createRootElement(elementFactory, moduleName);

		ArtifactPackagingElement artifactPackagingElement = ArtifactElementType.getInstance().createEmpty(context.getProject());
		artifactPackagingElement.setArtifactPointer(ArtifactPointerManager.getInstance(context.getProject()).create(artifact));

		root.addFirstChild(artifactPackagingElement);

		return new NewArtifactConfiguration(root, WarArtifactType.getInstance().getPresentableName() + ": " + moduleName, WarArtifactType.getInstance());
	}

	@Nullable
	@Override
	public NewArtifactConfiguration createArtifact()
	{
		List<Artifact> artifacts = new ArrayList<>();
		for(Artifact artifact : ArtifactManager.getInstance(myContext.getProject()).getArtifacts())
		{
			if(artifact.getArtifactType() == ExplodedWarArtifactType.getInstance())
			{
				artifacts.add(artifact);
			}
		}

		ChooseArtifactsDialog dialog = new ChooseArtifactsDialog(myContext.getProject(), artifacts, "Choose Artifact", "Choose Exploded War Artifact");

		List<Artifact> artifactList = dialog.showAndGetResult();
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
