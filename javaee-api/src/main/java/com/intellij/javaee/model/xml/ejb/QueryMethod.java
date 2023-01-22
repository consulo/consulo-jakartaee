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
import com.intellij.javaee.model.QueryMethodResolveConverter;
import com.intellij.javaee.model.xml.JavaeeDomModelElement;
import consulo.xml.util.xml.Convert;
import consulo.xml.util.xml.GenericDomValue;

import javax.annotation.Nonnull;

/**
 * http://java.sun.com/xml/ns/javaee:query-methodType interface.
 * <pre>
 * <h3>Type http://java.sun.com/xml/ns/javaee:query-methodType documentation</h3>
 * 	  The query-method specifies the method for a finder or select
 * 	  query.
 * 	  The method-name element specifies the name of a finder or select
 * 	  method in the entity bean's implementation class.
 * 	  Each method-param must be defined for a query-method using the
 * 	  method-params element.
 * 	  It is used by the query-method element.
 * 	  Example:
 * 	  <query>
 * 	      <description>Method finds large orders</description>
 * 	      <query-method>
 * 		  <method-name>findLargeOrders</method-name>
 * 		  <method-params></method-params>
 * 	      </query-method>
 * 	      <ejb-ql>
 * 		SELECT OBJECT(o) FROM Order o
 * 		  WHERE o.amount &gt; 1000
 * 	      </ejb-ql>
 * 	  </query>
 * 	  
 * </pre>
 */
public interface QueryMethod extends JavaeeDomModelElement {

	/**
	 * Returns the value of the method-name child.
	 * <pre>
	 * <h3>Type http://java.sun.com/xml/ns/javaee:method-nameType documentation</h3>
	 * The method-nameType contains a name of an enterprise
	 * 	bean method or the asterisk (*) character. The asterisk is
	 * 	used when the element denotes all the methods of an
	 * 	enterprise bean's client view interfaces.
	 * </pre>
	 * @return the value of the method-name child.
	 */
	@Nonnull
        @Convert(QueryMethodResolveConverter.class)
        GenericDomValue<PsiMethod> getMethodName();


	/**
	 * Returns the value of the method-params child.
	 * @return the value of the method-params child.
	 */
	@Nonnull
	MethodParams getMethodParams();


}
