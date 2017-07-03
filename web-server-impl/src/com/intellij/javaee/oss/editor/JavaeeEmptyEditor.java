/*
 * Copyright (c) 2004 - 2009 by Fuhrer Engineering AG, CH-2504 Biel/Bienne, Switzerland
 */

package com.intellij.javaee.oss.editor;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.util.xml.DomElement;
import com.intellij.util.xml.ui.CaptionComponent;
import com.intellij.util.xml.ui.CommittablePanel;
import com.intellij.util.xml.ui.DomFileEditor;
import com.intellij.util.xml.ui.PerspectiveFileEditor;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;

class JavaeeEmptyEditor extends PerspectiveFileEditor {

    private final CommittablePanel panel;

    private final String name;

    private DomElement selectedDomElement;

    JavaeeEmptyEditor(@NotNull Project project, @NotNull VirtualFile file, @NotNull String name, @NotNull Icon icon) {
        super(project, file);
        this.name = name;
        panel = DomFileEditor.createComponentWithCaption(new JavaeeEmptyPanel(), new CaptionComponent(name, icon), null);
    }

    @NotNull
    public String getName() {
        return name;
    }

    @Override
    @NotNull
    protected JComponent createCustomComponent() {
        return panel.getComponent();
    }

    public JComponent getPreferredFocusedComponent() {
        return panel.getComponent();
    }

    @Override
    protected DomElement getSelectedDomElement() {
        return selectedDomElement;
    }

    @Override
    protected void setSelectedDomElement(DomElement element) {
        selectedDomElement = element;
    }

    public void commit() {
    }

    public void reset() {
    }
}
