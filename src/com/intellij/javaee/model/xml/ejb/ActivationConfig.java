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

import com.intellij.javaee.model.xml.Description;
import com.intellij.javaee.model.xml.JavaeeDomModelElement;
import javax.annotation.Nonnull;

import java.util.List;

/**
 * http://java.sun.com/xml/ns/javaee:activation-configType interface.
 * <pre>
 * <h3>Type http://java.sun.com/xml/ns/javaee:activation-configType documentation</h3>
 * The activation-configType defines information about the
 * 	expected configuration properties of the message-driven bean
 * 	in its operational environment. This may include information
 * 	about message acknowledgement, message selector, expected
 * 	destination type, etc.
 * 	The configuration information is expressed in terms of
 * 	name/value configuration properties.
 * 	The properties that are recognized for a particular
 * 	message-driven bean are determined by the messaging type.
 * </pre>
 */
public interface ActivationConfig extends JavaeeDomModelElement {

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
	 * Returns the list of activation-config-property children.
	 * @return the list of activation-config-property children.
	 */
	@Nonnull
	List<ActivationConfigProperty> getActivationConfigProperties();
	/**
	 * Adds new child to the list of activation-config-property children.
	 * @return created child
	 */
	ActivationConfigProperty addActivationConfigProperty();


}
