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

package org.mustbe.consulo.java.web.jsp.psi.impl;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.mustbe.consulo.java.web.jsp.psi.JspJavaFile;
import com.intellij.extapi.psi.PsiFileBase;
import com.intellij.ide.highlighter.JavaFileType;
import com.intellij.lang.java.JavaLanguage;
import com.intellij.openapi.fileTypes.FileType;
import com.intellij.pom.java.LanguageLevel;
import com.intellij.psi.FileViewProvider;
import com.intellij.psi.PsiClass;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiImportList;
import com.intellij.psi.PsiJavaCodeReferenceElement;
import com.intellij.psi.PsiPackageStatement;
import com.intellij.util.IncorrectOperationException;

/**
 * @author VISTALL
 * @since 08.11.13.
 */
public class JspJavaFileImpl extends PsiFileBase implements JspJavaFile
{
	public JspJavaFileImpl(FileViewProvider viewProvider)
	{
		super(viewProvider, JavaLanguage.INSTANCE);
	}

	@NotNull
	@Override
	public FileType getFileType()
	{
		return JavaFileType.INSTANCE;
	}

	@Nullable
	@Override
	public PsiPackageStatement getPackageStatement()
	{
		return null;
	}

	@NotNull
	@Override
	public PsiClass[] getClasses()
	{
		return new PsiClass[0];
	}

	@NotNull
	@Override
	public String getPackageName()
	{
		return null;
	}

	@Override
	public void setPackageName(String s) throws IncorrectOperationException
	{

	}

	@Nullable
	@Override
	public PsiImportList getImportList()
	{
		return null;
	}

	@NotNull
	@Override
	public PsiElement[] getOnDemandImports(boolean b, @Deprecated boolean b2)
	{
		return new PsiElement[0];
	}

	@NotNull
	@Override
	public PsiClass[] getSingleClassImports(@Deprecated boolean b)
	{
		return new PsiClass[0];
	}

	@NotNull
	@Override
	public String[] getImplicitlyImportedPackages()
	{
		return new String[0];
	}

	@NotNull
	@Override
	public PsiJavaCodeReferenceElement[] getImplicitlyImportedPackageReferences()
	{
		return new PsiJavaCodeReferenceElement[0];
	}

	@Nullable
	@Override
	public PsiJavaCodeReferenceElement findImportReferenceTo(PsiClass psiClass)
	{
		return null;
	}

	@NotNull
	@Override
	public LanguageLevel getLanguageLevel()
	{
		return LanguageLevel.JDK_1_8;
	}

	@Override
	public boolean importClass(PsiClass psiClass)
	{
		return false;
	}
}
