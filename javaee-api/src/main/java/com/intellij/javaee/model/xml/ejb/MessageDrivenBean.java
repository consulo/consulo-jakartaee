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

import com.intellij.javaee.model.enums.TransactionType;
import com.intellij.javaee.model.xml.*;
import com.intellij.javaee.model.xml.compatibility.MessageDrivenSupport;
import com.intellij.psi.PsiClass;
import com.intellij.util.xml.GenericDomValue;
import javax.annotation.Nonnull;

import java.util.List;

/**
 * http://java.sun.com/xml/ns/javaee:message-driven-beanType interface.
 * <pre>
 * <h3>Type http://java.sun.com/xml/ns/javaee:message-driven-beanType documentation</h3>
 * The message-driven element declares a message-driven
 * 	bean. The declaration consists of:
 * 	    - an optional description
 * 	    - an optional display name
 * 	    - an optional icon element that contains a small and a large
 * 	      icon file name.
 * 	    - a name assigned to the enterprise bean in
 * 	      the deployment descriptor
 *             - an optional mapped-name element that can be used to provide
 *               vendor-specific deployment information such as the physical
 *               jndi-name of destination from which this message-driven bean
 *               should consume.  This element is not required to be supported
 *               by all implementations.  Any use of this element is non-portable.
 * 	    - the message-driven bean's implementation class
 * 	    - an optional declaration of the bean's messaging
 * 	      type
 *             - an optional declaration of the bean's timeout method for
 * 	      handling programmatically created timers
 * 	    - an optional declaration of timers to be automatically created at
 * 	      deployment time
 * 	    - the optional message-driven bean's transaction management
 *               type. If it is not defined, it is defaulted to Container.
 * 	    - an optional declaration of the bean's
 * 	      message-destination-type
 * 	    - an optional declaration of the bean's
 * 	      message-destination-link
 * 	    - an optional declaration of the message-driven bean's
 * 	      activation configuration properties
 *             - an optional list of the message-driven bean class and/or
 *               superclass around-invoke methods.
 *             - an optional list of the message-driven bean class and/or
 *               superclass around-timeout methods.
 * 	    - an optional declaration of the bean's environment
 * 	      entries
 * 	    - an optional declaration of the bean's EJB references
 * 	    - an optional declaration of the bean's local EJB
 * 	      references
 * 	    - an optional declaration of the bean's web service
 * 	      references
 * 	    - an optional declaration of the security role
 * 	      references
 * 	    - an optional declaration of the security
 * 	      identity to be used for the execution of the bean's
 * 	      methods
 * 	    - an optional declaration of the bean's
 * 	      resource manager connection factory
 * 	      references
 * 	    - an optional declaration of the bean's resource
 * 	      environment references.
 * 	    - an optional declaration of the bean's message
 * 	      destination references
 * </pre>
 */
public interface MessageDrivenBean extends EjbBase, com.intellij.javaee.model.common.ejb.MessageDrivenBean, MessageDrivenSupport {

	/**
	 * Returns the value of the ejb-name child.
	 * <pre>
	 * <h3>Type http://java.sun.com/xml/ns/javaee:ejb-nameType documentation</h3>
	 * 	  The ejb-nameType specifies an enterprise bean's name. It is
	 * 	  used by ejb-name elements. This name is assigned by the
	 * 	  ejb-jar file producer to name the enterprise bean in the
	 * 	  ejb-jar file's deployment descriptor. The name must be
	 * 	  unique among the names of the enterprise beans in the same
	 * 	  ejb-jar file.
	 * 	  There is no architected relationship between the used
	 * 	  ejb-name in the deployment descriptor and the JNDI name that
	 * 	  the Deployer will assign to the enterprise bean's home.
	 * 	  The name for an entity bean must conform to the lexical
	 * 	  rules for an NMTOKEN.
	 * 	  Example:
	 * 	  <ejb-name>EmployeeService</ejb-name>
	 * 	  
	 * </pre>
	 * @return the value of the ejb-name child.
	 */
	@Nonnull
	GenericDomValue<String> getEjbName();


	/**
	 * Returns the value of the mapped-name child.
	 * <pre>
	 * <h3>Type http://java.sun.com/xml/ns/javaee:xsdStringType documentation</h3>
	 * This type adds an "id" attribute to xsd:string.
	 * </pre>
	 * @return the value of the mapped-name child.
	 */
	GenericDomValue<String> getMappedName();


