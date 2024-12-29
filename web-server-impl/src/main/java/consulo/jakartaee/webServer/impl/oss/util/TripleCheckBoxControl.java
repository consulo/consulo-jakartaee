/*
 * Copyright (c) 2004 - 2009 by Fuhrer Engineering AG, CH-2504 Biel/Bienne, Switzerland
 */

package consulo.jakartaee.webServer.impl.oss.util;

import consulo.xml.util.xml.ui.BaseModifiableControl;
import consulo.xml.util.xml.ui.DomWrapper;

import jakarta.annotation.Nullable;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TripleCheckBoxControl extends BaseModifiableControl<TripleCheckBox, Boolean>
{

    public TripleCheckBoxControl(DomWrapper<Boolean> wrapper) {
        super(wrapper);
    }

    @Override
    protected TripleCheckBox createMainComponent(TripleCheckBox component) {
        TripleCheckBox box = component;
        if (box == null) {
            box = new TripleCheckBox();
        }
        box.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                setModified();
                commit();
            }
        });
        return box;
    }

    @Override
    @Nullable
    public Boolean getValue() {
        return getComponent().getValue();
    }

    @Override
    public void setValue(@Nullable Boolean value) {
        getComponent().setValue(value);
    }
}
