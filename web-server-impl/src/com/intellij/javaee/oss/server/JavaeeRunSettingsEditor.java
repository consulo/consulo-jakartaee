/*
 * Copyright (c) 2004 - 2009 by Fuhrer Engineering AG, CH-2504 Biel/Bienne, Switzerland
 */

package com.intellij.javaee.oss.server;

import com.intellij.javaee.oss.JavaeeBundle;
import com.intellij.javaee.run.configuration.CommonModel;
import com.intellij.javaee.run.configuration.PredefinedLogFilesListener;
import com.intellij.javaee.run.configuration.PredefinedLogFilesProviderEditor;
import com.intellij.openapi.options.ConfigurationException;
import com.intellij.openapi.options.SettingsEditor;
import com.intellij.util.EventDispatcher;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;

public abstract class JavaeeRunSettingsEditor<T extends JavaeeServerModel> extends SettingsEditor<CommonModel>
        implements PredefinedLogFilesProviderEditor {

    private final EventDispatcher<PredefinedLogFilesListener> dispatcher = EventDispatcher.create(PredefinedLogFilesListener.class);

    public void addListener(PredefinedLogFilesListener listener) {
        dispatcher.addListener(listener);
    }

    public void removeListener(PredefinedLogFilesListener listener) {
        dispatcher.removeListener(listener);
    }

    @Override
    @SuppressWarnings({"unchecked"})
    protected void resetEditorFrom(CommonModel config) {
        resetEditorFrom((T) config.getServerModel());
    }

    @Override
    @SuppressWarnings({"unchecked"})
    protected void applyEditorTo(CommonModel config) throws ConfigurationException {
        applyEditorTo((T) config.getServerModel());
    }

    @Override
    @NotNull
    protected JComponent createEditor() {
        JComponent editor = getEditor();
        String name = JavaeeIntegration.getInstance().getName();
        editor.setBorder(BorderFactory.createTitledBorder(JavaeeBundle.getText("RunEditor.title", name)));
        return editor;
    }

    @Override
    protected void disposeEditor() {
    }

    protected void fireLogFilesChanged() {
        try {
            dispatcher.getMulticaster().predefinedLogFilesChanged(getSnapshot());
        } catch (ConfigurationException ignore) {
        }
    }

    @NotNull
    protected abstract JComponent getEditor();

    protected abstract void resetEditorFrom(T model);

    protected abstract void applyEditorTo(T model) throws ConfigurationException;
}
