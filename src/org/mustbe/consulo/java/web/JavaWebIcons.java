/*
 * Copyright 2013 must-be.org
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

package org.mustbe.consulo.java.web;

import javax.swing.Icon;

import com.intellij.openapi.util.IconLoader;

/**
 * @author VISTALL
 * @since 07.11.13.
 */
public interface JavaWebIcons
{
	Icon Jsp = IconLoader.findIcon("/icons/jsp.png");
	Icon Jspx = IconLoader.findIcon("/icons/jspx.png");
	Icon WebXml = IconLoader.findIcon("/icons/web_xml.png");
	Icon WarArtifact = IconLoader.findIcon("/icons/warArtifact.png");
}
