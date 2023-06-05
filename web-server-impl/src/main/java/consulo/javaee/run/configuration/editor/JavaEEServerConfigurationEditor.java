package consulo.javaee.run.configuration.editor;

import com.intellij.javaee.J2EEBundle;
import consulo.content.bundle.SdkModel;
import consulo.ide.setting.ShowSettingsUtil;
import consulo.jakartaee.webServer.impl.run.configuration.ApplicationServerSelectionListener;
import consulo.configurable.ConfigurationException;
import consulo.content.bundle.Sdk;
import consulo.disposer.Disposer;
import consulo.execution.configuration.ui.SettingsEditor;
import consulo.ide.impl.idea.openapi.roots.ui.configuration.projectRoot.ProjectSdksModel;
import consulo.javaee.bundle.JavaEEServerBundleType;
import consulo.javaee.run.configuration.JavaEEConfigurationImpl;
import consulo.module.ui.awt.SdkComboBox;
import consulo.ui.ex.awt.IdeBorderFactory;
import consulo.ui.ex.awt.LabeledComponent;
import consulo.ui.ex.awt.VerticalFlowLayout;
import consulo.ui.ex.awt.Wrapper;
import consulo.util.lang.function.Conditions;

import javax.annotation.Nonnull;
import javax.swing.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

/**
 * @author VISTALL
 * @since 09-Jul-17
 */
public class JavaEEServerConfigurationEditor extends SettingsEditor<JavaEEConfigurationImpl>
{
	private final JavaEEServerBundleType myBundleType;

	private final Wrapper mySettingsWrapper = new Wrapper();

	private SdkComboBox myBundleBox;

	private ItemListener myBundleBoxListener;
	private SettingsEditor myServerEditor;

	public JavaEEServerConfigurationEditor(JavaEEServerBundleType bundleType)
	{
		myBundleType = bundleType;
	}

	@Nonnull
	@Override
	protected JComponent createEditor()
	{
		JPanel verticalLayout = new JPanel(new VerticalFlowLayout(0, 0));

		SdkModel model = ShowSettingsUtil.getInstance().getSdksModel();

		myBundleBox = new SdkComboBox(model, Conditions.equalTo(myBundleType), true);
		verticalLayout.add(LabeledComponent.left(myBundleBox, J2EEBundle.message("label.run.configuration.properties.application.server")));

		JPanel openBrowserPanel = new JPanel();
		openBrowserPanel.setBorder(IdeBorderFactory.createTitledBorder("Open browser"));
		verticalLayout.add(openBrowserPanel);

		verticalLayout.add(mySettingsWrapper);

		return verticalLayout;
	}

	@Override
	@SuppressWarnings("unchecked")
	protected void resetEditorFrom(JavaEEConfigurationImpl configuration)
	{
		if(myBundleBoxListener != null)
		{
			myBundleBox.removeItemListener(myBundleBoxListener);
			myBundleBoxListener = null;
		}

		if(myServerEditor != null)
		{
			Disposer.dispose(myServerEditor);
			mySettingsWrapper.setContent(null);
			myServerEditor = null;
		}

		myServerEditor = configuration.getServerModel().getEditor();
		Disposer.register(this, myServerEditor);

		mySettingsWrapper.setContent(myServerEditor.getComponent());

		myBundleBox.setSelectedSdk(configuration.APPLICATION_SERVER_NAME);

		myBundleBox.addItemListener(myBundleBoxListener = e ->
		{
			if(e.getStateChange() == ItemEvent.SELECTED)
			{
				Sdk selectedSdk = myBundleBox.getSelectedSdk();

				configuration.APPLICATION_SERVER_NAME = selectedSdk == null ? null : selectedSdk.getName();

				if(myServerEditor instanceof ApplicationServerSelectionListener)
				{
					((ApplicationServerSelectionListener) myServerEditor).serverSelected(selectedSdk);
				}

				myServerEditor.resetFrom(configuration);

				fireEditorStateChanged();
			}
		});

		myServerEditor.resetFrom(configuration);
	}

	@Override
	@SuppressWarnings("unchecked")
	protected void applyEditorTo(JavaEEConfigurationImpl configuration) throws ConfigurationException
	{
		myServerEditor.applyTo(configuration);
		configuration.APPLICATION_SERVER_NAME = myBundleBox.getSelectedSdkName();
	}
}
