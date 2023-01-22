/*
 * Copyright (c) 2004 - 2009 by Fuhrer Engineering AG, CH-2504 Biel/Bienne, Switzerland
 */

package consulo.jakartaee.webServer.impl.oss.editor;

import consulo.ui.ex.awt.ColumnInfo;
import consulo.xml.util.xml.DomElement;

import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;

public abstract class JavaeeSectionInfo<T extends DomElement> extends ColumnInfo<T, String>
{

    private static final TableCellRenderer renderer = new DefaultTableCellRenderer();

    protected JavaeeSectionInfo(String name) {
        super(name);
    }

    @Override
    public TableCellRenderer getRenderer(T item) {
        return renderer;
    }
}
