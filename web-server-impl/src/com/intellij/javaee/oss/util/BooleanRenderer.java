/*
 * Copyright (c) 2004 - 2009 by Fuhrer Engineering AG, CH-2504 Biel/Bienne, Switzerland
 */

package com.intellij.javaee.oss.util;

import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;

public class BooleanRenderer implements TableCellRenderer {

    private final TableCellRenderer defaultRenderer;

    private final TableCellRenderer booleanRenderer;

    public BooleanRenderer(JTable table) {
        defaultRenderer = table.getDefaultRenderer(Object.class);
        booleanRenderer = table.getDefaultRenderer(Boolean.class);
    }

    public Component getTableCellRendererComponent(JTable table, Object value, boolean selected, boolean focus, int row, int col) {
        TableCellRenderer renderer = (value instanceof Boolean) ? booleanRenderer : defaultRenderer;
        Component component = renderer.getTableCellRendererComponent(table, value, selected, focus, row, col);
        component.setEnabled(table.isEnabled());
        return component;
    }
}
