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

package consulo.javaee.dom.web;

import com.intellij.javaee.model.xml.web.WebApp;
import consulo.annotation.component.ExtensionImpl;
import consulo.component.util.Iconable;
import consulo.jakartaee.web.JavaWebConstants;
import consulo.jakartaee.web.module.extension.JavaWebModuleExtension;
import consulo.java.impl.roots.SpecialDirUtil;
import consulo.javaee.JavaEEIcons;
import consulo.language.util.ModuleUtilCore;
import consulo.module.Module;
import consulo.ui.image.Image;
import consulo.virtualFileSystem.VirtualFile;
import consulo.xml.psi.xml.XmlFile;
import consulo.xml.util.xml.DomFileDescription;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;

/**
 * @author VISTALL
 * @since 07.11.13.
 */
@ExtensionImpl
public class WebAppDescriptor extends DomFileDescription<WebApp>
{
	public WebAppDescriptor()
	{
		super(WebApp.class, "web-app");
	}

	@Nullable
	@Override
	public Image getFileIcon(@Iconable.IconFlags int flags)
	{
		return JavaEEIcons.Web_xml;
	}

	@Override
	public boolean isMyFile(@Nonnull XmlFile file)
	{
		if(!super.isMyFile(file))
		{
			return false;
		}

		if(!file.getName().equals(JavaWebConstants.WEB_APP_XML))
		{
			return false;
		}

		Module module = ModuleUtilCore.findModuleForPsiElement(file);
		if(module == null || ModuleUtilCore.getExtension(module, JavaWebModuleExtension.class) == null)
		{
			return false;
		}

		VirtualFile virtualFile = file.getVirtualFile();
		if(virtualFile == null)
		{
			return false;
		}

		VirtualFile parent = virtualFile.getParent();
		if(parent != null && parent.getName().equals(JavaWebConstants.WEB_INF))
		{
			List<VirtualFile> specialDirs = SpecialDirUtil.collectSpecialDirs(module, JavaWebConstants.WEB_INF);
			return specialDirs.contains(parent);
		}

		return true;
	}
}
