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

package com.intellij.javaee.model.common.ejb;

import com.intellij.javaee.model.annotations.JavaeeAnnotationConstants;
import org.jetbrains.annotations.NonNls;

public enum InterceptorMethodType {
  AROUND_INVOKE("AroundInvoke"),
  POST_CONSTRUCT("PostConstruct"),
  PRE_DESTROY("PreDestroy"),
  PRE_PASSIVATE("PrePassivate"),
  POST_ACTIVATE("PostActivate");

  private final String typeName;

  InterceptorMethodType(@NonNls final String typeName) {
   this.typeName = typeName;
 }

  public String getTypeName() { return typeName; }

  @NonNls
  public String getMethodStandardName() {
    switch (this) {
      case AROUND_INVOKE: return "aroundInvoke";
      case POST_ACTIVATE: return "ejbActivate";
      case POST_CONSTRUCT: return "ejbCreate";
      case PRE_DESTROY: return "ejbRemove";
      case PRE_PASSIVATE: return "ejbPassivate";
    }
    assert false;
    return null;
  }

  @NonNls
  public String getMethodAnnotation() {
    switch (this) {
      case AROUND_INVOKE:
        return JavaeeAnnotationConstants.AROUND_INVOKE_ANNO;
      case POST_ACTIVATE:
        return JavaeeAnnotationConstants.POST_ACTIVATE_ANNO;
      case POST_CONSTRUCT:
        return JavaeeAnnotationConstants.POST_CONSTRUCT_ANNO;
      case PRE_DESTROY:
        return JavaeeAnnotationConstants.PRE_DESTROY_ANNO;
      case PRE_PASSIVATE:
        return JavaeeAnnotationConstants.PRE_PASSIVATE_ANNO;
    }
    assert false;
    return null;
  }
}
