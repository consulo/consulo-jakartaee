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

import javax.annotation.Nonnull;

import com.intellij.javaee.model.EjbMethodResolveConverter;
import com.intellij.javaee.model.enums.MethodIntf;
import com.intellij.javaee.model.xml.JavaeeDomModelElement;
import com.intellij.util.xml.Convert;
import com.intellij.util.xml.GenericDomValue;
import com.intellij.util.xml.SubTag;

/**
 * http://java.sun.com/xml/ns/javaee:async-methodType interface.
 * <pre>
 * <h3>Type http://java.sun.com/xml/ns/javaee:async-methodType documentation</h3>
 * The async-methodType element specifies that a session
 * 	bean method has asynchronous invocation semantics.
 * 	The optional method-intf element constrains the async
 * 	method behavior to the client views of the given method-intf
 * 	type.  This value must be either Remote or Local.
 * </pre>
 */
public interface AsyncMethod extends JavaeeDomModelElement {

	/**
	 * Returns the value of the method-name child.
	 * <pre>
	 * <h3>Type http://java.sun.com/xml/ns/javaee:string documentation</h3>
	 * This is a special string datatype that is defined by Java EE as
	 * 	a base type for defining collapsed strings. When schemas
	 * 	require trailing/leading space elimination as well as
	 * 	collapsing the existing whitespace, this base type may be
	 * 	used.
	 * </pre>
	 * @return the value of the method-name child.
	 */
	@Nonnull
        @SubTag("method-name")
        @Convert(EjbMethodResolveConverter.class)
        GenericDomValue<String> getMethod();


	/**
	 * Returns the value of the method-params child.
	 * @return the value of the method-params child.
	 */
	@Nonnull
	MethodParams getMethodParams();


	/**
	 * Returns the value of the method-intf child.
	 * @return the value of the method-intf child.
	 */
	@Nonnull
	GenericDomValue<MethodIntf> getMethodIntf();


}
