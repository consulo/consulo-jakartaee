package consulo.javaee.run.configuration.editor;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JComponent;
import javax.swing.JPanel;

import javax.annotation.Nonnull;
import com.intellij.execution.ui.AlternativeJREPanel;
import com.intellij.javaee.J2EEBundle;
import com.intellij.javaee.run.configuration.ApplicationServerSelectionListener;
import com.intellij.openapi.options.ConfigurationException;
import com.intellij.openapi.options.SettingsEditor;
import com.intellij.openapi.projectRoots.Sdk;
import com.intellij.openapi.roots.ui.configuration.projectRoot.ProjectSdksModel;
import com.intellij.openapi.ui.LabeledComponent;
import com.intellij.openapi.ui.VerticalFlowLayout;
import com.intellij.openapi.util.Conditions;
import com.intellij.openapi.util.Disposer;
import com.intellij.ui.IdeBorderFactory;
import com.intellij.ui.components.panels.Wrapper;
import consulo.javaee.bundle.JavaEEServerBundleType;
import consulo.javaee.run.configuration.JavaEEConfigurationImpl;
import consulo.roots.ui.configuration.SdkComboBox;

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

		ProjectSdksModel model = new ProjectSdksModel();
		model.reset();

		myBundleBox = new SdkComboBox(model, Conditions.equalTo(myBundleType), true);
		verticalLayout.add(LabeledComponent.left(myBundleBox, J2EEBundle.message("label.run.configuration.properties.application.server")));

		JPanel openBrowserPanel = new JPanel();
		openBrowserPanel.setBorder(IdeBorderFactory.createTitledBorder("Open browser"));
		verticalLayout.add(openBrowserPanel);

		if(myBundleType.isJreCustomizable())
		{
			AlternativeJREPanel panel = new AlternativeJREPanel();
			verticalLayout.add(panel);
		}

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
