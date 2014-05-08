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

import com.intellij.javaee.model.enums.ResultTypeMapping;
import com.intellij.javaee.model.xml.Description;
import com.intellij.javaee.model.xml.JavaeeDomModelElement;
import com.intellij.util.xml.GenericDomValue;
import com.intellij.util.xml.Required;
import org.jetbrains.annotations.NotNull;

/**
 * http://java.sun.com/xml/ns/javaee:queryType interface.
 * <pre>
 * <h3>Type http://java.sun.com/xml/ns/javaee:queryType documentation</h3>
 * The queryType defines a finder or select
 * 	query. It contains
 * 	    - an optional description of the query
 * 	    - the specification of the finder or select
 * 	      method it is used by
 * 		- an optional specification of the result type
 * 		  mapping, if the query is for a select method
 * 		  and entity objects are returned.
 * 		- the EJB QL query string that defines the query.
 * 	Queries that are expressible in EJB QL must use the ejb-ql
 * 	element to specify the query. If a query is not expressible
 * 	in EJB QL, the description element should be used to
 * 	describe the semantics of the query and the ejb-ql element
 * 	should be empty.
 * 	The result-type-mapping is an optional element. It can only
 * 	be present if the query-method specifies a select method
 * 	that returns entity objects.  The default value for the
 * 	result-type-mapping element is "Local".
 * </pre>
 */
public interface Query extends JavaeeDomModelElement, com.intellij.javaee.model.common.ejb.Query {

	/**
	 * Returns the value of the description child.
	 * @return the value of the description child.
	 */
	Description getDescription();


	/**
	 * Returns the value of the query-method child.
	 * @return the value of the query-method child.
	 */
	@NotNull
	QueryMethod getQueryMethod();
  
        /**
	 * Returns the value of the result-type-mapping child.
	 * @return the value of the result-type-mapping child.
	 */
	GenericDomValue<ResultTypeMapping> getResultTypeMapping();


	/**
	 * Returns the value of the ejb-ql child.
	 * <pre>
	 * <h3>Type http://java.sun.com/xml/ns/javaee:xsdStringType documentation</h3>
	 * This type adds an "id" attribute to xsd:string.
	 * </pre>
	 * @return the value of the ejb-ql child.
	 */
	@Required
        GenericDomValue<String> getEjbQl();


}
