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
import com.intellij.javaee.model.xml.web.converters.ServletNameConverter;
import com.intellij.javaee.web.CommonServlet;
import com.intellij.javaee.web.CommonServletMapping;
import consulo.xml.util.xml.*;

import java.util.List;

/**
 * http://java.sun.com/xml/ns/javaee:servlet-mappingType interface.
 * <pre>
 * <h3>Type http://java.sun.com/xml/ns/javaee:servlet-mappingType documentation</h3>
 * The servlet-mappingType defines a mapping between a
 * 	servlet and a url pattern.
 * 	Used in: web-app
 * </pre>
 */
public interface ServletMapping extends CommonServletMapping<CommonServlet>, JavaeeDomModelElement
{

	/**
	 * Returns the value of the servlet-name child.
	 * <pre>
	 * <h3>Type http://java.sun.com/xml/ns/javaee:servlet-nameType documentation</h3>
	 * The servlet-name element contains the canonical name of the
	 * 	servlet. Each servlet name is unique within the web
	 * 	application.
	 * </pre>
	 *
	 * @return the value of the servlet-name child.
	 */
	@Required
	@Scope(RootScopeProvider.class)
	@NameValue(referencable = false, unique = false)
	@Convert(ServletNameConverter.class)
	@Stubbed
	GenericDomValue<Servlet> getServletName();


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
	 *
	 * @return the list of url-pattern children.
	 */
	@Required
	@Stubbed
	List<GenericDomValue<String>> getUrlPatterns();

	/**
	 * Adds new child to the list of url-pattern children.
	 *
	 * @return created child
	 */
	GenericDomValue<String> addUrlPattern();
}
