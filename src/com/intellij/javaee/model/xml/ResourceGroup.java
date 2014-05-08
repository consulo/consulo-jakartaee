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

import com.intellij.javaee.model.xml.InjectionTarget;
import com.intellij.util.xml.GenericDomValue;

import java.util.List;

/**
 * http://java.sun.com/xml/ns/javaee:resourceGroup model group interface.
 * <pre>
 * <h3>Type http://java.sun.com/xml/ns/javaee:resourceGroup documentation</h3>
 * This group collects elements that are common to all the
 * 	JNDI resource elements.
 * </pre>
 */
public interface ResourceGroup {

	GenericDomValue<String> getMappedName();


	List<InjectionTarget> getInjectionTargets();
	InjectionTarget addInjectionTarget();


}
