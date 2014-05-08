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

import com.intellij.javaee.model.xml.JavaeeDomModelElement;
import com.intellij.javaee.model.xml.ServiceRef_handler;
import com.intellij.util.xml.GenericDomValue;
import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * http://java.sun.com/xml/ns/javaee:service-ref_handler-chainType interface.
 * <pre>
 * <h3>Type http://java.sun.com/xml/ns/javaee:service-ref_handler-chainType documentation</h3>
 * The handler-chain element defines the handlerchain.
 *       Handlerchain can be defined such that the handlers in the
 *       handlerchain operate,all ports of a service, on a specific
 *       port or on a list of protocol-bindings. The choice of elements
 *       service-name-pattern, port-name-pattern and protocol-bindings
 *       are used to specify whether the handlers in handler-chain are
 *       for a service, port or protocol binding. If none of these
 *       choices are specified with the handler-chain element then the
 *       handlers specified in the handler-chain will be applied on
 *       everything.
 * </pre>
 */
public interface ServiceRef_handlerChain extends JavaeeDomModelElement {

	/**
	 * Returns the list of handler children.
	 * @return the list of handler children.
	 */
	@NotNull
	List<ServiceRef_handler> getHandlers();
	/**
	 * Adds new child to the list of handler children.
	 * @return created child
	 */
	ServiceRef_handler addHandler();


	/**
	 * Returns the value of the service-name-pattern child.
	 * @return the value of the service-name-pattern child.
	 */
	@NotNull
	GenericDomValue<String> getServiceNamePattern();


	/**
	 * Returns the value of the port-name-pattern child.
	 * @return the value of the port-name-pattern child.
	 */
	@NotNull
	GenericDomValue<String> getPortNamePattern();


	/**
	 * Returns the value of the protocol-bindings child.
	 * @return the value of the protocol-bindings child.
	 */
	@NotNull
	GenericDomValue<String> getProtocolBindings();


}
