/*
 * Copyright (c) 2004 - 2009 by Fuhrer Engineering AG, CH-2504 Biel/Bienne, Switzerland
 */

package com.intellij.javaee.oss.editor;

import com.intellij.util.ui.ColumnInfo;
import com.intellij.util.xml.DomElement;

import javax.swing.table.*;

public abstract class JavaeeSectionInfo<T extends DomElement> extends ColumnInfo<T, String> {

    private static final TableCellRenderer renderer = new DefaultTableCellRenderer();

    protected JavaeeSectionInfo(String name) {
        super(name);
    }

    @Override
    public TableCellRenderer getRenderer(T item) {
        return renderer;
    }
}
