/*
 * Copyright 2000-2013 JetBrains s.r.o.
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
import consulo.language.psi.path.PathReference;
import consulo.xml.util.xml.GenericDomValue;

import jakarta.annotation.Nonnull;

/**
 * http://java.sun.com/xml/ns/javaee:form-login-configType interface.
 * <pre>
 * <h3>Type http://java.sun.com/xml/ns/javaee:form-login-configType documentation</h3>
 * The form-login-configType specifies the login and error
 * 	pages that should be used in form based login. If form based
 * 	authentication is not used, these elements are ignored.
 * 	Used in: login-config
 * </pre>
 */
public interface FormLoginConfig extends JavaeeDomModelElement {

	/**
	 * Returns the value of the form-login-page child.
	 * <pre>
	 * <h3>Element http://java.sun.com/xml/ns/javaee:form-login-page documentation</h3>
	 * The form-login-page element defines the location in the web
	 * 	    app where the page that can be used for login can be
	 * 	    found.  The path begins with a leading / and is interpreted
	 * 	    relative to the root of the WAR.
	 * </pre>
	 * <pre>
	 * <h3>Type http://java.sun.com/xml/ns/javaee:war-pathType documentation</h3>
	 * The elements that use this type designate a path starting
	 * 	with a "/" and interpreted relative to the root of a WAR
	 * 	file.
	 * </pre>
	 * @return the value of the form-login-page child.
	 */
	@Nonnull
	GenericDomValue<PathReference> getFormLoginPage();


	/**
	 * Returns the value of the form-error-page child.
	 * <pre>
	 * <h3>Element http://java.sun.com/xml/ns/javaee:form-error-page documentation</h3>
	 * The form-error-page element defines the location in
	 * 	    the web app where the error page that is displayed
	 * 	    when login is not successful can be found.
	 * 	    The path begins with a leading / and is interpreted
	 * 	    relative to the root of the WAR.
	 * </pre>
	 * <pre>
	 * <h3>Type http://java.sun.com/xml/ns/javaee:war-pathType documentation</h3>
	 * The elements that use this type designate a path starting
	 * 	with a "/" and interpreted relative to the root of a WAR
	 * 	file.
	 * </pre>
	 * @return the value of the form-error-page child.
	 */
	@Nonnull
	GenericDomValue<PathReference> getFormErrorPage();


}