	/**
	 * Returns the value of the ejb-class child.
	 * <pre>
	 * <h3>Element http://java.sun.com/xml/ns/javaee:ejb-class documentation</h3>
	 * The ejb-class element specifies the fully qualified name
	 *              of the bean class for this ejb.  It is required unless
	 *              there is a component-defining annotation for the same
	 *              ejb-name.
	 * </pre>
	 * <pre>
	 * <h3>Type http://java.sun.com/xml/ns/javaee:ejb-classType documentation</h3>
	 * 	  The ejb-classType contains the fully-qualified name of the
	 * 	  enterprise bean's class. It is used by ejb-class elements.
	 * 	  Example:
	 * 	      <ejb-class>com.wombat.empl.EmployeeServiceBean</ejb-class>
	 * 	  
	 * </pre>
	 * @return the value of the ejb-class child.
	 */
	GenericDomValue<PsiClass> getEjbClass();


	/**
	 * Returns the value of the messaging-type child.
	 * <pre>
	 * <h3>Element http://java.sun.com/xml/ns/javaee:messaging-type documentation</h3>
	 * The messaging-type element specifies the message
	 * 	    listener interface of the message-driven bean.
	 * </pre>
	 * <pre>
	 * <h3>Type http://java.sun.com/xml/ns/javaee:fully-qualified-classType documentation</h3>
	 * The elements that use this type designate the name of a
	 * 	Java class or interface.  The name is in the form of a
	 * 	"binary name", as defined in the JLS.  This is the form
	 * 	of name used in Class.forName().  Tools that need the
	 * 	canonical name (the name used in source code) will need
	 * 	to convert this binary name to the canonical name.
	 * </pre>
	 * @return the value of the messaging-type child.
	 */
	GenericDomValue<PsiClass> getMessagingType();


	/**
	 * Returns the value of the timeout-method child.
	 * <pre>
	 * <h3>Element http://java.sun.com/xml/ns/javaee:timeout-method documentation</h3>
	 * The timeout-method element specifies the method that
	 * 	    will receive callbacks for programmatically
	 * 	    created timers.
	 * </pre>
	 * @return the value of the timeout-method child.
	 */
	NamedMethod getTimeoutMethod();


	/**
	 * Returns the list of timer children.
	 * @return the list of timer children.
	 */
	List<Timer> getTimers();
	/**
	 * Adds new child to the list of timer children.
	 * @return created child
	 */
	Timer addTimer();


	/**
	 * Returns the value of the transaction-type child.
	 * @return the value of the transaction-type child.
	 */
	GenericDomValue<TransactionType> getTransactionType();


	/**
	 * Returns the value of the message-destination-type child.
	 * <pre>
	 * <h3>Type http://java.sun.com/xml/ns/javaee:message-destination-typeType documentation</h3>
	 * 	  The message-destination-typeType specifies the type of
	 * 	  the destination. The type is specified by the Java interface
	 * 	  expected to be implemented by the destination.
	 * 	  Example:
	 * 	    <message-destination-type>javax.jms.Queue
	 * 	    </message-destination-type>
	 * 	  
	 * </pre>
	 * @return the value of the message-destination-type child.
	 */
	GenericDomValue<PsiClass> getMessageDestinationType();


	/**
	 * Returns the value of the message-destination-link child.
	 * <pre>
	 * <h3>Type http://java.sun.com/xml/ns/javaee:message-destination-linkType documentation</h3>
	 * The message-destination-linkType is used to link a message
	 * 	destination reference or message-driven bean to a message
	 * 	destination.
	 * 	The Assembler sets the value to reflect the flow of messages
	 * 	between producers and consumers in the application.
	 * 	The value must be the message-destination-name of a message
	 * 	destination in the same Deployment File or in another
	 * 	Deployment File in the same Java EE application unit.
	 * 	Alternatively, the value may be composed of a path name
	 * 	specifying a Deployment File containing the referenced
	 * 	message destination with the message-destination-name of the
	 * 	destination appended and separated from the path name by
	 * 	"#". The path name is relative to the Deployment File
	 * 	containing Deployment Component that is referencing the
	 * 	message destination.  This allows multiple message
	 * 	destinations with the same name to be uniquely identified.
	 * </pre>
	 * @return the value of the message-destination-link child.
	 */
	GenericDomValue<String> getMessageDestinationLink();


	/**
	 * Returns the value of the activation-config child.
	 * @return the value of the activation-config child.
	 */
	ActivationConfig getActivationConfig();


	/**
	 * Returns the list of around-invoke children.
	 * @return the list of around-invoke children.
	 */
	List<AroundInvoke> getAroundInvokes();
	/**
	 * Adds new child to the list of around-invoke children.
	 * @return created child
	 */
	AroundInvoke addAroundInvoke();


	/**
	 * Returns the list of around-timeout children.
	 * @return the list of around-timeout children.
	 */
	List<AroundTimeout> getAroundTimeouts();
	/**
	 * Adds new child to the list of around-timeout children.
	 * @return created child
	 */
	AroundTimeout addAroundTimeout();


