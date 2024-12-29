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

import consulo.module.Module;
import consulo.xml.util.xml.ConvertContext;
import consulo.xml.util.xml.ModuleContextProvider;
import consulo.xml.util.xml.ResolvingConverter;
import org.jetbrains.annotations.NonNls;

import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author Serega.Vasiliev
 */
public class ContextParamNameConverter extends ResolvingConverter<String> {
  @Nonnull
  @Override
  public Collection<? extends String> getVariants(ConvertContext context) {
    List<String> paramNames = new ArrayList<String>();
    Module[] modules = ModuleContextProvider.getModules(context.getFile());
    for (ContextParamsProvider provider : ContextParamsProvider.EP_NAME.getExtensionList()) {
      for (Module module : modules) {
        paramNames.addAll(provider.getContextParamNames(module, context));
      }
    }

    return paramNames;
  }

  @Override
  public String fromString(@Nullable @NonNls String s, ConvertContext context) {
    return s;
  }

  @Override
  public String toString(@Nullable String s, ConvertContext context) {
    return s;
  }
}
