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
import com.intellij.extapi.psi.PsiFileBase;
import com.intellij.openapi.fileTypes.FileType;
import com.intellij.psi.FileViewProvider;
import consulo.javaee.jsp.JspFileType;
import consulo.javaee.jsp.JspLanguage;
import consulo.javaee.jsp.psi.JspFile;

/**
 * @author VISTALL
 * @since 08.11.13.
 */
public class JspFileImpl extends PsiFileBase implements JspFile
{
	public JspFileImpl(@NotNull FileViewProvider viewProvider)
	{
		super(viewProvider, JspLanguage.INSTANCE);
	}

	@NotNull
	@Override
	public FileType getFileType()
	{
		return JspFileType.INSTANCE;
	}
}
