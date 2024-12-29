/*
 * Copyright (c) 2004 - 2009 by Fuhrer Engineering AG, CH-2504 Biel/Bienne, Switzerland
 */

package consulo.jakartaee.webServer.impl.oss.server;

import consulo.jakartaee.webServer.impl.oss.JavaeeBundle;
import consulo.jakartaee.webServer.impl.run.configuration.CommonModel;
import consulo.jakartaee.webServer.impl.run.configuration.PredefinedLogFilesListener;
import consulo.jakartaee.webServer.impl.run.configuration.PredefinedLogFilesProviderEditor;
import consulo.configurable.ConfigurationException;
import consulo.execution.configuration.ui.SettingsEditor;
import consulo.javaee.bundle.JavaEEServerBundleType;
import consulo.proxy.EventDispatcher;
import consulo.ui.ex.awt.IdeBorderFactory;

import jakarta.annotation.Nonnull;
import javax.swing.*;

public abstract class JavaeeRunSettingsEditor<T extends JavaeeServerModel> extends SettingsEditor<CommonModel> implements PredefinedLogFilesProviderEditor
{
	private final EventDispatcher<PredefinedLogFilesListener> dispatcher = EventDispatcher.create(PredefinedLogFilesListener.class);

	private JavaEEServerBundleType myBundleType;

	protected JavaeeRunSettingsEditor(JavaEEServerBundleType bundleType)
	{
		myBundleType = bundleType;
	}

	@Override
	public void addListener(PredefinedLogFilesListener listener)
	{
		dispatcher.addListener(listener);
	}

	@Override
	public void removeListener(PredefinedLogFilesListener listener)
	{
		dispatcher.removeListener(listener);
	}

	@Override
	@SuppressWarnings({"unchecked"})
	protected void resetEditorFrom(CommonModel config)
	{
		resetEditorFrom((T) config.getServerModel());
	}

	@Override
	@SuppressWarnings({"unchecked"})
	protected void applyEditorTo(CommonModel config) throws ConfigurationException
	{
		applyEditorTo((T) config.getServerModel());
	}

	@Override
	@Nonnull
	protected JComponent createEditor()
	{
		JComponent editor = getEditor();
		editor.setBorder(IdeBorderFactory.createTitledBorder(JavaeeBundle.message("RunEditor.title", myBundleType.getPresentableName())));
		return editor;
	}

	@Override
	protected void disposeEditor()
	{
	}

	protected void fireLogFilesChanged()
	{
		try
		{
			dispatcher.getMulticaster().predefinedLogFilesChanged(getSnapshot());
		}
		catch(ConfigurationException ignore)
		{
		}
	}

	protected static int getPort(JTextField text, String message) throws ConfigurationException
	{
		try
		{
			return Integer.parseInt(text.getText());
		}
		catch(NumberFormatException e)
		{
			throw new ConfigurationException(message);
		}
	}

	@Nonnull
	protected abstract JComponent getEditor();

	protected abstract void resetEditorFrom(T model);

	protected abstract void applyEditorTo(T model) throws ConfigurationException;
}
