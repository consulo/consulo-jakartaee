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

// Generated on Tue Feb 21 16:49:09 MSK 2006
// DTD/Schema  :    http://java.sun.com/xml/ns/javaee

package com.intellij.javaee.model.xml.web;

import com.intellij.jam.view.DeleteHandler;
import com.intellij.java.impl.util.xml.ClassMappingNameConverter;
import com.intellij.java.impl.util.xml.ClassTemplate;
import com.intellij.java.impl.util.xml.ExtendClass;
import com.intellij.java.impl.util.xml.MappingClass;
import com.intellij.java.language.psi.PsiClass;
import com.intellij.javaee.J2EEFileTemplateNames;
import com.intellij.javaee.model.xml.DescriptionGroup;
import com.intellij.javaee.model.xml.JavaeeDomModelElement;
import com.intellij.javaee.model.xml.RunAs;
import com.intellij.javaee.model.xml.SecurityRoleRef;
import com.intellij.javaee.model.xml.web.converters.WebDeleteHandler;
import com.intellij.javaee.web.CommonServlet;
import consulo.xml.util.xml.*;

import jakarta.annotation.Nonnull;
import java.util.List;

/**
 * http://java.sun.com/xml/ns/javaee:servletType interface.
 * <pre>
 * <h3>Type http://java.sun.com/xml/ns/javaee:servletType documentation</h3>
 * The servletType is used to declare a servlet.
 * 	It contains the declarative data of a
 * 	servlet. If a jsp-file is specified and the load-on-startup
 * 	element is present, then the JSP should be precompiled and
 * 	loaded.
 * 	Used in: web-app
 * </pre>
 */
//@Presentation(icon = "AllIcons.Nodes.Servlet")
@DeleteHandler(handlerClass = WebDeleteHandler.class)
public interface Servlet extends CommonServlet, JavaeeDomModelElement, DescriptionGroup
{

	/**
	 * Returns the value of the servlet-name child.
	 * <pre>
	 * <h3>Type http://java.sun.com/xml/ns/javaee:servlet-nameType documentation</h3>
	 * The servlet-name element contains the canonical name of the
	 * 	servlet. Each servlet name is unique within the web
	 * 	application.
	 * </pre>
	 *
	 * @return the value of the servlet-name child.
	 */
	@NameValue
	@Convert(ClassMappingNameConverter.class)
	@Nonnull
	@Required
	@Stubbed
	GenericDomValue<String> getServletName();


	/**
	 * Returns the list of init-param children.
	 *
	 * @return the list of init-param children.
	 */
	@Stubbed
	List<InitParam> getInitParams();

	/**
	 * Adds new child to the list of init-param children.
	 *
	 * @return created child
	 */
	InitParam addInitParam();


	/**
	 * Returns the value of the load-on-startup child.
	 * <pre>
	 * <h3>Element http://java.sun.com/xml/ns/javaee:load-on-startup documentation</h3>
	 * The load-on-startup element indicates that this
	 * 	    servlet should be loaded (instantiated and have
	 * 	    its init() called) on the startup of the web
	 * 	    application. The optional contents of these
	 * 	    element must be an integer indicating the order in
	 * 	    which the servlet should be loaded. If the value
	 * 	    is a negative integer, or the element is not
	 * 	    present, the container is free to load the servlet
	 * 	    whenever it chooses. If the value is a positive
	 * 	    integer or 0, the container must load and
	 * 	    initialize the servlet as the application is
	 * 	    deployed. The container must guarantee that
	 * 	    servlets marked with lower integers are loaded
	 * 	    before servlets marked with higher integers. The
	 * 	    container may choose the order of loading of
	 * 	    servlets with the same load-on-start-up value.
	 * </pre>
	 *
	 * @return the value of the load-on-startup child.
	 */
	@Required(value = false, nonEmpty = false)
	GenericDomValue<Integer> getLoadOnStartup();


	/**
	 * Returns the value of the run-as child.
	 *
	 * @return the value of the run-as child.
	 */
	RunAs getRunAs();


	/**
	 * Returns the list of security-role-ref children.
	 *
	 * @return the list of security-role-ref children.
	 */
	List<SecurityRoleRef> getSecurityRoleRefs();

	/**
	 * Adds new child to the list of security-role-ref children.
	 *
	 * @return created child
	 */
	SecurityRoleRef addSecurityRoleRef();

	/**
	 * Returns the value of the servlet-class child.
	 * <pre>
	 * <h3>Element http://java.sun.com/xml/ns/javaee:servlet-class documentation</h3>
	 * The servlet-class element contains the fully
	 * 	      qualified class name of the servlet.
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
	 *
	 * @return the value of the servlet-class child.
	 */
	@ExtendClass("javax.servlet.Servlet")
	@ClassTemplate(J2EEFileTemplateNames.SERVLET_CLASS_TEMPLATE)
	@MappingClass
	@Nonnull
	@Stubbed
	GenericDomValue<PsiClass> getServletClass();

	@Nonnull
	@Override
	WebApp getParent();

	/**
	 * Returns the value of the jsp-file child.
	 * <pre>
	 * <h3>Type http://java.sun.com/xml/ns/javaee:jsp-fileType documentation</h3>
	 * The jsp-file element contains the full path to a JSP file
	 * 	within the web application beginning with a `/'.
	 * </pre>
	 *
	 * @return the value of the jsp-file child.
	 */
	@Nonnull
	GenericDomValue<String> getJspFile();

}
