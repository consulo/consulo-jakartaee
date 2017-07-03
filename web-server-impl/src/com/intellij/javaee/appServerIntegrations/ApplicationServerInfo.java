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
package com.intellij.javaee.appServerIntegrations;

import org.jetbrains.annotations.NotNull;

import java.io.File;

public class ApplicationServerInfo {
  private final File[] myDefaultLibraries;
  private final String myDefaultName;

  public ApplicationServerInfo(@NotNull File[] defaultLibraries, @NotNull String defaultName) {
    myDefaultLibraries = defaultLibraries;
    myDefaultName = defaultName;
  }

  @NotNull
  public File[] getDefaultLibraries() {
    return myDefaultLibraries;
  }

  @NotNull
  public String getDefaultName() {
    return myDefaultName;
  }

}
