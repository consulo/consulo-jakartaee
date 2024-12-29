/*
 * Copyright (c) 2004 - 2009 by Fuhrer Engineering AG, CH-2504 Biel/Bienne, Switzerland
 */

package consulo.jakartaee.webServer.impl.oss.descriptor;

import com.intellij.java.impl.util.descriptors.ConfigFile;
import com.intellij.java.impl.util.descriptors.ConfigFileMetaData;
import consulo.jakartaee.webServer.impl.oss.server.JavaeeIntegration;
import consulo.jakartaee.webServer.impl.oss.util.FileWrapper;
import consulo.javaee.module.extension.JavaEEModuleExtension;
import consulo.ui.image.Image;
import consulo.util.jdom.JDOMUtil;
import consulo.util.lang.StringUtil;
import consulo.xml.psi.xml.XmlFile;
import consulo.xml.util.xml.DomElement;
import consulo.xml.util.xml.DomFileElement;
import consulo.xml.util.xml.DomManager;
import org.jdom.DocType;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jetbrains.annotations.NonNls;

import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public abstract class JavaeeDescriptor
{
	private final Image icon;

	private ConfigFileMetaData meta;

	private final Set<String> namespaces = new HashSet<String>();

	protected JavaeeDescriptor(Image icon)
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
