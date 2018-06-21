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

package com.intellij.javaee.model.xml;

import com.intellij.javaee.model.enums.MessageDestinationUsage;
import com.intellij.psi.PsiClass;
import com.intellij.util.xml.GenericDomValue;
import com.intellij.util.xml.NameValue;
import javax.annotation.Nonnull;

import java.util.List;

/**
 * http://java.sun.com/xml/ns/javaee:message-destination-refType interface.
 * <pre>
 * <h3>Type http://java.sun.com/xml/ns/javaee:message-destination-refType documentation</h3>
 * 	  The message-destination-ref element contains a declaration
 * 	  of Deployment Component's reference to a message destination
 * 	  associated with a resource in Deployment Component's
 * 	  environment. It consists of:
 * 		  - an optional description
 * 		  - the message destination reference name
 * 		  - an optional message destination type
 * 		  - an optional specification as to whether
 * 		    the destination is used for
 * 		    consuming or producing messages, or both.
 * 		    if not specified, "both" is assumed.
 * 		  - an optional link to the message destination
 * 		  - optional injection targets
 * 	  The message destination type must be supplied unless an
 * 	  injection target is specified, in which case the type
 * 	  of the target is used.  If both are specified, the type
 * 	  must be assignment compatible with the type of the injection
 * 	  target.
 * 	  Examples:
 * 	  <message-destination-ref>
 * 		  <message-destination-ref-name>jms/StockQueue
 * 		  </message-destination-ref-name>
 * 		  <message-destination-type>javax.jms.Queue
 * 		  </message-destination-type>
 * 		  <message-destination-usage>Consumes
 * 		  </message-destination-usage>
 * 		  <message-destination-link>CorporateStocks
 * 		  </message-destination-link>
 * 	  </message-destination-ref>
 * 	  
 * </pre>
 */
public interface MessageDestinationRef extends JavaeeDomModelElement, ResourceGroup, ResourceReference {

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
	 * Returns the value of the message-destination-ref-name child.
	 * <pre>
	 * <h3>Element http://java.sun.com/xml/ns/javaee:message-destination-ref-name documentation</h3>
	 * The message-destination-ref-name element specifies
	 * 	    the name of a message destination reference; its
	 * 	    value is the environment entry name used in
	 * 	    Deployment Component code.  The name is a JNDI name
	 * 	    relative to the java:comp/env context and must be
	 * 	    unique within an ejb-jar (for enterprise beans) or a
	 * 	    Deployment File (for others).
	 * </pre>
	 * <pre>
	 * <h3>Type http://java.sun.com/xml/ns/javaee:jndi-nameType documentation</h3>
	 * The jndi-nameType type designates a JNDI name in the
	 * 	Deployment Component's environment and is relative to the
	 * 	java:comp/env context.  A JNDI name must be unique within the
	 * 	Deployment Component.
	 * </pre>
	 * @return the value of the message-destination-ref-name child.
	 */
	@Nonnull
        @NameValue
        GenericDomValue<String> getMessageDestinationRefName();


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
	 * Returns the value of the message-destination-usage child.
	 * @return the value of the message-destination-usage child.
	 */
	GenericDomValue<MessageDestinationUsage> getMessageDestinationUsage();


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
