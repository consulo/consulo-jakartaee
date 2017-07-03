/*
 * Copyright (c) 2004 - 2009 by Fuhrer Engineering AG, CH-2504 Biel/Bienne, Switzerland
 */

package com.intellij.javaee.oss.util;

import java.awt.event.ItemEvent;
import java.util.Arrays;
import java.util.List;

import javax.swing.Icon;
import javax.swing.JCheckBox;
import javax.swing.plaf.metal.MetalIconFactory;

import org.jetbrains.annotations.Nullable;

public class TripleCheckBox extends JCheckBox {

    private static final Icon METAL_ICON = new TripleCheckBoxIcon(MetalIconFactory.getCheckBoxIcon());

    private static final List<Boolean> values = Arrays.asList(null, Boolean.TRUE, Boolean.FALSE);

    private int state;

    public TripleCheckBox() {
        setModel(new ToggleButtonModel() {
            @Override
            public void setSelected(boolean selected) {
                state = (state + 1) % 3;
                fireStateChanged();
                fireItemStateChanged(new ItemEvent(this, ItemEvent.ITEM_STATE_CHANGED, this, ItemEvent.SELECTED));
            }

            @Override
            public boolean isSelected() {
                return Boolean.TRUE.equals(getValue());
            }
        });
    }

    @Nullable
    public Boolean getValue() {
        return values.get(state);
    }

    public void setValue(@Nullable Boolean value) {
        state = values.indexOf(value);
        repaint();
    }

    @Override
    public void updateUI() {
        super.updateUI();
        setIcon(METAL_ICON);
    }
}
