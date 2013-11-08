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

package org.mustbe.consulo.java.web.module.extension;

import javax.swing.Icon;

import org.consulo.module.extension.ModuleExtensionProvider;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import com.intellij.icons.AllIcons;
import com.intellij.openapi.module.Module;

/**
 * @author VISTALL
 * @since 07.11.13.
 */
public class JavaWebModuleExtensionProvider implements ModuleExtensionProvider<JavaWebModuleExtension, JavaWebMutableModuleExtension>
{
	@Nullable
	@Override
	public Icon getIcon()
	{
		return AllIcons.General.Web;
	}

	@NotNull
	@Override
	public String getName()
	{
		return "Web";
	}

	@NotNull
	@Override
	public Class<JavaWebModuleExtension> getImmutableClass()
	{
		return JavaWebModuleExtension.class;
	}

	@NotNull
	@Override
	public JavaWebModuleExtension createImmutable(@NotNull String s, @NotNull Module module)
	{
		return new JavaWebModuleExtension(s, module);
	}

	@NotNull
	@Override
	public JavaWebMutableModuleExtension createMutable(@NotNull String s, @NotNull Module module, @NotNull JavaWebModuleExtension javaWebModuleExtension)
	{
		return new JavaWebMutableModuleExtension(s, module, javaWebModuleExtension);
	}
}
