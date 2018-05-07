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

import com.intellij.javaee.model.xml.*;
import com.intellij.javaee.web.StaticPathReferenceConverter;
import com.intellij.openapi.paths.PathReference;
import com.intellij.util.xml.Convert;
import com.intellij.util.xml.GenericDomValue;
import com.intellij.util.xml.Stubbed;
import javax.annotation.Nonnull;

import java.util.List;

/**
 * http://java.sun.com/xml/ns/javaee:jsp-property-groupType interface.
 * <pre>
 * <h3>Type http://java.sun.com/xml/ns/javaee:jsp-property-groupType documentation</h3>
 * The jsp-property-groupType is used to group a number of
 * 	files so they can be given global property information.
 * 	All files so described are deemed to be JSP files.  The
 * 	following additional properties can be described:
 * 	    - Control whether EL is ignored.
 * 	    - Control whether scripting elements are invalid.
 * 	    - Indicate pageEncoding information.
 * 	    - Indicate that a resource is a JSP document (XML).
 * 	    - Prelude and Coda automatic includes.
 *             - Control whether the character sequence #{ is allowed
 *               when used as a String literal.
 *             - Control whether template text containing only
 *               whitespaces must be removed from the response output.
 * </pre>
 */
public interface JspPropertyGroup extends JavaeeDomModelElement, DescriptionGroup {

	/**
	 * Returns the list of url-pattern children.
	 * <pre>
	 * <h3>Type http://java.sun.com/xml/ns/javaee:url-patternType documentation</h3>
	 * The url-patternType contains the url pattern of the mapping.
	 * 	It must follow the rules specified in Section 11.2 of the
	 * 	Servlet API Specification. This pattern is assumed to be in
	 * 	URL-decoded form and must not contain CR(#xD) or LF(#xA).
	 * 	If it contains those characters, the container must inform
	 * 	the developer with a descriptive error message.
	 * 	The container must preserve all characters including whitespaces.
	 * </pre>
	 * @return the list of url-pattern children.
	 */
	@Nonnull
        @Stubbed
	List<GenericDomValue<String>> getUrlPatterns();
	/**
	 * Adds new child to the list of url-pattern children.
	 * @return created child
	 */
	GenericDomValue<String> addUrlPattern();


	/**
	 * Returns the value of the el-ignored child.
	 * <pre>
	 * <h3>Element http://java.sun.com/xml/ns/javaee:el-ignored documentation</h3>
	 * Can be used to easily set the isELIgnored
	 * 	    property of a group of JSP pages.  By default, the
	 * 	    EL evaluation is enabled for Web Applications using
	 * 	    a Servlet 2.4 or greater web.xml, and disabled
	 * 	    otherwise.
	 * </pre>
	 * <pre>
	 * <h3>Type http://java.sun.com/xml/ns/javaee:true-falseType documentation</h3>
	 * This simple type designates a boolean with only two
	 * 	permissible values
	 * 	- true
	 * 	- false
	 * </pre>
	 * @return the value of the el-ignored child.
	 */
        @Stubbed
	GenericDomValue<Boolean> getElIgnored();


	/**
	 * Returns the value of the page-encoding child.
	 * <pre>
	 * <h3>Element http://java.sun.com/xml/ns/javaee:page-encoding documentation</h3>
	 * The valid values of page-encoding are those of the
	 * 	    pageEncoding page directive.  It is a
	 * 	    translation-time error to name different encodings
	 * 	    in the pageEncoding attribute of the page directive
	 * 	    of a JSP page and in a JSP configuration element
	 * 	    matching the page.  It is also a translation-time
	 * 	    error to name different encodings in the prolog
	 * 	    or text declaration of a document in XML syntax and
	 * 	    in a JSP configuration element matching the document.
	 * 	    It is legal to name the same encoding through
	 * 	    mulitple mechanisms.
	 * </pre>
	 * <pre>
	 * <h3>Type http://java.sun.com/xml/ns/javaee:string documentation</h3>
	 * This is a special string datatype that is defined by Java EE as
	 * 	a base type for defining collapsed strings. When schemas
	 * 	require trailing/leading space elimination as well as
	 * 	collapsing the existing whitespace, this base type may be
	 * 	used.
	 * </pre>
	 * @return the value of the page-encoding child.
	 */
	@Stubbed
        GenericDomValue<String> getPageEncoding();


