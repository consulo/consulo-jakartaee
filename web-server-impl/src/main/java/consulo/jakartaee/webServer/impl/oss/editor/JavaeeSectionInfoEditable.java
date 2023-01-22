/*
 * Copyright (c) 2004 - 2009 by Fuhrer Engineering AG, CH-2504 Biel/Bienne, Switzerland
 */

package consulo.jakartaee.webServer.impl.oss.editor;

import com.intellij.javaee.model.xml.JavaeeDomModelElement;
import consulo.application.Result;
import consulo.language.editor.WriteCommandAction;
import consulo.project.Project;

import javax.swing.*;
import javax.swing.table.TableCellEditor;

public abstract class JavaeeSectionInfoEditable<T extends JavaeeDomModelElement> extends JavaeeSectionInfo<T> {

    private static final DefaultCellEditor editor = new DefaultCellEditor(new JTextField());

    private final Project project;

    protected JavaeeSectionInfoEditable(String name, JavaeeDomModelElement element) {
        super(name);
        project = element.getManager().getProject();
    }

    @Override
    public void setValue(final T item, final String value) {
        new WriteCommandAction<Object>(project) {
            @Override
            protected void run(Result<Object> result) throws Throwable {
                write(item, value);
            }
        }.execute();
    }

    @Override
    public TableCellEditor getEditor(T item) {
        return editor;
    }

    @Override
    public boolean isCellEditable(T item) {
        return true;
    }

    protected abstract void write(T item, String value);
}
