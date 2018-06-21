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

// Generated on Mon Nov 28 17:28:23 MSK 2005
// DTD/Schema  :    ejb-jar_2_0.dtd

package com.intellij.javaee.model.xml.compatibility;

import com.intellij.javaee.model.enums.TransactionType;
import com.intellij.javaee.model.xml.*;
import com.intellij.javaee.model.xml.ejb.SecurityIdentity;
import com.intellij.psi.PsiClass;
import com.intellij.util.xml.GenericDomValue;
import javax.annotation.Nonnull;

import java.util.List;

/**
 * ejb-jar_2_0.dtd:message-driven interface.
 * Type message-driven documentation
 * <pre>
 * The message-driven element declares a message-driven bean. The
 * declaration consists of:
 * 	- an optional description
 * 	- an optional display name
 * 	- an optional small icon file name
 * 	- an optional large icon file name
 * 	- a name assigned to the enterprise bean in
 * 	  the deployment descriptor
 * 	- the message-driven bean's implementation class
 * 	- the message-driven bean's transaction management type
 * 	- an optional declaration of the message-driven bean's
 * 	  message selector
 * 	- an optional declaration of the
 * 	  acknowledgment mode for the message-driven bean
 * 	  if bean-managed transaction demarcation is used
 * 	- an optional declaration of the
 * 	  intended destination type of the message-driven bean
 * 	- an optional declaration of the bean's environment entries
 * 	- an optional declaration of the bean's EJB references
 * 	- an optional declaration of the bean's local EJB references
 * 	- an optional declaration of the security
 * 	  identity to be used for the execution of the bean's methods
 * 	- an optional declaration of the bean's resource manager
 * 	  connection factory references
 * 	- an optional declaration of the bean's resource
 *           environment references.
 * Used in: enterprise-beans
 * </pre>
 */
public interface MessageDriven extends JavaeeDomModelElement {

	/**
	 * Returns the value of the description child.
	 * Type description documentation
	 * <pre>
	 * The description element is used to provide text describing the parent
	 * element.  The description element should include any information that
	 * the enterprise bean ejb-jar file producer wants to provide to the consumer of
	 * the enterprise bean ejb-jar file (i.e., to the Deployer). Typically, the tools
	 * used by the enterprise bean ejb-jar file consumer will display the description
	 * when processing the parent element that contains the description.
	 * Used in: cmp-field, cmr-field, container-transaction, ejb-jar,
	 * ejb-local-ref, ejb-ref, ejb-relation, ejb-relationship-role, entity,
	 * env-entry, exclude-list, message-driven, method, method-permission,
	 * query, relationship-role-source, relationships, resource-env-ref,
	 * resource-ref, run-as, security-identity, security-role,
	 * security-role-ref, session
	 * </pre>
	 */
	//@SubTag ("description")
	//public GenericDomValue<String> getDescription();


	/**
	 * Returns the value of the display-name child.
	 * Type display-name documentation
	 * <pre>
	 * The display-name element contains a short name that is intended to be
	 * displayed by tools.  The display name need not be unique.
	 * Used in: ejb-jar, entity, message-driven, session
	 * Example:
	 * <display-name>Employee Self Service</display-name>
	 * </pre>
	 */
	//@SubTag ("display-name")
	//public GenericDomValue<String> getDisplayName();


	/**
         * <b>EJB2.0 Compatibility Method. Don't use for EJB 2.1 and up.</b><p>
	 * Returns the value of the small-icon child.
	 * Type small-icon documentation
	 * <pre>
	 * The small-icon element contains the name of a file
	 * containing a small (16 x 16) icon image. The file
	 * name is a relative path within the enterprise bean's
	 * ejb-jar file.
	 * The image may be either in the JPEG or GIF format.
	 * The icon can be used by tools.
	 * Used in: ejb-jar, entity, message-driven, session
	 * Example:
	 * <small-icon>employee-service-icon16x16.jpg</small-icon>
	 * </pre>
         * @return the value of the small-icon child
	 */
	GenericDomValue<String> getSmallIcon();


	/**
         * <b>EJB2.0 Compatibility Method. Don't use for EJB 2.1 and up.</b><p>
	 * Returns the value of the large-icon child.
	 * Type large-icon documentation
	 * <pre>
	 * The large-icon element contains the name of a file
	 * containing a large (32 x 32) icon image. The file
	 * name is a relative path within the enterprise bean's
	 * ejb-jar file.
	 * The image may be either in the JPEG or GIF format.
	 * The icon can be used by tools.
	 * Used in: ejb-jar, entity, message-driven, session
	 * Example:
	 * <large-icon>employee-service-icon32x32.jpg</large-icon>
	 * </pre>
         * @return the value of the large-icon child
	 */
	GenericDomValue<String> getLargeIcon();


