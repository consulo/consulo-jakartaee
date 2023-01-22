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

import consulo.annotation.component.ComponentScope;
import consulo.annotation.component.ExtensionAPI;
import consulo.component.extension.ExtensionPointName;
import consulo.module.Module;
import consulo.xml.util.xml.ConvertContext;
import consulo.xml.util.xml.Converter;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Collections;
import java.util.Set;

/**
 * @author Sergey Vasiliev
 */
@ExtensionAPI(ComponentScope.APPLICATION)
public abstract class ContextParamsProvider {
  public static final ExtensionPointName<ContextParamsProvider> EP_NAME = ExtensionPointName.create(ContextParamsProvider.class);

  /**
   * @param module         All modules from {@link consulo.xml.util.xml.ModuleContextProvider#getModules}.
   * @param convertContext Current convert context.
   * @return Parameter names applicable in this context.
   */
  @Nonnull
  public Set<String> getContextParamNames(@Nonnull Module module, ConvertContext convertContext) {
    return getContextParamNames(module);
  }

  /**
   * @deprecated use {@link #getContextParamNames(Module, consulo.xml.util.xml.ConvertContext)}
   */
  @Deprecated
  public Set<String> getContextParamNames(@Nonnull Module module) {
    return Collections.emptySet();
  }

  @Nullable
  public abstract Converter getContextParamValueConverter(@Nonnull String paramName, @Nullable Module module);

  @Nullable
  public String getContextParamDefaultValue(@Nonnull String paramName, @Nullable Module module) {
    return "";
  }
}
