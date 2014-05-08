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

import com.intellij.javaee.model.common.ejb.EnterpriseBeanSet;
import com.intellij.javaee.model.xml.JavaeeDomModelElement;

import java.util.List;

/**
 * http://java.sun.com/xml/ns/javaee:enterprise-beansType interface.
 * <pre>
 * <h3>Type http://java.sun.com/xml/ns/javaee:enterprise-beansType documentation</h3>
 * The enterprise-beansType declares one or more enterprise
 * 	beans. Each bean can be a session, entity or message-driven
 * 	bean.
 * </pre>
 */
public interface EnterpriseBeans extends JavaeeDomModelElement, EnterpriseBeanSet {

	/**
	 * Returns the list of session children.
	 * @return the list of session children.
	 */
	List<SessionBean> getSessions();
	/**
	 * Adds new child to the list of session children.
	 * @return created child
	 */
	SessionBean addSession();


	/**
	 * Returns the list of entity children.
	 * @return the list of entity children.
	 */
	List<EntityBean> getEntities();
	/**
	 * Adds new child to the list of entity children.
	 * @return created child
	 */
	EntityBean addEntity();


	/**
	 * Returns the list of message-driven children.
	 * @return the list of message-driven children.
	 */
	List<MessageDrivenBean> getMessageDrivens();
	/**
	 * Adds new child to the list of message-driven children.
	 * @return created child
	 */
	MessageDrivenBean addMessageDriven();


}
