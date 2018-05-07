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

import com.intellij.javaee.model.xml.JavaeeDomModelElement;
import com.intellij.psi.PsiClass;
import com.intellij.util.xml.GenericDomValue;
import javax.annotation.Nonnull;

import java.util.List;

/**
 * http://java.sun.com/xml/ns/javaee:interceptor-orderType interface.
 * <pre>
 * <h3>Type http://java.sun.com/xml/ns/javaee:interceptor-orderType documentation</h3>
 * The interceptor-orderType element describes a total ordering
 *         of interceptor classes.
 * </pre>
 */
public interface InterceptorOrder extends JavaeeDomModelElement {

	/**
	 * Returns the list of interceptor-class children.
	 * <pre>
	 * <h3>Type http://java.sun.com/xml/ns/javaee:fully-qualified-classType documentation</h3>
	 * The elements that use this type designate the name of a
	 * 	Java class or interface.  The name is in the form of a
	 * 	"binary name", as defined in the JLS.  This is the form
	 * 	of name used in Class.forName().  Tools that need the
	 * 	canonical name (the name used in source code) will need
	 * 	to convert this binary name to the canonical name.
	 * </pre>
	 * @return the list of interceptor-class children.
	 */
	@Nonnull
	List<GenericDomValue<PsiClass>> getInterceptorClasses();
	/**
	 * Adds new child to the list of interceptor-class children.
	 * @return created child
	 */
	GenericDomValue<PsiClass> addInterceptorClass();


}
