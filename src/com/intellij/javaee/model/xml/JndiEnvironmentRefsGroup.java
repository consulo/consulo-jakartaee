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

import java.util.List;

/**
 * http://java.sun.com/xml/ns/javaee:jndiEnvironmentRefsGroup model group interface.
 * <pre>
 * <h3>Type http://java.sun.com/xml/ns/javaee:jndiEnvironmentRefsGroup documentation</h3>
 * This group keeps the usage of the contained JNDI environment
 * 	reference elements consistent across Java EE deployment descriptors.
 * </pre>
 */
public interface JndiEnvironmentRefsGroup extends JavaeeDomModelElement, ServiceRefGroup, com.intellij.javaee.model.common.EnvironmentGroup {

	List<EnvEntry> getEnvEntries();
	EnvEntry addEnvEntry();


	List<EjbRef> getEjbRefs();
	EjbRef addEjbRef();


	List<EjbLocalRef> getEjbLocalRefs();
	EjbLocalRef addEjbLocalRef();


	List<ResourceRef> getResourceRefs();
	ResourceRef addResourceRef();


	List<ResourceEnvRef> getResourceEnvRefs();
	ResourceEnvRef addResourceEnvRef();


	List<MessageDestinationRef> getMessageDestinationRefs();
	MessageDestinationRef addMessageDestinationRef();


	List<PersistenceContextRef> getPersistenceContextRefs();
	PersistenceContextRef addPersistenceContextRef();


	List<PersistenceUnitRef> getPersistenceUnitRefs();
	PersistenceUnitRef addPersistenceUnitRef();


	List<LifecycleCallback> getPostConstructs();
	LifecycleCallback addPostConstruct();


	List<LifecycleCallback> getPreDestroys();
	LifecycleCallback addPreDestroy();


	List<ServiceRef> getServiceRefs();
	ServiceRef addServiceRef();


}
