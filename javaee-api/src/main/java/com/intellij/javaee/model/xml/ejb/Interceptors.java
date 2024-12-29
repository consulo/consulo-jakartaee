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

import com.intellij.javaee.model.common.ejb.InterceptorSet;
import com.intellij.javaee.model.xml.Description;
import com.intellij.javaee.model.xml.JavaeeDomModelElement;
import jakarta.annotation.Nonnull;

import java.util.List;

/**
 * http://java.sun.com/xml/ns/javaee:interceptorsType interface.
 * <pre>
 * <h3>Type http://java.sun.com/xml/ns/javaee:interceptorsType documentation</h3>
 * The interceptorsType element declares one or more interceptor
 *         classes used by components within this ejb-jar.  The declaration
 *         consists of :
 *             - An optional description.
 *             - One or more interceptor elements.
 * </pre>
 */
public interface Interceptors extends JavaeeDomModelElement, InterceptorSet {

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
	 * Returns the list of interceptor children.
	 * @return the list of interceptor children.
	 */
	@Nonnull
	List<Interceptor> getInterceptors();
	/**
	 * Adds new child to the list of interceptor children.
	 * @return created child
	 */
	Interceptor addInterceptor();


}
