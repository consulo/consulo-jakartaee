/*
 * Copyright (c) 2004 - 2009 by Fuhrer Engineering AG, CH-2504 Biel/Bienne, Switzerland
 */

package com.intellij.javaee.oss.util;

import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;

public class IconTableCellRenderer extends DefaultTableCellRenderer {

    private final Icon icon;

    public IconTableCellRenderer(Icon icon) {
        this.icon = icon;
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean selected, boolean focus, int row, int column) {
        super.getTableCellRendererComponent(table, value, selected, focus, row, column);
        setIcon((value == null) ? null : icon);
        return this;
    }
}
