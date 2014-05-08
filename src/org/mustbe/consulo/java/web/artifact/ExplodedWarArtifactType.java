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

import java.util.Collections;
import java.util.List;

import javax.swing.Icon;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.mustbe.consulo.java.web.JavaWebIcons;
import org.mustbe.consulo.java.web.module.extension.JavaWebModuleExtension;
import com.intellij.javaee.J2EEBundle;
import com.intellij.openapi.module.ModuleUtil;
import com.intellij.openapi.roots.ui.configuration.ModulesProvider;
import com.intellij.packaging.artifacts.ArtifactTemplate;
import com.intellij.packaging.artifacts.ArtifactType;
import com.intellij.packaging.elements.CompositePackagingElement;
import com.intellij.packaging.elements.PackagingElementOutputKind;
import com.intellij.packaging.elements.PackagingElementResolvingContext;
import com.intellij.packaging.impl.elements.ArtifactRootElementImpl;

/**
 * @author VISTALL
 * @since 07.11.13.
 */
public class ExplodedWarArtifactType extends ArtifactType
{
	public static ExplodedWarArtifactType getInstance()
	{
		return EP_NAME.findExtension(ExplodedWarArtifactType.class);
	}

	public ExplodedWarArtifactType()
	{
		super("exploded-war", J2EEBundle.message("exploded.war.artifact.name"));
	}

	@Override
	public boolean isAvailableForAdd(@NotNull ModulesProvider modulesProvider)
	{
		return ModuleUtil.hasModuleExtension(modulesProvider, JavaWebModuleExtension.class);
	}

	@NotNull
	@Override
	public Icon getIcon()
	{
		return JavaWebIcons.WarArtifact;
	}

	@Nullable
	@Override
	public String getDefaultPathFor(@NotNull PackagingElementOutputKind packagingElementOutputKind)
	{
		return "/";
	}

	@NotNull
	@Override
	public List<? extends ArtifactTemplate> getNewArtifactTemplates(@NotNull PackagingElementResolvingContext context)
	{
		return Collections.singletonList(new ExplodedWarArtifactTemplate(context));
	}

	@NotNull
	@Override
	public CompositePackagingElement<?> createRootElement(@NotNull String s)
	{
		return new ArtifactRootElementImpl();
	}
}
