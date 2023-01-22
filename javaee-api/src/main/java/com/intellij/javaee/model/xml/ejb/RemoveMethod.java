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

// Generated on Mon Mar 20 14:03:07 MSK 2006
// DTD/Schema  :    http://java.sun.com/xml/ns/javaee

package com.intellij.javaee.model.xml.ejb;

import javax.annotation.Nonnull;

import com.intellij.javaee.model.xml.JavaeeDomModelElement;
import consulo.xml.util.xml.GenericDomValue;

/**
 * http://java.sun.com/xml/ns/javaee:remove-methodType interface.
 */
public interface RemoveMethod extends JavaeeDomModelElement {

	/**
	 * Returns the value of the bean-method child.
	 * @return the value of the bean-method child.
	 */
	@Nonnull
	NamedMethod getBeanMethod();


	/**
	 * Returns the value of the retain-if-exception child.
	 * <pre>
	 * <h3>Type http://java.sun.com/xml/ns/javaee:true-falseType documentation</h3>
	 * This simple type designates a boolean with only two
	 * 	permissible values
	 * 	- true
	 * 	- false
	 * </pre>
	 * @return the value of the retain-if-exception child.
	 */
	GenericDomValue<Boolean> getRetainIfException();


}
