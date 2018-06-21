/*
 * Copyright (c) 2004 - 2009 by Fuhrer Engineering AG, CH-2504 Biel/Bienne, Switzerland
 */

package com.intellij.javaee.oss.editor;

import com.intellij.javaee.oss.JavaeeBundle;
import com.intellij.openapi.actionSystem.*;
import com.intellij.openapi.application.Result;
import com.intellij.openapi.command.WriteCommandAction;
import com.intellij.util.xml.DomElement;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;

public abstract class JavaeeResetEditor<T extends DomElement> extends JavaeeBaseEditor {

    private final T element;

    private final JPanel main = new JPanel(new BorderLayout());

    protected JavaeeResetEditor(T element) {
        this.element = element;
        main.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        AnAction revert = new AnAction(JavaeeBundle.getText("GenericAction.reset"), null, JavaeeBundle.getIcon("/general/reset.png")) {
            @Override
            public void actionPerformed(AnActionEvent event) {
                new WriteCommandAction<Object>(getElement().getManager().getProject()) {
                    @Override
                    protected void run(Result<Object> result) throws Throwable {
                        reset(getElement());
                    }
                }.execute();
            }

            @Override
            public void update(AnActionEvent event) {
                event.getPresentation().setEnabled(getElement().getXmlTag() != null);
            }
        };
        DefaultActionGroup group = new DefaultActionGroup();
        group.add(revert);
        JComponent toolbar = ActionManager.getInstance().createActionToolbar(ActionPlaces.UNKNOWN, group, true).getComponent();
        Border outer = BorderFactory.createMatteBorder(0, 0, 1, 0, Color.darkGray);
        toolbar.setBorder(BorderFactory.createCompoundBorder(outer, toolbar.getBorder()));
        main.add(toolbar, BorderLayout.NORTH);
    }

    public JComponent getComponent() {
        return main;
    }

    protected abstract void reset(T element);

    protected T getElement() {
        return element;
    }

    protected void setContent(JComponent content) {
        main.add(content, BorderLayout.CENTER);
        content.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createEmptyBorder(5, 0, 0, 0), content.getBorder()));
    }
}
