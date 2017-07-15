/*
 * Copyright (c) 2004 - 2009 by Fuhrer Engineering AG, CH-2504 Biel/Bienne, Switzerland
 */

package com.intellij.javaee.oss.util;

import javax.swing.*;
import java.awt.*;
import java.awt.image.*;

class TripleCheckBoxIcon implements Icon {

    private final Icon parent;

    private final Paint paint;

    TripleCheckBoxIcon(Icon parent) {
        this.parent = parent;
        BufferedImage img = new BufferedImage(2, 2, BufferedImage.TYPE_INT_ARGB);
        Graphics g = img.createGraphics();
        g.setColor(Color.black);
        g.fillRect(0, 0, 1, 1);
        g.fillRect(1, 1, 1, 1);
        paint = new TexturePaint(img, new Rectangle(2, 2));
    }

    public void paintIcon(Component c, Graphics g, int x, int y) {
        if (c.isEnabled() && (((TripleCheckBox) c).getValue() == null)) {
            ((Graphics2D) g).setPaint(paint);
            g.fillRect(x, y, getIconWidth(), getIconHeight());
        }
        parent.paintIcon(c, g, x, y);
    }

    public int getIconWidth() {
        return parent.getIconWidth();
    }

    public int getIconHeight() {
        return parent.getIconHeight();
    }
}
