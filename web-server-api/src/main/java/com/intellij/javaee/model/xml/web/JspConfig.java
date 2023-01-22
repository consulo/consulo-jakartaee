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

import com.intellij.javaee.model.xml.JavaeeDomModelElement;
import consulo.xml.util.xml.Stubbed;

import java.util.List;

/**
 * http://java.sun.com/xml/ns/javaee:jsp-configType interface.
 * <pre>
 * <h3>Type http://java.sun.com/xml/ns/javaee:jsp-configType documentation</h3>
 * The jsp-configType is used to provide global configuration
 * 	information for the JSP files in a web application. It has
 * 	two subelements, taglib and jsp-property-group.
 * </pre>
 */
public interface JspConfig extends JavaeeDomModelElement
{

	/**
	 * Returns the list of taglib children.
	 *
	 * @return the list of taglib children.
	 */
	List<Taglib> getTaglibs();

	/**
	 * Adds new child to the list of taglib children.
	 *
	 * @return created child
	 */
	Taglib addTaglib();


	/**
	 * Returns the list of jsp-property-group children.
	 *
	 * @return the list of jsp-property-group children.
	 */
	@Stubbed
	List<JspPropertyGroup> getJspPropertyGroups();

	/**
	 * Adds new child to the list of jsp-property-group children.
	 *
	 * @return created child
	 */
	JspPropertyGroup addJspPropertyGroup();
}
