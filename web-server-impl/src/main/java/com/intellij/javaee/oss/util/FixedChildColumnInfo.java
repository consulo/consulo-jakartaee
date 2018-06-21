/*
 * Copyright (c) 2004 - 2009 by Fuhrer Engineering AG, CH-2504 Biel/Bienne, Switzerland
 */

package com.intellij.javaee.oss.util;

import com.intellij.util.xml.DomElement;
import com.intellij.util.xml.reflect.DomGenericInfo;
import com.intellij.util.xml.ui.ChildGenericValueColumnInfo;
import org.jetbrains.annotations.NonNls;

import javax.swing.*;

public class FixedChildColumnInfo<T extends DomElement> extends ChildGenericValueColumnInfo<T> {

    public FixedChildColumnInfo(String name, DomGenericInfo info, @NonNls String tag, Icon icon) {
        super(name, info.getFixedChildDescription(tag), new IconTableCellRenderer(icon), new DefaultCellEditor(new JTextField()));
    }
}
