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
package com.intellij.javaee.model;

import com.intellij.java.impl.util.descriptors.ConfigFileMetaData;
import com.intellij.java.impl.util.descriptors.ConfigFileVersion;
import com.intellij.javaee.J2EEBundle;
import com.intellij.javaee.J2EEFileTemplateNames;
import org.jetbrains.annotations.NonNls;

/**
 * @author peter
 */
public interface EjbRootDescriptor {

  @NonNls String EJB_VERSION_NAME_2_1 = "2.1";
  @NonNls String EJB_VERSION_NAME_3_0 = "3.0";
  @NonNls String EJB_VERSION_NAME_3_1 = "3.1";

  ConfigFileVersion EJB_JAR_VERSION_1_X = new ConfigFileVersion("1.x", J2EEFileTemplateNames.EJB_JAR_XML_1_1);
  ConfigFileVersion EJB_JAR_VERSION_2_0 = new ConfigFileVersion("2.0", J2EEFileTemplateNames.EJB_JAR_XML_2_0);
  ConfigFileVersion EJB_JAR_VERSION_2_1 = new ConfigFileVersion("2.1", J2EEFileTemplateNames.EJB_JAR_XML_2_1);
  ConfigFileVersion EJB_JAR_VERSION_3_0 = new ConfigFileVersion("3.0", J2EEFileTemplateNames.EJB_JAR_XML_3_0);
  ConfigFileVersion EJB_JAR_VERSION_3_1 = new ConfigFileVersion("3.1", J2EEFileTemplateNames.EJB_JAR_XML_3_1);
  ConfigFileVersion[] VERSIONS = {EJB_JAR_VERSION_1_X, EJB_JAR_VERSION_2_0, EJB_JAR_VERSION_2_1, EJB_JAR_VERSION_3_0, EJB_JAR_VERSION_3_1};

  ConfigFileMetaData EJB_JAR_META_DATA =
    new ConfigFileMetaData(J2EEBundle.message("deployment.descriptor.title.ejb.module"), "ejb-jar.xml", "META-INF", VERSIONS, null, true,
                           true, true);


}
