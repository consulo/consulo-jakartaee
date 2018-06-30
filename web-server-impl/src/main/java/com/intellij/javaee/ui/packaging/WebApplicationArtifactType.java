package com.intellij.javaee.ui.packaging;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import org.jetbrains.annotations.NonNls;
import com.intellij.openapi.module.ModuleUtil;
import com.intellij.openapi.roots.ui.configuration.ModulesProvider;
import com.intellij.packaging.artifacts.ArtifactType;
import com.intellij.packaging.elements.PackagingElementOutputKind;
import consulo.javaee.JavaEEIcons;
import consulo.javaee.module.extension.JavaWebModuleExtension;
import consulo.ui.image.Image;

/**
 * @author VISTALL
 * @since 03-Jul-17
 */
public abstract class WebApplicationArtifactType extends ArtifactType
{
	protected WebApplicationArtifactType(@NonNls String id, String title)
	{
		super(id, title);
	}

	@Nullable
	@Override
	public String getDefaultPathFor(@Nonnull PackagingElementOutputKind packagingElementOutputKind)
	{
		return "/";
	}

	@Override
	public boolean isAvailableForAdd(@Nonnull ModulesProvider modulesProvider)
	{
		return ModuleUtil.hasModuleExtension(modulesProvider, JavaWebModuleExtension.class);
	}

	@Nonnull
	@Override
	public Image getIcon()
	{
		return JavaEEIcons.WarArtifact;
	}
}
