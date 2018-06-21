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
import com.intellij.javaee.model.xml.RunAs;
import com.intellij.util.xml.GenericDomValue;
import com.intellij.util.xml.SubTag;
import javax.annotation.Nonnull;

import java.util.List;

/**
 * http://java.sun.com/xml/ns/javaee:security-identityType interface.
 * <pre>
 * <h3>Type http://java.sun.com/xml/ns/javaee:security-identityType documentation</h3>
 * The security-identityType specifies whether the caller's
 * 	security identity is to be used for the execution of the
 * 	methods of the enterprise bean or whether a specific run-as
 * 	identity is to be used. It contains an optional description
 * 	and a specification of the security identity to be used.
 * </pre>
 */
public interface SecurityIdentity extends JavaeeDomModelElement {

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
	 * Returns the value of the use-caller-identity child.
	 * <pre>
	 * <h3>Element http://java.sun.com/xml/ns/javaee:use-caller-identity documentation</h3>
	 * The use-caller-identity element specifies that
	 * 	      the caller's security identity be used as the
	 * 	      security identity for the execution of the
	 * 	      enterprise bean's methods.
	 * </pre>
	 * <pre>
	 * <h3>Type http://java.sun.com/xml/ns/javaee:emptyType documentation</h3>
	 * This type is used to designate an empty
	 * 	element when used.
	 * </pre>
	 * @return the value of the use-caller-identity child.
	 */
	@SubTag (value = "use-caller-identity", indicator = true)
	@Nonnull
	GenericDomValue<Boolean> getUseCallerIdentity();


	/**
	 * Returns the value of the run-as child.
	 * @return the value of the run-as child.
	 */
	@Nonnull
	RunAs getRunAs();


}
