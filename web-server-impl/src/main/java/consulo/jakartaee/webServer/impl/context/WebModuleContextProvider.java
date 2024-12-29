package consulo.jakartaee.webServer.impl.context;

import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import consulo.javaee.module.extension.JavaEEApplicationModuleExtension;

/**
 * Created by IntelliJ IDEA.
 * User: michael.golubev
 */
public interface WebModuleContextProvider {

  @Nullable
  String getContext(@Nonnull JavaEEApplicationModuleExtension earFacet, @Nullable String moduleWebUri);
}
