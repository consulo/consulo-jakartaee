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

import com.intellij.javaee.model.enums.Multiplicity;
import com.intellij.javaee.model.xml.Description;
import com.intellij.javaee.model.xml.JavaeeDomModelElement;
import consulo.xml.util.xml.GenericDomValue;
import consulo.xml.util.xml.SubTag;
import javax.annotation.Nonnull;

import java.util.List;

/**
 * http://java.sun.com/xml/ns/javaee:ejb-relationship-roleType interface.
 * <pre>
 * <h3>Type http://java.sun.com/xml/ns/javaee:ejb-relationship-roleType documentation</h3>
 * 	  The ejb-relationship-roleType describes a role within a
 * 	  relationship. There are two roles in each relationship.
 * 	  The ejb-relationship-roleType contains an optional
 * 	  description; an optional name for the relationship role; a
 * 	  specification of the multiplicity of the role; an optional
 * 	  specification of cascade-delete functionality for the role;
 * 	  the role source; and a declaration of the cmr-field, if any,
 * 	  by means of which the other side of the relationship is
 * 	  accessed from the perspective of the role source.
 * 	  The multiplicity and role-source element are mandatory.
 * 	  The relationship-role-source element designates an entity
 * 	  bean by means of an ejb-name element. For bidirectional
 * 	  relationships, both roles of a relationship must declare a
 * 	  relationship-role-source element that specifies a cmr-field
 * 	  in terms of which the relationship is accessed. The lack of
 * 	  a cmr-field element in an ejb-relationship-role specifies
 * 	  that the relationship is unidirectional in navigability and
 * 	  the entity bean that participates in the relationship is
 * 	  "not aware" of the relationship.
 * 	  Example:
 * 	  <ejb-relation>
 * 	      <ejb-relation-name>Product-LineItem</ejb-relation-name>
 * 	      <ejb-relationship-role>
 * 		  <ejb-relationship-role-name>product-has-lineitems
 * 		  </ejb-relationship-role-name>
 * 		  <multiplicity>One</multiplicity>
 * 		  <relationship-role-source>
 * 		  <ejb-name>ProductEJB</ejb-name>
 * 		  </relationship-role-source>
 * 	       </ejb-relationship-role>
 * 	  </ejb-relation>
 * 	  
 * </pre>
 */
public interface EjbRelationshipRole extends JavaeeDomModelElement {

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
	 * Returns the value of the ejb-relationship-role-name child.
	 * <pre>
	 * <h3>Element http://java.sun.com/xml/ns/javaee:ejb-relationship-role-name documentation</h3>
	 * The ejb-relationship-role-name element defines a
	 * 	    name for a role that is unique within an
	 * 	    ejb-relation. Different relationships can use the
	 * 	    same name for a role.
	 * </pre>
	 * <pre>
	 * <h3>Type http://java.sun.com/xml/ns/javaee:string documentation</h3>
	 * This is a special string datatype that is defined by Java EE as
	 * 	a base type for defining collapsed strings. When schemas
	 * 	require trailing/leading space elimination as well as
	 * 	collapsing the existing whitespace, this base type may be
	 * 	used.
	 * </pre>
	 * @return the value of the ejb-relationship-role-name child.
	 */
	GenericDomValue<String> getEjbRelationshipRoleName();


	/**
	 * Returns the value of the multiplicity child.
	 * @return the value of the multiplicity child.
	 */
	@Nonnull
	GenericDomValue<Multiplicity> getMultiplicity();


	/**
	 * Returns the value of the cascade-delete child.
	 * <pre>
	 * <h3>Element http://java.sun.com/xml/ns/javaee:cascade-delete documentation</h3>
	 * The cascade-delete element specifies that, within a
	 * 	    particular relationship, the lifetime of one or more
	 * 	    entity beans is dependent upon the lifetime of
	 * 	    another entity bean. The cascade-delete element can
	 * 	    only be specified for an ejb-relationship-role
	 * 	    element contained in an ejb-relation element in
	 * 	    which the other ejb-relationship-role
	 * 	    element specifies a multiplicity of One.
	 * </pre>
	 * <pre>
	 * <h3>Type http://java.sun.com/xml/ns/javaee:emptyType documentation</h3>
	 * This type is used to designate an empty
	 * 	element when used.
	 * </pre>
	 * @return the value of the cascade-delete child.
	 */
	@SubTag (value = "cascade-delete", indicator = true)
	GenericDomValue<Boolean> getCascadeDelete();


	/**
	 * Returns the value of the relationship-role-source child.
	 * @return the value of the relationship-role-source child.
	 */
	@Nonnull
	RelationshipRoleSource getRelationshipRoleSource();


	/**
	 * Returns the value of the cmr-field child.
	 * @return the value of the cmr-field child.
	 */
	CmrField getCmrField();


}
