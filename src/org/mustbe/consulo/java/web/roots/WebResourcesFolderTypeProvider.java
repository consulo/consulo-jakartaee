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

package org.mustbe.consulo.java.web.roots;

import java.awt.Color;

import javax.swing.Icon;

import org.jetbrains.annotations.NotNull;
import org.mustbe.consulo.roots.ContentFolderTypeProvider;
import org.mustbe.consulo.roots.impl.BaseContentFolderTypeProvider;
import org.mustbe.consulo.roots.impl.ProductionResourceContentFolderTypeProvider;
import com.intellij.icons.AllIcons;

/**
 * @author VISTALL
 * @since 07.11.13.
 */
@Deprecated // use WebResourcesFolderTypeProvider from Consulo
public class WebResourcesFolderTypeProvider extends BaseContentFolderTypeProvider
{
	public static ContentFolderTypeProvider getInstance()
	{
		return EP_NAME.findExtension(WebResourcesFolderTypeProvider.class);
	}

	public WebResourcesFolderTypeProvider()
	{
		super("JAVA_WEB_RESOURCES");
	}

	@NotNull
	@Override
	public Icon getIcon()
	{
		return AllIcons.Modules.WebRoot;
	}

	@Override
	public Icon getChildDirectoryIcon()
	{
		return AllIcons.Nodes.WebFolder;
	}

	@NotNull
	@Override
	public String getName()
	{
		return "Web Resources";
	}

	@NotNull
	@Override
	public Color getGroupColor()
	{
		return ProductionResourceContentFolderTypeProvider.getInstance().getGroupColor();
	}
}
