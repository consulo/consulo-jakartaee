package consulo.javaee.module.extension;

import com.intellij.javaee.model.xml.application.JavaeeApplication;

/**
 * @author VISTALL
 * @since 02-Jul-17
 */
public interface JavaEEApplicationModuleExtension<T extends JavaEEApplicationModuleExtension<T>> extends JavaEEModuleExtension<T>
{
	JavaeeApplication getRoot();
}
