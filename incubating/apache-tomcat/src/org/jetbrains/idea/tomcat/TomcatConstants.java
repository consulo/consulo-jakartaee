/*
 * Copyright 2000-2005 JetBrains s.r.o.
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
package org.jetbrains.idea.tomcat;

import org.jetbrains.annotations.NonNls;

public interface TomcatConstants {
  @NonNls String CATALINA_CONFIG_DIRECTORY_NAME = "conf";
  @NonNls String CATALINA_WORK_DIRECTORY_NAME = "work";
  @NonNls String SCRATCHDIR_NAME = "_scratchdir";
  @NonNls String SERVER_XML = "server.xml";
  @NonNls String WEB_XML = "web.xml";
  @NonNls String CATALINA_BIN_DIRECTORY_NAME = "bin";
  @NonNls String CATALINA_COMMON_DIRECTORY_NAME = "common";
  @NonNls String CATALINA_LIB_DIRECTORY_NAME = "lib";

  @NonNls String APP_BASE_ATTR = "appBase";
}