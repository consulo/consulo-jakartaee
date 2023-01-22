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

// Generated on Mon Mar 20 14:16:35 MSK 2006
// DTD/Schema  :    http://java.sun.com/xml/ns/javaee

package com.intellij.javaee.model.xml;

import com.intellij.javaee.model.enums.PersistenceContextType;
import consulo.xml.util.xml.GenericDomValue;
import javax.annotation.Nonnull;

import java.util.List;

/**
 * http://java.sun.com/xml/ns/javaee:persistence-context-refType interface.
 * <pre>
 * <h3>Type http://java.sun.com/xml/ns/javaee:persistence-context-refType documentation</h3>
 * 	  The persistence-context-ref element contains a declaration
 * 	  of Deployment Component's reference to a persistence context
 * 	  associated within a Deployment Component's
 * 	  environment. It consists of:
 * 		  - an optional description
 * 		  - the persistence context reference name
 * 		  - an optional persistence unit name.  If not specified,
 *                     the default persistence unit is assumed.
 * 		  - an optional specification as to whether
 * 		    the persistence context is Transactional or
 * 		    Extended.  If not specificed, Transactional is assumed.
 *                   - an optional list of persistence properties
 * 		  - optional injection targets
 * 	  Examples:
 *             <persistence-context-ref>
 *               <persistence-context-ref-name>myPersistenceContext
 *               </persistence-context-ref-name>
 *             </persistence-context-ref>
 *             <persistence-context-ref>
 *               <persistence-context-ref-name>myPersistenceContext
 *                 </persistence-context-ref-name>
 *               <persistence-unit-name>PersistenceUnit1
 *                 </persistence-unit-name>
 *               <persistence-context-type>Extended</persistence-context-type>
 *             </persistence-context-ref>
 * 	  
 * </pre>
 */
public interface PersistenceContextRef extends JavaeeDomModelElement, ResourceGroup {

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
	 * Returns the value of the persistence-context-ref-name child.
	 * <pre>
	 * <h3>Element http://java.sun.com/xml/ns/javaee:persistence-context-ref-name documentation</h3>
	 * The persistence-context-ref-name element specifies
	 * 	    the name of a persistence context reference; its
	 * 	    value is the environment entry name used in
	 * 	    Deployment Component code.  The name is a JNDI name
	 * 	    relative to the java:comp/env context.
	 * </pre>
	 * <pre>
	 * <h3>Type http://java.sun.com/xml/ns/javaee:jndi-nameType documentation</h3>
	 * The jndi-nameType type designates a JNDI name in the
	 * 	Deployment Component's environment and is relative to the
	 * 	java:comp/env context.  A JNDI name must be unique within the
	 * 	Deployment Component.
	 * </pre>
	 * @return the value of the persistence-context-ref-name child.
	 */
	@Nonnull
	GenericDomValue<String> getPersistenceContextRefName();


	/**
	 * Returns the value of the persistence-unit-name child.
	 * <pre>
	 * <h3>Element http://java.sun.com/xml/ns/javaee:persistence-unit-name documentation</h3>
	 * The Application Assembler(or BeanProvider) may use the
	 *             following syntax to avoid the need to rename persistence
	 *             units to have unique names within a Java EE application.
	 *             The Application Assembler specifies the pathname of the
	 *             root of the persistence.xml file for the referenced
	 *             persistence unit and appends the name of the persistence
	 *             unit separated from the pathname by #. The pathname is
	 *             relative to the referencing application component jar file.
	 *             In this manner, multiple persistence units with the same
	 *             persistence unit name may be uniquely identified when the
	 *             Application Assembler cannot change persistence unit names.
	 * </pre>
	 * <pre>
	 * <h3>Type http://java.sun.com/xml/ns/javaee:string documentation</h3>
	 * This is a special string datatype that is defined by Java EE as
	 * 	a base type for defining collapsed strings. When schemas
	 * 	require trailing/leading space elimination as well as
	 * 	collapsing the existing whitespace, this base type may be
	 * 	used.
	 * </pre>
	 * @return the value of the persistence-unit-name child.
	 */
	GenericDomValue<String> getPersistenceUnitName();


	/**
	 * Returns the value of the persistence-context-type child.
	 * @return the value of the persistence-context-type child.
	 */
	GenericDomValue<PersistenceContextType> getPersistenceContextType();


	/**
	 * Returns the list of persistence-property children.
	 * <pre>
	 * <h3>Element http://java.sun.com/xml/ns/javaee:persistence-property documentation</h3>
	 * Used to specify properties for the container or persistence
	 *             provider.  Vendor-specific properties may be included in
	 *             the set of properties.  Properties that are not recognized
	 *             by a vendor must be ignored.  Entries that make use of the
	 *             namespace javax.persistence and its subnamespaces must not
	 *             be used for vendor-specific properties.  The namespace
	 *             javax.persistence is reserved for use by the specification.
	 * </pre>
	 * @return the list of persistence-property children.
	 */
	List<Property> getPersistenceProperties();
	/**
	 * Adds new child to the list of persistence-property children.
	 * @return created child
	 */
	Property addPersistenceProperty();


	/**
	 * Returns the value of the mapped-name child.
	 * <pre>
	 * <h3>Element http://java.sun.com/xml/ns/javaee:mapped-name documentation</h3>
	 * 	      A product specific name that this resource should be
	 * 	      mapped to.  The name of this resource, as defined by the
	 * 	      resource's name element or defaulted, is a name that is
	 * 	      local to the application component using the resource.
	 * 	      (It's a name in the JNDI java:comp/env namespace.)  Many
	 * 	      application servers provide a way to map these local
	 * 	      names to names of resources known to the application
	 * 	      server.  This mapped name is often a global JNDI name,
	 * 	      but may be a name of any form.
	 * 	      Application servers are not required to support any
	 * 	      particular form or type of mapped name, nor the ability
	 * 	      to use mapped names.  The mapped name is
	 * 	      product-dependent and often installation-dependent.  No
	 * 	      use of a mapped name is portable.
	 * 	      
	 * </pre>
	 * <pre>
	 * <h3>Type http://java.sun.com/xml/ns/javaee:xsdStringType documentation</h3>
	 * This type adds an "id" attribute to xsd:string.
	 * </pre>
	 * @return the value of the mapped-name child.
	 */
	GenericDomValue<String> getMappedName();


	/**
	 * Returns the list of injection-target children.
	 * @return the list of injection-target children.
	 */
	List<InjectionTarget> getInjectionTargets();
	/**
	 * Adds new child to the list of injection-target children.
	 * @return created child
	 */
	InjectionTarget addInjectionTarget();


}
