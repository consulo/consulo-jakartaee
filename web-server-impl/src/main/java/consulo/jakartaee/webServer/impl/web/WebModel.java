/*
 * Copyright 2000-2009 JetBrains s.r.o.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package consulo.jakartaee.webServer.impl.web;

import com.intellij.javaee.model.CommonListener;
import com.intellij.javaee.web.CommonFilter;
import com.intellij.javaee.web.CommonServlet;
import com.intellij.javaee.web.CommonServletMapping;

import jakarta.annotation.Nonnull;
import java.util.List;

/**
 * @author Dmitry Avdeev
 * @see com.intellij.javaee.web.facet.WebFacet#getWebModel() 
 */
public interface WebModel {

  List<CommonServlet> getServlets();

  CommonServlet findServlet(@Nonnull String name);

  CommonFilter findFilter(@Nonnull String name);

  List<CommonServletMapping<CommonServlet>> getServletMappings();

  List<CommonFilter> getFilters();

  List<CommonListener> getListeners();
}
