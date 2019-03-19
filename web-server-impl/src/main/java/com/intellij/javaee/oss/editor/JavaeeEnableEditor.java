/*
 * Copyright (c) 2004 - 2009 by Fuhrer Engineering AG, CH-2504 Biel/Bienne, Switzerland
 */

package com.intellij.javaee.oss.editor;

import com.intellij.javaee.oss.JavaeeBundle;
import com.intellij.openapi.application.Result;
import com.intellij.openapi.command.WriteCommandAction;
import com.intellij.openapi.util.Factory;
import com.intellij.util.xml.DomElement;
import javax.annotation.Nonnull;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;

public abstract class JavaeeEnableEditor<T extends DomElement, P extends DomElement> extends JavaeeBaseEditor {

    private final T element;

    private final JPanel main;

    private final JCheckBox enabler;

    private JComponent content;

    protected JavaeeEnableEditor(String title, @Nonnull final P parent) {
        element = parent.getManager().createStableValue(new Factory<T>() {
            public T create() {
                return createElement(parent);
            }
        });
        main = new JPanel(new BorderLayout());
        main.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        enabler = new JCheckBox(JavaeeBundle.message("EnableEditor.enable", title));
        enabler.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                new WriteCommandAction<Object>(element.getManager().getProject()) {
                    @Override
                    protected void run(Result<Object> result) throws Throwable {
                        if (enabler.isSelected()) {
                            element.ensureTagExists();
                        } else {
                            element.undefine();
                            undefined(parent);
                        }
                    }
                }.execute();
            }
        });
        JPanel panel = new JPanel(new BorderLayout());
        Border inner = BorderFactory.createMatteBorder(0, 0, 1, 0, Color.darkGray);
        Border outer = BorderFactory.createEmptyBorder(0, 0, 5, 0);
        panel.setBorder(BorderFactory.createCompoundBorder(outer, inner));
        panel.add(enabler, BorderLayout.WEST);
        main.add(panel, BorderLayout.NORTH);
    }

    public JComponent getComponent() {
        return main;
    }

    @Override
    public void reset() {
        super.reset();
        enabler.setSelected(element.getXmlTag() != null);
        enableChildren(content, element.getXmlTag() != null);
    }

    protected abstract T createElement(@Nonnull P parent);

    protected T getElement() {
        return element;
    }

    protected void undefined(@Nonnull P parent) {
    }

    protected void setContent(JComponent content) {
        this.content = content;
        main.add(content, BorderLayout.CENTER);
        markAsRoot(content);
    }
}
