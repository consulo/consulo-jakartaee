package com.intellij.javaee.context;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import consulo.javaee.module.extension.JavaEEApplicationModuleExtension;

/**
 * Created by IntelliJ IDEA.
 * User: michael.golubev
 */
public interface WebModuleContextProvider {

  @Nullable
  String getContext(@NotNull JavaEEApplicationModuleExtension earFacet, @Nullable String moduleWebUri);
}
