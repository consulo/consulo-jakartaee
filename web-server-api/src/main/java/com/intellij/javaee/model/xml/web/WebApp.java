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
import consulo.xml.util.xml.GenericAttributeValue;
import consulo.xml.util.xml.GenericDomValue;
import consulo.xml.util.xml.Stubbed;
import consulo.xml.util.xml.SubTag;

import jakarta.annotation.Nonnull;
import java.util.List;

/**
 * http://java.sun.com/xml/ns/javaee:web-appType interface.
 */
@Stubbed
public interface WebApp extends JavaeeDomModelElement, DescriptionGroup, JndiEnvironmentRefsGroup, ServiceRefGroup {

	/**
	 * Returns the value of the version child.
	 * @return the value of the version child.
	 */
	@Nonnull
	@Stubbed
	GenericAttributeValue<WebAppVersion> getVersion();


	/**
	 * Returns the value of the metadata-complete child.
	 * <pre>
	 * <h3>Attribute null:metadata-complete documentation</h3>
	 * The metadata-complete attribute defines whether this
	 * 	  deployment descriptor and other related deployment
	 * 	  descriptors for this module (e.g., web service
	 * 	  descriptors) are complete, or whether the class
	 * 	  files available to this module and packaged with
	 * 	  this application should be examined for annotations
	 * 	  that specify deployment information.
	 * 	  If metadata-complete is set to "true", the deployment
	 * 	  tool must ignore any annotations that specify deployment
	 * 	  information, which might be present in the class files
	 * 	  of the application.
	 * 	  If metadata-complete is not specified or is set to
	 * 	  "false", the deployment tool must examine the class
	 * 	  files of the application for annotations, as
	 * 	  specified by the specifications.
	 * </pre>
	 * @return the value of the metadata-complete child.
	 */
	GenericAttributeValue<Boolean> getMetadataComplete();


	/**
	 * Returns the value of the distributable child.
	 * <pre>
	 * <h3>Type http://java.sun.com/xml/ns/javaee:emptyType documentation</h3>
	 * This type is used to designate an empty
	 * 	element when used.
	 * </pre>
	 * @return the value of the distributable child.
	 */
	@SubTag(value = "distributable", indicator = true)
	GenericDomValue<Boolean> getDistributable();


	/**
	 * Returns the list of context-param children.
	 * <pre>
	 * <h3>Element http://java.sun.com/xml/ns/javaee:context-param documentation</h3>
	 * The context-param element contains the declaration
	 * 	    of a web application's servlet context
	 * 	    initialization parameters.
	 * </pre>
	 * @return the list of context-param children.
	 */
        @Stubbed
	List<ParamValue> getContextParams();
	/**
	 * Adds new child to the list of context-param children.
	 * @return created child
	 */
	ParamValue addContextParam();


	/**
	 * Returns the list of filter children.
	 * @return the list of filter children.
	 */
        @Stubbed
	List<Filter> getFilters();
	/**
	 * Adds new child to the list of filter children.
	 * @return created child
	 */
	Filter addFilter();


	/**
	 * Returns the list of filter-mapping children.
	 * @return the list of filter-mapping children.
	 */
	List<FilterMapping> getFilterMappings();
	/**
	 * Adds new child to the list of filter-mapping children.
	 * @return created child
	 */
	FilterMapping addFilterMapping();


	/**
	 * Returns the list of listener children.
	 * @return the list of listener children.
	 */
        @Stubbed
	List<Listener> getListeners();
	/**
	 * Adds new child to the list of listener children.
	 * @return created child
	 */
	Listener addListener();


	/**
	 * Returns the list of servlet children.
	 * @return the list of servlet children.
	 */
        @Stubbed
	List<Servlet> getServlets();
	/**
	 * Adds new child to the list of servlet children.
	 * @return created child
	 */
	Servlet addServlet();


	/**
	 * Returns the list of servlet-mapping children.
	 * @return the list of servlet-mapping children.
	 */
        @Stubbed
	List<ServletMapping> getServletMappings();
	/**
	 * Adds new child to the list of servlet-mapping children.
	 * @return created child
	 */
	ServletMapping addServletMapping();


	/**
	 * Returns the list of session-config children.
	 * @return the list of session-config children.
	 */
	List<SessionConfig> getSessionConfigs();
	/**
	 * Adds new child to the list of session-config children.
	 * @return created child
	 */
	SessionConfig addSessionConfig();


	/**
	 * Returns the list of mime-mapping children.
	 * @return the list of mime-mapping children.
	 */
	List<MimeMapping> getMimeMappings();
	/**
	 * Adds new child to the list of mime-mapping children.
	 * @return created child
	 */
	MimeMapping addMimeMapping();


	/**
	 * Returns the list of welcome-file-list children.
	 * @return the list of welcome-file-list children.
	 */
	List<WelcomeFileList> getWelcomeFileLists();
	/**
	 * Adds new child to the list of welcome-file-list children.
	 * @return created child
	 */
	WelcomeFileList addWelcomeFileList();


	/**
	 * Returns the list of error-page children.
	 * @return the list of error-page children.
	 */
	List<ErrorPage> getErrorPages();
	/**
	 * Adds new child to the list of error-page children.
	 * @return created child
	 */
	ErrorPage addErrorPage();


