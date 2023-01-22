/*
 * Copyright (c) 2004 - 2009 by Fuhrer Engineering AG, CH-2504 Biel/Bienne, Switzerland
 */

package consulo.jakartaee.webServer.impl.oss.util;

import consulo.xml.util.xml.ui.GenericValueColumnInfo;

import javax.swing.*;

public class GenericColumnInfo<T> extends GenericValueColumnInfo<T>
{

    public GenericColumnInfo(String name, Class<T> type, Icon icon) {
        super(name, type, new IconTableCellRenderer(icon), new DefaultCellEditor(new JTextField()));
    }
}
