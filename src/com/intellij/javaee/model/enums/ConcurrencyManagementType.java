/*
 * Copyright 2000-2009 JetBrains s.r.o.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

// Generated on Tue Apr 28 15:56:22 MSD 2009
// DTD/Schema  :    http://java.sun.com/xml/ns/javaee

package com.intellij.javaee.model.enums;

/**
 * http://java.sun.com/xml/ns/javaee:concurrency-management-typeType enumeration.
 * <pre>
 * <h3>Enumeration http://java.sun.com/xml/ns/javaee:concurrency-management-typeType documentation</h3>
 * The concurrency-management-typeType specifies the way concurrency
 * 	is managed for a singleton or stateful session bean.
 * 	The concurrency management type must be one of the following:
 * 	    Bean
 * 	    Container
 * 	    NotAllowed
 * 	Bean managed concurrency can only be specified for a singleton bean.
 * </pre>
 */
public enum ConcurrencyManagementType implements com.intellij.util.xml.NamedEnum {
	BEAN ("Bean"),
	CONTAINER ("Container"),
	NOT_ALLOWED ("NotAllowed");

	private final String value;
	private ConcurrencyManagementType(String value) { this.value = value; }
	public String getValue() { return value; }

}
