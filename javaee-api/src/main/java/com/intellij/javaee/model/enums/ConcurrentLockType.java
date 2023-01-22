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
 * http://java.sun.com/xml/ns/javaee:concurrent-lock-typeType enumeration.
 * <pre>
 * <h3>Enumeration http://java.sun.com/xml/ns/javaee:concurrent-lock-typeType documentation</h3>
 * The concurrent-lock-typeType specifies how the container must
 * 	manage concurrent access to a method of a Singleton bean
 * 	with container-managed concurrency.
 * 	The container managed concurrency lock type must be one
 * 	of the following :
 * 	    Read
 * 	    Write
 * </pre>
 */
public enum ConcurrentLockType implements consulo.xml.util.xml.NamedEnum {
	READ ("Read"),
	WRITE ("Write");

	private final String value;
	private ConcurrentLockType(String value) { this.value = value; }
	public String getValue() { return value; }

}
