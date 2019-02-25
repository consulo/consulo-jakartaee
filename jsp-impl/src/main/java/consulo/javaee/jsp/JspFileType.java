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

package consulo.javaee.jsp;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.intellij.ide.highlighter.XmlLikeFileType;
import com.intellij.openapi.fileTypes.TemplateLanguageFileType;
import consulo.javaee.JavaEEIcons;
import consulo.ui.image.Image;

/**
 * @author VISTALL
 * @since 07.11.13.
 */
public class JspFileType extends XmlLikeFileType implements TemplateLanguageFileType
{
	public static final JspFileType INSTANCE = new JspFileType();

	private JspFileType()
	{
		super(JspLanguage.INSTANCE);
	}

	@Nonnull
	@Override
	public String getId()
	{
		return "JSP";
	}

	@Nonnull
	@Override
	public String getDescription()
	{
		return "JSP files";
	}

	@Nonnull
	@Override
	public String getDefaultExtension()
	{
		return "jsp";
	}

	@Nullable
	@Override
	public Image getIcon()
	{
		return JavaEEIcons.Jsp;
	}
}
