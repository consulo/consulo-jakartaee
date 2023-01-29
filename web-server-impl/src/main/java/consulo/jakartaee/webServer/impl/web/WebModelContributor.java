/*
 * Copyright 2000-2016 JetBrains s.r.o.
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
package consulo.jakartaee.webServer.impl.web;

import com.intellij.javaee.model.CommonListener;
import com.intellij.javaee.web.CommonFilter;
import com.intellij.javaee.web.CommonServlet;
import com.intellij.javaee.web.CommonServletMapping;
import consulo.annotation.component.ComponentScope;
import consulo.annotation.component.ExtensionAPI;
import consulo.component.extension.ExtensionPointName;

import java.util.Collections;
import java.util.List;

/**
 * Allows to extend {@link WebModel} with elements provided programmatically.
 *
 * @since 144
 */
@ExtensionAPI(ComponentScope.APPLICATION)
public abstract class WebModelContributor {

  static final ExtensionPointName<WebModelContributor> EP_NAME = ExtensionPointName.create(WebModelContributor.class);

  public List<CommonServlet> getServlets(Module module) {
    return Collections.emptyList();
  }

  public List<CommonServletMapping<CommonServlet>> getServletMappings(Module module) {
    return Collections.emptyList();
  }

  public List<CommonFilter> getFilters(Module module) {
    return Collections.emptyList();
  }

  public List<CommonListener> getListeners(Module module) {
    return Collections.emptyList();
  }
}
