/*
 * Copyright (c) 2004 - 2009 by Fuhrer Engineering AG, CH-2504 Biel/Bienne, Switzerland
 */

package com.intellij.javaee.oss.editor;

import com.intellij.openapi.ui.Splitter;
import com.intellij.ui.GuiUtils;
import com.intellij.util.Consumer;
import com.intellij.util.xml.ui.CommittablePanel;
import com.intellij.util.xml.ui.CompositeCommittable;
import org.jetbrains.annotations.NonNls;

import javax.swing.*;
import java.awt.*;
import java.util.HashSet;
import java.util.Set;

public abstract class JavaeeBaseEditor extends CompositeCommittable implements CommittablePanel {

    @NonNls
    private static final String ROOT = "root";

    protected final void markAsRoot(JComponent component) {
        component.putClientProperty(ROOT, ROOT);
    }

    protected final void enableChildren(JComponent container, boolean enabled) {
        final Set<JComponent> excluded = new HashSet<JComponent>();
        GuiUtils.iterateChildren(container, new Consumer<Component>() {
            public void consume(Component component) {
                if ((component instanceof JComponent) && (((JComponent) component).getClientProperty(ROOT) != null)) {
                    excluded.add((JComponent) component);
                }
            }
        });
        excluded.remove(container);
        GuiUtils.enableChildren(container, enabled, excluded.toArray(new JComponent[excluded.size()]));
    }

    protected final void addContent(JPanel panel, Component content) {
        panel.setLayout(new BorderLayout());
        panel.add(content, BorderLayout.CENTER);
    }

    protected final void addContent(JPanel panel, CommittablePanel content) {
        panel.setLayout(new BorderLayout());
        panel.add(content.getComponent(), BorderLayout.CENTER);
        addComponent(content);
    }

    protected final void addContent(JTabbedPane pane, CommittablePanel content, String title) {
        pane.add(title, content.getComponent());
        addComponent(content);
    }

    protected final void addContent(JTabbedPane pane, Component content, String title) {
        pane.add(title, content);
    }

    protected final Splitter createSplitter(CommittablePanel first, CommittablePanel second, boolean vertical) {
        Splitter splitter = createSplitter(vertical);
        setFirstPanel(splitter, first);
        setSecondPanel(splitter, second);
        return splitter;
    }

    protected final Splitter createSplitter(CommittablePanel first, JComponent second, boolean vertical) {
        Splitter splitter = createSplitter(vertical);
        setFirstPanel(splitter, first);
        splitter.setSecondComponent(second);
        return splitter;
    }

    protected final Splitter createSplitter(JComponent first, CommittablePanel second, boolean vertical) {
        Splitter splitter = createSplitter(vertical);
        splitter.setFirstComponent(first);
        setSecondPanel(splitter, second);
        return splitter;
    }

    protected final Splitter createSplitter(JComponent first, JComponent second, boolean vertical) {
        Splitter splitter = createSplitter(vertical);
        splitter.setFirstComponent(first);
        splitter.setSecondComponent(second);
        return splitter;
    }

    private Splitter createSplitter(boolean vertical) {
        Splitter splitter = new Splitter(vertical);
        splitter.setShowDividerControls(true);
        return splitter;
    }

    private void setFirstPanel(Splitter splitter, CommittablePanel panel) {
        splitter.setFirstComponent(panel.getComponent());
        addComponent(panel);
    }

    private void setSecondPanel(Splitter splitter, CommittablePanel panel) {
        splitter.setSecondComponent(panel.getComponent());
        addComponent(panel);
    }
}
