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

import com.intellij.javaee.model.enums.Dispatcher;
import com.intellij.javaee.model.xml.JavaeeDomModelElement;
import com.intellij.javaee.model.xml.web.converters.ServletNameConverter;
import com.intellij.util.xml.*;

import java.util.List;

/**
 * http://java.sun.com/xml/ns/javaee:filter-mappingType interface.
 * <pre>
 * <h3>Type http://java.sun.com/xml/ns/javaee:filter-mappingType documentation</h3>
 * Declaration of the filter mappings in this web
 * 	application is done by using filter-mappingType.
 * 	The container uses the filter-mapping
 * 	declarations to decide which filters to apply to a request,
 * 	and in what order. The container matches the request URI to
 * 	a Servlet in the normal way. To determine which filters to
 * 	apply it matches filter-mapping declarations either on
 * 	servlet-name, or on url-pattern for each filter-mapping
 * 	element, depending on which style is used. The order in
 * 	which filters are invoked is the order in which
 * 	filter-mapping declarations that match a request URI for a
 * 	servlet appear in the list of filter-mapping elements.The
 * 	filter-name value must be the value of the filter-name
 * 	sub-elements of one of the filter declarations in the
 * 	deployment descriptor.
 * </pre>
 */
public interface FilterMapping extends JavaeeDomModelElement {

	/**
	 * Returns the value of the filter-name child.
	 * <pre>
	 * <h3>Type http://java.sun.com/xml/ns/javaee:filter-nameType documentation</h3>
	 * The logical name of the filter is declare
	 * 	by using filter-nameType. This name is used to map the
	 * 	filter.  Each filter name is unique within the web
	 * 	application.
	 * 	Used in: filter, filter-mapping
	 * </pre>
	 * @return the value of the filter-name child.
	 */
        @Scope(RootScopeProvider.class)
        @Required
        GenericDomValue<Filter> getFilterName();


	/**
	 * Returns the list of dispatcher children.
	 * @return the list of dispatcher children.
	 */
	List<GenericDomValue<Dispatcher>> getDispatchers();
	/**
	 * Adds new child to the list of dispatcher children.
	 * @return created child
	 */
	GenericDomValue<Dispatcher> addDispatcher();


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
        @Required(value=false, nonEmpty=true)
        List<GenericDomValue<String>> getUrlPatterns();
	/**
	 * Adds new child to the list of url-pattern children.
	 * @return created child
	 */
	GenericDomValue<String> addUrlPattern();


	/**
	 * Returns the list of servlet-name children.
	 * <pre>
	 * <h3>Type http://java.sun.com/xml/ns/javaee:servlet-nameType documentation</h3>
	 * The servlet-name element contains the canonical name of the
	 * 	servlet. Each servlet name is unique within the web
	 * 	application.
	 * </pre>
	 * @return the list of servlet-name children.
	 */
        @Convert(ServletNameConverter.class)
        List<GenericDomValue<Servlet>> getServletNames();
	/**
	 * Adds new child to the list of servlet-name children.
	 * @return created child
	 */
	GenericDomValue<Servlet> addServletName();


}
