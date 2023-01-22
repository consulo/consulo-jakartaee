/*
 * Copyright 2000-2007 JetBrains s.r.o.
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

package com.intellij.javaee.web;

import consulo.language.psi.PsiElement;
import consulo.language.psi.PsiReference;
import consulo.language.psi.path.PathReference;
import consulo.language.psi.path.PathReferenceManager;
import consulo.language.psi.path.PathReferenceProvider;
import consulo.xml.psi.xml.XmlElement;
import consulo.xml.util.xml.ConvertContext;
import consulo.xml.util.xml.converters.PathReferenceConverter;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/**
 * @author Dmitry Avdeev
 */
public class StaticPathReferenceConverter extends PathReferenceConverter
{

	private final PathReferenceProvider myStaticProvider = PathReferenceManager.getInstance().createStaticPathReferenceProvider(false);

	public PathReference fromString(@Nullable final String s, final ConvertContext context)
	{
		if(s == null)
		{
			return null;
		}
		final XmlElement element = context.getReferenceXmlElement();
		return element != null ? myStaticProvider.getPathReference(s, element) : null;
	}

	@Nonnull
	public PsiReference[] createReferences(@Nonnull final PsiElement psiElement, final boolean soft)
	{
		return PathReferenceManager.getReferencesFromProvider(myStaticProvider, psiElement, soft);
	}
}
