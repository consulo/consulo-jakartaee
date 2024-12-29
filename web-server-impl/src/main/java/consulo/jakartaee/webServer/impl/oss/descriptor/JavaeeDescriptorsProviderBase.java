package consulo.jakartaee.webServer.impl.oss.descriptor;

import consulo.jakartaee.webServer.impl.facet.DescriptorMetaDataProvider;
import consulo.jakartaee.webServer.impl.oss.server.JavaeeIntegration;

import jakarta.annotation.Nonnull;

/**
 * @author VISTALL
 * @since 03-Jul-17
 */
public abstract class JavaeeDescriptorsProviderBase extends DescriptorMetaDataProvider
{
	private JavaeeIntegration myJavaeeIntegration;

	public JavaeeDescriptorsProviderBase(JavaeeIntegration javaeeIntegration)
	{
		myJavaeeIntegration = javaeeIntegration;
	}

	@Override
	public void registerDescriptors(@Nonnull MetaDataRegistry registry)
	{

	}
}
