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

import com.intellij.javaee.model.enums.MethodIntf;
import com.intellij.javaee.model.xml.Description;
import com.intellij.javaee.model.xml.JavaeeDomModelElement;
import com.intellij.javaee.model.EjbResolveConverter;
import com.intellij.javaee.model.EjbMethodResolveConverter;
import com.intellij.javaee.model.common.ejb.EnterpriseBean;
import com.intellij.util.xml.GenericDomValue;
import com.intellij.util.xml.SubTag;
import com.intellij.util.xml.Convert;
import com.intellij.psi.PsiMethod;
import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * http://java.sun.com/xml/ns/javaee:methodType interface.
 * <pre>
 * <h3>Type http://java.sun.com/xml/ns/javaee:methodType documentation</h3>
 * 	  The methodType is used to denote a method of an enterprise
 * 	  bean's business, home, component, and/or web service endpoint
 * 	  interface, or, in the case of a message-driven bean, the
 * 	  bean's message listener method, or a set of such
 * 	  methods. The ejb-name element must be the name of one of the
 * 	  enterprise beans declared in the deployment descriptor; the
 * 	  optional method-intf element allows to distinguish between a
 * 	  method with the same signature that is multiply defined
 * 	  across the business, home, component, and/or web service
 *           endpoint nterfaces; the method-name element specifies the
 *           method name; and the optional method-params elements identify
 *           a single method among multiple methods with an overloaded
 * 	  method name.
 * 	  There are three possible styles of using methodType element
 * 	  within a method element:
 * 	  1.
 * 	  <method>
 * 	      <ejb-name>EJBNAME</ejb-name>
 * 	      <method-name>*</method-name>
 * 	  </method>
 * 	     This style is used to refer to all the methods of the
 * 	     specified enterprise bean's business, home, component,
 *              and/or web service endpoint interfaces.
 * 	  2.
 * 	  <method>
 * 	      <ejb-name>EJBNAME</ejb-name>
 * 	      <method-name>METHOD</method-name>
 * 	  </method>
 * 	     This style is used to refer to the specified method of
 * 	     the specified enterprise bean. If there are multiple
 * 	     methods with the same overloaded name, the element of
 * 	     this style refers to all the methods with the overloaded
 * 	     name.
 * 	  3.
 * 	  <method>
 * 	      <ejb-name>EJBNAME</ejb-name>
 * 	      <method-name>METHOD</method-name>
 * 	      <method-params>
 * 		  <method-param>PARAM-1</method-param>
 * 		  <method-param>PARAM-2</method-param>
 * 		  ...
 * 		  <method-param>PARAM-n</method-param>
 * 	      </method-params>
 * 	  </method>
 * 	     This style is used to refer to a single method within a
 * 	     set of methods with an overloaded name. PARAM-1 through
 * 	     PARAM-n are the fully-qualified Java types of the
 * 	     method's input parameters (if the method has no input
 * 	     arguments, the method-params element contains no
 * 	     method-param elements). Arrays are specified by the
 * 	     array element's type, followed by one or more pair of
 * 	     square brackets (e.g. int[][]). If there are multiple
 * 	     methods with the same overloaded name, this style refers
 * 	     to all of the overloaded methods.
 * 	  Examples:
 * 	  Style 1: The following method element refers to all the
 * 	  methods of the EmployeeService bean's business, home,
 *           component, and/or web service endpoint interfaces:
 * 	  <method>
 * 	      <ejb-name>EmployeeService</ejb-name>
 * 	      <method-name>*</method-name>
 * 	  </method>
 * 	  Style 2: The following method element refers to all the
 * 	  create methods of the EmployeeService bean's home
 * 	  interface(s).
 * 	  <method>
 * 	      <ejb-name>EmployeeService</ejb-name>
 * 	      <method-name>create</method-name>
 * 	  </method>
 * 	  Style 3: The following method element refers to the
 * 	  create(String firstName, String LastName) method of the
 * 	  EmployeeService bean's home interface(s).
 * 	  <method>
 * 	      <ejb-name>EmployeeService</ejb-name>
 * 	      <method-name>create</method-name>
 * 	      <method-params>
 * 		  <method-param>java.lang.String</method-param>
 * 		  <method-param>java.lang.String</method-param>
 * 	      </method-params>
 * 	  </method>
 * 	  The following example illustrates a Style 3 element with
 * 	  more complex parameter types. The method
 * 	  foobar(char s, int i, int[] iar, mypackage.MyClass mycl,
 * 	  mypackage.MyClass[][] myclaar) would be specified as:
 * 	  <method>
 * 	      <ejb-name>EmployeeService</ejb-name>
 * 	      <method-name>foobar</method-name>
 * 	      <method-params>
 * 		  <method-param>char</method-param>
 * 		  <method-param>int</method-param>
 * 		  <method-param>int[]</method-param>
 * 		  <method-param>mypackage.MyClass</method-param>
 * 		  <method-param>mypackage.MyClass[][]</method-param>
 * 	      </method-params>
 * 	  </method>
 * 	  The optional method-intf element can be used when it becomes
 * 	  necessary to differentiate between a method that is multiply
 * 	  defined across the enterprise bean's business, home, component,
 *           and/or web service endpoint interfaces with the same name and
 * 	  signature. However, if the same method is a method of both the
 *           local business interface, and the local component interface,
 *           the same attribute applies to the method for both interfaces.
 *           Likewise, if the same method is a method of both the remote
 *           business interface and the remote component interface, the same
 *           attribute applies to the method for both interfaces.
 * 	  For example, the method element
 * 	  <method>
 * 	      <ejb-name>EmployeeService</ejb-name>
 * 	      <method-intf>Remote</method-intf>
 * 	      <method-name>create</method-name>
 * 	      <method-params>
 * 		  <method-param>java.lang.String</method-param>
 * 		  <method-param>java.lang.String</method-param>
 * 	      </method-params>
 * 	  </method>
 * 	  can be used to differentiate the create(String, String)
 * 	  method defined in the remote interface from the
 * 	  create(String, String) method defined in the remote home
 * 	  interface, which would be defined as
 * 	  <method>
 * 	      <ejb-name>EmployeeService</ejb-name>
 * 	      <method-intf>Home</method-intf>
 * 	      <method-name>create</method-name>
 * 	      <method-params>
 * 		  <method-param>java.lang.String</method-param>
 * 		  <method-param>java.lang.String</method-param>
 * 	      </method-params>
 * 	  </method>
 * 	  and the create method that is defined in the local home
 * 	  interface which would be defined as
 * 	  <method>
 * 	      <ejb-name>EmployeeService</ejb-name>
 * 	      <method-intf>LocalHome</method-intf>
 * 	      <method-name>create</method-name>
 * 	      <method-params>
 * 		  <method-param>java.lang.String</method-param>
 * 		  <method-param>java.lang.String</method-param>
 * 	      </method-params>
 * 	  </method>
 * 	  The method-intf element can be used with all three Styles
 * 	  of the method element usage. For example, the following
 * 	  method element example could be used to refer to all the
 * 	  methods of the EmployeeService bean's remote home interface
 *           and the remote business interface.
 * 	  <method>
 * 	      <ejb-name>EmployeeService</ejb-name>
 * 	      <method-intf>Home</method-intf>
 * 	      <method-name>*</method-name>
 * 	  </method>
 * 	  
 * </pre>
 */
public interface Method extends JavaeeDomModelElement {

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
	@NotNull
        @SubTag("ejb-name")
        @Convert(EjbResolveConverter.class)
        GenericDomValue<EnterpriseBean> getEnterpriseBean();


	/**
	 * Returns the value of the method-intf child.
	 * @return the value of the method-intf child.
	 */
	GenericDomValue<MethodIntf> getMethodIntf();


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
	@NotNull
        @SubTag("method-name")
        @Convert(EjbMethodResolveConverter.class)
        GenericDomValue<PsiMethod> getMethod();


	/**
	 * Returns the value of the method-params child.
	 * @return the value of the method-params child.
	 */
	MethodParams getMethodParams();


}
