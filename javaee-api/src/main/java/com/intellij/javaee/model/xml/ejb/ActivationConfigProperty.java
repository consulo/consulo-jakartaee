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

import javax.annotation.Nonnull;

import com.intellij.javaee.model.xml.JavaeeDomModelElement;
import consulo.xml.util.xml.GenericDomValue;

/**
 * http://java.sun.com/xml/ns/javaee:activation-config-propertyType interface.
 * <pre>
 * <h3>Type http://java.sun.com/xml/ns/javaee:activation-config-propertyType documentation</h3>
 * The activation-config-propertyType contains a name/value
 * 	configuration property pair for a message-driven bean.
 * 	The properties that are recognized for a particular
 * 	message-driven bean are determined by the messaging type.
 * </pre>
 */
public interface ActivationConfigProperty extends JavaeeDomModelElement, com.intellij.javaee.model.common.ejb.ActivationConfigProperty {

	/**
	 * Returns the value of the activation-config-property-name child.
	 * <pre>
	 * <h3>Element http://java.sun.com/xml/ns/javaee:activation-config-property-name documentation</h3>
	 * The activation-config-property-name element contains
	 * 	    the name for an activation configuration property of
	 * 	    a message-driven bean.
	 * 	    For JMS message-driven beans, the following property
	 * 	    names are recognized: acknowledgeMode,
	 * 	    messageSelector, destinationType, subscriptionDurability
	 * </pre>
	 * <pre>
	 * <h3>Type http://java.sun.com/xml/ns/javaee:xsdStringType documentation</h3>
	 * This type adds an "id" attribute to xsd:string.
	 * </pre>
	 * @return the value of the activation-config-property-name child.
	 */
	@Nonnull
	GenericDomValue<String> getActivationConfigPropertyName();


	/**
	 * Returns the value of the activation-config-property-value child.
	 * <pre>
	 * <h3>Element http://java.sun.com/xml/ns/javaee:activation-config-property-value documentation</h3>
	 * The activation-config-property-value element
	 * 	    contains the value for an activation configuration
	 * 	    property of a message-driven bean.
	 * </pre>
	 * <pre>
	 * <h3>Type http://java.sun.com/xml/ns/javaee:xsdStringType documentation</h3>
	 * This type adds an "id" attribute to xsd:string.
	 * </pre>
	 * @return the value of the activation-config-property-value child.
	 */
	@Nonnull
	GenericDomValue<String> getActivationConfigPropertyValue();


}
