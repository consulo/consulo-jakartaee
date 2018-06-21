package consulo.javaee.module.extension;

import com.intellij.javaee.model.xml.ejb.EjbJar;

/**
 * @author VISTALL
 * @since 02-Jul-17
 */
public interface EjbModuleExtension<T extends EjbModuleExtension<T>> extends JavaEEModuleExtension<T>
{
	EjbJar getXmlRoot();
}
