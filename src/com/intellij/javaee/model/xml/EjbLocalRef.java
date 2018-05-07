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

// Generated on Tue Feb 14 17:35:30 MSK 2006
// DTD/Schema  :    http://java.sun.com/xml/ns/javaee

package com.intellij.javaee.model.xml;

import com.intellij.javaee.model.common.ejb.EnterpriseBean;
import com.intellij.javaee.model.enums.EjbRefType;
import com.intellij.psi.PsiClass;
import com.intellij.util.xml.GenericDomValue;
import com.intellij.util.xml.PropertyAccessor;
import javax.annotation.Nonnull;

import java.util.List;

/**
 * http://java.sun.com/xml/ns/javaee:ejb-local-refType interface.
 * <pre>
 * <h3>Type http://java.sun.com/xml/ns/javaee:ejb-local-refType documentation</h3>
 * The ejb-local-refType is used by ejb-local-ref elements for
 * 	the declaration of a reference to an enterprise bean's local
 * 	home or to the local business interface of a 3.0 bean.
 *         The declaration consists of:
 * 	    - an optional description
 * 	    - the EJB reference name used in the code of the Deployment
 * 	      Component that's referencing the enterprise bean.
 * 	    - the optional expected type of the referenced enterprise bean
 * 	    - the optional expected local interface of the referenced
 *               enterprise bean or the local business interface of the
 *               referenced enterprise bean.
 * 	    - the optional expected local home interface of the referenced
 *               enterprise bean. Not applicable if this ejb-local-ref refers
 *               to the local business interface of a 3.0 bean.
 * 	    - optional ejb-link information, used to specify the
 * 	      referenced enterprise bean
 *             - optional elements to define injection of the named enterprise
 *               bean into a component field or property.
 * </pre>
 */
public interface EjbLocalRef extends JavaeeDomModelElement, ResourceGroup, EjbReference {

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
	 * Returns the value of the ejb-ref-name child.
	 * <pre>
	 * <h3>Type http://java.sun.com/xml/ns/javaee:ejb-ref-nameType documentation</h3>
	 * 	  The ejb-ref-name element contains the name of an EJB
	 * 	  reference. The EJB reference is an entry in the
	 * 	  Deployment Component's environment and is relative to the
	 * 	  java:comp/env context.  The name must be unique within the
	 * 	  Deployment Component.
	 * 	  It is recommended that name is prefixed with "ejb/".
	 * 	  Example:
	 * 	  <ejb-ref-name>ejb/Payroll</ejb-ref-name>
	 * 	  
	 * </pre>
	 * @return the value of the ejb-ref-name child.
	 */
	@Nonnull
        GenericDomValue<String> getEjbRefName();


	/**
	 * Returns the value of the ejb-ref-type child.
	 * @return the value of the ejb-ref-type child.
	 */
	GenericDomValue<EjbRefType> getEjbRefType();


	/**
	 * Returns the value of the local-home child.
	 * <pre>
	 * <h3>Type http://java.sun.com/xml/ns/javaee:local-homeType documentation</h3>
	 * The local-homeType defines the fully-qualified
	 * 	name of an enterprise bean's local home interface.
	 * </pre>
	 * @return the value of the local-home child.
	 */
	GenericDomValue<PsiClass> getLocalHome();


	/**
	 * Returns the value of the local child.
	 * <pre>
	 * <h3>Type http://java.sun.com/xml/ns/javaee:localType documentation</h3>
	 * The localType defines the fully-qualified name of an
	 * 	enterprise bean's local interface.
	 * </pre>
	 * @return the value of the local child.
	 */
	GenericDomValue<PsiClass> getLocal();


	/**
	 * Returns the value of the ejb-link child.
	 * <pre>
	 * <h3>Type http://java.sun.com/xml/ns/javaee:ejb-linkType documentation</h3>
	 * 	  The ejb-linkType is used by ejb-link
	 * 	  elements in the ejb-ref or ejb-local-ref elements to specify
	 * 	  that an EJB reference is linked to enterprise bean.
	 * 	  The value of the ejb-link element must be the ejb-name of an
	 * 	  enterprise bean in the same ejb-jar file or in another ejb-jar
	 * 	  file in the same Java EE application unit.
	 * 	  Alternatively, the name in the ejb-link element may be
	 * 	  composed of a path name specifying the ejb-jar containing the
	 * 	  referenced enterprise bean with the ejb-name of the target
	 * 	  bean appended and separated from the path name by "#".  The
	 * 	  path name is relative to the Deployment File containing
	 * 	  Deployment Component that is referencing the enterprise
	 * 	  bean.  This allows multiple enterprise beans with the same
	 * 	  ejb-name to be uniquely identified.
	 * 	  Examples:
	 * 	      <ejb-link>EmployeeRecord</ejb-link>
	 * 	      <ejb-link>../products/product.jar#ProductEJB</ejb-link>
	 * 	  
	 * </pre>
	 * @return the value of the ejb-link child.
	 */
        GenericDomValue<EnterpriseBean> getEjbLink();


	/**
	 * Returns the value of the mapped-name child.
	 * <pre>
	 * <h3>Element http://java.sun.com/xml/ns/javaee:mapped-name documentation</h3>
	 * 	      A product specific name that this resource should be
	 * 	      mapped to.  The name of this resource, as defined by the
	 * 	      resource's name element or defaulted, is a name that is
	 * 	      local to the application component using the resource.
	 * 	      (It's a name in the JNDI java:comp/env namespace.)  Many
	 * 	      application servers provide a way to map these local
	 * 	      names to names of resources known to the application
	 * 	      server.  This mapped name is often a global JNDI name,
	 * 	      but may be a name of any form.
	 * 	      Application servers are not required to support any
	 * 	      particular form or type of mapped name, nor the ability
	 * 	      to use mapped names.  The mapped name is
	 * 	      product-dependent and often installation-dependent.  No
	 * 	      use of a mapped name is portable.
	 * 	      
	 * </pre>
	 * <pre>
	 * <h3>Type http://java.sun.com/xml/ns/javaee:xsdStringType documentation</h3>
	 * This type adds an "id" attribute to xsd:string.
	 * </pre>
	 * @return the value of the mapped-name child.
	 */
	GenericDomValue<String> getMappedName();


	/**
	 * Returns the list of injection-target children.
	 * @return the list of injection-target children.
	 */
	List<InjectionTarget> getInjectionTargets();
	/**
	 * Adds new child to the list of injection-target children.
	 * @return created child
	 */
	InjectionTarget addInjectionTarget();

  @PropertyAccessor({"local"})
  GenericDomValue<PsiClass> getComponentInterface();

  @PropertyAccessor({"localHome"})
  GenericDomValue<PsiClass> getHomeInterface();

}
