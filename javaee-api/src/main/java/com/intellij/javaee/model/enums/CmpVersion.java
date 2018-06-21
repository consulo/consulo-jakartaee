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
 * http://java.sun.com/xml/ns/javaee:cmp-versionType enumeration.
 * <pre>
 * <h3>Enumeration http://java.sun.com/xml/ns/javaee:cmp-versionType documentation</h3>
 * The cmp-versionType specifies the version of an entity bean
 * 	with container-managed persistence. It is used by
 * 	cmp-version elements.
 * 	The value must be one of the two following:
 * 	    1.x
 * 	    2.x
 * </pre>
 */
public enum CmpVersion implements com.intellij.util.xml.NamedEnum {
	CmpVersion_1_X ("1.x"),
	CmpVersion_2_X ("2.x");

	private final String value;
	private CmpVersion(String value) { this.value = value; }
	public String getValue() { return value; }

}
