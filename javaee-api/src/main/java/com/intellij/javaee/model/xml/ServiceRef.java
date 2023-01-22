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

import com.intellij.java.language.psi.PsiClass;
import consulo.xml.util.xml.GenericDomValue;

import javax.annotation.Nonnull;
import java.util.List;

/**
 * http://java.sun.com/xml/ns/javaee:service-refType interface.
 * <pre>
 * <h3>Type http://java.sun.com/xml/ns/javaee:service-refType documentation</h3>
 * The service-ref element declares a reference to a Web
 * 	service. It contains optional description, display name and
 * 	icons, a declaration of the required Service interface,
 * 	an optional WSDL document location, an optional set
 * 	of JAX-RPC mappings, an optional QName for the service element,
 * 	an optional set of Service Endpoint Interfaces to be resolved
 * 	by the container to a WSDL port, and an optional set of handlers.
 * </pre>
 */
public interface ServiceRef extends JavaeeDomModelElement, DescriptionGroup, ResourceGroup, com.intellij.javaee.model.common.ServiceRef {

	/**
	 * Returns the value of the service-ref-name child.
	 * <pre>
	 * <h3>Element http://java.sun.com/xml/ns/javaee:service-ref-name documentation</h3>
	 * The service-ref-name element declares logical name that the
	 * 	    components in the module use to look up the Web service. It
	 * 	    is recommended that all service reference names start with
	 * 	    "service/".
	 * </pre>
	 * <pre>
	 * <h3>Type http://java.sun.com/xml/ns/javaee:jndi-nameType documentation</h3>
	 * The jndi-nameType type designates a JNDI name in the
	 * 	Deployment Component's environment and is relative to the
	 * 	java:comp/env context.  A JNDI name must be unique within the
	 * 	Deployment Component.
	 * </pre>
	 * @return the value of the service-ref-name child.
	 */
	@Nonnull
	GenericDomValue<String> getServiceRefName();


	/**
	 * Returns the value of the service-interface child.
	 * <pre>
	 * <h3>Element http://java.sun.com/xml/ns/javaee:service-interface documentation</h3>
	 * The service-interface element declares the fully qualified class
	 * 	    name of the JAX-RPC Service interface the client depends on.
	 * 	    In most cases the value will be javax.xml.rpc.Service.  A JAX-RPC
	 * 	    generated Service Interface class may also be specified.
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
	 * @return the value of the service-interface child.
	 */
	@Nonnull
	GenericDomValue<PsiClass> getServiceInterface();


	/**
	 * Returns the value of the service-ref-type child.
	 * <pre>
	 * <h3>Element http://java.sun.com/xml/ns/javaee:service-ref-type documentation</h3>
	 * The service-ref-type element declares the type of the service-ref
	 * 	    element that is injected or returned when a JNDI lookup is done.
	 * 	    This must be either a fully qualified name of Service class or
	 * 	    the fully qualified name of service endpoint interface class.
	 * 	    This is only used with JAX-WS runtime where the corresponding
	 * 	    @WebServiceRef annotation can be used to denote both a Service
	 * 	    or a Port.
	 * 	    If this is not specified, then the type of service-ref element
	 * 	    that is injected or returned when a JNDI lookup is done is
	 * 	    always a Service interface/class.
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
	 * @return the value of the service-ref-type child.
	 */
	GenericDomValue<PsiClass> getServiceRefType();


	/**
	 * Returns the value of the wsdl-file child.
	 * <pre>
	 * <h3>Element http://java.sun.com/xml/ns/javaee:wsdl-file documentation</h3>
	 * The wsdl-file element contains the URI location of a WSDL
	 * 	    file. The location is relative to the root of the module.
	 * </pre>
	 * <pre>
	 * <h3>Type http://java.sun.com/xml/ns/javaee:xsdAnyURIType documentation</h3>
	 * This type adds an "id" attribute to xsd:anyURI.
	 * </pre>
	 * @return the value of the wsdl-file child.
	 */
	GenericDomValue<String> getWsdlFile();


