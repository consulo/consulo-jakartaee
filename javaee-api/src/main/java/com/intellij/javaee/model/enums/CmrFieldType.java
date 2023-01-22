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

import com.intellij.java.language.psi.CommonClassNames;

/**
 * http://java.sun.com/xml/ns/javaee:cmr-field-typeType enumeration.
 * <pre>
 * <h3>Enumeration http://java.sun.com/xml/ns/javaee:cmr-field-typeType documentation</h3>
 * The cmr-field-type element specifies the class of a
 * 	collection-valued logical relationship field in the entity
 * 	bean class. The value of an element using cmr-field-typeType
 * 	must be either: java.util.Collection or java.util.Set.
 * </pre>
 */
public enum CmrFieldType implements consulo.xml.util.xml.NamedEnum {
	JAVA_UTIL_COLLECTION ("java.util.Collection"),
	JAVA_UTIL_SET (CommonClassNames.JAVA_UTIL_SET);

	private final String value;
	private CmrFieldType(String value) { this.value = value; }
	public String getValue() { return value; }

}
