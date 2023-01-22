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

/**
 * http://java.sun.com/xml/ns/javaee:login-configType interface.
 * <pre>
 * <h3>Type http://java.sun.com/xml/ns/javaee:login-configType documentation</h3>
 * The login-configType is used to configure the authentication
 * 	method that should be used, the realm name that should be
 * 	used for this application, and the attributes that are
 * 	needed by the form login mechanism.
 * 	Used in: web-app
 * </pre>
 */
public interface LoginConfig extends JavaeeDomModelElement {

	/**
	 * Returns the value of the auth-method child.
	 * <pre>
	 * <h3>Type http://java.sun.com/xml/ns/javaee:auth-methodType documentation</h3>
	 * The auth-methodType is used to configure the authentication
	 * 	mechanism for the web application. As a prerequisite to
	 * 	gaining access to any web resources which are protected by
	 * 	an authorization constraint, a user must have authenticated
	 * 	using the configured mechanism. Legal values are "BASIC",
	 * 	"DIGEST", "FORM", "CLIENT-CERT", or a vendor-specific
	 * 	authentication scheme.
	 * 	Used in: login-config
	 * </pre>
	 * @return the value of the auth-method child.
	 */
	GenericDomValue<String> getAuthMethod();


	/**
	 * Returns the value of the realm-name child.
	 * <pre>
	 * <h3>Element http://java.sun.com/xml/ns/javaee:realm-name documentation</h3>
	 * The realm name element specifies the realm name to
	 * 	    use in HTTP Basic authorization.
	 * </pre>
	 * <pre>
	 * <h3>Type http://java.sun.com/xml/ns/javaee:string documentation</h3>
	 * This is a special string datatype that is defined by Java EE as
	 * 	a base type for defining collapsed strings. When schemas
	 * 	require trailing/leading space elimination as well as
	 * 	collapsing the existing whitespace, this base type may be
	 * 	used.
	 * </pre>
	 * @return the value of the realm-name child.
	 */
	GenericDomValue<String> getRealmName();


	/**
	 * Returns the value of the form-login-config child.
	 * @return the value of the form-login-config child.
	 */
	FormLoginConfig getFormLoginConfig();


}
