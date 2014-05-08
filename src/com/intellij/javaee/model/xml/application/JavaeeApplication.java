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

package com.intellij.javaee.model.xml.application;

import com.intellij.javaee.model.common.JavaeeCommonConstants;
import com.intellij.javaee.model.xml.*;
import com.intellij.util.xml.GenericAttributeValue;
import com.intellij.util.xml.GenericDomValue;
import com.intellij.util.xml.Namespace;
import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * http://java.sun.com/xml/ns/javaee:applicationType interface.
 * <pre>
 * <h3>Type http://java.sun.com/xml/ns/javaee:applicationType documentation</h3>
 * The applicationType defines the structure of the
 * 	application.
 * </pre>
 */
@Namespace(JavaeeCommonConstants.APP_NAMESPACE_KEY)
public interface JavaeeApplication extends JavaeeDomModelElement, DescriptionGroup {

	/**
	 * Returns the value of the version child.
	 * <pre>
	 * <h3>Attribute null:version documentation</h3>
	 * The required value for the version is 5.
	 * </pre>
	 * @return the value of the version child.
	 */
	@NotNull
	GenericAttributeValue<String> getVersion();


	/**
	 * Returns the list of module children.
	 * <pre>
	 * <h3>Element http://java.sun.com/xml/ns/javaee:module documentation</h3>
	 * The application deployment descriptor must have one
	 * 	    module element for each Java EE module in the
	 * 	    application package. A module element is defined
	 * 	    by moduleType definition.
	 * </pre>
	 * @return the list of module children.
	 */
	@NotNull
	List<JavaeeModule> getModules();
	/**
	 * Adds new child to the list of module children.
	 * @return created child
	 */
	JavaeeModule addModule();

  /**
	 * Returns the list of security-role children.
	 * @return the list of security-role children.
	 */
	List<SecurityRole> getSecurityRoles();
	/**
	 * Adds new child to the list of security-role children.
	 * @return created child
	 */
	SecurityRole addSecurityRole();


	/**
	 * Returns the value of the library-directory child.
	 * <pre>
	 * <h3>Element http://java.sun.com/xml/ns/javaee:library-directory documentation</h3>
	 * The library-directory element specifies the pathname
	 * 	    of a directory within the application package, relative
	 * 	    to the top level of the application package.  All files
	 * 	    named "*.jar" in this directory must be made available
	 * 	    in the class path of all components included in this
	 * 	    application package.  If this element isn't specified,
	 * 	    the directory named "lib" is searched.  An empty element
	 * 	    may be used to disable searching.
	 * </pre>
	 * <pre>
	 * <h3>Type http://java.sun.com/xml/ns/javaee:pathType documentation</h3>
	 * The elements that use this type designate either a relative
	 * 	path or an absolute path starting with a "/".
	 * 	In elements that specify a pathname to a file within the
	 * 	same Deployment File, relative filenames (i.e., those not
	 * 	starting with "/") are considered relative to the root of
	 * 	the Deployment File's namespace.  Absolute filenames (i.e.,
	 * 	those starting with "/") also specify names in the root of
	 * 	the Deployment File's namespace.  In general, relative names
	 * 	are preferred.  The exception is .war files where absolute
	 * 	names are preferred for consistency with the Servlet API.
	 * </pre>
	 * @return the value of the library-directory child.
	 */
	GenericDomValue<String> getLibraryDirectory();


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

  /**
   * since version 6
   */
  GenericDomValue<String> getApplicationName();
}
