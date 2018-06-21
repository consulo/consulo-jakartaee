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

// Generated on Tue Feb 14 17:35:30 MSK 2006
// DTD/Schema  :    http://java.sun.com/xml/ns/javaee

package com.intellij.javaee.model.xml;

import com.intellij.psi.PsiClass;
import com.intellij.psi.PsiMember;
import com.intellij.util.xml.GenericDomValue;
import com.intellij.util.xml.Convert;
import com.intellij.javaee.model.EjbInjectionTargetConverter;
import javax.annotation.Nonnull;

/**
 * http://java.sun.com/xml/ns/javaee:injection-targetType interface.
 * <pre>
 * <h3>Type http://java.sun.com/xml/ns/javaee:injection-targetType documentation</h3>
 * An injection target specifies a class and a name within
 * 	that class into which a resource should be injected.
 * 	The injection target class specifies the fully qualified
 * 	class name that is the target of the injection.  The
 * 	Java EE specifications describe which classes can be an
 * 	injection target.
 * 	The injection target name specifies the target within
 * 	the specified class.  The target is first looked for as a
 * 	JavaBeans property name.  If not found, the target is
 * 	looked for as a field name.
 * 	The specified resource will be injected into the target
 * 	during initialization of the class by either calling the
 * 	set method for the target property or by setting a value
 * 	into the named field.
 * </pre>
 */
public interface InjectionTarget extends JavaeeDomModelElement {

	/**
	 * Returns the value of the injection-target-class child.
	 * <pre>
	 * <h3>Type http://java.sun.com/xml/ns/javaee:fully-qualified-classType documentation</h3>
	 * The elements that use this type designate the name of a
	 * 	Java class or interface.  The name is in the form of a
	 * 	"binary name", as defined in the JLS.  This is the form
	 * 	of name used in Class.forName().  Tools that need the
	 * 	canonical name (the name used in source code) will need
	 * 	to convert this binary name to the canonical name.
	 * </pre>
	 * @return the value of the injection-target-class child.
	 */
	@Nonnull
	GenericDomValue<PsiClass> getInjectionTargetClass();


	/**
	 * Returns the value of the injection-target-name child.
	 * <pre>
	 * <h3>Type http://java.sun.com/xml/ns/javaee:java-identifierType documentation</h3>
	 * The java-identifierType defines a Java identifier.
	 * 	The users of this type should further verify that
	 * 	the content does not contain Java reserved keywords.
	 * </pre>
	 * @return the value of the injection-target-name child.
	 */
        @Convert(EjbInjectionTargetConverter.class)
        @com.intellij.util.xml.SubTag("injection-target-name")
        @Nonnull
	GenericDomValue<PsiMember> getInjectionTargetMember();


}