	/**
	 * Returns the value of the scripting-invalid child.
	 * <pre>
	 * <h3>Element http://java.sun.com/xml/ns/javaee:scripting-invalid documentation</h3>
	 * Can be used to easily disable scripting in a
	 * 	    group of JSP pages.  By default, scripting is
	 * 	    enabled.
	 * </pre>
	 * <pre>
	 * <h3>Type http://java.sun.com/xml/ns/javaee:true-falseType documentation</h3>
	 * This simple type designates a boolean with only two
	 * 	permissible values
	 * 	- true
	 * 	- false
	 * </pre>
	 * @return the value of the scripting-invalid child.
	 */
	GenericDomValue<Boolean> getScriptingInvalid();


	/**
	 * Returns the value of the is-xml child.
	 * <pre>
	 * <h3>Element http://java.sun.com/xml/ns/javaee:is-xml documentation</h3>
	 * If true, denotes that the group of resources
	 * 	    that match the URL pattern are JSP documents,
	 * 	    and thus must be interpreted as XML documents.
	 * 	    If false, the resources are assumed to not
	 * 	    be JSP documents, unless there is another
	 * 	    property group that indicates otherwise.
	 * </pre>
	 * <pre>
	 * <h3>Type http://java.sun.com/xml/ns/javaee:true-falseType documentation</h3>
	 * This simple type designates a boolean with only two
	 * 	permissible values
	 * 	- true
	 * 	- false
	 * </pre>
	 * @return the value of the is-xml child.
	 */
	GenericDomValue<Boolean> getIsXml();


	/**
	 * Returns the list of include-prelude children.
	 * <pre>
	 * <h3>Element http://java.sun.com/xml/ns/javaee:include-prelude documentation</h3>
	 * The include-prelude element is a context-relative
	 * 	    path that must correspond to an element in the
	 * 	    Web Application.  When the element is present,
	 * 	    the given path will be automatically included (as
	 * 	    in an include directive) at the beginning of each
	 * 	    JSP page in this jsp-property-group.
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
	 * @return the list of include-prelude children.
	 */
        @Convert(value = StaticPathReferenceConverter.class)
        @Stubbed
        List<GenericDomValue<PathReference>> getIncludePreludes();
	/**
	 * Adds new child to the list of include-prelude children.
	 * @return created child
	 */
        @Convert(value = StaticPathReferenceConverter.class)
        GenericDomValue<PathReference> addIncludePrelude();


	/**
	 * Returns the list of include-coda children.
	 * <pre>
	 * <h3>Element http://java.sun.com/xml/ns/javaee:include-coda documentation</h3>
	 * The include-coda element is a context-relative
	 * 	    path that must correspond to an element in the
	 * 	    Web Application.  When the element is present,
	 * 	    the given path will be automatically included (as
	 * 	    in an include directive) at the end of each
	 * 	    JSP page in this jsp-property-group.
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
	 * @return the list of include-coda children.
	 */
        @Convert(value = StaticPathReferenceConverter.class)
        @Stubbed
        List<GenericDomValue<PathReference>> getIncludeCodas();
	/**
	 * Adds new child to the list of include-coda children.
	 * @return created child
	 */
        @Convert(value = StaticPathReferenceConverter.class)
        GenericDomValue<PathReference> addIncludeCoda();


	/**
	 * Returns the value of the deferred-syntax-allowed-as-literal child.
	 * <pre>
	 * <h3>Element http://java.sun.com/xml/ns/javaee:deferred-syntax-allowed-as-literal documentation</h3>
	 * The character sequence #{ is reserved for EL expressions.
	 *              Consequently, a translation error occurs if the #{
	 *              character sequence is used as a String literal, unless
	 *              this element is enabled (true). Disabled (false) by
	 *              default.
	 * </pre>
	 * <pre>
	 * <h3>Type http://java.sun.com/xml/ns/javaee:true-falseType documentation</h3>
	 * This simple type designates a boolean with only two
	 * 	permissible values
	 * 	- true
	 * 	- false
	 * </pre>
	 * @return the value of the deferred-syntax-allowed-as-literal child.
	 */
	GenericDomValue<Boolean> getDeferredSyntaxAllowedAsLiteral();


	/**
	 * Returns the value of the trim-directive-whitespaces child.
	 * <pre>
	 * <h3>Element http://java.sun.com/xml/ns/javaee:trim-directive-whitespaces documentation</h3>
	 * Indicates that template text containing only whitespaces
	 *              must be removed from the response output. It has no
	 *              effect on JSP documents (XML syntax). Disabled (false)
	 *              by default.
	 * </pre>
	 * <pre>
	 * <h3>Type http://java.sun.com/xml/ns/javaee:true-falseType documentation</h3>
	 * This simple type designates a boolean with only two
	 * 	permissible values
	 * 	- true
	 * 	- false
	 * </pre>
	 * @return the value of the trim-directive-whitespaces child.
	 */
	GenericDomValue<Boolean> getTrimDirectiveWhitespaces();


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
