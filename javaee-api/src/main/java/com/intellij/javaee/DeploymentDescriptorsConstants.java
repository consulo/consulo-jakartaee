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

package com.intellij.javaee;

import com.intellij.util.descriptors.ConfigFileMetaData;
import com.intellij.util.descriptors.ConfigFileVersion;

/**
 * author: lesya
 */
public interface DeploymentDescriptorsConstants
{
	String SERVLET_VERSION_2_2 = "2.2";
	String SERVLET_VERSION_2_3 = "2.3";
	String SERVLET_VERSION_2_4 = "2.4";
	String SERVLET_VERSION_2_5 = "2.5";
	String SERVLET_VERSION_3_0 = "3.0";

	String APPLICATION_VERSION_1_2 = "1.2";
	String APPLICATION_VERSION_1_3 = "1.3";
	String APPLICATION_VERSION_1_4 = "1.4";
	String APPLICATION_VERSION_5_0 = "5";
	String APPLICATION_VERSION_6_0 = "6";

	ConfigFileVersion APPLICATION_XML_VERSION_1_2 = new ConfigFileVersion(APPLICATION_VERSION_1_2, J2EEFileTemplateNames.APPLICATION_XML_1_2);
	ConfigFileVersion APPLICATION_XML_VERSION_1_3 = new ConfigFileVersion(APPLICATION_VERSION_1_3, J2EEFileTemplateNames.APPLICATION_XML_1_3);
	ConfigFileVersion APPLICATION_XML_VERSION_1_4 = new ConfigFileVersion(APPLICATION_VERSION_1_4, J2EEFileTemplateNames.APPLICATION_XML_1_4);
	ConfigFileVersion APPLICATION_XML_VERSION_5_0 = new ConfigFileVersion(APPLICATION_VERSION_5_0, J2EEFileTemplateNames.APPLICATION_XML_5_0);
	ConfigFileVersion APPLICATION_XML_VERSION_6_0 = new ConfigFileVersion(APPLICATION_VERSION_6_0, J2EEFileTemplateNames.APPLICATION_XML_6_0);
	ConfigFileVersion[] APPLICATION_XML_VERSIONS = {
			APPLICATION_XML_VERSION_1_2,
			APPLICATION_XML_VERSION_1_3,
			APPLICATION_XML_VERSION_1_4,
			APPLICATION_XML_VERSION_5_0,
			APPLICATION_XML_VERSION_6_0
	};
	ConfigFileMetaData APPLICATION_XML_META_DATA = new ConfigFileMetaData(J2EEBundle.message("deployment.descriptor.title.application.module"),
			"application.xml", "META-INF", APPLICATION_XML_VERSIONS, null, false, true, true);


	ConfigFileVersion WEB_XML_VERSION_2_2 = new ConfigFileVersion(SERVLET_VERSION_2_2, J2EEFileTemplateNames.WEB_XML_22);
	ConfigFileVersion WEB_XML_VERSION_2_3 = new ConfigFileVersion(SERVLET_VERSION_2_3, J2EEFileTemplateNames.WEB_XML_23);
	ConfigFileVersion WEB_XML_VERSION_2_4 = new ConfigFileVersion(SERVLET_VERSION_2_4, J2EEFileTemplateNames.WEB_XML_24);
	ConfigFileVersion WEB_XML_VERSION_2_5 = new ConfigFileVersion(SERVLET_VERSION_2_5, J2EEFileTemplateNames.WEB_XML_25);
	ConfigFileVersion WEB_XML_VERSION_3_0 = new ConfigFileVersion(SERVLET_VERSION_3_0, J2EEFileTemplateNames.WEB_XML_30);
	ConfigFileVersion[] WEB_XML_VERSIONS = {
			WEB_XML_VERSION_2_2,
			WEB_XML_VERSION_2_3,
			WEB_XML_VERSION_2_4,
			WEB_XML_VERSION_2_5,
			WEB_XML_VERSION_3_0
	};
	ConfigFileMetaData WEB_XML_META_DATA = new ConfigFileMetaData(J2EEBundle.message("deployment.descriptor.title.web.module"), "web.xml",
			"WEB-INF", WEB_XML_VERSIONS, WEB_XML_VERSIONS[3], false, true, true);
}
