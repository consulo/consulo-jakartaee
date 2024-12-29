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

import com.intellij.java.language.psi.PsiClass;
import com.intellij.javaee.model.xml.JavaeeDomModelElement;
import consulo.xml.util.xml.GenericDomValue;
import consulo.xml.util.xml.Required;

import jakarta.annotation.Nonnull;

/**
 * http://java.sun.com/xml/ns/javaee:around-timeoutType interface.
 * <pre>
 * <h3>Type http://java.sun.com/xml/ns/javaee:around-timeoutType documentation</h3>
 * The around-timeout type specifies a method on a
 *         class to be called during the around-timeout portion of
 *         a timer timeout callback.  Note that each class may have
 *         only one around-timeout method and that the method may not
 *         be overloaded.
 *         If the class element is missing then
 *         the class defining the callback is assumed to be the
 *         interceptor class or component class in scope at the
 *         location in the descriptor in which the around-timeout
 *         definition appears.
 * </pre>
 */
public interface AroundTimeout extends JavaeeDomModelElement {

	/**
	 * Returns the value of the class child.
	 * <pre>
	 * <h3>Type http://java.sun.com/xml/ns/javaee:fully-qualified-classType documentation</h3>
	 * The elements that use this type designate the name of a
	 * 	Java class or interface.  The name is in the form of a
	 * 	"binary name", as defined in the JLS.  This is the form
	 * 	of name used in Class.forName().  Tools that need the
	 * 	canonical name (the name used in source code) will need
	 * 	to convert this binary name to the canonical name.
	 * </pre>
	 * @return the value of the class child.
	 */
	@Nonnull
	@consulo.xml.util.xml.SubTag ("class")
	GenericDomValue<PsiClass> getClazz();


	/**
	 * Returns the value of the method-name child.
	 * <pre>
	 * <h3>Type http://java.sun.com/xml/ns/javaee:java-identifierType documentation</h3>
	 * The java-identifierType defines a Java identifier.
	 * 	The users of this type should further verify that
	 * 	the content does not contain Java reserved keywords.
	 * </pre>
	 * @return the value of the method-name child.
	 */
	@Nonnull
	@Required
	GenericDomValue<String> getMethodName();


}
