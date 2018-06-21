/*
 * Copyright 2000-2010 JetBrains s.r.o.
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
package com.intellij.javaee.model.xml.web.actions;

import com.intellij.codeInsight.template.TemplateBuilder;
import com.intellij.codeInsight.template.TemplateBuilderFactory;
import com.intellij.codeInsight.template.impl.EmptyNode;
import com.intellij.codeInsight.template.impl.MacroCallNode;
import com.intellij.codeInsight.template.impl.VariableNode;
import com.intellij.codeInsight.template.macro.CompleteMacro;
import com.intellij.javaee.model.xml.web.Servlet;
import com.intellij.javaee.model.xml.web.ServletMapping;
import com.intellij.javaee.model.xml.web.WebApp;
import com.intellij.openapi.command.WriteCommandAction;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiClass;
import com.intellij.psi.PsiDocumentManager;
import com.intellij.psi.PsiFile;
import com.intellij.util.xml.DomElement;
import com.intellij.util.xml.actions.CreateClassMappingAction;

/**
 * @author Dmitry Avdeev
 */
public class GenerateServletAction extends CreateClassMappingAction<WebApp> {

  public GenerateServletAction() {
    super(WebApp.class, "javax.servlet.Servlet", null);
  }

  @Override
  protected DomElement createElement(final WebApp context,
                                     final Editor editor,
                                     PsiFile file,
                                     final Project project,
                                     final PsiClass selectedClass) {

    new WriteCommandAction.Simple(project, "Create servlet", file) {
      @Override
      protected void run() throws Throwable {
        Servlet servlet = (Servlet)createElement(context);
        servlet.getServletClass().setValue(selectedClass);

        ServletMapping mapping = context.addServletMapping();

        TemplateBuilder builder = TemplateBuilderFactory.getInstance().createTemplateBuilder(context.getXmlElement());
        VariableNode nameNode = new VariableNode("NAME", new MacroCallNode(new CompleteMacro()));
        replaceElementValue(builder, servlet.getServletName(), nameNode);

        replaceElementValue(builder, mapping.getServletName(), nameNode);
        replaceElementValue(builder, mapping.addUrlPattern(), new EmptyNode());

        PsiDocumentManager.getInstance(project).doPostponedOperationsAndUnblockDocument(editor.getDocument());
        builder.run();
      }
    }.execute();
    return null;
  }

  @Override
  protected DomElement createElement(WebApp context) {
    return context.addServlet();
  }
}
