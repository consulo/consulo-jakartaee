package com.intellij.javaee.oss.descriptor;

import org.jetbrains.annotations.NotNull;
import com.intellij.javaee.facet.DescriptorMetaDataProvider;
import com.intellij.javaee.oss.server.JavaeeIntegration;

/**
 * @author VISTALL
 * @since 03-Jul-17
 */
public class JavaeeDescriptorsProviderBase extends DescriptorMetaDataProvider
{
	private JavaeeIntegration myJavaeeIntegration;

	public JavaeeDescriptorsProviderBase(JavaeeIntegration javaeeIntegration)
	{
		myJavaeeIntegration = javaeeIntegration;
	}

	@Override
	public void registerDescriptors(@NotNull MetaDataRegistry registry)
	{

	}
}
