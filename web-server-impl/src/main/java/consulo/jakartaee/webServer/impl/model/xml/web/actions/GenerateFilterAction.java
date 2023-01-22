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
import com.intellij.javaee.model.xml.web.Filter;
import com.intellij.javaee.model.xml.web.FilterMapping;
import com.intellij.javaee.model.xml.web.WebApp;
import consulo.codeEditor.Editor;
import consulo.document.util.TextRange;
import consulo.language.editor.WriteCommandAction;
import consulo.language.editor.template.TemplateBuilder;
import consulo.language.editor.template.TemplateBuilderFactory;
import consulo.language.editor.template.VariableNode;
import consulo.language.editor.template.macro.CompleteMacro;
import consulo.language.editor.template.macro.CompleteSmartMacro;
import consulo.language.editor.template.macro.MacroCallNode;
import consulo.language.psi.PsiDocumentManager;
import consulo.language.psi.PsiElement;
import consulo.language.psi.PsiFile;
import consulo.project.Project;
import consulo.xml.psi.xml.XmlTag;
import consulo.xml.util.xml.DomElement;
import consulo.xml.util.xml.GenericDomValue;

/**
 * @author Dmitry Avdeev
 */
public class GenerateFilterAction extends CreateClassMappingAction<WebApp>
{

  public GenerateFilterAction() {
    super(WebApp.class, "javax.servlet.Filter", null);
  }

  @Override
  protected DomElement createElement(final WebApp context,
									 final Editor editor,
									 PsiFile file,
									 final Project project,
									 final PsiClass selectedClass) {

    new WriteCommandAction.Simple(project, "Create Filter", file) {
      @Override
      protected void run() throws Throwable {
        Filter filter = (Filter)createElement(context);
        filter.getFilterClass().setValue(selectedClass);

        FilterMapping mapping = context.addFilterMapping();

        TemplateBuilder builder = TemplateBuilderFactory.getInstance().createTemplateBuilder(context.getXmlElement());
        VariableNode nameNode = new VariableNode("NAME", new MacroCallNode(new CompleteMacro()));
        replaceElementValue(builder, filter.getFilterName(), nameNode);

        replaceElementValue(builder, mapping.getFilterName(), nameNode);
        GenericDomValue<String> value = mapping.addUrlPattern();
        XmlTag tag = value.ensureTagExists();
        PsiElement child = tag.getLastChild();
        while (child != null && child != tag.getFirstChild()) {
          PsiElement prevSibling = child.getPrevSibling();
          child.delete();
          child = prevSibling;
        }
        builder.replaceElement(tag, TextRange.from(1, 0), new MacroCallNode(new CompleteSmartMacro()));

        PsiDocumentManager.getInstance(project).doPostponedOperationsAndUnblockDocument(editor.getDocument());
        builder.run();
      }
    }.execute();
    return null;
  }

  @Override
  protected DomElement createElement(WebApp context) {
    return context.addFilter();
  }
}
