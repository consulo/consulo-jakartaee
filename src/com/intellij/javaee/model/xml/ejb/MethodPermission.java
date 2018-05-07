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

package com.intellij.javaee.model.xml.ejb;

import com.intellij.javaee.model.xml.Description;
import com.intellij.javaee.model.xml.JavaeeDomModelElement;
import com.intellij.javaee.model.xml.SecurityRole;
import com.intellij.util.xml.GenericDomValue;
import com.intellij.util.xml.SubTag;
import javax.annotation.Nonnull;

import java.util.List;

/**
 * http://java.sun.com/xml/ns/javaee:method-permissionType interface.
 * <pre>
 * <h3>Type http://java.sun.com/xml/ns/javaee:method-permissionType documentation</h3>
 * The method-permissionType specifies that one or more
 * 	security roles are allowed to invoke one or more enterprise
 * 	bean methods. The method-permissionType consists of an
 * 	optional description, a list of security role names or an
 * 	indicator to state that the method is unchecked for
 * 	authorization, and a list of method elements.
 * 	The security roles used in the method-permissionType
 * 	must be defined in the security-role elements of the
 * 	deployment descriptor, and the methods must be methods
 * 	defined in the enterprise bean's business, home, component
 *         and/or web service endpoint interfaces.
 * </pre>
 */
public interface MethodPermission extends JavaeeDomModelElement, MethodContainer {

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
	 * Returns the list of method children.
	 * @return the list of method children.
	 */
	@Nonnull
	List<Method> getMethods();
	/**
	 * Adds new child to the list of method children.
	 * @return created child
	 */
	Method addMethod();


	/**
	 * Returns the list of role-name children.
	 * <pre>
	 * <h3>Type http://java.sun.com/xml/ns/javaee:role-nameType documentation</h3>
	 * The role-nameType designates the name of a security role.
	 * 	The name must conform to the lexical rules for a token.
	 * </pre>
	 * @return the list of role-name children.
	 */
	@Nonnull
	List<GenericDomValue<SecurityRole>> getRoleNames();
	/**
	 * Adds new child to the list of role-name children.
	 * @return created child
	 */
	GenericDomValue<SecurityRole> addRoleName();


	/**
	 * Returns the value of the unchecked child.
	 * <pre>
	 * <h3>Element http://java.sun.com/xml/ns/javaee:unchecked documentation</h3>
	 * The unchecked element specifies that a method is
	 * 	      not checked for authorization by the container
	 * 	      prior to invocation of the method.
	 * </pre>
	 * <pre>
	 * <h3>Type http://java.sun.com/xml/ns/javaee:emptyType documentation</h3>
	 * This type is used to designate an empty
	 * 	element when used.
	 * </pre>
	 * @return the value of the unchecked child.
	 */
	@SubTag (value = "unchecked", indicator = true)
	@Nonnull
	GenericDomValue<Boolean> getUnchecked();


}
