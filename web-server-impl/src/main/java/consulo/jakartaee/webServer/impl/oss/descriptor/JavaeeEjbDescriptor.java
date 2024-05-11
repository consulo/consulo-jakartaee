/*
 * Copyright (c) 2004 - 2009 by Fuhrer Engineering AG, CH-2504 Biel/Bienne, Switzerland
 */

package consulo.jakartaee.webServer.impl.oss.descriptor;

import consulo.jakartaee.webServer.impl.oss.JavaeeBundle;
import consulo.jakartaee.webServer.impl.oss.server.JavaeeIntegration;
import consulo.javaee.module.extension.EjbModuleExtension;
import consulo.javaee.module.extension.JavaEEModuleExtension;
import consulo.ui.image.Image;

class JavaeeEjbDescriptor extends JavaeeDescriptor
{
	JavaeeEjbDescriptor()
	{
		super(Image.empty(Image.DEFAULT_ICON_SIZE));
	}

	@Override
	String getTitle(JavaeeIntegration integration)
	{
		return JavaeeBundle.message("EjbDescriptor.title", integration.getName());
	}

	@Override
	Class<? extends JavaEEModuleExtension> getFacetType()
	{
		return EjbModuleExtension.class;
	}
}
