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

package com.intellij.javaee.model.xml;

import consulo.xml.util.xml.GenericDomValue;
import javax.annotation.Nonnull;

import java.util.List;

/**
 * http://java.sun.com/xml/ns/javaee:run-asType interface.
 * <pre>
 * <h3>Type http://java.sun.com/xml/ns/javaee:run-asType documentation</h3>
 * The run-asType specifies the run-as identity to be
 * 	used for the execution of a component. It contains an
 * 	optional description, and the name of a security role.
 * </pre>
 */
public interface RunAs extends JavaeeDomModelElement {

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
	 * Returns the value of the role-name child.
	 * <pre>
	 * <h3>Type http://java.sun.com/xml/ns/javaee:role-nameType documentation</h3>
	 * The role-nameType designates the name of a security role.
	 * 	The name must conform to the lexical rules for a token.
	 * </pre>
	 * @return the value of the role-name child.
	 */
	@Nonnull
	GenericDomValue<SecurityRole> getRoleName();


}
