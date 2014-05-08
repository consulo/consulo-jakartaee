/*
 * Copyright 2000-2009 JetBrains s.r.o.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

// Generated on Wed Apr 29 15:54:26 MSD 2009
// DTD/Schema  :    http://java.sun.com/xml/ns/javaee

package com.intellij.javaee.model.xml.ejb;

import com.intellij.javaee.model.enums.TimeUnitType;
import com.intellij.javaee.model.xml.JavaeeDomModelElement;
import com.intellij.util.xml.GenericDomValue;
import com.intellij.util.xml.Required;
import org.jetbrains.annotations.NotNull;

/**
 * http://java.sun.com/xml/ns/javaee:access-timeoutType interface.
 * <pre>
 * <h3>Type http://java.sun.com/xml/ns/javaee:access-timeoutType documentation</h3>
 * The access-timeoutType represents the maximum amount of
 * 	time (in a given time unit) that the container should wait for
 * 	a concurrency lock before throwing a timeout exception to the
 * 	client.
 * </pre>
 */
public interface AccessTimeout extends JavaeeDomModelElement {

	/**
	 * Returns the value of the timeout child.
	 * <pre>
	 * <h3>Type http://java.sun.com/xml/ns/javaee:xsdPositiveIntegerType documentation</h3>
	 * This type adds an "id" attribute to xsd:positiveInteger.
	 * </pre>
	 * @return the value of the timeout child.
	 */
	@NotNull
	@Required
	GenericDomValue<Integer> getTimeout();


	/**
	 * Returns the value of the unit child.
	 * @return the value of the unit child.
	 */
	@NotNull
	@Required
	GenericDomValue<TimeUnitType> getUnit();


}
