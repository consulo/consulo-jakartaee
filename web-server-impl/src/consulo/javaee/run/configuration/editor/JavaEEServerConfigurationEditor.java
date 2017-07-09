package consulo.javaee.run.configuration.editor;

import javax.swing.JComponent;
import javax.swing.JPanel;

import org.jetbrains.annotations.NotNull;
import com.intellij.javaee.J2EEBundle;
import com.intellij.javaee.oss.server.JavaeeIntegration;
import com.intellij.javaee.run.configuration.ServerModel;
import com.intellij.openapi.options.ConfigurationException;
import com.intellij.openapi.options.SettingsEditor;
import com.intellij.openapi.roots.ui.configuration.projectRoot.ProjectSdksModel;
import com.intellij.openapi.ui.LabeledComponent;
import com.intellij.openapi.ui.VerticalFlowLayout;
import com.intellij.openapi.util.Disposer;
import consulo.javaee.run.configuration.JavaEEConfigurationImpl;
import consulo.roots.ui.configuration.SdkComboBox;

/**
 * @author VISTALL
 * @since 09-Jul-17
 */
public class JavaEEServerConfigurationEditor extends SettingsEditor<JavaEEConfigurationImpl>
{
	private final JavaeeIntegration myIntegration;
	private final ServerModel myServerModel;

	private final SettingsEditor myServerEditor;

	private SdkComboBox myBundleBox;

	public JavaEEServerConfigurationEditor(JavaeeIntegration integration, ServerModel serverModel)
	{
		myIntegration = integration;
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

		myBundleBox = new SdkComboBox(model);

		verticalLayout.add(LabeledComponent.left(myBundleBox, J2EEBundle.message("label.run.configuration.properties.application.server")));
		verticalLayout.add(myServerEditor.getComponent());

		return verticalLayout;
	}

	@Override
	protected void resetEditorFrom(JavaEEConfigurationImpl s)
	{

	}

	@Override
	protected void applyEditorTo(JavaEEConfigurationImpl s) throws ConfigurationException
	{

	}
}
