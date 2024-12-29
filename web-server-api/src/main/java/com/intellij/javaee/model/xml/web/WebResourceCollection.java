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

import com.intellij.javaee.model.xml.Description;
import com.intellij.javaee.model.xml.JavaeeDomModelElement;
import consulo.xml.util.xml.GenericDomValue;

import jakarta.annotation.Nonnull;
import java.util.List;

/**
 * http://java.sun.com/xml/ns/javaee:web-resource-collectionType interface.
 * <pre>
 * <h3>Type http://java.sun.com/xml/ns/javaee:web-resource-collectionType documentation</h3>
 * The web-resource-collectionType is used to identify a subset
 * 	of the resources and HTTP methods on those resources within
 * 	a web application to which a security constraint applies. If
 * 	no HTTP methods are specified, then the security constraint
 * 	applies to all HTTP methods.
 * 	Used in: security-constraint
 * </pre>
 */
public interface WebResourceCollection extends JavaeeDomModelElement {

	/**
	 * Returns the value of the web-resource-name child.
	 * <pre>
	 * <h3>Element http://java.sun.com/xml/ns/javaee:web-resource-name documentation</h3>
	 * The web-resource-name contains the name of this web
	 * 	    resource collection.
	 * </pre>
	 * <pre>
	 * <h3>Type http://java.sun.com/xml/ns/javaee:string documentation</h3>
	 * This is a special string datatype that is defined by Java EE as
	 * 	a base type for defining collapsed strings. When schemas
	 * 	require trailing/leading space elimination as well as
	 * 	collapsing the existing whitespace, this base type may be
	 * 	used.
	 * </pre>
	 * @return the value of the web-resource-name child.
	 */
	@Nonnull
	GenericDomValue<String> getWebResourceName();


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
	 * Returns the list of url-pattern children.
	 * <pre>
	 * <h3>Type http://java.sun.com/xml/ns/javaee:url-patternType documentation</h3>
	 * The url-patternType contains the url pattern of the mapping.
	 * 	It must follow the rules specified in Section 11.2 of the
	 * 	Servlet API Specification. This pattern is assumed to be in
	 * 	URL-decoded form and must not contain CR(#xD) or LF(#xA).
	 * 	If it contains those characters, the container must inform
	 * 	the developer with a descriptive error message.
	 * 	The container must preserve all characters including whitespaces.
	 * </pre>
	 * @return the list of url-pattern children.
	 */
	@Nonnull
	List<GenericDomValue<String>> getUrlPatterns();
	/**
	 * Adds new child to the list of url-pattern children.
	 * @return created child
	 */
	GenericDomValue<String> addUrlPattern();


	/**
	 * Returns the list of http-method children.
	 * @return the list of http-method children.
	 */
	List<GenericDomValue<String>> getHttpMethods();
	/**
	 * Adds new child to the list of http-method children.
	 * @return created child
	 */
	GenericDomValue<String> addHttpMethod();


}
