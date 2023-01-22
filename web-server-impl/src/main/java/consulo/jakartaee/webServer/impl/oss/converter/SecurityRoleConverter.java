/*
 * Copyright (c) 2004 - 2009 by Fuhrer Engineering AG, CH-2504 Biel/Bienne, Switzerland
 */

package consulo.jakartaee.webServer.impl.oss.converter;

import com.intellij.javaee.facet.JavaeeFacetUtil;
import com.intellij.javaee.model.xml.SecurityRole;
import com.intellij.javaee.model.xml.application.JavaeeApplication;
import com.intellij.javaee.model.xml.ejb.EjbJar;
import com.intellij.javaee.model.xml.web.WebApp;
import consulo.jakartaee.web.module.extension.JavaWebModuleExtension;
import consulo.javaee.module.extension.EjbModuleExtension;
import consulo.javaee.module.extension.JavaEEApplicationModuleExtension;
import consulo.javaee.module.extension.JavaEEModuleExtension;
import consulo.xml.util.xml.ConvertContext;
import consulo.xml.util.xml.ElementPresentationManager;
import consulo.xml.util.xml.ResolvingConverter;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Collection;
import java.util.Collections;

public class SecurityRoleConverter extends ResolvingConverter<SecurityRole>
{

	@Override
	@Nullable
	public SecurityRole fromString(String value, ConvertContext context)
	{
		return ElementPresentationManager.findByName(getVariants(context), value);
	}

	@Override
	@Nullable
	public String toString(SecurityRole value, ConvertContext context)
	{
		return (value != null) ? value.getRoleName().getValue() : null;
	}

	@Override
	@Nonnull
	public Collection<SecurityRole> getVariants(ConvertContext context)
	{
		Collection<SecurityRole> variants = Collections.emptyList();
		JavaEEModuleExtension<?> facet = JavaeeFacetUtil.getInstance().getJavaeeFacet(context);
		if(facet == null)
		{
			// ignore...
		}
		else if(facet instanceof JavaEEApplicationModuleExtension)
		{
			JavaeeApplication root = ((JavaEEApplicationModuleExtension) facet).getRoot();
			if(root != null)
			{
				variants = root.getSecurityRoles();
			}
		}
		else if(facet instanceof EjbModuleExtension)
		{
			EjbJar root = ((EjbModuleExtension) facet).getXmlRoot();
			if(root != null)
			{
				variants = root.getAssemblyDescriptor().getSecurityRoles();
			}
		}
		else if(facet instanceof JavaWebModuleExtension)
		{
			WebApp root = ((JavaWebModuleExtension) facet).getRoot();
			if(root != null)
			{
				variants = root.getSecurityRoles();
			}
		}
		return variants;
	}
}
