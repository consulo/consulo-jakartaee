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

import org.jetbrains.annotations.NonNls;

/**
 * @author Alexey Kudravtsev
 */
public abstract class J2EEFileTemplateNames
{
	@NonNls
	public static final String EJB_JAR_XML_1_1 = "ejb-jar.1_1.xml";
	@NonNls
	public static final String EJB_JAR_XML_2_0 = "ejb-jar.2_0.xml";
	@NonNls
	public static final String EJB_JAR_XML_2_1 = "ejb-jar.2_1.xml";
	@NonNls
	public static final String EJB_JAR_XML_3_0 = "ejb-jar.3_0.xml";
	@NonNls
	public static final String EJB_JAR_XML_3_1 = "ejb-jar.3_1.xml";
	@NonNls
	public static final String APPLICATION_XML_1_2 = "application.1_2.xml";
	@NonNls
	public static final String APPLICATION_XML_1_3 = "application.1_3.xml";
	@NonNls
	public static final String APPLICATION_XML_1_4 = "application.1_4.xml";
	@NonNls
	public static final String APPLICATION_XML_5_0 = "application.5.xml";
	@NonNls
	public static final String APPLICATION_XML_6_0 = "application.6.xml";
	@NonNls
	public static final String WEB_XML_22 = "web.2_2.xml";
	@NonNls
	public static final String WEB_XML_23 = "web.2_3.xml";
	@NonNls
	public static final String WEB_XML_24 = "web.2_4.xml";
	@NonNls
	public static final String WEB_XML_25 = "web.2_5.xml";
	@NonNls
	public static final String WEB_XML_30 = "web.3_0.xml";
	@NonNls
	public static final String ENTITY_HOME_INTERFACE_TEMPLATE = "Entity Home Interface.java";
	@NonNls
	public static final String ENTITY_LOCAL_HOME_INTERFACE_TEMPLATE = "Entity Local Home Interface.java";
	@NonNls
	public static final String SESSION_HOME_INTERFACE_TEMPLATE = "Session Home Interface.java";
	@NonNls
	public static final String SESSION_LOCAL_HOME_INTERFACE_TEMPLATE = "Session Local Home Interface.java";
	@NonNls
	public static final String SESSION_SERVICE_ENDPOINT_INTERFACE = "Session Service Endpoint Interface.java";
	@NonNls
	public static final String REMOTE_INTERFACE_TEMPLATE = "EJB Remote Interface.java";
	@NonNls
	public static final String LOCAL_INTERFACE_TEMPLATE = "EJB Local Interface.java";
	//@NonNls public static final String SESSION_SERVICE_ENDPOINT_INTERFACE_3 = "Service Endpoint Interface.java";
	@NonNls
	public static final String ENTITY_CLASS_CMP_1x_TEMPLATE = "Entity Bean Class CMP 1x.java";
	@NonNls
	public static final String ENTITY_CLASS_CMP_2x_TEMPLATE = "Entity Bean Class CMP 2x.java";
	@NonNls
	public static final String ENTITY_CLASS_BMP_TEMPLATE = "Entity Bean Class BMP.java";
	@NonNls
	public static final String PERSISTENT_ENTITY_CLASS_TEMPLATE_3 = "Persistent Entity Class 3.java";
	@NonNls
	public static final String PERSISTENT_EMBEDDABLE_CLASS_TEMPLATE_3 = "Persistent Embeddable Class 3.java";
	@NonNls
	public static final String SESSION_CLASS_STATEFUL_TEMPLATE = "Session Bean Class Stateful.java";
	@NonNls
	public static final String SESSION_CLASS_STATEFUL_TEMPLATE_3 = "Session Bean Class Stateful 3.java";
	@NonNls
	public static final String SESSION_CLASS_STATELESS_TEMPLATE = "Session Bean Class Stateless.java";
	@NonNls
	public static final String SESSION_CLASS_STATELESS_TEMPLATE_3 = "Session Bean Class Stateless 3.java";
	@NonNls
	public static final String SESSION_CLASS_SINGLETON_TEMPLATE = "Session Bean Class Singleton.java";
	@NonNls
	public static final String MESSAGE_CLASS_TEMPLATE = "Message Driven Bean Class.java";
	@NonNls
	public static final String MESSAGE_CLASS_TEMPLATE_3 = "Message Driven Bean Class 3.java";
	@NonNls
	public static final String INTERCEPTOR_TEMPLATE_3 = "EnterpriseBean Interceptor 3.java";

	@NonNls
	public static final String SERVLET_CLASS_TEMPLATE = "Servlet Class.java";
	@NonNls
	public static final String SERVLET_ANNOTATED_CLASS_TEMPLATE = "Servlet Annotated Class.java";
	@NonNls
	public static final String FILTER_CLASS_TEMPLATE = "Filter Class.java";
	public static final String FILTER_ANNOTATED_CLASS_TEMPLATE = "Filter Annotated Class.java";
	@NonNls
	public static final String LISTENER_CLASS_TEMPLATE = "Listener Class.java";
	public static final String LISTENER_ANNOTATED_CLASS_TEMPLATE = "Listener Annotated Class.java";

	@NonNls
	public static final String JSP_FILE = "Jsp File.jsp";
	@NonNls
	public static final String JSPX_FILE = "Jspx File.jspx";
	@NonNls
	public static final String TLD_FILE = "Tag Library Descriptor.tld";

	@NonNls
	public static final String PERSISTENCE_XML_1_0 = "persistence.xml";
	@NonNls
	public static final String PERSISTENCE_XML_2_0 = "persistence_2_0.xml";
	@NonNls
	public static final String ORM_XML_1_0 = "orm_1_0.xml";
	@NonNls
	public static final String ORM_XML_2_0 = "orm_2_0.xml";
}
