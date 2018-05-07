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
import com.intellij.javaee.model.xml.JavaeeDomModelElement;
import com.intellij.javaee.model.EntityBeanResolveConverter;
import com.intellij.util.xml.GenericDomValue;
import com.intellij.util.xml.SubTag;
import com.intellij.util.xml.Convert;
import javax.annotation.Nonnull;

import java.util.List;

/**
 * http://java.sun.com/xml/ns/javaee:relationship-role-sourceType interface.
 * <pre>
 * <h3>Type http://java.sun.com/xml/ns/javaee:relationship-role-sourceType documentation</h3>
 * The relationship-role-sourceType designates the source of a
 * 	role that participates in a relationship. A
 * 	relationship-role-sourceType is used by
 * 	relationship-role-source elements to uniquely identify an
 * 	entity bean.
 * </pre>
 */
public interface RelationshipRoleSource extends JavaeeDomModelElement {

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
	 * Returns the value of the ejb-name child.
	 * <pre>
	 * <h3>Type http://java.sun.com/xml/ns/javaee:ejb-nameType documentation</h3>
	 * 	  The ejb-nameType specifies an enterprise bean's name. It is
	 * 	  used by ejb-name elements. This name is assigned by the
	 * 	  ejb-jar file producer to name the enterprise bean in the
	 * 	  ejb-jar file's deployment descriptor. The name must be
	 * 	  unique among the names of the enterprise beans in the same
	 * 	  ejb-jar file.
	 * 	  There is no architected relationship between the used
	 * 	  ejb-name in the deployment descriptor and the JNDI name that
	 * 	  the Deployer will assign to the enterprise bean's home.
	 * 	  The name for an entity bean must conform to the lexical
	 * 	  rules for an NMTOKEN.
	 * 	  Example:
	 * 	  <ejb-name>EmployeeService</ejb-name>
	 * 	  
	 * </pre>
	 * @return the value of the ejb-name child.
	 */
	@Nonnull
        @SubTag("ejb-name")
        @Convert(EntityBeanResolveConverter.class)
        GenericDomValue<com.intellij.javaee.model.common.ejb.EntityBean> getEntityBean();


}
