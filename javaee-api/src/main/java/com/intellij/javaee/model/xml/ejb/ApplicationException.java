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

import com.intellij.java.language.psi.PsiClass;
import com.intellij.javaee.model.xml.JavaeeDomModelElement;
import consulo.xml.util.xml.GenericDomValue;

import javax.annotation.Nonnull;

/**
 * http://java.sun.com/xml/ns/javaee:application-exceptionType interface.
 * <pre>
 * <h3>Type http://java.sun.com/xml/ns/javaee:application-exceptionType documentation</h3>
 * The application-exceptionType declares an application
 *         exception. The declaration consists of:
 *             - the exception class. When the container receives
 *               an exception of this type, it is required to
 *               forward this exception as an applcation exception
 *               to the client regardless of whether it is a checked
 *               or unchecked exception.
 *             - an optional rollback element. If this element is
 *               set to true, the container must rollback the current
 *               transaction before forwarding the exception to the
 *               client.  If not specified, it defaults to false.
 *             - an optional inherited element. If this element is
 *               set to true, subclasses of the exception class type
 * 	      are also automatically considered application
 * 	      exceptions (unless overriden at a lower level).
 * 	      If set to false, only the exception class type is
 * 	      considered an application-exception, not its
 * 	      exception subclasses. If not specified, this
 *  	      value defaults to true.
 * </pre>
 */
public interface ApplicationException extends JavaeeDomModelElement, com.intellij.javaee.model.common.ejb.ApplicationException {

	/**
	 * Returns the value of the exception-class child.
	 * <pre>
	 * <h3>Type http://java.sun.com/xml/ns/javaee:fully-qualified-classType documentation</h3>
	 * The elements that use this type designate the name of a
	 * 	Java class or interface.  The name is in the form of a
	 * 	"binary name", as defined in the JLS.  This is the form
	 * 	of name used in Class.forName().  Tools that need the
	 * 	canonical name (the name used in source code) will need
	 * 	to convert this binary name to the canonical name.
	 * </pre>
	 * @return the value of the exception-class child.
	 */
	@Nonnull
	GenericDomValue<PsiClass> getExceptionClass();


	/**
	 * Returns the value of the rollback child.
	 * <pre>
	 * <h3>Type http://java.sun.com/xml/ns/javaee:true-falseType documentation</h3>
	 * This simple type designates a boolean with only two
	 * 	permissible values
	 * 	- true
	 * 	- false
	 * </pre>
	 * @return the value of the rollback child.
	 */
	GenericDomValue<Boolean> getRollback();


	/**
	 * Returns the value of the inherited child.
	 * <pre>
	 * <h3>Type http://java.sun.com/xml/ns/javaee:true-falseType documentation</h3>
	 * This simple type designates a boolean with only two
	 * 	permissible values
	 * 	- true
	 * 	- false
	 * </pre>
	 * @return the value of the inherited child.
	 */
	GenericDomValue<Boolean> getInherited();


}
