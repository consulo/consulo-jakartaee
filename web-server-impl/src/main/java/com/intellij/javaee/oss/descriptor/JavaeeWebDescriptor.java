/*
 * Copyright (c) 2004 - 2009 by Fuhrer Engineering AG, CH-2504 Biel/Bienne, Switzerland
 */

package com.intellij.javaee.oss.descriptor;

import org.jetbrains.annotations.NonNls;
import com.intellij.javaee.oss.JavaeeBundle;
import com.intellij.javaee.oss.server.JavaeeIntegration;
import consulo.javaee.module.extension.JavaEEModuleExtension;
import consulo.javaee.module.extension.JavaWebModuleExtension;

public class JavaeeWebDescriptor extends JavaeeDescriptor
{
	public JavaeeWebDescriptor(JavaeeIntegration integration, Class<?> type, String name)
	{
		super(integration.getIcon());
	}

	@Override
	public String getTitle(JavaeeIntegration integration)
	{
		return JavaeeBundle.message("WebDescriptor.title", integration.getName());
	}

	@Override
	Class<? extends JavaEEModuleExtension> getFacetType()
	{
		return JavaWebModuleExtension.class;
	}

	@Override
	@NonNls
	public String getPath()
	{
		return "WEB-INF";
	}
}
