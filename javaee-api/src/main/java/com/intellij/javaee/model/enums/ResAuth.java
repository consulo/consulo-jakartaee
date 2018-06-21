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
 * http://java.sun.com/xml/ns/javaee:res-authType enumeration.
 * <pre>
 * <h3>Enumeration http://java.sun.com/xml/ns/javaee:res-authType documentation</h3>
 * The res-authType specifies whether the Deployment Component
 * 	code signs on programmatically to the resource manager, or
 * 	whether the Container will sign on to the resource manager
 * 	on behalf of the Deployment Component. In the latter case,
 * 	the Container uses information that is supplied by the
 * 	Deployer.
 * 	The value must be one of the two following:
 * 	    Application
 * 	    Container
 * </pre>
 */
public enum ResAuth implements com.intellij.util.xml.NamedEnum {
	APPLICATION ("Application"),
	CONTAINER ("Container");

	private final String value;
	private ResAuth(String value) { this.value = value; }
	public String getValue() { return value; }

}
