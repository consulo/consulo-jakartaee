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

package com.intellij.javaee.model.xml.web;

import com.intellij.ide.presentation.Presentation;
import com.intellij.jam.view.DeleteHandler;
import com.intellij.javaee.J2EEFileTemplateNames;
import com.intellij.javaee.model.xml.*;
import com.intellij.javaee.model.xml.web.converters.WebDeleteHandler;
import com.intellij.javaee.web.CommonFilter;
import com.intellij.psi.PsiClass;
import com.intellij.util.xml.*;
import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * http://java.sun.com/xml/ns/javaee:filterType interface.
 * <pre>
 * <h3>Type http://java.sun.com/xml/ns/javaee:filterType documentation</h3>
 * The filterType is used to declare a filter in the web
 * 	application. The filter is mapped to either a servlet or a
 * 	URL pattern in the filter-mapping element, using the
 * 	filter-name value to reference. Filters can access the
 * 	initialization parameters declared in the deployment
 * 	descriptor at runtime via the FilterConfig interface.
 * 	Used in: web-app
 * </pre>
 */
@Presentation(icon = "AllIcons.General.Filter")
@DeleteHandler(handlerClass = WebDeleteHandler.class)
public interface Filter extends CommonFilter, JavaeeDomModelElement, DescriptionGroup {

	/**
	 * Returns the value of the filter-name child.
	 * <pre>
	 * <h3>Type http://java.sun.com/xml/ns/javaee:filter-nameType documentation</h3>
	 * The logical name of the filter is declare
	 * 	by using filter-nameType. This name is used to map the
	 * 	filter.  Each filter name is unique within the web
	 * 	application.
	 * 	Used in: filter, filter-mapping
	 * </pre>
	 * @return the value of the filter-name child.
	 */
	@NotNull
        @NameValue
        @Required
        @Convert(ClassMappingNameConverter.class)
        @Stubbed
        GenericDomValue<String> getFilterName();


	/**
	 * Returns the value of the filter-class child.
	 * <pre>
	 * <h3>Element http://java.sun.com/xml/ns/javaee:filter-class documentation</h3>
	 * The fully qualified classname of the filter.
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
	 * @return the value of the filter-class child.
	 */
        @ExtendClass("javax.servlet.Filter")
        @ClassTemplate(J2EEFileTemplateNames.FILTER_CLASS_TEMPLATE)
        @NotNull
        @Required
        @MappingClass
        @Stubbed
	GenericDomValue<PsiClass> getFilterClass();


	/**
	 * Returns the list of init-param children.
	 * <pre>
	 * <h3>Element http://java.sun.com/xml/ns/javaee:init-param documentation</h3>
	 * The init-param element contains a name/value pair as
	 * 	    an initialization param of a servlet filter
	 * </pre>
	 * @return the list of init-param children.
	 */
        @Stubbed
	List<InitParam> getInitParams();
	/**
	 * Adds new child to the list of init-param children.
	 * @return created child
	 */
	InitParam addInitParam();


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

  @NotNull
  @Override
  WebApp getParent();

}
