package consulo.javaee.module.extension;

import com.intellij.util.descriptors.ConfigFileContainer;
import consulo.module.extension.ModuleExtension;

/**
 * @author VISTALL
 * @since 02-Jul-17
 */
public interface JavaEEModuleExtension<T extends JavaEEModuleExtension<T>> extends ModuleExtension<T>
{
	default ConfigFileContainer getDescriptorsContainer()
	{
		return null;
	}
}
