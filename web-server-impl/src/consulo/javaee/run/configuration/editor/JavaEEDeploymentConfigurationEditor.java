package consulo.javaee.run.configuration.editor;

import javax.swing.JComponent;
import javax.swing.JPanel;

import org.jetbrains.annotations.NotNull;
import com.intellij.openapi.options.ConfigurationException;
import com.intellij.openapi.options.SettingsEditor;
import consulo.javaee.run.configuration.JavaEEConfigurationImpl;

/**
 * @author VISTALL
 * @since 09-Jul-17
 */
public class JavaEEDeploymentConfigurationEditor extends SettingsEditor<JavaEEConfigurationImpl>
{
	@NotNull
	@Override
	protected JComponent createEditor()
	{
		return new JPanel();
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
