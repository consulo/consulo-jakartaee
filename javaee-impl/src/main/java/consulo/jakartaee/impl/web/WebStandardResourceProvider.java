package consulo.jakartaee.impl.web;

import consulo.annotation.component.ExtensionImpl;
import consulo.xml.javaee.ResourceRegistrar;
import consulo.xml.javaee.StandardResourceProvider;

/**
 * @author VISTALL
 * @since 22/01/2023
 */
@ExtensionImpl
public class WebStandardResourceProvider implements StandardResourceProvider
{
	@Override
	public void registerResources(ResourceRegistrar registrar)
	{
		registrar.addStdResource("http://java.sun.com/xml/ns/javaee/web-app_2_5.xsd", "2.5", "/standardSchemas/web-app_2_5.xsd", WebStandardResourceProvider.class);
		registrar.addStdResource("http://java.sun.com/xml/ns/javaee/web-app_3_0.xsd", "3.0", "/standardSchemas/web-app_3_0.xsd", WebStandardResourceProvider.class);
		registrar.addStdResource("http://java.sun.com/xml/ns/javaee/web-app_3_1.xsd", "3.1", "/standardSchemas/web-app_3_1.xsd", WebStandardResourceProvider.class);
	}
}
