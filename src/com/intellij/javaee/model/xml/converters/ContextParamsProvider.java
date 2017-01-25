/*
 * Copyright 2000-2014 JetBrains s.r.o.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.intellij.javaee.model.xml.converters;

import java.util.Collections;
import java.util.Set;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import com.intellij.openapi.extensions.ExtensionPointName;
import com.intellij.openapi.module.Module;
import com.intellij.util.xml.ConvertContext;
import com.intellij.util.xml.Converter;

/**
 * @author Sergey Vasiliev
 */
public abstract class ContextParamsProvider {
  public static final ExtensionPointName<ContextParamsProvider> EP_NAME = ExtensionPointName.create("consulo.javaee.contextParamsProvider");

  /**
   * @param module         All modules from {@link com.intellij.util.xml.ModuleContextProvider#getModules}.
   * @param convertContext Current convert context.
   * @return Parameter names applicable in this context.
   */
  @NotNull
  public Set<String> getContextParamNames(@NotNull Module module, ConvertContext convertContext) {
    return getContextParamNames(module);
  }

  /**
   * @deprecated use {@link #getContextParamNames(com.intellij.openapi.module.Module, com.intellij.util.xml.ConvertContext)}
   */
  @Deprecated
  public Set<String> getContextParamNames(@NotNull Module module) {
    return Collections.emptySet();
  }

  @Nullable
  public abstract Converter getContextParamValueConverter(@NotNull String paramName, @Nullable Module module);

  @Nullable
  public String getContextParamDefaultValue(@NotNull String paramName, @Nullable Module module) {
    return "";
  }
}
