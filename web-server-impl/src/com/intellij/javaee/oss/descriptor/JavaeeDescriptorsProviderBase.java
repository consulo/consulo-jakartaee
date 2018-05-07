package com.intellij.javaee.oss.descriptor;

import javax.annotation.Nonnull;
import com.intellij.javaee.facet.DescriptorMetaDataProvider;
import com.intellij.javaee.oss.server.JavaeeIntegration;

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