	/**
	 * Returns the list of jsp-config children.
	 * @return the list of jsp-config children.
	 */
        @Stubbed
	List<JspConfig> getJspConfigs();
	/**
	 * Adds new child to the list of jsp-config children.
	 * @return created child
	 */
	JspConfig addJspConfig();


	/**
	 * Returns the list of security-constraint children.
	 * @return the list of security-constraint children.
	 */
	List<SecurityConstraint> getSecurityConstraints();
	/**
	 * Adds new child to the list of security-constraint children.
	 * @return created child
	 */
	SecurityConstraint addSecurityConstraint();


	/**
	 * Returns the list of login-config children.
	 * @return the list of login-config children.
	 */
	List<LoginConfig> getLoginConfigs();
	/**
	 * Adds new child to the list of login-config children.
	 * @return created child
	 */
	LoginConfig addLoginConfig();


	/**
	 * Returns the list of security-role children.
	 * @return the list of security-role children.
	 */
        @Stubbed
	List<SecurityRole> getSecurityRoles();
	/**
	 * Adds new child to the list of security-role children.
	 * @return created child
	 */
	SecurityRole addSecurityRole();


	/**
	 * Returns the list of message-destination children.
	 * @return the list of message-destination children.
	 */
	List<MessageDestination> getMessageDestinations();
	/**
	 * Adds new child to the list of message-destination children.
	 * @return created child
	 */
	MessageDestination addMessageDestination();


	/**
	 * Returns the list of locale-encoding-mapping-list children.
	 * @return the list of locale-encoding-mapping-list children.
	 */
	List<LocaleEncodingMappingList> getLocaleEncodingMappingLists();
	/**
	 * Adds new child to the list of locale-encoding-mapping-list children.
	 * @return created child
	 */
	LocaleEncodingMappingList addLocaleEncodingMappingList();


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
	 * Returns the list of env-entry children.
	 * @return the list of env-entry children.
	 */
	List<EnvEntry> getEnvEntries();
	/**
	 * Adds new child to the list of env-entry children.
	 * @return created child
	 */
	EnvEntry addEnvEntry();


	/**
	 * Returns the list of ejb-ref children.
	 * @return the list of ejb-ref children.
	 */
	List<EjbRef> getEjbRefs();
	/**
	 * Adds new child to the list of ejb-ref children.
	 * @return created child
	 */
	EjbRef addEjbRef();


	/**
	 * Returns the list of ejb-local-ref children.
	 * @return the list of ejb-local-ref children.
	 */
	List<EjbLocalRef> getEjbLocalRefs();
	/**
	 * Adds new child to the list of ejb-local-ref children.
	 * @return created child
	 */
	EjbLocalRef addEjbLocalRef();


	/**
	 * Returns the list of resource-ref children.
	 * @return the list of resource-ref children.
	 */
	List<ResourceRef> getResourceRefs();
	/**
	 * Adds new child to the list of resource-ref children.
	 * @return created child
	 */
	ResourceRef addResourceRef();


	/**
	 * Returns the list of resource-env-ref children.
	 * @return the list of resource-env-ref children.
	 */
	List<ResourceEnvRef> getResourceEnvRefs();
	/**
	 * Adds new child to the list of resource-env-ref children.
	 * @return created child
	 */
	ResourceEnvRef addResourceEnvRef();


	/**
	 * Returns the list of message-destination-ref children.
	 * @return the list of message-destination-ref children.
	 */
	List<MessageDestinationRef> getMessageDestinationRefs();
	/**
	 * Adds new child to the list of message-destination-ref children.
	 * @return created child
	 */
	MessageDestinationRef addMessageDestinationRef();


	/**
	 * Returns the list of persistence-context-ref children.
	 * @return the list of persistence-context-ref children.
	 */
	List<PersistenceContextRef> getPersistenceContextRefs();
	/**
	 * Adds new child to the list of persistence-context-ref children.
	 * @return created child
	 */
	PersistenceContextRef addPersistenceContextRef();


	/**
	 * Returns the list of persistence-unit-ref children.
	 * @return the list of persistence-unit-ref children.
	 */
	List<PersistenceUnitRef> getPersistenceUnitRefs();
	/**
	 * Adds new child to the list of persistence-unit-ref children.
	 * @return created child
	 */
	PersistenceUnitRef addPersistenceUnitRef();


	/**
	 * Returns the list of post-construct children.
	 * @return the list of post-construct children.
	 */
	List<LifecycleCallback> getPostConstructs();
	/**
	 * Adds new child to the list of post-construct children.
	 * @return created child
	 */
	LifecycleCallback addPostConstruct();


	/**
	 * Returns the list of pre-destroy children.
	 * @return the list of pre-destroy children.
	 */
	List<LifecycleCallback> getPreDestroys();
	/**
	 * Adds new child to the list of pre-destroy children.
	 * @return created child
	 */
	LifecycleCallback addPreDestroy();


	/**
	 * Returns the list of service-ref children.
	 * @return the list of service-ref children.
	 */
	List<ServiceRef> getServiceRefs();
	/**
	 * Adds new child to the list of service-ref children.
	 * @return created child
	 */
	ServiceRef addServiceRef();


}