	/**
	 * Returns the value of the ejb-name child.
	 * Type ejb-name documentation
	 * <pre>
	 * The ejb-name element specifies an enterprise bean's name. This name is
	 * assigned by the ejb-jar file producer to name the enterprise bean in
	 * the ejb-jar file's deployment descriptor. The name must be unique
	 * among the names of the enterprise beans in the same ejb-jar file.
	 * There is no architected relationship between the ejb-name in the
	 * deployment descriptor and the JNDI name that the Deployer will assign
	 * to the enterprise bean's home.
	 * The name for an entity bean with cmp-version 2.x must conform to the
	 * lexical rules for an NMTOKEN. The name for an entity bean with
	 * cmp-version 2.x must not be a reserved literal in EJB QL.
	 * Used in: entity, message-driven, method, relationship-role-source,
	 * session
	 * Example:
	 * <ejb-name>EmployeeService</ejb-name>
	 * </pre>
         * @return the value of the ejb-name child
	 */
	@Nonnull
	GenericDomValue<String> getEjbName();


	/**
	 * Returns the value of the ejb-class child.
	 * Type ejb-class documentation
	 * <pre>
	 * The ejb-class element contains the fully-qualified name of the
	 * enterprise bean's class.
	 * Used in: entity, message-driven, session
	 * Example:
	 * <ejb-class>com.wombat.empl.EmployeeServiceBean</ejb-class>
	 * </pre>
         * @return the value of the ejb-class child
	 */
	@Nonnull
	GenericDomValue<PsiClass> getEjbClass();


	/**
	 * Returns the value of the transaction-type child.
	 * Type transaction-type documentation
	 * <pre>
	 * The transaction-type element specifies an enterprise bean's
	 * transaction management type.
	 * The transaction-type element must be one of the two following:
	 * 	<transaction-type>Bean</transaction-type>
	 * 	<transaction-type>Container</transaction-type>
	 * Used in: message-driven, session
	 * </pre>
         * @return the value of the transaction-type child
	 */
	@Nonnull
	GenericDomValue<TransactionType> getTransactionType();


	/**
         * <b>EJB2.0 Compatibility Method. Don't use for EJB 2.1 and up.</b><p>
	 * Returns the value of the message-selector child.
	 * Type message-selector documentation
	 * <pre>
	 * The message-selector element is used to specify the JMS message
	 * selector to be used in determining which messages a message-driven
	 * bean is to receive.
	 * Example:
	 * <message-selector>JMSType = `car' AND color = `blue' AND weight &gt; 2500
	 * </message-selector>
	 * Used in: message-driven
	 * </pre>
         * @return the value of the message-selector child
	 */
	GenericDomValue<String> getMessageSelector();


	/**
         * <b>EJB2.0 Compatibility Method. Don't use for EJB 2.1 and up.</b><p>
	 * Returns the value of the acknowledge-mode child.
	 * Type acknowledge-mode documentation
	 * <pre>
	 * The acknowledge-mode element specifies whether JMS AUTO_ACKNOWLEDGE or
	 * DUPS_OK_ACKNOWLEDGE message acknowledgment semantics should be used
	 * for the onMessage message of a message-driven bean that uses bean
	 * managed transaction demarcation.
	 * The acknowledge-mode element must be one of the two following:
	 * 	<acknowledge-mode>Auto-acknowledge</acknowledge-mode>
	 * 	<acknowledge-mode>Dups-ok-acknowledge</acknowledgemode>
	 * Used in: message-driven
	 * </pre>
         * @return the value of the acknowledge-mode child
	 */
	GenericDomValue<AcknowledgeMode> getAcknowledgeMode();


	/**
         * <b>EJB2.0 Compatibility Method. Don't use for EJB 2.1 and up.</b><p>
	 * Returns the value of the message-driven-destination child.
	 * Type message-driven-destination documentation
	 * <pre>
	 * The message-driven-destination element provides advice to the Deployer
	 * as to whether a message-driven bean is intended for a Queue or a
	 * Topic. The declaration consists of: the type of the message-driven
	 * bean's intended destination and an optional declaration of whether a
	 * durable or non-durable subscription should be used if the
	 * destination-type is javax.jms.Topic.
	 * Used in: message-driven
	 * </pre>
         * @return the value of the message-driven-destination child
	 */
	MessageDrivenDestination getMessageDrivenDestination();


	/**
	 * Returns the list of env-entry children.
	 * Type env-entry documentation
	 * <pre>
	 * The env-entry element contains the declaration of an enterprise bean's
	 * environment entry. The declaration consists of an optional
	 * description, the name of the environment entry, and an optional
	 * value.  If a value is not specified, one must be supplied
	 * during deployment.
	 * Used in: entity, message-driven, session
	 * </pre>
         * @return the list of env-entry children
	 */
	List<EnvEntry> getEnvEntries();
	/**
	 * Adds new child to the list of env-entry children.
	 * @return created child
	 */
	EnvEntry addEnvEntry();


