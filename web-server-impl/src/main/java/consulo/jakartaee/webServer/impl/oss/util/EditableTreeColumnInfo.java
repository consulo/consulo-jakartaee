/*
 * Copyright (c) 2004 - 2009 by Fuhrer Engineering AG, CH-2504 Biel/Bienne, Switzerland
 */

package consulo.jakartaee.webServer.impl.oss.util;


import consulo.application.Result;
import consulo.ide.impl.idea.ui.treeStructure.treetable.TreeColumnInfo;
import consulo.language.editor.WriteCommandAction;
import consulo.project.Project;
import consulo.ui.ex.tree.NodeDescriptor;
import consulo.xml.util.xml.DomElement;

import javax.swing.*;
import javax.swing.table.TableCellEditor;

public abstract class EditableTreeColumnInfo<T extends DomElement> extends TreeColumnInfo {
    private static final DefaultCellEditor editor = new DefaultCellEditor(new JTextField());

    private final Project project;

    private final Class<T> type;

    protected EditableTreeColumnInfo(Project project, Class<T> type) {
        super("");
        this.project = project;
        this.type = type;
    }

    @Override
    public boolean isCellEditable(Object item) {
        return (item instanceof NodeDescriptor) && type.isAssignableFrom(((NodeDescriptor<?>) item).getElement().getClass());
    }

    @Override
    public TableCellEditor getEditor(Object item) {
        return editor;
    }

    @Override
    public void setValue(final Object item, final Object value) {
        if (isCellEditable(item)) {
            new WriteCommandAction<Object>(project) {
                @Override
                protected void run(Result<Object> result) throws Throwable {
                    storeValue(type.cast(((NodeDescriptor<?>) item).getElement()), (String) value);
                }
            }.execute();
        }
    }

    protected abstract void storeValue(T element, String value);
}
