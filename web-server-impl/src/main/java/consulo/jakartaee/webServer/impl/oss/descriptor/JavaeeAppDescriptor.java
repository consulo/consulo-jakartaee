/*
 * Copyright (c) 2004 - 2009 by Fuhrer Engineering AG, CH-2504 Biel/Bienne, Switzerland
 */

package consulo.jakartaee.webServer.impl.oss.descriptor;

import consulo.jakartaee.webServer.impl.oss.JavaeeBundle;
import consulo.jakartaee.webServer.impl.oss.server.JavaeeIntegration;
import consulo.application.AllIcons;
import consulo.javaee.module.extension.JavaEEApplicationModuleExtension;
import consulo.javaee.module.extension.JavaEEModuleExtension;

class JavaeeAppDescriptor extends JavaeeDescriptor
{
	JavaeeAppDescriptor()
	{
		super(AllIcons.RunConfigurations.Application);
	}

	@Override
	String getTitle(JavaeeIntegration integration)
	{
		return JavaeeBundle.message("AppDescriptor.title", integration.getName());
	}

	@Override
	Class<? extends JavaEEModuleExtension> getFacetType()
	{
		return JavaEEApplicationModuleExtension.class;
	}
}
