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

import consulo.xml.util.xml.GenericDomValue;
import jakarta.annotation.Nonnull;

/**
 * http://java.sun.com/xml/ns/javaee:propertyType interface.
 * <pre>
 * <h3>Type http://java.sun.com/xml/ns/javaee:propertyType documentation</h3>
 * Specifies a name/value pair.
 * </pre>
 */
public interface Property extends JavaeeDomModelElement {

	/**
	 * Returns the value of the name child.
	 * <pre>
	 * <h3>Type http://java.sun.com/xml/ns/javaee:xsdStringType documentation</h3>
	 * This type adds an "id" attribute to xsd:string.
	 * </pre>
	 * @return the value of the name child.
	 */
	@Nonnull
	GenericDomValue<String> getName();


	/**
	 * Returns the value of the value child.
	 * <pre>
	 * <h3>Type http://java.sun.com/xml/ns/javaee:xsdStringType documentation</h3>
	 * This type adds an "id" attribute to xsd:string.
	 * </pre>
	 * @return the value of the value child.
	 */
	@Nonnull
	GenericDomValue<String> getValue();


}
