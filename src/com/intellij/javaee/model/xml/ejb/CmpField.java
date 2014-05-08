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

import com.intellij.javaee.model.xml.Description;
import com.intellij.javaee.model.xml.DescriptionOwner;
import com.intellij.javaee.model.xml.JavaeeDomModelElement;
import com.intellij.util.xml.GenericDomValue;
import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * http://java.sun.com/xml/ns/javaee:cmp-fieldType interface.
 * <pre>
 * <h3>Type http://java.sun.com/xml/ns/javaee:cmp-fieldType documentation</h3>
 * The cmp-fieldType describes a container-managed field. The
 * 	cmp-fieldType contains an optional description of the field,
 * 	and the name of the field.
 * </pre>
 */
public interface CmpField extends JavaeeDomModelElement, com.intellij.javaee.model.common.ejb.CmpField, DescriptionOwner {

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
	 * Returns the value of the field-name child.
	 * <pre>
	 * <h3>Element http://java.sun.com/xml/ns/javaee:field-name documentation</h3>
	 * The field-name element specifies the name of a
	 * 	    container managed field.
	 * 	    The name of the cmp-field of an entity bean with
	 * 	    cmp-version 2.x must begin with a lowercase
	 * 	    letter. This field is accessed by methods whose
	 * 	    names consists of the name of the field specified by
	 * 	    field-name in which the first letter is uppercased,
	 * 	    prefixed by "get" or "set".
	 * 	    The name of the cmp-field of an entity bean with
	 * 	    cmp-version 1.x must denote a public field of the
	 * 	    enterprise bean class or one of its superclasses.
	 * </pre>
	 * <pre>
	 * <h3>Type http://java.sun.com/xml/ns/javaee:java-identifierType documentation</h3>
	 * The java-identifierType defines a Java identifier.
	 * 	The users of this type should further verify that
	 * 	the content does not contain Java reserved keywords.
	 * </pre>
	 * @return the value of the field-name child.
	 */
	@NotNull
	GenericDomValue<String> getFieldName();


}