	/**
	 * Returns the value of the jaxrpc-mapping-file child.
	 * <pre>
	 * <h3>Element http://java.sun.com/xml/ns/javaee:jaxrpc-mapping-file documentation</h3>
	 * The jaxrpc-mapping-file element contains the name of a file that
	 * 	    describes the JAX-RPC mapping between the Java interaces used by
	 * 	    the application and the WSDL description in the wsdl-file.  The
	 * 	    file name is a relative path within the module file.
	 * 	    This is not required when JAX-WS based runtime is used.
	 * </pre>
	 * <pre>
	 * <h3>Type http://java.sun.com/xml/ns/javaee:pathType documentation</h3>
	 * The elements that use this type designate either a relative
	 * 	path or an absolute path starting with a "/".
	 * 	In elements that specify a pathname to a file within the
	 * 	same Deployment File, relative filenames (i.e., those not
	 * 	starting with "/") are considered relative to the root of
	 * 	the Deployment File's namespace.  Absolute filenames (i.e.,
	 * 	those starting with "/") also specify names in the root of
	 * 	the Deployment File's namespace.  In general, relative names
	 * 	are preferred.  The exception is .war files where absolute
	 * 	names are preferred for consistency with the Servlet API.
	 * </pre>
	 * @return the value of the jaxrpc-mapping-file child.
	 */
	GenericDomValue<String> getJaxrpcMappingFile();


	/**
	 * Returns the value of the service-qname child.
	 * <pre>
	 * <h3>Element http://java.sun.com/xml/ns/javaee:service-qname documentation</h3>
	 * The service-qname element declares the specific WSDL service
	 * 	    element that is being refered to.  It is not specified if no
	 * 	    wsdl-file is declared.
	 * </pre>
	 * <pre>
	 * <h3>Type http://java.sun.com/xml/ns/javaee:xsdQNameType documentation</h3>
	 * This type adds an "id" attribute to xsd:QName.
	 * </pre>
	 * @return the value of the service-qname child.
	 */
	GenericDomValue<String> getServiceQname();


	/**
	 * Returns the list of port-component-ref children.
	 * <pre>
	 * <h3>Element http://java.sun.com/xml/ns/javaee:port-component-ref documentation</h3>
	 * The port-component-ref element declares a client dependency
	 * 	    on the container for resolving a Service Endpoint Interface
	 * 	    to a WSDL port. It optionally associates the Service Endpoint
	 * 	    Interface with a particular port-component. This is only used
	 * 	    by the container for a Service.getPort(Class) method call.
	 * </pre>
	 * @return the list of port-component-ref children.
	 */
	List<PortComponentRef> getPortComponentRefs();
	/**
	 * Adds new child to the list of port-component-ref children.
	 * @return created child
	 */
	PortComponentRef addPortComponentRef();


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


	/**
	 * Returns the list of handler children.
	 * <pre>
	 * <h3>Element http://java.sun.com/xml/ns/javaee:handler documentation</h3>
	 * Declares the handler for a port-component. Handlers can
	 * 		access the init-param name/value pairs using the
	 * 		HandlerInfo interface. If port-name is not specified, the
	 * 		handler is assumed to be associated with all ports of the
	 * 		service.
	 * 		To be used with JAX-RPC based runtime only.
	 * </pre>
	 * @return the list of handler children.
	 */
	List<ServiceRef_handler> getHandlers();
	/**
	 * Adds new child to the list of handler children.
	 * @return created child
	 */
	ServiceRef_handler addHandler();


	/**
	 * Returns the value of the handler-chains child.
	 * <pre>
	 * <h3>Element http://java.sun.com/xml/ns/javaee:handler-chains documentation</h3>
	 * To be used with JAX-WS based runtime only.
	 * </pre>
	 * @return the value of the handler-chains child.
	 */
	ServiceRef_handlerChains getHandlerChains();


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


}
