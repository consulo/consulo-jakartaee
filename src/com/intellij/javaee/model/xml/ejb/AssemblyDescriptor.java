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

// Generated on Tue Feb 14 17:35:31 MSK 2006
// DTD/Schema  :    http://java.sun.com/xml/ns/javaee

package com.intellij.javaee.model.xml.ejb;

import com.intellij.javaee.model.xml.JavaeeDomModelElement;
import com.intellij.javaee.model.xml.MessageDestination;
import com.intellij.javaee.model.xml.SecurityRole;

import java.util.List;

/**
 * http://java.sun.com/xml/ns/javaee:assembly-descriptorType interface.
 * <pre>
 * <h3>Type http://java.sun.com/xml/ns/javaee:assembly-descriptorType documentation</h3>
 * The assembly-descriptorType defines
 * 	application-assembly information.
 * 	The application-assembly information consists of the
 * 	following parts: the definition of security roles, the
 * 	definition of method permissions, the definition of
 * 	transaction attributes for enterprise beans with
 * 	container-managed transaction demarcation, the definition
 *         of interceptor bindings, a list of
 * 	methods to be excluded from being invoked, and a list of
 *         exception types that should be treated as application exceptions.
 * 	All the parts are optional in the sense that they are
 * 	omitted if the lists represented by them are empty.
 * 	Providing an assembly-descriptor in the deployment
 * 	descriptor is optional for the ejb-jar file producer.
 * </pre>
 */
public interface AssemblyDescriptor extends JavaeeDomModelElement {

	/**
	 * Returns the list of security-role children.
	 * @return the list of security-role children.
	 */
	List<SecurityRole> getSecurityRoles();
	/**
	 * Adds new child to the list of security-role children.
	 * @return created child
	 */
	SecurityRole addSecurityRole();


	/**
	 * Returns the list of method-permission children.
	 * @return the list of method-permission children.
	 */
	List<MethodPermission> getMethodPermissions();
	/**
	 * Adds new child to the list of method-permission children.
	 * @return created child
	 */
	MethodPermission addMethodPermission();


	/**
	 * Returns the list of container-transaction children.
	 * @return the list of container-transaction children.
	 */
	List<ContainerTransaction> getContainerTransactions();
	/**
	 * Adds new child to the list of container-transaction children.
	 * @return created child
	 */
	ContainerTransaction addContainerTransaction();


	/**
	 * Returns the list of interceptor-binding children.
	 * @return the list of interceptor-binding children.
	 */
	List<InterceptorBinding> getInterceptorBindings();
	/**
	 * Adds new child to the list of interceptor-binding children.
	 * @return created child
	 */
	InterceptorBinding addInterceptorBinding();


	/**
	 * Returns the list of message-destination children.
	 * @return the list of message-destination children.
	 */
	List<MessageDestination> getMessageDestinations();
	/**
	 * Adds new child to the list of message-destination children.
	 * @return created child
	 */
	MessageDestination addMessageDestination();


	/**
	 * Returns the value of the exclude-list child.
	 * @return the value of the exclude-list child.
	 */
	ExcludeList getExcludeList();


	/**
	 * Returns the list of application-exception children.
	 * @return the list of application-exception children.
	 */
	List<ApplicationException> getApplicationExceptions();
	/**
	 * Adds new child to the list of application-exception children.
	 * @return created child
	 */
	ApplicationException addApplicationException();


}
