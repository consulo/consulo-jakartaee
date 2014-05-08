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

import com.intellij.javaee.model.enums.TransportGuarantee;
import com.intellij.javaee.model.xml.Description;
import com.intellij.javaee.model.xml.JavaeeDomModelElement;
import com.intellij.util.xml.GenericDomValue;
import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * http://java.sun.com/xml/ns/javaee:user-data-constraintType interface.
 * <pre>
 * <h3>Type http://java.sun.com/xml/ns/javaee:user-data-constraintType documentation</h3>
 * The user-data-constraintType is used to indicate how
 * 	data communicated between the client and container should be
 * 	protected.
 * 	Used in: security-constraint
 * </pre>
 */
public interface UserDataConstraint extends JavaeeDomModelElement {

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
	 * Returns the value of the transport-guarantee child.
	 * @return the value of the transport-guarantee child.
	 */
	@NotNull
	GenericDomValue<TransportGuarantee> getTransportGuarantee();


}
