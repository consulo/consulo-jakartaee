package org.jetbrains.idea.tomcat;

import java.util.Arrays;

import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JTextField;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import org.jetbrains.idea.tomcat.server.TomcatRemoteModel;
import org.jetbrains.idea.tomcat.server.TomcatRunSettingsEditor;
import com.intellij.javaee.oss.transport.MultiTargetRemoteServerModel;
import com.intellij.javaee.oss.transport.MultiTargetRemoteStagingEditor;
import com.intellij.javaee.oss.util.Version;
import com.intellij.javaee.run.configuration.ApplicationServerSelectionListener;
import com.intellij.openapi.options.ConfigurationException;
import com.intellij.openapi.projectRoots.Sdk;

/**
 * Created by IntelliJ IDEA.
 * User: michael.golubev
 */
public class TomcatRemoteRunConfigurationEditor extends TomcatRunSettingsEditor<TomcatRemoteModel> implements ApplicationServerSelectionListener
{
	private JTextField myJndiPortField;
	private MultiTargetRemoteStagingEditor myRemoteStagingEditor;
	private JPanel myMainPanel;
	private JPanel myJndiPortPanel;


	@Override
	public void serverSelected(@Nullable Sdk server)
	{
		updateUI(server);
	}

	@Override
	public void serverProbablyEdited(@Nullable Sdk server)
	{

	}

	private void updateUI(@Nullable Sdk server)
	{
		boolean showDeployOptions;
		if(server == null)
		{
			showDeployOptions = true;
		}
		else
		{
			showDeployOptions = new Version(server.getVersionString()).getMajor() >= 5;
		}
		myMainPanel.setVisible(showDeployOptions);
	}

	@Nonnull
	@Override
	protected JComponent getEditor()
	{
		return myMainPanel;
	}

	@Override
	protected void resetEditorFrom(TomcatRemoteModel serverModel)
	{
		updateUI(serverModel.getApplicationServer());
		myJndiPortPanel.setVisible(serverModel.isUseJmx());
		myJndiPortField.setText(String.valueOf(serverModel.getJndiPort()));
		myRemoteStagingEditor.resetEditorFrom(serverModel);
	}

	@Override
	protected void applyEditorTo(TomcatRemoteModel serverModel) throws ConfigurationException
	{
		serverModel.setJndiPort(getJndiPort(myJndiPortField, serverModel));
		myRemoteStagingEditor.applyEditorTo(serverModel);
	}

	private void createUIComponents()
	{
		myRemoteStagingEditor = new MultiTargetRemoteStagingEditor(Arrays.asList(TomcatRemoteModel.CONTEXT_TARGET_DEFINITION, TomcatRemoteModel.STAGING_TARGET_DEFINITION))
		{

			@Override
			public void resetEditorFrom(MultiTargetRemoteServerModel model)
			{
				super.resetEditorFrom(model);
				TomcatRemoteModel remoteModel = (TomcatRemoteModel) model;
				setTargetVisible(TomcatRemoteModel.CONTEXT_TARGET_NAME, !remoteModel.isTomEE() && remoteModel.isContextDescriptorExist());
			}
		};
	}
}
