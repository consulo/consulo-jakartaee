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

// Generated on Mon Mar 20 14:16:35 MSK 2006
// DTD/Schema  :    http://java.sun.com/xml/ns/javaee

package com.intellij.javaee.model.xml;

import com.intellij.java.language.psi.PsiClass;
import consulo.xml.util.xml.GenericDomValue;

import jakarta.annotation.Nonnull;

/**
 * http://java.sun.com/xml/ns/javaee:port-component-refType interface.
 * <pre>
 * <h3>Type http://java.sun.com/xml/ns/javaee:port-component-refType documentation</h3>
 * The port-component-ref element declares a client dependency
 * 	on the container for resolving a Service Endpoint Interface
 * 	to a WSDL port. It optionally associates the Service Endpoint
 * 	Interface with a particular port-component. This is only used
 * 	by the container for a Service.getPort(Class) method call.
 * </pre>
 */
public interface PortComponentRef extends JavaeeDomModelElement, com.intellij.javaee.model.common.ejb.PortComponentRef {

	/**
	 * Returns the value of the service-endpoint-interface child.
	 * <pre>
	 * <h3>Element http://java.sun.com/xml/ns/javaee:service-endpoint-interface documentation</h3>
	 * The service-endpoint-interface element defines a fully qualified
	 * 	    Java class that represents the Service Endpoint Interface of a
	 * 	    WSDL port.
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
	 * @return the value of the service-endpoint-interface child.
	 */
	@Nonnull
	GenericDomValue<PsiClass> getServiceEndpointInterface();


	/**
	 * Returns the value of the enable-mtom child.
	 * <pre>
	 * <h3>Element http://java.sun.com/xml/ns/javaee:enable-mtom documentation</h3>
	 * Used to enable or disable SOAP MTOM/XOP mechanism on the client
	 * 	    side for a port-component. By default its value is false. If
	 * 	    this element is not specified, default value is assumed.
	 * 	    Not to be specified for JAX-RPC runtime
	 * </pre>
	 * <pre>
	 * <h3>Type http://java.sun.com/xml/ns/javaee:true-falseType documentation</h3>
	 * This simple type designates a boolean with only two
	 * 	permissible values
	 * 	- true
	 * 	- false
	 * </pre>
	 * @return the value of the enable-mtom child.
	 */
	GenericDomValue<Boolean> getEnableMtom();


	/**
	 * Returns the value of the port-component-link child.
	 * <pre>
	 * <h3>Element http://java.sun.com/xml/ns/javaee:port-component-link documentation</h3>
	 * The port-component-link element links a port-component-ref
	 * 	    to a specific port-component required to be made available
	 * 	    by a service reference.
	 * 	    The value of a port-component-link must be the
	 * 	    port-component-name of a port-component in the same module
	 * 	    or another module in the same application unit. The syntax
	 * 	    for specification follows the syntax defined for ejb-link
	 * 	    in the EJB 2.0 specification.
	 * </pre>
	 * <pre>
	 * <h3>Type http://java.sun.com/xml/ns/javaee:string documentation</h3>
	 * This is a special string datatype that is defined by Java EE as
	 * 	a base type for defining collapsed strings. When schemas
	 * 	require trailing/leading space elimination as well as
	 * 	collapsing the existing whitespace, this base type may be
	 * 	used.
	 * </pre>
	 * @return the value of the port-component-link child.
	 */
	GenericDomValue<String> getPortComponentLink();


}
