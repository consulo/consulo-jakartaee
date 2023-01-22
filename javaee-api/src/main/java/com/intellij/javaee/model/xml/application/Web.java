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

package com.intellij.javaee.model.xml.application;

import com.intellij.javaee.model.xml.JavaeeDomModelElement;
import consulo.xml.util.xml.GenericDomValue;
import consulo.xml.util.xml.Required;

/**
 * http://java.sun.com/xml/ns/javaee:webType interface.
 * <pre>
 * <h3>Type http://java.sun.com/xml/ns/javaee:webType documentation</h3>
 * The webType defines the web-uri and context-root of
 * 	a web application module.
 * </pre>
 */
public interface Web extends JavaeeDomModelElement {

	/**
	 * Returns the value of the web-uri child.
	 * <pre>
	 * <h3>Element http://java.sun.com/xml/ns/javaee:web-uri documentation</h3>
	 * The web-uri element specifies the URI of a web
	 * 	    application file, relative to the top level of the
	 * 	    application package.
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
	 * @return the value of the web-uri child.
	 */
	@Required
	GenericDomValue<String> getWebUri();


	/**
	 * Returns the value of the context-root child.
	 * <pre>
	 * <h3>Element http://java.sun.com/xml/ns/javaee:context-root documentation</h3>
	 * The context-root element specifies the context root
	 * 	    of a web application.
	 * </pre>
	 * <pre>
	 * <h3>Type http://java.sun.com/xml/ns/javaee:string documentation</h3>
	 * This is a special string datatype that is defined by Java EE as
	 * 	a base type for defining collapsed strings. When schemas
	 * 	require trailing/leading space elimination as well as
	 * 	collapsing the existing whitespace, this base type may be
	 * 	used.
	 * </pre>
	 * @return the value of the context-root child.
	 */
	@Required
	GenericDomValue<String> getContextRoot();


}
