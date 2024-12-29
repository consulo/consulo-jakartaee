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
 * http://java.sun.com/xml/ns/javaee:taglibType interface.
 * <pre>
 * <h3>Type http://java.sun.com/xml/ns/javaee:taglibType documentation</h3>
 * The taglibType defines the syntax for declaring in
 * 	the deployment descriptor that a tag library is
 * 	available to the application.  This can be done
 * 	to override implicit map entries from TLD files and
 * 	from the container.
 * </pre>
 */
public interface Taglib extends JavaeeDomModelElement {

	/**
	 * Returns the value of the taglib-uri child.
	 * <pre>
	 * <h3>Element http://java.sun.com/xml/ns/javaee:taglib-uri documentation</h3>
	 * A taglib-uri element describes a URI identifying a
	 * 	    tag library used in the web application.  The body
	 * 	    of the taglib-uri element may be either an
	 * 	    absolute URI specification, or a relative URI.
	 * 	    There should be no entries in web.xml with the
	 * 	    same taglib-uri value.
	 * </pre>
	 * <pre>
	 * <h3>Type http://java.sun.com/xml/ns/javaee:string documentation</h3>
	 * This is a special string datatype that is defined by Java EE as
	 * 	a base type for defining collapsed strings. When schemas
	 * 	require trailing/leading space elimination as well as
	 * 	collapsing the existing whitespace, this base type may be
	 * 	used.
	 * </pre>
	 * @return the value of the taglib-uri child.
	 */
	@Nonnull
	GenericDomValue<String> getTaglibUri();


	/**
	 * Returns the value of the taglib-location child.
	 * <pre>
	 * <h3>Element http://java.sun.com/xml/ns/javaee:taglib-location documentation</h3>
	 * the taglib-location element contains the location
	 * 	    (as a resource relative to the root of the web
	 * 	    application) where to find the Tag Library
	 * 	    Description file for the tag library.
	 * </pre>
	 * <pre>
	 * <h3>Type http://java.sun.com/xml/ns/javaee:pathType documentation</h3>
	 * The elements that use this type designate either a relative
	 * 	path or an absolute path starting with a "/".
	 * 	In elements that specify a pathname to a file within the
	 * 	same Deployment File, relative filenames (i.e., those not
	 * 	starting with "/") are considered relative to the root of
	 * 	the Deployment File's namespace.  Absolute filenames (i.e.,
	 * 	those starting with "/") also specify names in the root of
	 * 	the Deployment File's namespace.  In general, relative names
	 * 	are preferred.  The exception is .war files where absolute
	 * 	names are preferred for consistency with the Servlet API.
	 * </pre>
	 * @return the value of the taglib-location child.
	 */
	@Nonnull
	GenericDomValue<String> getTaglibLocation();


}
