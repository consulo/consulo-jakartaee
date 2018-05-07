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

import com.intellij.javaee.model.enums.CmpVersion;
import com.intellij.javaee.model.enums.PersistenceType;
import com.intellij.javaee.model.xml.*;
import com.intellij.javaee.model.PrimkeyFieldResolvingConverter;
import com.intellij.javaee.model.CapitalizedBooleanConverter;
import com.intellij.psi.PsiClass;
import com.intellij.util.xml.GenericDomValue;
import com.intellij.util.xml.Convert;
import javax.annotation.Nonnull;

import java.util.List;

/**
 * http://java.sun.com/xml/ns/javaee:entity-beanType interface.
 * <pre>
 * <h3>Type http://java.sun.com/xml/ns/javaee:entity-beanType documentation</h3>
 * The entity-beanType declares an entity bean. The declaration
 * 	consists of:
 * 	    - an optional description
 * 	    - an optional display name
 * 	    - an optional icon element that contains a small and a large
 * 	      icon file name
 * 	    - a unique name assigned to the enterprise bean
 * 	      in the deployment descriptor
 *             - an optional mapped-name element that can be used to provide
 *               vendor-specific deployment information such as the physical
 *               jndi-name of the entity bean's remote home interface. This
 *               element is not required to be supported by all implementations.
 *               Any use of this element is non-portable.
 * 	    - the names of the entity bean's remote home
 * 	      and remote interfaces, if any
 * 	    - the names of the entity bean's local home and local
 * 	      interfaces, if any
 * 	    - the entity bean's implementation class
 * 	    - the optional entity bean's persistence management type. If
 *               this element is not specified it is defaulted to Container.
 * 	    - the entity bean's primary key class name
 * 	    - an indication of the entity bean's reentrancy
 * 	    - an optional specification of the
 * 	      entity bean's cmp-version
 * 	    - an optional specification of the entity bean's
 * 	      abstract schema name
 * 	    - an optional list of container-managed fields
 * 	    - an optional specification of the primary key
 * 	      field
 * 	    - an optional declaration of the bean's environment
 * 	      entries
 * 	    - an optional declaration of the bean's EJB
 * 	      references
 * 	    - an optional declaration of the bean's local
 * 	      EJB references
 * 	    - an optional declaration of the bean's web
 * 	      service references
 * 	    - an optional declaration of the security role
 * 	      references
 * 	    - an optional declaration of the security identity
 * 	      to be used for the execution of the bean's methods
 * 	    - an optional declaration of the bean's
 * 	      resource manager connection factory references
 * 	    - an optional declaration of the bean's
 * 	      resource environment references
 * 	    - an optional declaration of the bean's message
 * 	      destination references
 * 	    - an optional set of query declarations
 * 	      for finder and select methods for an entity
 * 	      bean with cmp-version 2.x.
 * 	The optional abstract-schema-name element must be specified
 * 	for an entity bean with container-managed persistence and
 * 	cmp-version 2.x.
 * 	The optional primkey-field may be present in the descriptor
 * 	if the entity's persistence-type is Container.
 * 	The optional cmp-version element may be present in the
 * 	descriptor if the entity's persistence-type is Container. If
 * 	the persistence-type is Container and the cmp-version
 * 	element is not specified, its value defaults to 2.x.
 * 	The optional home and remote elements must be specified if
 * 	the entity bean cmp-version is 1.x.
 * 	The optional home and remote elements must be specified if
 * 	the entity bean has a remote home and remote interface.
 * 	The optional local-home and local elements must be specified
 * 	if the entity bean has a local home and local interface.
 * 	Either both the local-home and the local elements or both
 * 	the home and the remote elements must be specified.
 * 	The optional query elements must be present if the
 * 	persistence-type is Container and the cmp-version is 2.x and
 * 	query methods other than findByPrimaryKey have been defined
 * 	for the entity bean.
 * 	The other elements that are optional are "optional" in the
 * 	sense that they are omitted if the lists represented by them
 * 	are empty.
 * 	At least one cmp-field element must be present in the
 * 	descriptor if the entity's persistence-type is Container and
 * 	the cmp-version is 1.x, and none must not be present if the
 * 	entity's persistence-type is Bean.
 * </pre>
 */
public interface EntityBean extends EjbWithHome, com.intellij.javaee.model.common.ejb.EntityBean {

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
	 * Returns the value of the home child.
	 * <pre>
	 * <h3>Type http://java.sun.com/xml/ns/javaee:homeType documentation</h3>
	 * 	  The homeType defines the fully-qualified name of
	 * 	  an enterprise bean's home interface.
	 * 	  Example:
	 * 	      <home>com.aardvark.payroll.PayrollHome</home>
	 * 	  
	 * </pre>
	 * @return the value of the home child.
	 */
	GenericDomValue<PsiClass> getHome();


	/**
	 * Returns the value of the remote child.
	 * <pre>
	 * <h3>Type http://java.sun.com/xml/ns/javaee:remoteType documentation</h3>
	 * 	  The remote element contains the fully-qualified name
	 * 	  of the enterprise bean's remote interface.
	 * 	  Example:
	 * 	      <remote>com.wombat.empl.EmployeeService</remote>
	 * 	  
	 * </pre>
	 * @return the value of the remote child.
	 */
	GenericDomValue<PsiClass> getRemote();


