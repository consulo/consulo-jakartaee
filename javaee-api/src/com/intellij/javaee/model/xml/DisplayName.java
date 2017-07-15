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
 * http://java.sun.com/xml/ns/javaee:display-nameType interface.
 * <pre>
 * <h3>Type http://java.sun.com/xml/ns/javaee:display-nameType documentation</h3>
 * 	  The display-name type contains a short name that is intended
 * 	  to be displayed by tools. It is used by display-name
 * 	  elements.  The display name need not be unique.
 * 	  Example:
 * 	  ...
 * 	     <display-name xml:lang="en">
 * 	       Employee Self Service
 * 	     </display-name>
 * 	  The value of the xml:lang attribute is "en" (English) by default.
 * 	  
 * </pre>
 */
public interface DisplayName extends JavaeeDomModelElement {

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
