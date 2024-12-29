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

import com.intellij.jam.view.DeleteHandler;
import com.intellij.javaee.model.xml.Description;
import com.intellij.javaee.model.xml.DescriptionOwner;
import com.intellij.javaee.model.xml.JavaeeDomModelElement;
import consulo.xml.util.xml.GenericDomValue;
import consulo.xml.util.xml.SubTag;
import consulo.xml.util.xml.NameValue;
import jakarta.annotation.Nonnull;

import java.util.List;

/**
 * http://java.sun.com/xml/ns/javaee:ejb-relationType interface.
 * <pre>
 * <h3>Type http://java.sun.com/xml/ns/javaee:ejb-relationType documentation</h3>
 * The ejb-relationType describes a relationship between two
 * 	entity beans with container-managed persistence.  It is used
 * 	by ejb-relation elements. It contains a description; an
 * 	optional ejb-relation-name element; and exactly two
 * 	relationship role declarations, defined by the
 * 	ejb-relationship-role elements. The name of the
 * 	relationship, if specified, is unique within the ejb-jar
 * 	file.
 * </pre>
 */
@DeleteHandler("com.intellij.openapi.module.EjbDeleteHandler")
public interface EjbRelation extends JavaeeDomModelElement, DescriptionOwner {

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
	 * Returns the value of the ejb-relation-name child.
	 * <pre>
	 * <h3>Element http://java.sun.com/xml/ns/javaee:ejb-relation-name documentation</h3>
	 * The ejb-relation-name element provides a unique name
	 * 	    within the ejb-jar file for a relationship.
	 * </pre>
	 * <pre>
	 * <h3>Type http://java.sun.com/xml/ns/javaee:string documentation</h3>
	 * This is a special string datatype that is defined by Java EE as
	 * 	a base type for defining collapsed strings. When schemas
	 * 	require trailing/leading space elimination as well as
	 * 	collapsing the existing whitespace, this base type may be
	 * 	used.
	 * </pre>
	 * @return the value of the ejb-relation-name child.
	 */
        @NameValue
        GenericDomValue<String> getEjbRelationName();


	/**
	 * Returns the value of the ejb-relationship-role child.
	 * @return the value of the ejb-relationship-role child.
	 */
	@SubTag (value = "ejb-relationship-role", index = 0)
	@Nonnull
	EjbRelationshipRole getEjbRelationshipRole1();


	/**
	 * Returns the value of the ejb-relationship-role child.
	 * @return the value of the ejb-relationship-role child.
	 */
	@SubTag (value = "ejb-relationship-role", index = 1)
	@Nonnull
	EjbRelationshipRole getEjbRelationshipRole2();


}
