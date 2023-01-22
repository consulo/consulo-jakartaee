/*
 * Copyright (c) 2004 - 2009 by Fuhrer Engineering AG, CH-2504 Biel/Bienne, Switzerland
 */

package consulo.jakartaee.webServer.impl.oss.util;

import consulo.ui.ex.TreeExpander;
import consulo.ui.ex.awt.tree.TreeUtil;

import javax.swing.*;

public class TreeExpanderImpl implements TreeExpander
{

    private final JTree tree;

    public TreeExpanderImpl(JTree tree) {
        this.tree = tree;
    }

    public void expandAll() {
        TreeUtil.expandAll(tree);
    }

    public boolean canExpand() {
        return tree.getRowCount() > 0;
    }

    public void collapseAll() {
        TreeUtil.collapseAll(tree, 0);
    }

    public boolean canCollapse() {
        return tree.getRowCount() > 0;
    }
}
