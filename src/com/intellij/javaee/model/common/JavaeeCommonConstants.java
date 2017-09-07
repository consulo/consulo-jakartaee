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

package com.intellij.javaee.model.common;

import org.jetbrains.annotations.NonNls;
import com.intellij.psi.CommonClassNames;

/**
 * Created by IntelliJ IDEA.
 * User: greg
 * Date: 19.12.2005
 * Time: 17:51:28
 */
public interface JavaeeCommonConstants {
  @NonNls String APPLICATION_XML_ROOT_TAG = "application";
  @NonNls String WEB_XML_ROOT_TAG = "web-app";
  @NonNls String WEB_FRAGMENT_ROOT_TAG = "web-fragment";
  @NonNls String EJB_JAR_XML_ROOT_TAG = "ejb-jar";

  @NonNls String JAVAX_EJB_PACKAGE_PREFIX = "javax.ejb.";
  String SERIALIZABLE_CLASS = "java.io.Serializable";
  String EXTERNALIZABLE_CLASS = "java.io.Externalizable";
  String OBJECT_CLASS = CommonClassNames.JAVA_LANG_OBJECT;
  String BIG_INTEGER_CLASS = "java.math.BigInteger";
  String BIG_DECIMAL_CLASS = "java.math.BigDecimal";
  String SQL_DATE_CLASS = "java.sql.Date";
  String SQL_TIME_CLASS = "java.sql.Time";
  String SQL_TIMESTAMP_CLASS = "java.sql.Timestamp";
  String UTIL_CALENDAR_CLASS = "java.util.Calendar";
  @NonNls String EJB_JNDI_CONTEXT = "java:comp/env/";
  String JAVAX_NAMING_CONTEXT_CLASS = "javax.naming.Context";
  String JAVAX_EJB_CONTEXT_CLASS = "javax.ejb.EJBContext";
  String INTERCEPTOR_INVOCATION_CONTEXT_CLASS = "javax.interceptor.InvocationContext";
  String JAVA_LANG_OBJECT_CLASS = CommonClassNames.JAVA_LANG_OBJECT;
  String MESSAGE_DRIVEN_BEAN_CLASS = "javax.ejb.MessageDrivenBean";
  String SESSION_BEAN_CLASS = "javax.ejb.SessionBean";
  String TIMED_OBJECT_CLASS = "javax.ejb.TimedObject";
  String SESSION_SYNCHRONIZATION_CLASS = "javax.ejb.SessionSynchronization";
  String ENTITY_BEAN_CLASS = "javax.ejb.EntityBean";
  String EJBOBJECT_CLASS = "javax.ejb.EJBObject";
  String EJB_LOCAL_OBJECT_CLASS = "javax.ejb.EJBLocalObject";
  String REMOTE_EXCEPTION_CLASS = "java.rmi.RemoteException";
  String REMOTE_INTERFACE_CLASS = "java.rmi.Remote";
  String JAVAX_EJB_FINDER_EXCEPTION_CLASS = "javax.ejb.FinderException";
  String JAVAX_EJB_CREATE_EXCEPTION_CLASS = "javax.ejb.CreateException";
  String JAVAX_EJB_EXCEPTION_CLASS = "javax.ejb.EJBException";
  String SESSION_CONTEXT_CLASS = "javax.ejb.SessionContext";
  String MESSAGE_DRIVEN_CONTEXT_CLASS = "javax.ejb.MessageDrivenContext";
  String ENTITY_CONTEXT_CLASS = "javax.ejb.EntityContext";
  @NonNls String JMS_ACKNOWLEDGE_MODE_PROPERTY_NAME = "acknowledgeMode";
  @NonNls String JMS_MESSAGE_SELECTOR_PROPERTY_NAME = "messageSelector";
  @NonNls String JMS_DESTINATION_TYPE_PROPERTY_NAME = "destinationType";
  @NonNls String JMS_SUBSCRIPTION_DURABILITY_TYPE_PROPERTY_NAME = "subscriptionDurability";
  String QUEUE_DEST_TYPE = "javax.jms.Queue";
  String TOPIC_DEST_TYPE = "javax.jms.Topic";
  String JMS_MESSAGE_LISTENER_CLASS = "javax.jms.MessageListener";
  @NonNls String ABSTRACT_SCHEMA_NAME = "abstract-schema-name";
  @NonNls String COLLECTION_CLASS = "java.util.Collection";
  @NonNls String MAP_CLASS = CommonClassNames.JAVA_UTIL_MAP;
  @NonNls String SET_CLASS = CommonClassNames.JAVA_UTIL_SET;
  @NonNls String LIST_CLASS = CommonClassNames.JAVA_UTIL_LIST;
  @NonNls String META_INF = "META-INF";
  @NonNls String J2EE_NAMESPACE = "http://java.sun.com/xml/ns/j2ee";
  @NonNls String JAVAEE_NAMESPACE = "http://java.sun.com/xml/ns/javaee";
  @NonNls String JAVAEE7_NAMESPACE = "http://xmlns.jcp.org/xml/ns/javaee";
  @NonNls String EJB_JAR_1_0_DTD = "http://java.sun.com/j2ee/dtds/ejb-jar_1_0.dtd";
  @NonNls String EJB_JAR_1_1_DTD = "http://java.sun.com/j2ee/dtds/ejb-jar_1_1.dtd";
  @NonNls String EJB_JAR_2_0_DTD = "http://java.sun.com/dtd/ejb-jar_2_0.dtd";
  @NonNls String EJB_1_0_PUBLIC_ID = "-//Sun Microsystems, Inc.//DTD Enterprise JavaBeans 1.0//EN";
  @NonNls String EJB_1_1_PUBLIC_ID = "-//Sun Microsystems, Inc.//DTD Enterprise JavaBeans 1.1//EN";
  @NonNls String EJB_2_0_PUBLIC_ID = "-//Sun Microsystems, Inc.//DTD Enterprise JavaBeans 2.0//EN";
  @NonNls String VERSION_ATTR = "version";

  @NonNls String SERVLET_CLASS = "javax.servlet.Servlet";
  @NonNls String JSP_PAGE_CLASS = "javax.servlet.jsp.JspPage";
}
