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

import com.intellij.javaee.JavaeeVersion;
import com.intellij.javaee.model.common.JavaeeCommonConstants;
import com.intellij.util.descriptors.ConfigFileVersion;
import com.intellij.util.xml.NamedEnum;
import org.jetbrains.annotations.NonNls;
import javax.annotation.Nonnull;

import java.util.Arrays;
import java.util.List;

import static com.intellij.javaee.model.EjbRootDescriptor.*;

/**
 * @author peter
 */
public enum EjbDescriptorVersion implements NamedEnum {
  EJB_VERSION_1_X("1.x", EJB_JAR_VERSION_1_X, JavaeeVersion.J2EE_1_4,
                  JavaeeCommonConstants.EJB_JAR_1_0_DTD, JavaeeCommonConstants.EJB_JAR_1_1_DTD, JavaeeCommonConstants.EJB_1_0_PUBLIC_ID, JavaeeCommonConstants.EJB_1_1_PUBLIC_ID),
  EJB_VERSION_2_0("2.0", EJB_JAR_VERSION_2_0, JavaeeVersion.J2EE_1_4, JavaeeCommonConstants.EJB_JAR_2_0_DTD, JavaeeCommonConstants.EJB_2_0_PUBLIC_ID),
  EJB_VERSION_2_1("2.1", EJB_JAR_VERSION_2_1, JavaeeVersion.J2EE_1_4, JavaeeCommonConstants.J2EE_NAMESPACE),
  EJB_VERSION_3_0("3.0", EJB_JAR_VERSION_3_0, JavaeeVersion.JAVAEE_5, JavaeeCommonConstants.JAVAEE_NAMESPACE),
  EJB_VERSION_3_1("3.1", EJB_JAR_VERSION_3_1, JavaeeVersion.JAVAEE_6, JavaeeCommonConstants.JAVAEE_NAMESPACE),
  EJB_VERSION_3_2("3.2", EJB_JAR_VERSION_3_1, JavaeeVersion.JAVAEE_7);//todo[nik] add template for version 3.2

  private final String myName;
  @Nonnull
  private final ConfigFileVersion myConfigFileVersion;
  @Nonnull
  private final JavaeeVersion myJavaeeVersion;
  private final String[] myAllowedNamespaces;

  EjbDescriptorVersion(@NonNls final String name, @Nonnull ConfigFileVersion configFileVersion, @Nonnull JavaeeVersion javaeeVersion, @NonNls String... allowedNamespaces) {
    myName = name;
    myConfigFileVersion = configFileVersion;
    myJavaeeVersion = javaeeVersion;
    myAllowedNamespaces = allowedNamespaces;
 }

  public final String getValue() {
    return myName;
  }

  @Nonnull
  public ConfigFileVersion getConfigFileVersion() {
    return myConfigFileVersion;
  }

  @Nonnull
  public JavaeeVersion getJavaeeVersion() {
    return myJavaeeVersion;
  }

  public List<String> getAllowedNamespaces() {
    return Arrays.asList(myAllowedNamespaces);
  }

  public static EjbDescriptorVersion getEjbDescriptorVersion(String value) {
    for (EjbDescriptorVersion version : EjbDescriptorVersion.values()) {
      if (version.getValue().equals(value)) {
        return version;
      }
    }
    assert false: value;
    return null;
  }
}
