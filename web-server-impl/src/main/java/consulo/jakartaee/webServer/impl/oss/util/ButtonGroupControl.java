/*
 * Copyright (c) 2004 - 2009 by Fuhrer Engineering AG, CH-2504 Biel/Bienne, Switzerland
 */

package consulo.jakartaee.webServer.impl.oss.util;

import consulo.xml.util.xml.ui.BaseModifiableControl;
import consulo.xml.util.xml.ui.DomWrapper;

import jakarta.annotation.Nullable;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Enumeration;

public class ButtonGroupControl extends BaseModifiableControl<JComponent, String>
{

    private final JComponent parent;

    private final ButtonGroup group;

    public ButtonGroupControl(DomWrapper<String> wrapper, AbstractButton... buttons) {
        super(wrapper);
        parent = (JComponent) buttons[0].getParent();
        group = new ButtonGroup();
        for (AbstractButton button : buttons) {
            group.add(button);
            button.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    setModified();
                    commit();
                }
            });
        }
    }

    @Override
    protected JComponent createMainComponent(JComponent component) {
        return (component != null) ? component : parent;
    }

    @Override
    @Nullable
    public String getValue() {
        return parent.isEnabled() ? group.getSelection().getActionCommand() : null;
    }

    @Override
	public void setValue(@Nullable String value) {
        for (Enumeration<AbstractButton> e = group.getElements(); e.hasMoreElements();) {
            ButtonModel model = e.nextElement().getModel();
            String cmd = model.getActionCommand();
            group.setSelected(model, (value == null) ? ((cmd == null) || (cmd.length() == 0)) : value.equals(cmd));
        }
    }
}
