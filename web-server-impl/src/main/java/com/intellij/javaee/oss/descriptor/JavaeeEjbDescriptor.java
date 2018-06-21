/*
 * Copyright (c) 2004 - 2009 by Fuhrer Engineering AG, CH-2504 Biel/Bienne, Switzerland
 */

package com.intellij.javaee.oss.descriptor;

import com.intellij.javaee.oss.JavaeeBundle;
import com.intellij.javaee.oss.server.JavaeeIntegration;
import consulo.javaee.module.extension.EjbModuleExtension;
import consulo.javaee.module.extension.JavaEEModuleExtension;

class JavaeeEjbDescriptor extends JavaeeDescriptor
{
	JavaeeEjbDescriptor()
	{
		super(JavaeeBundle.getIcon("/resources/ejb.png"));
	}

	@Override
	String getTitle(JavaeeIntegration integration)
	{
		return JavaeeBundle.getText("EjbDescriptor.title", integration.getName());
	}

	@Override
	Class<? extends JavaEEModuleExtension> getFacetType()
	{
		return EjbModuleExtension.class;
	}
}
