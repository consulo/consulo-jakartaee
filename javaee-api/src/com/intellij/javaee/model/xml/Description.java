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

// Generated on Tue Feb 14 17:35:30 MSK 2006
// DTD/Schema  :    http://java.sun.com/xml/ns/javaee

package com.intellij.javaee.model.xml;

import org.jetbrains.annotations.NotNull;
import com.intellij.util.xml.GenericAttributeValue;

/**
 * http://java.sun.com/xml/ns/javaee:descriptionType interface.
 * <pre>
 * <h3>Type http://java.sun.com/xml/ns/javaee:descriptionType documentation</h3>
 * The description type is used by a description element to
 * 	provide text describing the parent element.  The elements
 * 	that use this type should include any information that the
 * 	Deployment Component's Deployment File file producer wants
 * 	to provide to the consumer of the Deployment Component's
 * 	Deployment File (i.e., to the Deployer). Typically, the
 * 	tools used by such a Deployment File consumer will display
 * 	the description when processing the parent element that
 * 	contains the description.
 * 	The lang attribute defines the language that the
 * 	description is provided in. The default value is "en" (English).
 * </pre>
 */
public interface Description extends JavaeeDomModelElement {

	/**
	 * Returns the value of the simple content.
	 * @return the value of the simple content.
	 */
	@NotNull
	String getValue();
	/**
	 * Sets the value of the simple content.
	 * @param value the new value to set
	 */
	void setValue(@NotNull String value);


	/**
	 * Returns the value of the lang child.
	 * @return the value of the lang child.
	 */
	GenericAttributeValue<String> getLang();


}
