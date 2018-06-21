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

import javax.annotation.Nonnull;

import com.intellij.javaee.model.xml.JavaeeDomModelElement;
import com.intellij.util.xml.GenericDomValue;
import com.intellij.util.xml.Attribute;
import com.intellij.util.xml.GenericAttributeValue;
import com.intellij.util.xml.NameValue;

/**
 * http://java.sun.com/xml/ns/javaee:moduleType interface.
 * <pre>
 * <h3>Type http://java.sun.com/xml/ns/javaee:moduleType documentation</h3>
 * The moduleType defines a single Java EE module and contains a
 * 	connector, ejb, java, or web element, which indicates the
 * 	module type and contains a path to the module file, and an
 * 	optional alt-dd element, which specifies an optional URI to
 * 	the post-assembly version of the deployment descriptor.
 * </pre>
 */
public interface JavaeeModule extends JavaeeDomModelElement {

  @Attribute
  @NameValue
  GenericAttributeValue<String> getId();

        /**
	 * Returns the value of the alt-dd child.
	 * <pre>
	 * <h3>Element http://java.sun.com/xml/ns/javaee:alt-dd documentation</h3>
	 * The alt-dd element specifies an optional URI to the
	 * 	    post-assembly version of the deployment descriptor
	 * 	    file for a particular Java EE module.  The URI must
	 * 	    specify the full pathname of the deployment
	 * 	    descriptor file relative to the application's root
	 * 	    directory. If alt-dd is not specified, the deployer
	 * 	    must read the deployment descriptor from the default
	 * 	    location and file name required by the respective
	 * 	    component specification.
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
	 * @return the value of the alt-dd child.
	 */
	GenericDomValue<String> getAltDd();


	/**
	 * Returns the value of the connector child.
	 * <pre>
	 * <h3>Element http://java.sun.com/xml/ns/javaee:connector documentation</h3>
	 * The connector element specifies the URI of a
	 * 	      resource adapter archive file, relative to the
	 * 	      top level of the application package.
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
	 * @return the value of the connector child.
	 */
	@Nonnull
	GenericDomValue<String> getConnector();


	/**
	 * Returns the value of the ejb child.
	 * <pre>
	 * <h3>Element http://java.sun.com/xml/ns/javaee:ejb documentation</h3>
	 * The ejb element specifies the URI of an ejb-jar,
	 * 	      relative to the top level of the application
	 * 	      package.
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
	 * @return the value of the ejb child.
	 */
	@Nonnull
	GenericDomValue<String> getEjb();


	/**
	 * Returns the value of the java child.
	 * <pre>
	 * <h3>Element http://java.sun.com/xml/ns/javaee:java documentation</h3>
	 * The java element specifies the URI of a java
	 * 	      application client module, relative to the top
	 * 	      level of the application package.
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
	 * @return the value of the java child.
	 */
	@Nonnull
	GenericDomValue<String> getJava();


	/**
	 * Returns the value of the web child.
	 * @return the value of the web child.
	 */
	@Nonnull
	Web getWeb();


}
