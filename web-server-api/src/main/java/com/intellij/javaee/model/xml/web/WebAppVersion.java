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

// Generated on Mon Jan 23 20:05:38 MSK 2006
// DTD/Schema  :    http://java.sun.com/xml/ns/javaee

package com.intellij.javaee.model.xml.web;

import com.intellij.java.impl.util.descriptors.ConfigFileVersion;
import com.intellij.javaee.DeploymentDescriptorsConstants;
import com.intellij.javaee.JavaeeVersion;
import consulo.jsp.language.file.JspVersion;
import consulo.xml.util.xml.NamedEnum;

import jakarta.annotation.Nonnull;

/**
 * http://java.sun.com/xml/ns/javaee:web-app-versionType enumeration.
 * <pre>
 * <h3>Enumeration http://java.sun.com/xml/ns/javaee:web-app-versionType documentation</h3>
 * This type contains the recognized versions of
 * 	web-application supported. It is used to designate the
 * 	version of the web application.
 * </pre>
 */
public enum WebAppVersion implements NamedEnum
{
	WebAppVersion_2_4("2.4", DeploymentDescriptorsConstants.WEB_XML_VERSION_2_4, JspVersion.JSP_2_0, JavaeeVersion.J2EE_1_4),
	WebAppVersion_2_5("2.5", DeploymentDescriptorsConstants.WEB_XML_VERSION_2_5, JspVersion.JSP_2_1, JavaeeVersion.JAVAEE_5),
	WebAppVersion_3_0("3.0", DeploymentDescriptorsConstants.WEB_XML_VERSION_3_0, JspVersion.JSP_2_2, JavaeeVersion.JAVAEE_6),
	WebAppVersion_3_1("3.1", DeploymentDescriptorsConstants.WEB_XML_VERSION_3_0, JspVersion.JSP_2_3, JavaeeVersion.JAVAEE_7);//todo[nik] add template for version 3.1

	private final String value;
	private final ConfigFileVersion myConfigFileVersion;
	private final JspVersion myJspVersion;
	private final JavaeeVersion myJavaeeVersion;

	WebAppVersion(@Nonnull String value, @Nonnull ConfigFileVersion configFileVersion, @Nonnull JspVersion jspVersion, @Nonnull JavaeeVersion javaeeVersion)
	{
		this.value = value;
		myConfigFileVersion = configFileVersion;
		myJspVersion = jspVersion;
		myJavaeeVersion = javaeeVersion;
	}

	@Nonnull
	public ConfigFileVersion getConfigFileVersion()
	{
		return myConfigFileVersion;
	}

	public String getValue()
	{
		return value;
	}

	public JspVersion getJspVersion()
	{
		return myJspVersion;
	}

	public JavaeeVersion getJavaeeVersion()
	{
		return myJavaeeVersion;
	}
}
