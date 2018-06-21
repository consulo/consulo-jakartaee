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

import com.intellij.javaee.model.xml.Description;
import com.intellij.javaee.model.xml.JavaeeDomModelElement;
import com.intellij.javaee.model.xml.SecurityRole;
import com.intellij.util.xml.GenericDomValue;

/**
 * http://java.sun.com/xml/ns/javaee:auth-constraintType interface.
 * <pre>
 * <h3>Type http://java.sun.com/xml/ns/javaee:auth-constraintType documentation</h3>
 * The auth-constraintType indicates the user roles that
 * 	should be permitted access to this resource
 * 	collection. The role-name used here must either correspond
 * 	to the role-name of one of the security-role elements
 * 	defined for this web application, or be the specially
 * 	reserved role-name "*" that is a compact syntax for
 * 	indicating all roles in the web application. If both "*"
 * 	and rolenames appear, the container interprets this as all
 * 	roles.  If no roles are defined, no user is allowed access
 * 	to the portion of the web application described by the
 * 	containing security-constraint.  The container matches
 * 	role names case sensitively when determining access.
 * </pre>
 */
public interface AuthConstraint extends JavaeeDomModelElement {

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
	 * Returns the list of role-name children.
	 * <pre>
	 * <h3>Type http://java.sun.com/xml/ns/javaee:role-nameType documentation</h3>
	 * The role-nameType designates the name of a security role.
	 * 	The name must conform to the lexical rules for a token.
	 * </pre>
	 * @return the list of role-name children.
	 */
	List<GenericDomValue<SecurityRole>> getRoleNames();
	/**
	 * Adds new child to the list of role-name children.
	 * @return created child
	 */
	GenericDomValue<SecurityRole> addRoleName();


}
