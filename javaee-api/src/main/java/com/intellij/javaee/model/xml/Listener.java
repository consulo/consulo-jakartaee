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

import com.intellij.java.impl.util.xml.ClassTemplate;
import com.intellij.java.language.psi.PsiClass;
import com.intellij.javaee.J2EEFileTemplateNames;
import com.intellij.javaee.model.CommonListener;
import consulo.xml.util.xml.GenericDomValue;
import consulo.xml.util.xml.NameValue;
import consulo.xml.util.xml.Stubbed;

import javax.annotation.Nonnull;
import java.util.List;

/**
 * http://java.sun.com/xml/ns/javaee:listenerType interface.
 * <pre>
 * <h3>Type http://java.sun.com/xml/ns/javaee:listenerType documentation</h3>
 * The listenerType indicates the deployment properties for a web
 * 	application listener bean.
 * </pre>
 */
//@Presentation(icon = "AllIcons.Nodes.Weblistener")
public interface Listener extends CommonListener, JavaeeDomModelElement, DescriptionGroup {

	/**
	 * Returns the value of the listener-class child.
	 * <pre>
	 * <h3>Element http://java.sun.com/xml/ns/javaee:listener-class documentation</h3>
	 * The listener-class element declares a class in the
	 * 	    application must be registered as a web
	 * 	    application listener bean. The value is the fully
	 * 	    qualified classname of the listener class.
	 * </pre>
	 * <pre>
	 * <h3>Type http://java.sun.com/xml/ns/javaee:fully-qualified-classType documentation</h3>
	 * The elements that use this type designate the name of a
	 * 	Java class or interface.  The name is in the form of a
	 * 	"binary name", as defined in the JLS.  This is the form
	 * 	of name used in Class.forName().  Tools that need the
	 * 	canonical name (the name used in source code) will need
	 * 	to convert this binary name to the canonical name.
	 * </pre>
	 * @return the value of the listener-class child.
	 */
	@Nonnull
        @ClassTemplate(J2EEFileTemplateNames.LISTENER_CLASS_TEMPLATE)
        @NameValue
        @Stubbed
        GenericDomValue<PsiClass> getListenerClass();


	/**
	 * Returns the list of description children.
	 * @return the list of description children.
	 */
	List<Description> getDescriptions();
	/**
	 * Adds new child to the list of description children.
	 * @return created child
	 */
	Description addDescription();


	/**
	 * Returns the list of display-name children.
	 * @return the list of display-name children.
	 */
	List<DisplayName> getDisplayNames();
	/**
	 * Adds new child to the list of display-name children.
	 * @return created child
	 */
	DisplayName addDisplayName();


	/**
	 * Returns the list of icon children.
	 * @return the list of icon children.
	 */
	List<Icon> getIcons();
	/**
	 * Adds new child to the list of icon children.
	 * @return created child
	 */
	Icon addIcon();


}
