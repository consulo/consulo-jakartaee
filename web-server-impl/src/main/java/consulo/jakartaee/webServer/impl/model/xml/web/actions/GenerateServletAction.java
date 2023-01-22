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
package consulo.jakartaee.webServer.impl.model.xml.web.actions;

import com.intellij.java.impl.util.xml.actions.CreateClassMappingAction;
import com.intellij.java.language.psi.PsiClass;
import com.intellij.javaee.model.xml.web.Servlet;
import com.intellij.javaee.model.xml.web.ServletMapping;
import com.intellij.javaee.model.xml.web.WebApp;
import consulo.codeEditor.Editor;
import consulo.language.editor.WriteCommandAction;
import consulo.language.editor.template.EmptyNode;
import consulo.language.editor.template.TemplateBuilder;
import consulo.language.editor.template.TemplateBuilderFactory;
import consulo.language.editor.template.VariableNode;
import consulo.language.editor.template.macro.CompleteMacro;
import consulo.language.editor.template.macro.MacroCallNode;
import consulo.language.psi.PsiDocumentManager;
import consulo.language.psi.PsiFile;
import consulo.project.Project;
import consulo.xml.util.xml.DomElement;

/**
 * @author Dmitry Avdeev
 */
public class GenerateServletAction extends CreateClassMappingAction<WebApp>
{

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