	/**
	 * Returns the list of security-role-ref children.
	 * @return the list of security-role-ref children.
	 */
	List<SecurityRoleRef> getSecurityRoleRefs();
	/**
	 * Adds new child to the list of security-role-ref children.
	 * @return created child
	 */
	SecurityRoleRef addSecurityRoleRef();


	/**
	 * Returns the value of the security-identity child.
	 * @return the value of the security-identity child.
	 */
	SecurityIdentity getSecurityIdentity();


	/**
	 * Returns the list of description children.
	 * @return the list of description children.
	 */
	List<Description> getDescriptions();
	/**
	 * Adds new child to the list of description children.
	 * @return created child
	 */
	Description addDescription();


	/**
	 * Returns the list of display-name children.
	 * @return the list of display-name children.
	 */
	List<DisplayName> getDisplayNames();
	/**
	 * Adds new child to the list of display-name children.
	 * @return created child
	 */
	DisplayName addDisplayName();


	/**
	 * Returns the list of icon children.
	 * @return the list of icon children.
	 */
	List<Icon> getIcons();
	/**
	 * Adds new child to the list of icon children.
	 * @return created child
	 */
	Icon addIcon();


	/**
	 * Returns the list of env-entry children.
	 * @return the list of env-entry children.
	 */
	List<EnvEntry> getEnvEntries();
	/**
	 * Adds new child to the list of env-entry children.
	 * @return created child
	 */
	EnvEntry addEnvEntry();


	/**
	 * Returns the list of ejb-ref children.
	 * @return the list of ejb-ref children.
	 */
	List<EjbRef> getEjbRefs();
	/**
	 * Adds new child to the list of ejb-ref children.
	 * @return created child
	 */
	EjbRef addEjbRef();


	/**
	 * Returns the list of ejb-local-ref children.
	 * @return the list of ejb-local-ref children.
	 */
	List<EjbLocalRef> getEjbLocalRefs();
	/**
	 * Adds new child to the list of ejb-local-ref children.
	 * @return created child
	 */
	EjbLocalRef addEjbLocalRef();


	/**
	 * Returns the list of resource-ref children.
	 * @return the list of resource-ref children.
	 */
	List<ResourceRef> getResourceRefs();
	/**
	 * Adds new child to the list of resource-ref children.
	 * @return created child
	 */
	ResourceRef addResourceRef();


	/**
	 * Returns the list of resource-env-ref children.
	 * @return the list of resource-env-ref children.
	 */
	List<ResourceEnvRef> getResourceEnvRefs();
	/**
	 * Adds new child to the list of resource-env-ref children.
	 * @return created child
	 */
	ResourceEnvRef addResourceEnvRef();


	/**
	 * Returns the list of message-destination-ref children.
	 * @return the list of message-destination-ref children.
	 */
	List<MessageDestinationRef> getMessageDestinationRefs();
	/**
	 * Adds new child to the list of message-destination-ref children.
	 * @return created child
	 */
	MessageDestinationRef addMessageDestinationRef();


	/**
	 * Returns the list of persistence-context-ref children.
	 * @return the list of persistence-context-ref children.
	 */
	List<PersistenceContextRef> getPersistenceContextRefs();
	/**
	 * Adds new child to the list of persistence-context-ref children.
	 * @return created child
	 */
	PersistenceContextRef addPersistenceContextRef();


	/**
	 * Returns the list of persistence-unit-ref children.
	 * @return the list of persistence-unit-ref children.
	 */
	List<PersistenceUnitRef> getPersistenceUnitRefs();
	/**
	 * Adds new child to the list of persistence-unit-ref children.
	 * @return created child
	 */
	PersistenceUnitRef addPersistenceUnitRef();


	/**
	 * Returns the list of post-construct children.
	 * @return the list of post-construct children.
	 */
	List<LifecycleCallback> getPostConstructs();
	/**
	 * Adds new child to the list of post-construct children.
	 * @return created child
	 */
	LifecycleCallback addPostConstruct();


	/**
	 * Returns the list of pre-destroy children.
	 * @return the list of pre-destroy children.
	 */
	List<LifecycleCallback> getPreDestroys();
	/**
	 * Adds new child to the list of pre-destroy children.
	 * @return created child
	 */
	LifecycleCallback addPreDestroy();


	/**
	 * Returns the list of service-ref children.
	 * @return the list of service-ref children.
	 */
	List<ServiceRef> getServiceRefs();
	/**
	 * Adds new child to the list of service-ref children.
	 * @return created child
	 */
	ServiceRef addServiceRef();


}
