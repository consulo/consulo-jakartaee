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

import com.intellij.javaee.model.enums.TransAttribute;
import com.intellij.javaee.model.xml.Description;
import com.intellij.javaee.model.xml.JavaeeDomModelElement;
import consulo.xml.util.xml.GenericDomValue;
import jakarta.annotation.Nonnull;

import java.util.List;

/**
 * http://java.sun.com/xml/ns/javaee:container-transactionType interface.
 * <pre>
 * <h3>Type http://java.sun.com/xml/ns/javaee:container-transactionType documentation</h3>
 * The container-transactionType specifies how the container
 * 	must manage transaction scopes for the enterprise bean's
 * 	method invocations. It defines an optional description, a
 * 	list of method elements, and a transaction attribute. The
 * 	transaction attribute is to be applied to all the specified
 * 	methods.
 * </pre>
 */
public interface ContainerTransaction extends JavaeeDomModelElement, MethodContainer {

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
	 * Returns the value of the trans-attribute child.
	 * @return the value of the trans-attribute child.
	 */
	@Nonnull
	GenericDomValue<TransAttribute> getTransAttribute();


}
