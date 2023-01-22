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
package consulo.jakartaee.webServer.impl.deployment;

import consulo.jakartaee.webServer.impl.run.configuration.CommonModel;
import javax.annotation.Nullable;
import javax.annotation.Nonnull;

import javax.swing.*;

public class DeploymentMethod {
  public static final DeploymentMethod[] EMPTY_ARRAY = new DeploymentMethod[0];
  private final String myName;
  private final boolean myApplicableForLocal;
  private final boolean myApplicableForRemote;

  public DeploymentMethod(String name, boolean local, boolean remote) {
    myName = name;
    myApplicableForLocal = local;
    myApplicableForRemote = remote;
  }

  public String getName() {
    return myName;
  }

  public String toString() {
    return getName();
  }

  @Nullable
  public Icon getIcon() {
    return null;
  }

  public boolean isApplicable(@Nonnull CommonModel commonModel) {
    if (commonModel.isLocal()){
      return myApplicableForLocal;
    } else {
      return myApplicableForRemote;
    }
  }
}
