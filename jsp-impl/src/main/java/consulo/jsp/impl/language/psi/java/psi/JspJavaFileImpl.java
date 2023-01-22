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

package consulo.jsp.impl.language.psi.java.psi;

import com.intellij.java.language.impl.psi.impl.PsiImplUtil;
import com.intellij.java.language.impl.psi.impl.source.PsiJavaFileBaseImpl;
import com.intellij.java.language.psi.PsiClass;
import com.intellij.java.language.psi.PsiJavaCodeReferenceElement;
import com.intellij.java.language.psi.PsiJavaFile;
import consulo.annotation.access.RequiredReadAction;
import consulo.jsp.impl.language.JspFileType;
import consulo.jsp.impl.language.psi.java.JspJavaStubElements;
import consulo.language.file.FileViewProvider;
import consulo.language.file.light.LightVirtualFile;
import consulo.language.psi.PsiElement;
import consulo.module.content.ProjectFileIndex;
import consulo.util.collection.ArrayUtil;
import consulo.util.lang.StringUtil;
import consulo.virtualFileSystem.VirtualFile;
import consulo.virtualFileSystem.fileType.FileType;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/**
 * @author VISTALL
 * @since 08.11.13.
 */
public class JspJavaFileImpl extends PsiJavaFileBaseImpl implements PsiJavaFile
{
	private static final String[] ourImplicityImports = {
			"java.lang",
			"javax.servlet",
			"javax.servlet.jsp",
			"javax.servlet.http"
	};

	private final JspxImportListImpl myImportList = new JspxImportListImpl(this);

	public JspJavaFileImpl(FileViewProvider viewProvider)
	{
		super(JspJavaStubElements.JAVA_IN_JSP_FILE, JspJavaStubElements.JAVA_IN_JSP_FILE, viewProvider);
	}

	@Nonnull
	@Override
	public String getPackageName()
	{
		VirtualFile virtualFile = getViewProvider().getVirtualFile();

		if(virtualFile instanceof LightVirtualFile)
		{
			virtualFile = ((LightVirtualFile) virtualFile).getOriginalFile();
		}

		VirtualFile parentFile = null;
		if(virtualFile == null || (parentFile = virtualFile.getParent()) != null)
		{
			return "";
		}

		String packageName = ProjectFileIndex.getInstance(getProject()).getPackageNameByDirectory(parentFile);
		if(StringUtil.isEmpty(packageName))
		{
			return "";
		}
		return packageName;
	}

	@Override
	public void subtreeChanged()
	{
		super.subtreeChanged();
	}

	@Nonnull
	@Override
	public PsiClass[] getClasses()
	{
		return findChildrenByClass(PsiClass.class);
	}

	@Nonnull
	@Override
	public String[] getImplicitlyImportedPackages()
	{
		return ourImplicityImports;
	}

	@Override
	@Nonnull
	public PsiJavaCodeReferenceElement[] getImplicitlyImportedPackageReferences()
	{
		return PsiImplUtil.namesToPackageReferences(myManager, getImplicitlyImportedPackages());
	}

	@RequiredReadAction
	@Nullable
	@Override
	public JspxImportListImpl getImportList()
	{
		return myImportList;
	}

	@RequiredReadAction
	@Nonnull
	@Override
	public PsiElement[] getChildren()
	{
		return ArrayUtil.prepend(myImportList, super.getChildren());
	}

	@Nonnull
	@Override
	public FileType getFileType()
	{
		return JspFileType.INSTANCE;
	}
}
