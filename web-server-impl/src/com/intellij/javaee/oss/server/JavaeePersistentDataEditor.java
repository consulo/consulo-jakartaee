/*
 * Copyright (c) 2004 - 2009 by Fuhrer Engineering AG, CH-2504 Biel/Bienne, Switzerland
 */

package com.intellij.javaee.oss.server;

import java.awt.Component;
import java.text.MessageFormat;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.event.DocumentEvent;

import org.jetbrains.annotations.NotNull;
import com.intellij.javaee.appServerIntegrations.ApplicationServerPersistentDataEditor;
import com.intellij.javaee.oss.JavaeeBundle;
import com.intellij.openapi.fileChooser.FileChooserDescriptor;
import com.intellij.openapi.options.ConfigurationException;
import com.intellij.openapi.ui.TextFieldWithBrowseButton;
import com.intellij.ui.DocumentAdapter;

class JavaeePersistentDataEditor extends ApplicationServerPersistentDataEditor<JavaeePersistentData>
{

	private JPanel panel;

	private TextFieldWithBrowseButton home;

	private JLabel version;

	private JLabel error;

	private JavaeeIntegration myIntegration;

	protected JavaeePersistentDataEditor(JavaeeIntegration integration)
	{
		myIntegration = integration;
		for(Component component : panel.getComponents())
		{
			if(component instanceof JLabel)
			{
				JLabel label = (JLabel) component;
				label.setText(MessageFormat.format(label.getText(), getServerName()));
			}
		}
		home.addBrowseFolderListener(JavaeeBundle.getText("PersistentDataEditor.chooser.title", getServerName()), JavaeeBundle.getText("PersistentDataEditor.chooser.description", getServerName()),
				null, new FileChooserDescriptor(false, true, false, false, false, false));
		home.getTextField().getDocument().addDocumentListener(new DocumentAdapter()
		{
			@Override
			protected void textChanged(DocumentEvent event)
			{
				updateVersion();
			}
		});
		error.setIcon(JavaeeBundle.getIcon("/runConfigurations/configurationWarning.png"));
		updateVersion();
	}

	@Override
	protected void resetEditorFrom(JavaeePersistentData settings)
	{
		home.setText(settings.HOME);
	}

	@Override
	protected void applyEditorTo(JavaeePersistentData settings) throws ConfigurationException
	{
		settings.HOME = home.getText();
		settings.VERSION = getVersion();
	}

	@Override
	@NotNull
	protected JComponent createEditor()
	{
		return panel;
	}

	@Override
	protected void disposeEditor()
	{
	}

	private String getServerName()
	{
		return myIntegration.getName();
	}

	private void updateVersion()
	{
		version.setText(JavaeeBundle.getText("PersistentDataEditor.unknown"));
		try
		{
			version.setText(getVersion());
			myIntegration.checkValidServerHome(home.getText(), version.getText());
			error.setVisible(false);
		}
		catch(Exception e)
		{
			error.setText(JavaeeBundle.getText("PersistentDataEditor.warning", getServerName()));
			error.setVisible(true);
		}
	}

	private String getVersion() throws ConfigurationException
	{
		try
		{
			return myIntegration.getServerVersion(home.getText());
		}
		catch(Exception e)
		{
			throw new ConfigurationException(JavaeeBundle.getText("PersistentDataEditor.invalid", getServerName(), home.getText()));
		}
	}
}
