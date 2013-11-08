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

package org.mustbe.consulo.java.web.jsp;

import javax.swing.Icon;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.mustbe.consulo.java.web.JavaWebIcons;
import org.mustbe.consulo.java.web.jsp.highlight.JspEditorHighlighter;
import com.intellij.openapi.editor.colors.EditorColorsScheme;
import com.intellij.openapi.editor.highlighter.EditorHighlighter;
import com.intellij.openapi.fileTypes.EditorHighlighterProvider;
import com.intellij.openapi.fileTypes.FileType;
import com.intellij.openapi.fileTypes.FileTypeEditorHighlighterProviders;
import com.intellij.openapi.fileTypes.LanguageFileType;
import com.intellij.openapi.fileTypes.TemplateLanguageFileType;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;

/**
 * @author VISTALL
 * @since 07.11.13.
 */
public class JspFileType extends LanguageFileType implements TemplateLanguageFileType
{
	public static final JspFileType INSTANCE = new JspFileType();

	protected JspFileType()
	{
		super(JspLanguage.INSTANCE);

		FileTypeEditorHighlighterProviders.INSTANCE.addExplicitExtension(this, new EditorHighlighterProvider()
		{
			@Override
			public EditorHighlighter getEditorHighlighter(@Nullable Project project, @NotNull FileType fileType, @Nullable VirtualFile virtualFile, @NotNull EditorColorsScheme colors)
			{
				return new JspEditorHighlighter(project, virtualFile, colors);
			}
		});
	}

	@NotNull
	@Override
	public String getName()
	{
		return "JSP";
	}

	@NotNull
	@Override
	public String getDescription()
	{
		return "JSP files";
	}

	@NotNull
	@Override
	public String getDefaultExtension()
	{
		return "jsp";
	}

	@Nullable
	@Override
	public Icon getIcon()
	{
		return JavaWebIcons.Jsp;
	}
}
