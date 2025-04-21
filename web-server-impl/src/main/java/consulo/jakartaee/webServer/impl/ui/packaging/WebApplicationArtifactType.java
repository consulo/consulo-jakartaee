package consulo.jakartaee.webServer.impl.ui.packaging;

import consulo.compiler.artifact.ArtifactType;
import consulo.compiler.artifact.element.PackagingElementOutputKind;
import consulo.jakartaee.web.module.extension.JavaWebModuleExtension;
import consulo.javaee.JavaEEIcons;
import consulo.language.util.ModuleUtilCore;
import consulo.module.content.layer.ModulesProvider;
import consulo.ui.image.Image;
import org.jetbrains.annotations.NonNls;

import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;

/**
 * @author VISTALL
 * @since 03-Jul-17
 */
public abstract class WebApplicationArtifactType extends ArtifactType {
    protected WebApplicationArtifactType(@NonNls String id, String title) {
        super(id, title);
    }

    @Nullable
    @Override
    public String getDefaultPathFor(@Nonnull PackagingElementOutputKind packagingElementOutputKind) {
        return "/";
    }

    @Override
    public boolean isAvailableForAdd(@Nonnull ModulesProvider modulesProvider) {
        return ModuleUtilCore.hasModuleExtension(modulesProvider, JavaWebModuleExtension.class);
    }

    @Nonnull
    @Override
    public Image getIcon() {
        return JavaEEIcons.WarArtifact;
    }
}
