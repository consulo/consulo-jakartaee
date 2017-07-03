/*
 * Copyright (c) 2004 - 2009 by Fuhrer Engineering AG, CH-2504 Biel/Bienne, Switzerland
 */

package com.intellij.javaee.oss.util;

import com.intellij.openapi.actionSystem.AnActionEvent;

import javax.swing.*;

public class TableSourceAction extends OpenSourceAction {

    private final JTable table;

    public TableSourceAction(JTable table) {
        super(table);
        this.table = table;
    }

    @Override
    public void update(AnActionEvent event) {
        event.getPresentation().setEnabled(table.getSelectedRowCount() > 0);
    }
}
