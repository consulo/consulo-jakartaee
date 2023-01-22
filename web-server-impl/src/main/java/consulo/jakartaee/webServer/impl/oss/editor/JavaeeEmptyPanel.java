/*
 * Copyright (c) 2004 - 2009 by Fuhrer Engineering AG, CH-2504 Biel/Bienne, Switzerland
 */

package consulo.jakartaee.webServer.impl.oss.editor;

import consulo.xml.util.xml.ui.CommittablePanel;

import javax.swing.*;
import java.awt.*;

class JavaeeEmptyPanel implements CommittablePanel
{

    private final JPanel panel = new JPanel(new BorderLayout());

    JavaeeEmptyPanel() {
        JLabel label = new JLabel("Not implemented yet");
        label.setFont(label.getFont().deriveFont(18.0f));
        label.setForeground(Color.lightGray);
        label.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(label, BorderLayout.CENTER);
    }

    public JComponent getComponent() {
        return panel;
    }

    public void commit() {
    }

    public void reset() {
    }

    public void dispose() {
    }
}
