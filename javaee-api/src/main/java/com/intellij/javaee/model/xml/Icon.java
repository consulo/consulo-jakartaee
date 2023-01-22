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

import consulo.xml.util.xml.GenericAttributeValue;
import consulo.xml.util.xml.GenericDomValue;

/**
 * http://java.sun.com/xml/ns/javaee:iconType interface.
 * <pre>
 * <h3>Type http://java.sun.com/xml/ns/javaee:iconType documentation</h3>
 * The icon type contains small-icon and large-icon elements
 * 	that specify the file names for small and large GIF, JPEG,
 * 	or PNG icon images used to represent the parent element in a
 * 	GUI tool.
 * 	The xml:lang attribute defines the language that the
 * 	icon file names are provided in. Its value is "en" (English)
 * 	by default.
 * </pre>
 */
public interface Icon extends JavaeeDomModelElement {

	/**
	 * Returns the value of the lang child.
	 * @return the value of the lang child.
	 */
	GenericAttributeValue<String> getLang();


	/**
	 * Returns the value of the small-icon child.
	 * <pre>
	 * <h3>Element http://java.sun.com/xml/ns/javaee:small-icon documentation</h3>
	 * 	      The small-icon element contains the name of a file
	 * 	      containing a small (16 x 16) icon image. The file
	 * 	      name is a relative path within the Deployment
	 * 	      Component's Deployment File.
	 * 	      The image may be in the GIF, JPEG, or PNG format.
	 * 	      The icon can be used by tools.
	 * 	      Example:
	 * 	      <small-icon>employee-service-icon16x16.jpg</small-icon>
	 * 	      
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
	 * @return the value of the small-icon child.
	 */
	GenericDomValue<String> getSmallIcon();


	/**
	 * Returns the value of the large-icon child.
	 * <pre>
	 * <h3>Element http://java.sun.com/xml/ns/javaee:large-icon documentation</h3>
	 * 	      The large-icon element contains the name of a file
	 * 	      containing a large
	 * 	      (32 x 32) icon image. The file name is a relative
	 * 	      path within the Deployment Component's Deployment
	 * 	      File.
	 * 	      The image may be in the GIF, JPEG, or PNG format.
	 * 	      The icon can be used by tools.
	 * 	      Example:
	 * 	      <large-icon>employee-service-icon32x32.jpg</large-icon>
	 * 	      
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
	 * @return the value of the large-icon child.
	 */
	GenericDomValue<String> getLargeIcon();


}