	/**
	 * Returns the value of the local-home child.
	 * <pre>
	 * <h3>Type http://java.sun.com/xml/ns/javaee:local-homeType documentation</h3>
	 * The local-homeType defines the fully-qualified
	 * 	name of an enterprise bean's local home interface.
	 * </pre>
	 * @return the value of the local-home child.
	 */
	GenericDomValue<PsiClass> getLocalHome();


	/**
	 * Returns the value of the local child.
	 * <pre>
	 * <h3>Type http://java.sun.com/xml/ns/javaee:localType documentation</h3>
	 * The localType defines the fully-qualified name of an
	 * 	enterprise bean's local interface.
	 * </pre>
	 * @return the value of the local child.
	 */
	GenericDomValue<PsiClass> getLocal();


	/**
	 * Returns the value of the ejb-class child.
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
	@Nonnull
	GenericDomValue<PsiClass> getEjbClass();


	/**
	 * Returns the value of the persistence-type child.
	 * @return the value of the persistence-type child.
	 */
	@Nonnull
	GenericDomValue<PersistenceType> getPersistenceType();


	/**
	 * Returns the value of the prim-key-class child.
	 * <pre>
	 * <h3>Element http://java.sun.com/xml/ns/javaee:prim-key-class documentation</h3>
	 * The prim-key-class element contains the
	 * 	    fully-qualified name of an
	 * 	    entity bean's primary key class.
	 * 	    If the definition of the primary key class is
	 * 	    deferred to deployment time, the prim-key-class
	 * 	    element should specify java.lang.Object.
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
	 * @return the value of the prim-key-class child.
	 */
	@Nonnull
        GenericDomValue<PsiClass> getPrimKeyClass();


	/**
	 * Returns the value of the reentrant child.
	 * <pre>
	 * <h3>Element http://java.sun.com/xml/ns/javaee:reentrant documentation</h3>
	 * The reentrant element specifies whether an entity
	 * 	    bean is reentrant or not.
	 * 	    The reentrant element must be one of the two
	 * 	    following: true or false
	 * </pre>
	 * <pre>
	 * <h3>Type http://java.sun.com/xml/ns/javaee:true-falseType documentation</h3>
	 * This simple type designates a boolean with only two
	 * 	permissible values
	 * 	- true
	 * 	- false
	 * </pre>
	 * @return the value of the reentrant child.
	 */
	@Nonnull
        @Convert(CapitalizedBooleanConverter.class)
        GenericDomValue<Boolean> getReentrant();


	/**
	 * Returns the value of the cmp-version child.
	 * @return the value of the cmp-version child.
	 */
	GenericDomValue<CmpVersion> getCmpVersion();


	/**
	 * Returns the value of the abstract-schema-name child.
	 * <pre>
	 * <h3>Element http://java.sun.com/xml/ns/javaee:abstract-schema-name documentation</h3>
	 * The abstract-schema-name element specifies the name
	 * 	    of the abstract schema type of an entity bean with
	 * 	    cmp-version 2.x. It is used in EJB QL queries.
	 * 	    For example, the abstract-schema-name for an entity
	 * 	    bean whose local interface is
	 * 	    com.acme.commerce.Order might be Order.
	 * </pre>
	 * <pre>
	 * <h3>Type http://java.sun.com/xml/ns/javaee:java-identifierType documentation</h3>
	 * The java-identifierType defines a Java identifier.
	 * 	The users of this type should further verify that
	 * 	the content does not contain Java reserved keywords.
	 * </pre>
	 * @return the value of the abstract-schema-name child.
	 */
	GenericDomValue<String> getAbstractSchemaName();


	/**
	 * Returns the list of cmp-field children.
	 * @return the list of cmp-field children.
	 */
	List<CmpField> getCmpFields();
	/**
	 * Adds new child to the list of cmp-field children.
	 * @return created child
	 */
	CmpField addCmpField();


	/**
	 * Returns the value of the primkey-field child.
	 * <pre>
	 * <h3>Element http://java.sun.com/xml/ns/javaee:primkey-field documentation</h3>
	 * The primkey-field element is used to specify the
	 * 	    name of the primary key field for an entity with
	 * 	    container-managed persistence.
	 * 	    The primkey-field must be one of the fields declared
	 * 	    in the cmp-field element, and the type of the field
	 * 	    must be the same as the primary key type.
	 * 	    The primkey-field element is not used if the primary
	 * 	    key maps to multiple container-managed fields
	 * 	    (i.e. the key is a compound key). In this case, the
	 * 	    fields of the primary key class must be public, and
	 * 	    their names must correspond to the field names of
	 * 	    the entity bean class that comprise the key.
	 * </pre>
	 * <pre>
	 * <h3>Type http://java.sun.com/xml/ns/javaee:string documentation</h3>
	 * This is a special string datatype that is defined by Java EE as
	 * 	a base type for defining collapsed strings. When schemas
	 * 	require trailing/leading space elimination as well as
	 * 	collapsing the existing whitespace, this base type may be
	 * 	used.
	 * </pre>
	 * @return the value of the primkey-field child.
	 */
        @Convert(PrimkeyFieldResolvingConverter.class)
        GenericDomValue<com.intellij.javaee.model.common.ejb.CmpField> getPrimkeyField();


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
	 * Returns the list of query children.
	 * @return the list of query children.
	 */
	List<Query> getQueries();
	/**
	 * Adds new child to the list of query children.
	 * @return created child
	 */
	Query addQuery();


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
