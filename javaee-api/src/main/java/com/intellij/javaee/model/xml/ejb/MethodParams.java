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

import com.intellij.java.impl.util.xml.converters.AbstractMethodParams;
import com.intellij.java.language.psi.PsiType;
import com.intellij.javaee.model.xml.JavaeeDomModelElement;
import consulo.xml.util.xml.GenericDomValue;

import java.util.List;

/**
 * http://java.sun.com/xml/ns/javaee:method-paramsType interface.
 * <pre>
 * <h3>Type http://java.sun.com/xml/ns/javaee:method-paramsType documentation</h3>
 * The method-paramsType defines a list of the
 * 	fully-qualified Java type names of the method parameters.
 * </pre>
 */
public interface MethodParams extends JavaeeDomModelElement, AbstractMethodParams
{

	/**
	 * Returns the list of method-param children.
	 * <pre>
	 * <h3>Element http://java.sun.com/xml/ns/javaee:method-param documentation</h3>
	 * The method-param element contains a primitive
	 * 	    or a fully-qualified Java type name of a method
	 * 	    parameter.
	 * </pre>
	 * <pre>
	 * <h3>Type http://java.sun.com/xml/ns/javaee:java-typeType documentation</h3>
	 * This is a generic type that designates a Java primitive
	 * 	type or a fully qualified name of a Java interface/type,
	 * 	or an array of such types.
	 * </pre>
	 * @return the list of method-param children.
	 */
	List<GenericDomValue<PsiType>> getMethodParams();
	/**
	 * Adds new child to the list of method-param children.
	 * @return created child
	 */
	GenericDomValue<PsiType> addMethodParam();


}
