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

// Generated on Tue Feb 21 16:49:09 MSK 2006
// DTD/Schema  :    http://java.sun.com/xml/ns/javaee

package com.intellij.javaee.model.xml.web;

import com.intellij.javaee.model.xml.JavaeeDomModelElement;
import com.intellij.util.xml.GenericDomValue;

/**
 * http://java.sun.com/xml/ns/javaee:session-configType interface.
 * <pre>
 * <h3>Type http://java.sun.com/xml/ns/javaee:session-configType documentation</h3>
 * The session-configType defines the session parameters
 * 	for this web application.
 * 	Used in: web-app
 * </pre>
 */
public interface SessionConfig extends JavaeeDomModelElement {

	/**
	 * Returns the value of the session-timeout child.
	 * <pre>
	 * <h3>Element http://java.sun.com/xml/ns/javaee:session-timeout documentation</h3>
	 * The session-timeout element defines the default
	 * 	    session timeout interval for all sessions created
	 * 	    in this web application. The specified timeout
	 * 	    must be expressed in a whole number of minutes.
	 * 	    If the timeout is 0 or less, the container ensures
	 * 	    the default behaviour of sessions is never to time
	 * 	    out. If this element is not specified, the container
	 * 	    must set its default timeout period.
	 * </pre>
	 * <pre>
	 * <h3>Type http://java.sun.com/xml/ns/javaee:xsdIntegerType documentation</h3>
	 * This type adds an "id" attribute to xsd:integer.
	 * </pre>
	 * @return the value of the session-timeout child.
	 */
	GenericDomValue<Integer> getSessionTimeout();


}
