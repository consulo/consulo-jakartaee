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

import java.util.List;

import com.intellij.javaee.model.xml.JavaeeDomModelElement;
import com.intellij.util.xml.GenericDomValue;

/**
 * http://java.sun.com/xml/ns/javaee:welcome-file-listType interface.
 * <pre>
 * <h3>Type http://java.sun.com/xml/ns/javaee:welcome-file-listType documentation</h3>
 * The welcome-file-list contains an ordered list of welcome
 * 	files elements.
 * 	Used in: web-app
 * </pre>
 */
public interface WelcomeFileList extends JavaeeDomModelElement {

	/**
	 * Returns the list of welcome-file children.
	 * <pre>
	 * <h3>Element http://java.sun.com/xml/ns/javaee:welcome-file documentation</h3>
	 * The welcome-file element contains file name to use
	 * 	    as a default welcome file, such as index.html
	 * </pre>
	 * @return the list of welcome-file children.
	 */
	List<GenericDomValue<String>> getWelcomeFiles();
	/**
	 * Adds new child to the list of welcome-file children.
	 * @return created child
	 */
	GenericDomValue<String> addWelcomeFile();


}
