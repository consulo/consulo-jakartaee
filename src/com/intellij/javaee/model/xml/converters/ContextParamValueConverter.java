/*
 * Copyright 2000-2010 JetBrains s.r.o.
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

import com.intellij.javaee.model.xml.ParamValue;
import com.intellij.openapi.extensions.Extensions;
import com.intellij.openapi.module.Module;
import com.intellij.util.xml.Converter;
import com.intellij.util.xml.GenericDomValue;
import com.intellij.util.xml.WrappingConverter;
import org.jetbrains.annotations.NotNull;

/**
 * @author Serega.Vasiliev
 */
public class ContextParamValueConverter extends WrappingConverter {

  @Override
  public Converter getConverter(@NotNull GenericDomValue domElement) {
    Module module = domElement.getModule();

    ParamValue value = domElement.getParentOfType(ParamValue.class, false);
    if (value != null) {
      String paramName = value.getParamName().getStringValue();
      if (paramName != null) {
        for (ContextParamsProvider provider : Extensions.getExtensions(ContextParamsProvider.WEB_XML_CONTEXT_PARAMS_EP)) {
          Converter converter = provider.getContextParamValueConverter(paramName, module);
          if (converter != null) return converter;
        }
      }
    }
    return null;
  }
}
