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

// Generated on Thu Dec 22 19:27:11 MSK 2005
// DTD/Schema  :    http://java.sun.com/xml/ns/javaee

package com.intellij.javaee.model.enums;

/**
 * http://java.sun.com/xml/ns/javaee:method-intfType enumeration.
 * <pre>
 * <h3>Enumeration http://java.sun.com/xml/ns/javaee:method-intfType documentation</h3>
 * The method-intf element allows a method element to
 * 	differentiate between the methods with the same name and
 * 	signature that are multiply defined across the home and
 * 	component interfaces (e.g, in both an enterprise bean's
 * 	remote and local interfaces or in both an enterprise bean's
 * 	home and remote interfaces, etc.); the component and web
 * 	service endpoint interfaces, and so on.
 * 	Local applies to the local component interface, local business
 *         interfaces, and the no-interface view.
 * 	Remote applies to both remote component interface and the remote
 * 	business interfaces.
 * 	ServiceEndpoint refers to methods exposed through a web service
 * 	endpoint.
 * 	Timer refers to the bean's timeout callback methods.
 * 	MessageEndpoint refers to the methods of a message-driven bean's
 * 	message-listener interface.
 * 	The method-intf element must be one of the following:
 * 	    Home
 * 	    Remote
 * 	    LocalHome
 * 	    Local
 * 	    ServiceEndpoint
 * 	    Timer
 *             MessageEndpoint
 * </pre>
 */
public enum MethodIntf implements com.intellij.util.xml.NamedEnum {
	HOME ("Home"),
	LOCAL ("Local"),
	LOCAL_HOME ("LocalHome"),
	MESSAGE_ENDPOINT ("MessageEndpoint"),
	REMOTE ("Remote"),
	SERVICE_ENDPOINT ("ServiceEndpoint"),
	TIMER ("Timer");

	private final String value;
	private MethodIntf(String value) { this.value = value; }
	public String getValue() { return value; }

}
