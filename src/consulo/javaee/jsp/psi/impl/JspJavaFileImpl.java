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
import com.intellij.openapi.fileTypes.FileType;
import com.intellij.psi.FileViewProvider;
import com.intellij.psi.PsiJavaFile;
import com.intellij.psi.impl.source.PsiJavaFileBaseImpl;
import consulo.annotations.RequiredReadAction;
import consulo.javaee.jsp.JspFileType;
import consulo.javaee.jsp.psi.impl.java.parsing.JavaInJspParser;
import consulo.javaee.jsp.psi.impl.java.psi.JspxImportListImpl;

/**
 * @author VISTALL
 * @since 08.11.13.
 */
public class JspJavaFileImpl extends PsiJavaFileBaseImpl implements PsiJavaFile
{
	private JspxImportListImpl myImportList = new JspxImportListImpl(this);

	public JspJavaFileImpl(FileViewProvider viewProvider)
	{
		super(JavaInJspParser.JAVA_IN_JSP_FILE_TYPE, JavaInJspParser.JAVA_IN_JSP_FILE_TYPE, viewProvider);
	}

	@RequiredReadAction
	@Nullable
	@Override
	public JspxImportListImpl getImportList()
	{
		return myImportList;
	}

	@NotNull
	@Override
	public FileType getFileType()
	{
		return JspFileType.INSTANCE;
	}
}
