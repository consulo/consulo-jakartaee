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

import com.intellij.java.language.psi.PsiMethod;
import com.intellij.javaee.model.NamedMethodResolveConverter;
import com.intellij.javaee.model.xml.JavaeeDomModelElement;
import consulo.xml.util.xml.Convert;
import consulo.xml.util.xml.GenericDomValue;
import consulo.xml.util.xml.SubTag;

import javax.annotation.Nonnull;

/**
 * http://java.sun.com/xml/ns/javaee:named-methodType interface.
 */
public interface NamedMethod extends JavaeeDomModelElement {

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
        @Convert(NamedMethodResolveConverter.class)
        GenericDomValue<PsiMethod> getMethod();


	/**
	 * Returns the value of the method-params child.
	 * @return the value of the method-params child.
	 */
	MethodParams getMethodParams();


}
