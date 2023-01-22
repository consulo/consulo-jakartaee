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

import org.jetbrains.annotations.NonNls;

/**
 * http://java.sun.com/xml/ns/javaee:session-typeType enumeration.
 * <pre>
 * <h3>Enumeration http://java.sun.com/xml/ns/javaee:session-typeType documentation</h3>
 * The session-typeType describes whether the session bean is a
 * 	singleton, stateful or stateless session. It is used by
 * 	session-type elements.
 * 	The value must be one of the three following:
 * 	    Singleton
 * 	    Stateful
 * 	    Stateless
 * </pre>
 */
public enum SessionType implements consulo.xml.util.xml.NamedEnum {
	SINGLETON ("Singleton"),
	STATEFUL ("Stateful"),
	STATELESS ("Stateless");

	private final String value;
	private SessionType(@NonNls String value) { this.value = value; }
	public String getValue() { return value; }

}
