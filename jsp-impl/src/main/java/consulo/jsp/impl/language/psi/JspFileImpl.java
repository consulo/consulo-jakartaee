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

package consulo.jsp.impl.language.psi;

import com.intellij.java.language.JavaLanguage;
import com.intellij.java.language.psi.PsiClass;
import com.intellij.java.language.psi.PsiJavaFile;
import consulo.annotation.access.RequiredReadAction;
import consulo.jsp.impl.language.JspFileType;
import consulo.jsp.impl.language.parser.JspParserDefinition;
import consulo.jsp.language.file.JspxFileViewProvider;
import consulo.jsp.language.psi.JspDirectiveKind;
import consulo.jsp.language.psi.JspFile;
import consulo.jsp.language.psi.xml.JspDirective;
import consulo.language.file.FileViewProvider;
import consulo.language.psi.PsiElement;
import consulo.language.psi.PsiFile;
import consulo.util.collection.ArrayUtil;
import consulo.util.collection.SmartList;
import consulo.virtualFileSystem.fileType.FileType;
import consulo.xml.psi.impl.source.xml.XmlFileImpl;
import consulo.xml.psi.xml.XmlTag;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;

/**
 * @author VISTALL
 * @since 08.11.13.
 */
public class JspFileImpl extends XmlFileImpl implements JspFile
{
	public JspFileImpl(@Nonnull FileViewProvider viewProvider)
	{
		super(viewProvider, JspParserDefinition.FILE_ELEMENT_TYPE);
	}

	@Nonnull
	@Override
	public JspxFileViewProvider getViewProvider()
	{
		return (JspxFileViewProvider) super.getViewProvider();
	}

	@Nonnull
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

	@RequiredReadAction
	@Override
	public boolean isErrorPage()
	{
		XmlTag[] directiveTags = getDirectiveTags(JspDirectiveKind.PAGE, false);

		for(XmlTag directiveTag : directiveTags)
		{
			String isErrorPage = directiveTag.getAttributeValue("isErrorPage");
			if(isErrorPage != null && "true".equals(isErrorPage))
			{
				return true;
			}
		}
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

	@Nonnull
	@RequiredReadAction
	@Override
	public XmlTag[] getDirectiveTags(JspDirectiveKind directiveKind, boolean searchInIncludes)
	{
		XmlTag rootTag = getRootTag();
		if(rootTag == null)
		{
			return XmlTag.EMPTY;
		}
		List<XmlTag> list = new SmartList<>();
		XmlTag[] subTags = rootTag.getSubTags();
		for(XmlTag subTag : subTags)
		{
			if(kind(subTag) == directiveKind)
			{
				list.add(subTag);
			}
		}
		return list.toArray(XmlTag.EMPTY);
	}

	@Nullable
	private JspDirectiveKind kind(XmlTag tag)
	{
		if(!(tag instanceof JspDirective))
		{
			return null;
		}
		String localName = tag.getLocalName();
		switch(localName)
		{
			case "page":
				return JspDirectiveKind.PAGE;
			case "include":
				return JspDirectiveKind.INCLUDE;
			default:
				return null;
		}
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
		return new XmlTag[0];
	}

	@Nullable
	@Override
	public PsiClass getJavaClass()
	{
		PsiFile file = getViewProvider().getPsi(JavaLanguage.INSTANCE);
		if(!(file instanceof PsiJavaFile))
		{
			return null;
		}
		return ArrayUtil.getFirstElement(((PsiJavaFile) file).getClasses());
	}
}
