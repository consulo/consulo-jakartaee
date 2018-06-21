/*
 * Copyright (c) 2004 - 2009 by Fuhrer Engineering AG, CH-2504 Biel/Bienne, Switzerland
 */

package com.intellij.javaee.oss.descriptor;

import com.intellij.javaee.oss.JavaeeBundle;
import com.intellij.javaee.oss.server.JavaeeIntegration;
import consulo.javaee.module.extension.JavaEEApplicationModuleExtension;
import consulo.javaee.module.extension.JavaEEModuleExtension;

class JavaeeAppDescriptor extends JavaeeDescriptor
{

	JavaeeAppDescriptor()
	{
		super(JavaeeBundle.getIcon("/resources/app.png"));
	}

	@Override
	String getTitle(JavaeeIntegration integration)
	{
		return JavaeeBundle.getText("AppDescriptor.title", integration.getName());
	}

	@Override
	Class<? extends JavaEEModuleExtension> getFacetType()
	{
		return JavaEEApplicationModuleExtension.class;
	}
}
