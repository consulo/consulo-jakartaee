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
import com.intellij.javaee.model.xml.web.converters.MappingExtensionConverter;
import com.intellij.javaee.model.xml.web.converters.MimeTypeConverter;
import consulo.xml.util.xml.Convert;
import consulo.xml.util.xml.GenericDomValue;
import consulo.xml.util.xml.NameValue;

import jakarta.annotation.Nonnull;

/**
 * http://java.sun.com/xml/ns/javaee:mime-mappingType interface.
 * <pre>
 * <h3>Type http://java.sun.com/xml/ns/javaee:mime-mappingType documentation</h3>
 * The mime-mappingType defines a mapping between an extension
 * 	and a mime type.
 * 	Used in: web-app
 * </pre>
 */
public interface MimeMapping extends JavaeeDomModelElement
{

	/**
	 * Returns the value of the extension child.
	 * <pre>
	 * <h3>Type http://java.sun.com/xml/ns/javaee:string documentation</h3>
	 * This is a special string datatype that is defined by Java EE as
	 * 	a base type for defining collapsed strings. When schemas
	 * 	require trailing/leading space elimination as well as
	 * 	collapsing the existing whitespace, this base type may be
	 * 	used.
	 * </pre>
	 *
	 * @return the value of the extension child.
	 */
	@Convert(MappingExtensionConverter.class)
	@NameValue
	@Nonnull
	GenericDomValue<String> getExtension();


	/**
	 * Returns the value of the mime-type child.
	 * <pre>
	 * <h3>Type http://java.sun.com/xml/ns/javaee:mime-typeType documentation</h3>
	 * The mime-typeType is used to indicate a defined mime type.
	 * 	Example:
	 * 	"text/plain"
	 * 	Used in: mime-mapping
	 * </pre>
	 *
	 * @return the value of the mime-type child.
	 */
	@Convert(MimeTypeConverter.class)
	@Nonnull
	GenericDomValue<String> getMimeType();


}
