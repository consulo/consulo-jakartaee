package consulo.jakartaee.web.module.extension;

import consulo.annotation.component.ExtensionImpl;
import consulo.localize.LocalizeValue;
import consulo.module.content.layer.ModuleExtensionProvider;
import consulo.module.content.layer.ModuleRootLayer;
import consulo.module.extension.ModuleExtension;
import consulo.module.extension.MutableModuleExtension;
import consulo.platform.base.icon.PlatformIconGroup;
import consulo.ui.image.Image;

import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;

/**
 * @author VISTALL
 * @since 22/01/2023
 */
@ExtensionImpl
public class JavaWebModuleExtensionProvider implements ModuleExtensionProvider<JavaWebModuleExtension>
{
	@Nonnull
	@Override
	public String getId()
	{
		return "java-web";
	}

	@Nullable
	@Override
	public String getParentId()
	{
		return "java";
	}

	@Nonnull
	@Override
	public LocalizeValue getName()
	{
		return LocalizeValue.of("JSP");
	}

	@Nonnull
	@Override
	public Image getIcon()
	{
		return PlatformIconGroup.nodesPpweb();
	}

	@Nonnull
	@Override
	public ModuleExtension<JavaWebModuleExtension> createImmutableExtension(@Nonnull ModuleRootLayer moduleRootLayer)
	{
		return new JavaWebModuleExtension(getId(), moduleRootLayer);
	}

	@Nonnull
	@Override
	public MutableModuleExtension<JavaWebModuleExtension> createMutableExtension(@Nonnull ModuleRootLayer moduleRootLayer)
	{
		return new JavaWebMutableModuleExtension(getId(), moduleRootLayer);
	}
}
