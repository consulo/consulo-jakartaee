/*
 * Copyright (c) 2004 - 2009 by Fuhrer Engineering AG, CH-2504 Biel/Bienne, Switzerland
 */

package com.intellij.javaee.oss.descriptor;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import javax.swing.Icon;

import org.jdom.DocType;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jetbrains.annotations.NonNls;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import com.intellij.javaee.oss.server.JavaeeIntegration;
import com.intellij.javaee.oss.util.FileWrapper;
import com.intellij.openapi.util.JDOMUtil;
import com.intellij.openapi.util.text.StringUtil;
import com.intellij.psi.xml.XmlFile;
import com.intellij.util.descriptors.ConfigFile;
import com.intellij.util.descriptors.ConfigFileMetaData;
import com.intellij.util.xml.DomElement;
import com.intellij.util.xml.DomFileElement;
import com.intellij.util.xml.DomManager;
import consulo.javaee.module.extension.JavaEEModuleExtension;

public abstract class JavaeeDescriptor
{
	private final Icon icon;

	private ConfigFileMetaData meta;

	private final Set<String> namespaces = new HashSet<String>();

	protected JavaeeDescriptor(Icon icon)
	{
		this.icon = icon;
	}

	@Nullable
	public <T extends DomElement> T getRoot(@Nullable JavaEEModuleExtension<?> facet, @Nonnull Class<T> type)
	{
		if(facet != null)
		{
			ConfigFile item = facet.getDescriptorsContainer().getConfigFile(meta);
			if(item != null)
			{
				XmlFile xml = item.getXmlFile();
				if(xml != null)
				{
					DomFileElement<T> element = DomManager.getDomManager(facet.getModule().getProject()).getFileElement(xml, type);
					if(element != null)
					{
						return element.getRootElement();
					}
				}
			}
		}
		return null;
	}

	boolean hasNamespace(String namespace)
	{
		return namespaces.contains(namespace);
	}

	public ConfigFileMetaData getMetaData()
	{
		return meta;
	}

	abstract String getTitle(JavaeeIntegration integration);

	abstract Class<? extends JavaEEModuleExtension> getFacetType();

	@NonNls
	String getPath()
	{
		return "META-INF";
	}

	private String getNamespace(FileWrapper file) throws JDOMException, IOException
	{
		Element root = JDOMUtil.loadDocument(file.getStream()).getRootElement();
		String namespace = root.getNamespaceURI();
		if(StringUtil.isEmpty(namespace))
		{
			DocType type = root.getDocument().getDocType();
			namespace = (type != null) ? type.getSystemID() : "";
		}
		return namespace;
	}
}
