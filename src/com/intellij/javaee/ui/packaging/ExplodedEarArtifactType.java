package com.intellij.javaee.ui.packaging;

import javax.swing.Icon;

import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import com.intellij.packaging.artifacts.ArtifactType;
import com.intellij.packaging.elements.CompositePackagingElement;
import com.intellij.packaging.elements.PackagingElementOutputKind;

/**
 * @author VISTALL
 * @since 03-Jul-17
 *
 * STUB !!!!!!!!!!!!
 */
public class ExplodedEarArtifactType extends ArtifactType
{
	public static ExplodedEarArtifactType getInstance()
	{
		return null;
	}

	protected ExplodedEarArtifactType(@NonNls String id, String title)
	{
		super(id, title);
	}

	@NotNull
	@Override
	public Icon getIcon()
	{
		return null;
	}

	@Nullable
	@Override
	public String getDefaultPathFor(@NotNull PackagingElementOutputKind packagingElementOutputKind)
	{
		return null;
	}

	@NotNull
	@Override
	public CompositePackagingElement<?> createRootElement(@NotNull String s)
	{
		return null;
	}
}
