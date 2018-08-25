package com.intellij.javaee.ui.packaging;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.intellij.packaging.artifacts.ArtifactType;
import com.intellij.packaging.elements.CompositePackagingElement;
import com.intellij.packaging.elements.PackagingElementFactory;
import com.intellij.packaging.elements.PackagingElementOutputKind;
import consulo.ui.image.Image;

/**
 * @author VISTALL
 * @since 03-Jul-17
 * <p>
 * STUB !!!!!!!!!!!!
 */
public class ExplodedEarArtifactType extends ArtifactType
{
	private static final ExplodedEarArtifactType INSTANCE = new ExplodedEarArtifactType();

	public static ExplodedEarArtifactType getInstance()
	{
		return INSTANCE;
	}

	protected ExplodedEarArtifactType()
	{
		super("exploded-ear", "JavaEE Application: Exploded");
	}

	@Nonnull
	@Override
	public Image getIcon()
	{
		return null;
	}

	@Nullable
	@Override
	public String getDefaultPathFor(@Nonnull PackagingElementOutputKind packagingElementOutputKind)
	{
		return null;
	}

	@Nonnull
	@Override
	public CompositePackagingElement<?> createRootElement(@Nonnull PackagingElementFactory elementFactory, @Nonnull String s)
	{
		return null;
	}
}
