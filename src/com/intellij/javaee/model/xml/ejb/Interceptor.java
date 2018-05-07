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

package com.intellij.javaee.model.xml.ejb;

import com.intellij.javaee.model.xml.*;
import com.intellij.psi.PsiClass;
import com.intellij.util.xml.GenericDomValue;
import javax.annotation.Nonnull;

import java.util.List;

/**
 * http://java.sun.com/xml/ns/javaee:interceptorType interface.
 * <pre>
 * <h3>Type http://java.sun.com/xml/ns/javaee:interceptorType documentation</h3>
 * The interceptorType element declares information about a single
 *         interceptor class.  It consists of :
 *             - An optional description.
 *             - The fully-qualified name of the interceptor class.
 *             - An optional list of around invoke methods declared on the
 *               interceptor class and/or its super-classes.
 *             - An optional list of around timeout methods declared on the
 *               interceptor class and/or its super-classes.
 *             - An optional list environment dependencies for the interceptor
 *               class and/or its super-classes.
 *             - An optional list of post-activate methods declared on the
 *               interceptor class and/or its super-classes.
 *             - An optional list of pre-passivate methods declared on the
 *               interceptor class and/or its super-classes.
 * </pre>
 */
public interface Interceptor extends JavaeeDomModelElement, JndiEnvironmentRefsGroup, ServiceRefGroup, com.intellij.javaee.model.common.ejb.Interceptor {

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
	 * Returns the value of the interceptor-class child.
	 * <pre>
	 * <h3>Type http://java.sun.com/xml/ns/javaee:fully-qualified-classType documentation</h3>
	 * The elements that use this type designate the name of a
	 * 	Java class or interface.  The name is in the form of a
	 * 	"binary name", as defined in the JLS.  This is the form
	 * 	of name used in Class.forName().  Tools that need the
	 * 	canonical name (the name used in source code) will need
	 * 	to convert this binary name to the canonical name.
	 * </pre>
	 * @return the value of the interceptor-class child.
	 */
	@Nonnull
	GenericDomValue<PsiClass> getInterceptorClass();


	/**
	 * Returns the list of around-invoke children.
	 * @return the list of around-invoke children.
	 */
	List<AroundInvoke> getAroundInvokes();
	/**
	 * Adds new child to the list of around-invoke children.
	 * @return created child
	 */
	AroundInvoke addAroundInvoke();


	/**
	 * Returns the list of around-timeout children.
	 * @return the list of around-timeout children.
	 */
	List<AroundTimeout> getAroundTimeouts();
	/**
	 * Adds new child to the list of around-timeout children.
	 * @return created child
	 */
	AroundTimeout addAroundTimeout();


	/**
	 * Returns the list of post-activate children.
	 * @return the list of post-activate children.
	 */
	List<LifecycleCallback> getPostActivates();
	/**
	 * Adds new child to the list of post-activate children.
	 * @return created child
	 */
	LifecycleCallback addPostActivate();


	/**
	 * Returns the list of pre-passivate children.
	 * @return the list of pre-passivate children.
	 */
	List<LifecycleCallback> getPrePassivates();
	/**
	 * Adds new child to the list of pre-passivate children.
	 * @return created child
	 */
	LifecycleCallback addPrePassivate();


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
