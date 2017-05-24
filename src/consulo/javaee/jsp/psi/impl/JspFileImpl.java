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

package consulo.javaee.jsp.psi.impl;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import com.intellij.lang.jsp.JspxFileViewProvider;
import com.intellij.openapi.fileTypes.FileType;
import com.intellij.psi.FileViewProvider;
import com.intellij.psi.PsiClass;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.psi.impl.source.xml.XmlFileImpl;
import com.intellij.psi.jsp.JspDirectiveKind;
import com.intellij.psi.jsp.JspFile;
import com.intellij.psi.xml.XmlDocument;
import com.intellij.psi.xml.XmlTag;
import consulo.javaee.jsp.JspFileType;
import consulo.javaee.jsp.JspParserDefinition;

/**
 * @author VISTALL
 * @since 08.11.13.
 */
public class JspFileImpl extends XmlFileImpl implements JspFile
{
	public JspFileImpl(@NotNull FileViewProvider viewProvider)
	{
		super(viewProvider, JspParserDefinition.FILE_ELEMENT_TYPE);
	}

	@NotNull
	@Override
	public JspxFileViewProvider getViewProvider()
	{
		return (JspxFileViewProvider) super.getViewProvider();
	}

	@NotNull
	@Override
	public FileType getFileType()
	{
		return JspFileType.INSTANCE;
	}

	@Override
	public PsiElement[] getContentsElements()
	{
		return new PsiElement[0];
	}

	@Override
	public boolean isErrorPage()
	{
		return false;
	}

	@Override
	public boolean isSessionPage()
	{
		return false;
	}

	@Override
	public boolean isTagPage()
	{
		return false;
	}

	@Override
	public XmlTag[] getDirectiveTags(JspDirectiveKind directiveKind, boolean searchInIncludes)
	{
		XmlDocument document = getDocument();
		
		XmlTag[] subTags = getRootTag().getSubTags();
		
		return new XmlTag[0];
	}

	@Override
	public XmlTag createDirective(XmlTag context, JspDirectiveKind directiveKind)
	{
		return null;
	}

	@Override
	public XmlTag createDirective(JspDirectiveKind directiveKind)
	{
		return null;
	}

	@Override
	public PsiFile getBaseLanguageRoot()
	{
		return null;
	}

	@Override
	public PsiFile getErrorPage()
	{
		return null;
	}

	@Override
	public XmlTag[] getDirectiveTagsInContext(JspDirectiveKind directiveKind)
	{
		XmlTag[] subTags = getRootTag().getSubTags();

		return new XmlTag[0];
	}

	@Nullable
	@Override
	public PsiClass getJavaClass()
	{
		return null;
	}
}
