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

import javax.annotation.Nonnull;

import com.intellij.javaee.model.xml.JavaeeDomModelElement;
import com.intellij.javaee.model.xml.web.converters.HttpErrorCodeConverter;
import com.intellij.openapi.paths.PathReference;
import com.intellij.psi.CommonClassNames;
import com.intellij.psi.PsiClass;
import com.intellij.util.xml.Convert;
import com.intellij.util.xml.ExtendClass;
import com.intellij.util.xml.GenericDomValue;

/**
 * http://java.sun.com/xml/ns/javaee:error-pageType interface.
 * <pre>
 * <h3>Type http://java.sun.com/xml/ns/javaee:error-pageType documentation</h3>
 * The error-pageType contains a mapping between an error code
 * 	or exception type to the path of a resource in the web
 * 	application.
 * 	Used in: web-app
 * </pre>
 */
public interface ErrorPage extends JavaeeDomModelElement {

	/**
	 * Returns the value of the location child.
	 * <pre>
	 * <h3>Element http://java.sun.com/xml/ns/javaee:location documentation</h3>
	 * The location element contains the location of the
	 * 	    resource in the web application relative to the root of
	 * 	    the web application. The value of the location must have
	 * 	    a leading `/'.
	 * </pre>
	 * <pre>
	 * <h3>Type http://java.sun.com/xml/ns/javaee:war-pathType documentation</h3>
	 * The elements that use this type designate a path starting
	 * 	with a "/" and interpreted relative to the root of a WAR
	 * 	file.
	 * </pre>
	 * @return the value of the location child.
	 */
	@Nonnull
	GenericDomValue<PathReference> getLocation();


	/**
	 * Returns the value of the error-code child.
	 * <pre>
	 * <h3>Type http://java.sun.com/xml/ns/javaee:error-codeType documentation</h3>
	 * The error-code contains an HTTP error code, ex: 404
	 * 	Used in: error-page
	 * </pre>
	 * @return the value of the error-code child.
	 */
	@Nonnull
        @Convert(HttpErrorCodeConverter.class)
	GenericDomValue<HttpErrorCode> getErrorCode();


	/**
	 * Returns the value of the exception-type child.
	 * <pre>
	 * <h3>Element http://java.sun.com/xml/ns/javaee:exception-type documentation</h3>
	 * The exception-type contains a fully qualified class
	 * 	      name of a Java exception type.
	 * </pre>
	 * <pre>
	 * <h3>Type http://java.sun.com/xml/ns/javaee:fully-qualified-classType documentation</h3>
	 * The elements that use this type designate the name of a
	 * 	Java class or interface.  The name is in the form of a
	 * 	"binary name", as defined in the JLS.  This is the form
	 * 	of name used in Class.forName().  Tools that need the
	 * 	canonical name (the name used in source code) will need
	 * 	to convert this binary name to the canonical name.
	 * </pre>
	 * @return the value of the exception-type child.
	 */
	@Nonnull
        @ExtendClass(value = CommonClassNames.JAVA_LANG_EXCEPTION, instantiatable = false)
        GenericDomValue<PsiClass> getExceptionType();


}
