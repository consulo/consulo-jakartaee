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

import com.intellij.javaee.model.enums.ConcurrentLockType;
import com.intellij.javaee.model.xml.JavaeeDomModelElement;
import com.intellij.util.xml.GenericDomValue;
import com.intellij.util.xml.Required;
import org.jetbrains.annotations.NotNull;

/**
 * http://java.sun.com/xml/ns/javaee:concurrent-methodType interface.
 * <pre>
 * <h3>Type http://java.sun.com/xml/ns/javaee:concurrent-methodType documentation</h3>
 * The concurrent-methodType specifies information about a method
 * 	of a bean with container managed concurrency.
 * 	The optional lock element specifies the kind of concurrency
 * 	lock asssociated with the method.
 * 	The optional access-timeout element specifies the amount of
 * 	time (in a given time unit) the container should wait for a
 * 	concurrency lock before throwing an exception to the client.
 * </pre>
 */
public interface ConcurrentMethod extends JavaeeDomModelElement {

	/**
	 * Returns the value of the method child.
	 * @return the value of the method child.
	 */
	@NotNull
	@Required
	NamedMethod getMethod();


	/**
	 * Returns the value of the lock child.
	 * @return the value of the lock child.
	 */
	@NotNull
	GenericDomValue<ConcurrentLockType> getLock();


	/**
	 * Returns the value of the access-timeout child.
	 * @return the value of the access-timeout child.
	 */
	@NotNull
	AccessTimeout getAccessTimeout();


}
