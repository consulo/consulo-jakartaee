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

import com.intellij.javaee.model.EjbAsteriskResolveConverter;
import com.intellij.javaee.model.common.ejb.EnterpriseBean;
import com.intellij.javaee.model.xml.Description;
import com.intellij.javaee.model.xml.JavaeeDomModelElement;
import com.intellij.psi.PsiClass;
import com.intellij.util.xml.Convert;
import com.intellij.util.xml.GenericDomValue;
import com.intellij.util.xml.SubTag;
import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * http://java.sun.com/xml/ns/javaee:interceptor-bindingType interface.
 * <pre>
 * <h3>Type http://java.sun.com/xml/ns/javaee:interceptor-bindingType documentation</h3>
 * The interceptor-bindingType element describes the binding of
 *         interceptor classes to beans within the ejb-jar.
 *         It consists of :
 *           - An optional description.
 *           - The name of an ejb within the ejb-jar or the wildcard value "*",
 *             which is used to define interceptors that are bound to all
 *             beans in the ejb-jar.
 *           - A list of interceptor classes that are bound to the contents of
 *             the ejb-name element or a specification of the total ordering
 *             over the interceptors defined for the given level and above.
 *           - An optional exclude-default-interceptors element.  If set to true,
 *             specifies that default interceptors are not to be applied to
 *             a bean-class and/or business method.
 *           - An optional exclude-class-interceptors element.  If set to true,
 *             specifies that class interceptors are not to be applied to
 *             a business method.
 *           - An optional set of method elements for describing the name/params
 *             of a method-level interceptor.
 *          Interceptors bound to all classes using the wildcard syntax
 *          "*" are default interceptors for the components in the ejb-jar.
 *          In addition, interceptors may be bound at the level of the bean
 *          class (class-level interceptors) or business methods (method-level
 *          interceptors ).
 *          The binding of interceptors to classes is additive.  If interceptors
 *          are bound at the class-level and/or default-level as well as the
 *          method-level, both class-level and/or default-level as well as
 *          method-level will apply.
 *          There are four possible styles of the interceptor element syntax :
 *          1.
 *          <interceptor-binding>
 *            <ejb-name>*</ejb-name>
 *            <interceptor-class>INTERCEPTOR</interceptor-class>
 *          </interceptor-binding>
 *          Specifying the ejb-name as the wildcard value "*" designates
 *          default interceptors (interceptors that apply to all session and
 *          message-driven beans contained in the ejb-jar).
 *          2.
 *          <interceptor-binding>
 *            <ejb-name>EJBNAME</ejb-name>
 *            <interceptor-class>INTERCEPTOR</interceptor-class>
 *          </interceptor-binding>
 *          This style is used to refer to interceptors associated with the
 *          specified enterprise bean(class-level interceptors).
 *          3.
 *          <interceptor-binding>
 *            <ejb-name>EJBNAME</ejb-name>
 *            <interceptor-class>INTERCEPTOR</interceptor-class>
 *            <method>
 *              <method-name>METHOD</method-name>
 *            </method>
 *          </interceptor-binding>
 *          This style is used to associate a method-level interceptor with
 *          the specified enterprise bean.  If there are multiple methods
 *          with the same overloaded name, the element of this style refers
 *          to all the methods with the overloaded name.  Method-level
 *          interceptors can only be associated with business methods of the
 *          bean class.   Note that the wildcard value "*" cannot be used
 *          to specify method-level interceptors.
 *          4.
 *          <interceptor-binding>
 *            <ejb-name>EJBNAME</ejb-name>
 *            <interceptor-class>INTERCEPTOR</interceptor-class>
 *            <method>
 *              <method-name>METHOD</method-name>
 *              <method-params>
 *                <method-param>PARAM-1</method-param>
 *                <method-param>PARAM-2</method-param>
 *                ...
 *                <method-param>PARAM-N</method-param>
 *              </method-params>
 *            </method>
 *          </interceptor-binding>
 *          This style is used to associate a method-level interceptor with
 *          the specified method of the specified enterprise bean.  This
 *          style is used to refer to a single method within a set of methods
 *          with an overloaded name.  The values PARAM-1 through PARAM-N
 *          are the fully-qualified Java types of the method's input parameters
 *          (if the method has no input arguments, the method-params element
 *          contains no method-param elements). Arrays are specified by the
 *          array element's type, followed by one or more pair of square
 *          brackets (e.g. int[][]).
 * </pre>
 */
public interface InterceptorBinding extends JavaeeDomModelElement {

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
	 * <h3>Type http://java.sun.com/xml/ns/javaee:string documentation</h3>
	 * This is a special string datatype that is defined by Java EE as
	 * 	a base type for defining collapsed strings. When schemas
	 * 	require trailing/leading space elimination as well as
	 * 	collapsing the existing whitespace, this base type may be
	 * 	used.
	 * </pre>
	 * @return the value of the ejb-name child.
	 */
	@NotNull
        @SubTag("ejb-name")
        @Convert(EjbAsteriskResolveConverter.class)
        GenericDomValue<EnterpriseBean> getEnterpriseBean();


	/**
	 * Returns the value of the exclude-default-interceptors child.
	 * <pre>
	 * <h3>Type http://java.sun.com/xml/ns/javaee:true-falseType documentation</h3>
	 * This simple type designates a boolean with only two
	 * 	permissible values
	 * 	- true
	 * 	- false
	 * </pre>
	 * @return the value of the exclude-default-interceptors child.
	 */
	GenericDomValue<Boolean> getExcludeDefaultInterceptors();


	/**
	 * Returns the value of the exclude-class-interceptors child.
	 * <pre>
	 * <h3>Type http://java.sun.com/xml/ns/javaee:true-falseType documentation</h3>
	 * This simple type designates a boolean with only two
	 * 	permissible values
	 * 	- true
	 * 	- false
	 * </pre>
	 * @return the value of the exclude-class-interceptors child.
	 */
	GenericDomValue<Boolean> getExcludeClassInterceptors();


	/**
	 * Returns the value of the method child.
	 * @return the value of the method child.
	 */
	NamedMethod getMethod();


	/**
	 * Returns the list of interceptor-class children.
	 * <pre>
	 * <h3>Type http://java.sun.com/xml/ns/javaee:fully-qualified-classType documentation</h3>
	 * The elements that use this type designate the name of a
	 * 	Java class or interface.  The name is in the form of a
	 * 	"binary name", as defined in the JLS.  This is the form
	 * 	of name used in Class.forName().  Tools that need the
	 * 	canonical name (the name used in source code) will need
	 * 	to convert this binary name to the canonical name.
	 * </pre>
	 * @return the list of interceptor-class children.
	 */
	List<GenericDomValue<PsiClass>> getInterceptorClasses();
	/**
	 * Adds new child to the list of interceptor-class children.
	 * @return created child
	 */
	GenericDomValue<PsiClass> addInterceptorClass();


	/**
	 * Returns the value of the interceptor-order child.
	 * @return the value of the interceptor-order child.
	 */
	@NotNull
	InterceptorOrder getInterceptorOrder();


}
