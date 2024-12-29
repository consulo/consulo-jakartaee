package consulo.jakartaee.webServer.impl.facet;

import com.intellij.java.impl.util.descriptors.ConfigFileMetaData;
import consulo.annotation.component.ComponentScope;
import consulo.annotation.component.ExtensionAPI;
import consulo.jakartaee.webServer.impl.appServerIntegrations.AppServerIntegration;
import consulo.component.extension.ExtensionPointName;
import consulo.javaee.module.extension.JavaEEModuleExtension;

import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;

/**
 * @author nik
 */
@ExtensionAPI(ComponentScope.APPLICATION)
public abstract class DescriptorMetaDataProvider {
  public static final ExtensionPointName<DescriptorMetaDataProvider> EXTENSION_POINT = ExtensionPointName.create(DescriptorMetaDataProvider.class);

  public abstract void registerDescriptors(@Nonnull MetaDataRegistry registry);

  public interface MetaDataRegistry {
    void register(@Nonnull Class<? extends JavaEEModuleExtension> facetTypeId, @Nullable AppServerIntegration appServer, @Nonnull ConfigFileMetaData metaData);
  }
}
