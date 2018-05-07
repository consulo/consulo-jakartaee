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

package com.intellij.javaee.model.xml;

import com.intellij.psi.PsiClass;
import com.intellij.util.xml.GenericDomValue;
import javax.annotation.Nonnull;

import java.util.List;

/**
 * http://java.sun.com/xml/ns/javaee:service-ref_handlerType interface.
 * <pre>
 * <h3>Type http://java.sun.com/xml/ns/javaee:service-ref_handlerType documentation</h3>
 * Declares the handler for a port-component. Handlers can access the
 * 	init-param name/value pairs using the HandlerInfo interface. If
 * 	port-name is not specified, the handler is assumed to be associated
 * 	with all ports of the service.
 * 	Used in: service-ref
 * </pre>
 */
public interface ServiceRef_handler extends JavaeeDomModelElement, DescriptionGroup, com.intellij.javaee.model.common.ServiceRefHandler {

	/**
	 * Returns the value of the handler-name child.
	 * <pre>
	 * <h3>Element http://java.sun.com/xml/ns/javaee:handler-name documentation</h3>
	 * Defines the name of the handler. The name must be unique
	 * 	    within the module.
	 * </pre>
	 * <pre>
	 * <h3>Type http://java.sun.com/xml/ns/javaee:string documentation</h3>
	 * This is a special string datatype that is defined by Java EE as
	 * 	a base type for defining collapsed strings. When schemas
	 * 	require trailing/leading space elimination as well as
	 * 	collapsing the existing whitespace, this base type may be
	 * 	used.
	 * </pre>
	 * @return the value of the handler-name child.
	 */
	@Nonnull
	GenericDomValue<String> getHandlerName();


	/**
	 * Returns the value of the handler-class child.
	 * <pre>
	 * <h3>Element http://java.sun.com/xml/ns/javaee:handler-class documentation</h3>
	 * Defines a fully qualified class name for the handler
	 * 	    implementation.
	 * </pre>
	 * <pre>
	 * <h3>Type http://java.sun.com/xml/ns/javaee:fully-qualified-classType documentation</h3>
	 * The elements that use this type designate the name of a
	 * 	Java class or interface.  The name is in the form of a
	 * 	"binary name", as defined in the JLS.  This is the form
	 * 	of name used in Class.forName().  Tools that need the
	 * 	canonical name (the name used in source code) will need
	 * 	to convert this binary name to the canonical name.
	 * </pre>
	 * @return the value of the handler-class child.
	 */
	@Nonnull
	GenericDomValue<PsiClass> getHandlerClass();


	/**
	 * Returns the list of init-param children.
	 * @return the list of init-param children.
	 */
	List<ParamValue> getInitParams();
	/**
	 * Adds new child to the list of init-param children.
	 * @return created child
	 */
	ParamValue addInitParam();


	/**
	 * Returns the list of soap-header children.
	 * <pre>
	 * <h3>Element http://java.sun.com/xml/ns/javaee:soap-header documentation</h3>
	 * Defines the QName of a SOAP header that will be processed
	 * 	    by the handler.
	 * </pre>
	 * <pre>
	 * <h3>Type http://java.sun.com/xml/ns/javaee:xsdQNameType documentation</h3>
	 * This type adds an "id" attribute to xsd:QName.
	 * </pre>
	 * @return the list of soap-header children.
	 */
	List<GenericDomValue<String>> getSoapHeaders();
	/**
	 * Adds new child to the list of soap-header children.
	 * @return created child
	 */
	GenericDomValue<String> addSoapHeader();


	/**
	 * Returns the list of soap-role children.
	 * <pre>
	 * <h3>Element http://java.sun.com/xml/ns/javaee:soap-role documentation</h3>
	 * The soap-role element contains a SOAP actor definition that
	 * 	    the Handler will play as a role.
	 * </pre>
	 * <pre>
	 * <h3>Type http://java.sun.com/xml/ns/javaee:string documentation</h3>
	 * This is a special string datatype that is defined by Java EE as
	 * 	a base type for defining collapsed strings. When schemas
	 * 	require trailing/leading space elimination as well as
	 * 	collapsing the existing whitespace, this base type may be
	 * 	used.
	 * </pre>
	 * @return the list of soap-role children.
	 */
	List<GenericDomValue<String>> getSoapRoles();
	/**
	 * Adds new child to the list of soap-role children.
	 * @return created child
	 */
	GenericDomValue<String> addSoapRole();


	/**
	 * Returns the list of port-name children.
	 * <pre>
	 * <h3>Element http://java.sun.com/xml/ns/javaee:port-name documentation</h3>
	 * The port-name element defines the WSDL port-name that a
	 * 	    handler should be associated with.
	 * </pre>
	 * <pre>
	 * <h3>Type http://java.sun.com/xml/ns/javaee:string documentation</h3>
	 * This is a special string datatype that is defined by Java EE as
	 * 	a base type for defining collapsed strings. When schemas
	 * 	require trailing/leading space elimination as well as
	 * 	collapsing the existing whitespace, this base type may be
	 * 	used.
	 * </pre>
	 * @return the list of port-name children.
	 */
	List<GenericDomValue<String>> getPortNames();
	/**
	 * Adds new child to the list of port-name children.
	 * @return created child
	 */
	GenericDomValue<String> addPortName();


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
	 * Returns the list of display-name children.
	 * @return the list of display-name children.
	 */
	List<DisplayName> getDisplayNames();
	/**
	 * Adds new child to the list of display-name children.
	 * @return created child
	 */
	DisplayName addDisplayName();


	/**
	 * Returns the list of icon children.
	 * @return the list of icon children.
	 */
	List<Icon> getIcons();
	/**
	 * Adds new child to the list of icon children.
	 * @return created child
	 */
	Icon addIcon();


}
