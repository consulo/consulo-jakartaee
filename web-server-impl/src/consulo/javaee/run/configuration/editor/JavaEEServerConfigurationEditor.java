package consulo.javaee.run.configuration.editor;

import javax.swing.JComponent;
import javax.swing.JPanel;

import org.jetbrains.annotations.NotNull;
import com.intellij.execution.ui.AlternativeJREPanel;
import com.intellij.javaee.J2EEBundle;
import com.intellij.javaee.run.configuration.ServerModel;
import com.intellij.openapi.options.ConfigurationException;
import com.intellij.openapi.options.SettingsEditor;
import com.intellij.openapi.roots.ui.configuration.projectRoot.ProjectSdksModel;
import com.intellij.openapi.ui.LabeledComponent;
import com.intellij.openapi.ui.VerticalFlowLayout;
import com.intellij.openapi.util.Conditions;
import com.intellij.openapi.util.Disposer;
import com.intellij.ui.IdeBorderFactory;
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
	private final ServerModel myServerModel;

	private final SettingsEditor myServerEditor;

	private SdkComboBox myBundleBox;

	public JavaEEServerConfigurationEditor(JavaEEServerBundleType bundleType, ServerModel serverModel)
	{
		myBundleType = bundleType;
		myServerModel = serverModel;
		myServerEditor = myServerModel.getEditor();

		Disposer.register(this, myServerEditor);
	}

	@NotNull
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

		JComponent component = myServerEditor.getComponent();
		verticalLayout.add(component);

		return verticalLayout;
	}

	@Override
	@SuppressWarnings("unchecked")
	protected void resetEditorFrom(JavaEEConfigurationImpl configuration)
	{
		myServerEditor.resetFrom(configuration);
	}

	@Override
	@SuppressWarnings("unchecked")
	protected void applyEditorTo(JavaEEConfigurationImpl configuration) throws ConfigurationException
	{
		myServerEditor.applyTo(configuration);
	}
}
