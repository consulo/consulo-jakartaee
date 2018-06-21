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
import com.intellij.javaee.model.xml.ServiceRef_handlerChain;

import java.util.List;

/**
 * http://java.sun.com/xml/ns/javaee:service-ref_handler-chainsType interface.
 * <pre>
 * <h3>Type http://java.sun.com/xml/ns/javaee:service-ref_handler-chainsType documentation</h3>
 * The handler-chains element defines the handlerchains associated with this
 *       service or service endpoint.
 * </pre>
 */
public interface ServiceRef_handlerChains extends JavaeeDomModelElement {

	/**
	 * Returns the list of handler-chain children.
	 * @return the list of handler-chain children.
	 */
	List<ServiceRef_handlerChain> getHandlerChains();
	/**
	 * Adds new child to the list of handler-chain children.
	 * @return created child
	 */
	ServiceRef_handlerChain addHandlerChain();


}
