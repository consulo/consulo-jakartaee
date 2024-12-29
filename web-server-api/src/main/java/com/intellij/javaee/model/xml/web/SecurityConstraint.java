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

import java.util.List;

import jakarta.annotation.Nonnull;

import com.intellij.javaee.model.xml.DisplayName;
import com.intellij.javaee.model.xml.JavaeeDomModelElement;

/**
 * http://java.sun.com/xml/ns/javaee:security-constraintType interface.
 * <pre>
 * <h3>Type http://java.sun.com/xml/ns/javaee:security-constraintType documentation</h3>
 * The security-constraintType is used to associate
 * 	security constraints with one or more web resource
 * 	collections
 * 	Used in: web-app
 * </pre>
 */
public interface SecurityConstraint extends JavaeeDomModelElement {

	/**
	 * Returns the list of display-name children.
	 * @return the list of display-name children.
	 */
	List<DisplayName> getDisplayNames();
	/**
	 * Adds new child to the list of display-name children.
	 * @return created child
	 */
	DisplayName addDisplayName();


	/**
	 * Returns the list of web-resource-collection children.
	 * @return the list of web-resource-collection children.
	 */
	@Nonnull
	List<WebResourceCollection> getWebResourceCollections();
	/**
	 * Adds new child to the list of web-resource-collection children.
	 * @return created child
	 */
	WebResourceCollection addWebResourceCollection();


	/**
	 * Returns the value of the auth-constraint child.
	 * @return the value of the auth-constraint child.
	 */
	AuthConstraint getAuthConstraint();


	/**
	 * Returns the value of the user-data-constraint child.
	 * @return the value of the user-data-constraint child.
	 */
	UserDataConstraint getUserDataConstraint();


}
