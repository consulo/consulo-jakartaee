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

import com.intellij.javaee.model.enums.CmrFieldType;
import com.intellij.javaee.model.xml.Description;
import com.intellij.javaee.model.xml.JavaeeDomModelElement;
import com.intellij.javaee.model.xml.DescriptionOwner;
import com.intellij.util.xml.GenericDomValue;
import javax.annotation.Nonnull;

import java.util.List;

/**
 * http://java.sun.com/xml/ns/javaee:cmr-fieldType interface.
 * <pre>
 * <h3>Type http://java.sun.com/xml/ns/javaee:cmr-fieldType documentation</h3>
 * The cmr-fieldType describes the bean provider's view of
 * 	a relationship. It consists of an optional description, and
 * 	the name and the class type of a field in the source of a
 * 	role of a relationship. The cmr-field-name element
 * 	corresponds to the name used for the get and set accessor
 * 	methods for the relationship. The cmr-field-type element is
 * 	used only for collection-valued cmr-fields. It specifies the
 * 	type of the collection that is used.
 * </pre>
 */
public interface CmrField extends JavaeeDomModelElement, com.intellij.javaee.model.common.ejb.CmrField, DescriptionOwner {

	/**
	 * Returns the list of description children.
	 * @return the list of description children.
	 */
	List<Description> getDescriptions();
	/**
	 * Adds new child to the list of description children.
	 * @return created child
	 */
	Description addDescription();


	/**
	 * Returns the value of the cmr-field-name child.
	 * <pre>
	 * <h3>Element http://java.sun.com/xml/ns/javaee:cmr-field-name documentation</h3>
	 * The cmr-field-name element specifies the name of a
	 * 	    logical relationship field in the entity bean
	 * 	    class. The name of the cmr-field must begin with a
	 * 	    lowercase letter. This field is accessed by methods
	 * 	    whose names consist of the name of the field
	 * 	    specified by cmr-field-name in which the first
	 * 	    letter is uppercased, prefixed by "get" or "set".
	 * </pre>
	 * <pre>
	 * <h3>Type http://java.sun.com/xml/ns/javaee:string documentation</h3>
	 * This is a special string datatype that is defined by Java EE as
	 * 	a base type for defining collapsed strings. When schemas
	 * 	require trailing/leading space elimination as well as
	 * 	collapsing the existing whitespace, this base type may be
	 * 	used.
	 * </pre>
	 * @return the value of the cmr-field-name child.
	 */
	@Nonnull
	GenericDomValue<String> getCmrFieldName();


	/**
	 * Returns the value of the cmr-field-type child.
	 * @return the value of the cmr-field-type child.
	 */
	GenericDomValue<CmrFieldType> getCmrFieldType();


}
