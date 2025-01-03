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

import com.intellij.javaee.model.xml.JavaeeDomModelElement;
import consulo.xml.util.xml.GenericDomValue;

import jakarta.annotation.Nonnull;

/**
 * http://java.sun.com/xml/ns/javaee:locale-encoding-mappingType interface.
 * <pre>
 * <h3>Type http://java.sun.com/xml/ns/javaee:locale-encoding-mappingType documentation</h3>
 * The locale-encoding-mapping contains locale name and
 * 	encoding name. The locale name must be either "Language-code",
 * 	such as "ja", defined by ISO-639 or "Language-code_Country-code",
 * 	such as "ja_JP".  "Country code" is defined by ISO-3166.
 * </pre>
 */
public interface LocaleEncodingMapping extends JavaeeDomModelElement {

	/**
	 * Returns the value of the locale child.
	 * @return the value of the locale child.
	 */
	@Nonnull
	GenericDomValue<String> getLocale();


	/**
	 * Returns the value of the encoding child.
	 * @return the value of the encoding child.
	 */
	@Nonnull
	GenericDomValue<String> getEncoding();


}
