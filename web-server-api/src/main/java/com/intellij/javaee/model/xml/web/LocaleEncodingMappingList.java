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

package com.intellij.javaee.model.xml.web;

import java.util.List;

import javax.annotation.Nonnull;

import com.intellij.javaee.model.xml.JavaeeDomModelElement;

/**
 * http://java.sun.com/xml/ns/javaee:locale-encoding-mapping-listType interface.
 * <pre>
 * <h3>Type http://java.sun.com/xml/ns/javaee:locale-encoding-mapping-listType documentation</h3>
 * The locale-encoding-mapping-list contains one or more
 * 	locale-encoding-mapping(s).
 * </pre>
 */
public interface LocaleEncodingMappingList extends JavaeeDomModelElement {

	/**
	 * Returns the list of locale-encoding-mapping children.
	 * @return the list of locale-encoding-mapping children.
	 */
	@Nonnull
	List<LocaleEncodingMapping> getLocaleEncodingMappings();
	/**
	 * Adds new child to the list of locale-encoding-mapping children.
	 * @return created child
	 */
	LocaleEncodingMapping addLocaleEncodingMapping();
}