	/**
	 * Returns the list of ejb-ref children.
	 * Type ejb-ref documentation
	 * <pre>
	 * The ejb-ref element is used for the declaration of a reference to
	 * an enterprise bean's home. The declaration consists of:
	 * 	- an optional description
	 * 	- the EJB reference name used in the code of
	 * 	  the enterprise bean that's referencing the enterprise bean
	 * 	- the expected type of the referenced enterprise bean
	 * 	- the expected home and remote interfaces of the referenced
	 * 	  enterprise bean
	 * 	- optional ejb-link information, used to specify the referenced
	 * 	  enterprise bean
	 * Used in: entity, message-driven, session
	 * </pre>
         * @return the list of ejb-ref children
	 */
	List<EjbRef> getEjbRefs();
	/**
	 * Adds new child to the list of ejb-ref children.
	 * @return created child
	 */
	EjbRef addEjbRef();


	/**
	 * Returns the list of ejb-local-ref children.
	 * Type ejb-local-ref documentation
	 * <pre>
	 * The ejb-local-ref element is used for the declaration of a reference to
	 * an enterprise bean's local home. The declaration consists of:
	 * 	- an optional description
	 * 	- the EJB reference name used in the code of the enterprise bean
	 * 	  that's referencing the enterprise bean
	 * 	- the expected type of the referenced enterprise bean
	 * 	- the expected local home and local interfaces of the referenced
	 * 	  enterprise bean
	 * 	- optional ejb-link information, used to specify the referenced
	 * 	  enterprise bean
	 * Used in: entity, message-driven, session
	 * </pre>
         * @return the list of ejb-local-ref children
	 */
	List<EjbLocalRef> getEjbLocalRefs();
	/**
	 * Adds new child to the list of ejb-local-ref children.
	 * @return created child
	 */
	EjbLocalRef addEjbLocalRef();


	/**
	 * Returns the value of the security-identity child.
	 * Type security-identity documentation
	 * <pre>
	 * The security-identity element specifies whether the caller's
	 * security identity is to be used for the execution of the methods of
	 * the enterprise bean or whether a specific run-as identity is to be
	 * used. It contains an optional description and a specification of the
	 * security identity to be used.
	 * Used in: entity, message-driven, session
	 * </pre>
         * @return the value of the security-identity child
	 */
	SecurityIdentity getSecurityIdentity();


	/**
	 * Returns the list of resource-ref children.
	 * Type resource-ref documentation
	 * <pre>
	 * The resource-ref element contains a declaration of an enterprise bean's
	 * reference to an external resource. It consists of an optional
	 * description, the resource manager connection factory reference name,
	 * the indication of the resource manager connection factory type
	 * expected by the enterprise bean code, the type of authentication
	 * (Application or Container), and an optional specification of the
	 * shareability of connections obtained from the resource (Shareable or
	 * Unshareable).
	 * Used in: entity, message-driven, session
	 * Example:
	 *     <resource-ref>
	 * 	<res-ref-name>jdbc/EmployeeAppDB</res-ref-name>
	 * 	<res-type>javax.sql.DataSource</res-type>
	 * 	<res-auth>Container</res-auth>
	 * 	<res-sharing-scope>Shareable</res-sharing-scope>
	 *     </resource-ref>
	 * </pre>
         * @return the list of resource-ref children
	 */
	List<ResourceRef> getResourceRefs();
	/**
	 * Adds new child to the list of resource-ref children.
	 * @return created child
	 */
	ResourceRef addResourceRef();


	/**
	 * Returns the list of resource-env-ref children.
	 * Type resource-env-ref documentation
	 * <pre>
	 * The resource-env-ref element contains a declaration of an enterprise bean's
	 * reference to an administered object associated with a resource
	 * in the enterprise bean's environment.  It consists of an optional
	 * description, the resource environment reference name, and an
	 * indication of the resource environment reference type expected by
	 * the enterprise bean code.
	 * Used in: entity, message-driven, session
	 * Example:
	 * <resource-env-ref>
	 *     <resource-env-ref-name>jms/StockQueue</resource-env-ref-name>
	 *     <resource-env-ref-type>javax.jms.Queue</resource-env-ref-type>
	 * </resource-env-ref>
	 * </pre>
         * @return the list of resource-env-ref children
	 */
	List<ResourceEnvRef> getResourceEnvRefs();
	/**
	 * Adds new child to the list of resource-env-ref children.
	 * @return created child
	 */
	ResourceEnvRef addResourceEnvRef();


}
