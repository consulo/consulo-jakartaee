/*
 * Copyright 2000-2007 JetBrains s.r.o.
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

package com.intellij.javaee.model.xml.web.converters;

import com.intellij.javaee.model.xml.web.FilterMapping;
import com.intellij.javaee.model.xml.web.Servlet;
import com.intellij.javaee.model.xml.web.WebApp;
import com.intellij.javaee.model.xml.web.WebAppVersion;
import consulo.xml.util.xml.ConvertContext;
import consulo.xml.util.xml.DomResolveConverter;

import javax.annotation.Nonnull;
import java.util.Collections;
import java.util.Set;

/**
 * @author peter
 */
public class ServletNameConverter extends DomResolveConverter<Servlet>
{
  public ServletNameConverter() {
    super(Servlet.class);
  }

  @Nonnull
  public Set<String> getAdditionalVariants(@Nonnull final ConvertContext context) {
    if (context.getInvocationElement().getParent() instanceof FilterMapping) {
      WebApp webApp = context.getInvocationElement().getParentOfType(WebApp.class, false);
      assert webApp != null;
      WebAppVersion version = webApp.getVersion().getValue();
      if (version != null && WebAppVersion.WebAppVersion_2_5.compareTo(version) <= 0) {
        return Collections.singleton("*");
      }
    } else {
      return Collections.singleton("default");
    }
    return Collections.emptySet();
  }
}
