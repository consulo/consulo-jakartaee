/*
 * Copyright 2000-2009 JetBrains s.r.o.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package com.intellij.javaee.web;

import com.intellij.java.language.psi.PsiClass;
import com.intellij.javaee.model.common.JavaeeModelElement;
import consulo.xml.util.xml.GenericValue;
import consulo.xml.util.xml.MutableGenericValue;
import consulo.xml.util.xml.NameValue;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;

/**
 * @author Dmitry Avdeev
 */
public interface CommonFilter extends JavaeeModelElement
{
	@Nonnull
	@NameValue
	MutableGenericValue<String> getFilterName();

	List<? extends GenericValue<String>> getUrlPatterns();

	List<? extends GenericValue<? extends CommonServlet>> getServletNames();

	List<? extends CommonParamValue> getInitParams();

	CommonParamValue addInitParam();

	@Nullable
	PsiClass getPsiClass();
}
