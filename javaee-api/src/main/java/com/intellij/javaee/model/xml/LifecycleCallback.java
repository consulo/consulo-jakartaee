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

package com.intellij.javaee.model.xml;

import com.intellij.java.language.psi.PsiClass;
import com.intellij.java.language.psi.PsiMethod;
import com.intellij.javaee.model.LifecycleCallbackResolveConverter;
import consulo.xml.util.xml.Convert;
import consulo.xml.util.xml.GenericDomValue;

import javax.annotation.Nonnull;

/**
 * http://java.sun.com/xml/ns/javaee:lifecycle-callbackType interface.
 * <pre>
 * <h3>Type http://java.sun.com/xml/ns/javaee:lifecycle-callbackType documentation</h3>
 * The lifecycle-callback type specifies a method on a
 * 	class to be called when a lifecycle event occurs.
 * 	Note that each class may have only one lifecycle callback
 *         method for any given event and that the method may not
 * 	be overloaded.
 *         If the lifefycle-callback-class element is missing then
 *         the class defining the callback is assumed to be the
 *         component class in scope at the place in the descriptor
 *         in which the callback definition appears.
 * </pre>
 */
public interface LifecycleCallback extends JavaeeDomModelElement {

	/**
	 * Returns the value of the lifecycle-callback-class child.
	 * <pre>
	 * <h3>Type http://java.sun.com/xml/ns/javaee:fully-qualified-classType documentation</h3>
	 * The elements that use this type designate the name of a
	 * 	Java class or interface.  The name is in the form of a
	 * 	"binary name", as defined in the JLS.  This is the form
	 * 	of name used in Class.forName().  Tools that need the
	 * 	canonical name (the name used in source code) will need
	 * 	to convert this binary name to the canonical name.
	 * </pre>
	 * @return the value of the lifecycle-callback-class child.
	 */
	GenericDomValue<PsiClass> getLifecycleCallbackClass();


	/**
	 * Returns the value of the lifecycle-callback-method child.
	 * <pre>
	 * <h3>Type http://java.sun.com/xml/ns/javaee:java-identifierType documentation</h3>
	 * The java-identifierType defines a Java identifier.
	 * 	The users of this type should further verify that
	 * 	the content does not contain Java reserved keywords.
	 * </pre>
	 * @return the value of the lifecycle-callback-method child.
	 */
	@Nonnull
        @Convert(LifecycleCallbackResolveConverter.class)
        GenericDomValue<PsiMethod> getLifecycleCallbackMethod();


}
