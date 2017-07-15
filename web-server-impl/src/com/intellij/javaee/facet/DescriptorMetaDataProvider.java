package com.intellij.javaee.facet;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import com.intellij.javaee.appServerIntegrations.AppServerIntegration;
import com.intellij.openapi.extensions.ExtensionPointName;
import com.intellij.util.descriptors.ConfigFileMetaData;
import consulo.javaee.module.extension.JavaEEModuleExtension;

/**
 * @author nik
 */
public abstract class DescriptorMetaDataProvider {
  public static final ExtensionPointName<DescriptorMetaDataProvider> EXTENSION_POINT = ExtensionPointName.create("com.intellij.javaee.descriptorMetaDataProvider");

  public abstract void registerDescriptors(@NotNull MetaDataRegistry registry);

  public interface MetaDataRegistry {
    void register(@NotNull Class<? extends JavaEEModuleExtension> facetTypeId, @Nullable AppServerIntegration appServer, @NotNull ConfigFileMetaData metaData);
  }
}
