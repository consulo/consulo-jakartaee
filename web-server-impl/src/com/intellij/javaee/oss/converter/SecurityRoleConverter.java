/*
 * Copyright (c) 2004 - 2009 by Fuhrer Engineering AG, CH-2504 Biel/Bienne, Switzerland
 */

package com.intellij.javaee.oss.converter;

import java.util.Collection;
import java.util.Collections;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import com.intellij.javaee.facet.JavaeeFacetUtil;
import com.intellij.javaee.model.xml.SecurityRole;
import com.intellij.javaee.model.xml.application.JavaeeApplication;
import com.intellij.javaee.model.xml.ejb.EjbJar;
import com.intellij.javaee.model.xml.web.WebApp;
import com.intellij.util.xml.ConvertContext;
import com.intellij.util.xml.ElementPresentationManager;
import com.intellij.util.xml.ResolvingConverter;
import consulo.javaee.module.extension.EjbModuleExtension;
import consulo.javaee.module.extension.JavaEEApplicationModuleExtension;
import consulo.javaee.module.extension.JavaEEModuleExtension;
import consulo.javaee.module.extension.JavaWebModuleExtension;

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
	@NotNull
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
