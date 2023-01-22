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
 * http://java.sun.com/xml/ns/javaee:env-entry-type-valuesType enumeration.
 * <pre>
 * <h3>Enumeration http://java.sun.com/xml/ns/javaee:env-entry-type-valuesType documentation</h3>
 * 	  This type contains the fully-qualified Java type of the
 * 	  environment entry value that is expected by the
 * 	  application's code.
 * 	  The following are the legal values of env-entry-type-valuesType:
 * 	      java.lang.Boolean
 * 	      java.lang.Byte
 * 	      java.lang.Character
 * 	      java.lang.String
 * 	      java.lang.Short
 * 	      java.lang.Integer
 * 	      java.lang.Long
 * 	      java.lang.Float
 * 	      java.lang.Double
 * 	  Example:
 * 	  <env-entry-type>java.lang.Boolean</env-entry-type>
 * 	  
 * </pre>
 */
public enum EnvEntryTypeValues implements consulo.xml.util.xml.NamedEnum {
	JAVA_LANG_BOOLEAN ("java.lang.Boolean"),
	JAVA_LANG_BYTE ("java.lang.Byte"),
	JAVA_LANG_CHARACTER ("java.lang.Character"),
	JAVA_LANG_DOUBLE ("java.lang.Double"),
	JAVA_LANG_FLOAT ("java.lang.Float"),
	JAVA_LANG_INTEGER ("java.lang.Integer"),
	JAVA_LANG_LONG ("java.lang.Long"),
	JAVA_LANG_SHORT ("java.lang.Short"),
	JAVA_LANG_STRING ("java.lang.String");

	private final String value;
	private EnvEntryTypeValues(String value) { this.value = value; }
	public String getValue() { return value; }

}
