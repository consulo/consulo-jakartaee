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


import java.util.List;

/**
 * http://java.sun.com/xml/ns/javaee:descriptionGroup model group interface.
 * <pre>
 * <h3>Type http://java.sun.com/xml/ns/javaee:descriptionGroup documentation</h3>
 * This group keeps the usage of the contained description related
 * 	elements consistent across Java EE deployment descriptors.
 * 	All elements may occur multiple times with different languages,
 * 	to support localization of the content.
 * </pre>
 */
public interface DescriptionGroup extends DescriptionOwner{

	List<Description> getDescriptions();
	Description addDescription();


	List<DisplayName> getDisplayNames();
	DisplayName addDisplayName();


	List<Icon> getIcons();
	Icon addIcon();
}
