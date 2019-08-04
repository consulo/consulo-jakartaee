package com.intellij.javaee.facet;

import com.intellij.javaee.appServerIntegrations.AppServerIntegration;
import com.intellij.openapi.extensions.ExtensionPointName;
import com.intellij.util.descriptors.ConfigFileMetaData;
import consulo.javaee.module.extension.JavaEEModuleExtension;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/**
 * @author nik
 */
public abstract class DescriptorMetaDataProvider {
  public static final ExtensionPointName<DescriptorMetaDataProvider> EXTENSION_POINT = ExtensionPointName.create("consulo.javaee.descriptorMetaDataProvider");

  public abstract void registerDescriptors(@Nonnull MetaDataRegistry registry);

  public interface MetaDataRegistry {
    void register(@Nonnull Class<? extends JavaEEModuleExtension> facetTypeId, @Nullable AppServerIntegration appServer, @Nonnull ConfigFileMetaData metaData);
  }
}
