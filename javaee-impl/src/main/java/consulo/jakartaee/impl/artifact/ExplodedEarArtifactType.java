package consulo.jakartaee.impl.artifact;

import consulo.compiler.artifact.ArtifactType;
import consulo.compiler.artifact.element.CompositePackagingElement;
import consulo.compiler.artifact.element.PackagingElementFactory;
import consulo.compiler.artifact.element.PackagingElementOutputKind;
import consulo.ui.image.Image;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

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
