/*
 * Copyright 2000-2005 JetBrains s.r.o.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.jetbrains.idea.tomcat;

import java.io.CharArrayReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.jar.JarFile;
import java.util.zip.ZipEntry;

import org.jdom.Content;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.Namespace;
import org.jdom.input.SAXBuilder;
import org.jetbrains.annotations.NonNls;
import javax.annotation.Nullable;
import org.jetbrains.idea.tomcat.model.TomcatContextRoot;
import org.jetbrains.idea.tomcat.server.TomcatIntegration;
import org.jetbrains.idea.tomcat.server.TomcatLocalModel;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.ext.EntityResolver2;
import com.intellij.execution.ExecutionException;
import com.intellij.javaee.artifact.JavaeeArtifactUtil;
import com.intellij.javaee.oss.descriptor.JavaeeDescriptor;
import com.intellij.openapi.diagnostic.Logger;
import com.intellij.openapi.util.JDOMUtil;
import com.intellij.openapi.util.SystemInfo;
import com.intellij.openapi.util.io.FileUtil;
import com.intellij.openapi.util.text.StringUtil;
import com.intellij.openapi.vfs.CharsetToolkit;
import com.intellij.openapi.vfs.VfsUtilCore;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.packaging.artifacts.Artifact;
import com.intellij.util.ArrayUtil;
import com.intellij.util.EnvironmentUtil;
import com.intellij.util.descriptors.ConfigFile;
import com.intellij.util.descriptors.ConfigFileMetaData;
import consulo.javaee.module.extension.JavaEEModuleExtension;
import consulo.javaee.module.extension.JavaWebModuleExtension;

@SuppressWarnings({"unchecked"})
public class TomcatUtil
{
	private static final Logger LOG = Logger.getInstance("#org.jetbrains.idea.tomcat.TomcatUtil");

	@NonNls
	private static final String CATALINA_NAME = "Catalina";
	@NonNls
	private static final String LOCALHOST_DIR = "localhost";
	@NonNls
	private static final String SERVLET_ELEMENT_NAME = "servlet";
	@NonNls
	private static final String KEEPGEN_PARAMETER_NAME = "keepgenerated";
	@NonNls
	private static final String MAPPEDFILE_PARAMETER_NAME = "mappedfile";
	@NonNls
	private static final String SCRDIR_PARAMETER_NAME = "scratchdir";
	@NonNls
	private static final String CLS_DI_PARAMETER_NAME = "classdebuginfo";
	@NonNls
	private static final String SERVLET_NAME_ELEM_NAME = "servlet-name";
	@NonNls
	private static final String JSP_VALUE = "jsp";
	@NonNls
	private static final String INIT_PARAM_ELEM_NAME = "init-param";
	@NonNls
	private static final String SERVLET_CLASS_ELEM_NAME = "servlet-class";
	@NonNls
	private static final String PARAM_NAME_ELEM_NAME = "param-name";
	@NonNls
	private static final String PARAM_VALUE_ELEM_NAME = "param-value";
	@NonNls
	private static final String TOMCAT_HOME_ENV_PROPERTY = "TOMCAT_HOME";
	@NonNls
	private static final String CATALINA_HOME_ENV_PROPERTY = "CATALINA_HOME";

	private TomcatUtil()
	{
	}

	public static File getSetEnvFile(String parentPath)
	{
		return new File(new File(parentPath, "bin"), "setenv." + (SystemInfo.isWindows ? "bat" : "sh"));
	}

	public static String getDefaultLocation()
	{
		String result = EnvironmentUtil.getValue(TOMCAT_HOME_ENV_PROPERTY);
		if(result == null)
		{
			result = EnvironmentUtil.getValue(CATALINA_HOME_ENV_PROPERTY);
		}
		return result != null ? result.replace(File.separatorChar, '/') : "";
	}

	/**
	 * search in META-INF/context.xml
	 */
	@Nullable
	public static Element findContextInContextXml(TomcatDeploymentModelBase tomcatModel)
	{
		final String deploymentPath = tomcatModel.getDeploymentSource().getFilePath();
		if(deploymentPath == null)
		{
			return null;
		}

		File deploymentRoot = new File(deploymentPath);

		// may search via facet
		JavaeeDescriptor contextDescriptor = TomcatIntegration.getInstance().getDescriptorsManager().getItem(TomcatContextRoot.class);
		ConfigFileMetaData metaData = contextDescriptor.getMetaData();
		String contextXmlPath = metaData.getDirectoryPath() + "/" + metaData.getFileName();
		try
		{
			if(deploymentRoot.isDirectory())
			{
				Document contextDocument = loadXMLFile(new File(deploymentRoot, FileUtil.toSystemDependentName(contextXmlPath)));
				return contextDocument.getRootElement();
			}
			else if(deploymentRoot.isFile())
			{
				JarFile jarFile = new JarFile(deploymentRoot);
				try
				{
					ZipEntry entry = jarFile.getEntry(contextXmlPath);
					if(entry != null)
					{
						Document document = JDOMUtil.loadDocument(jarFile.getInputStream(entry));
						return document.getRootElement();
					}
				}
				catch(JDOMException e)
				{
					LOG.info(e);
				}
				finally
				{
					jarFile.close();
				}
			}
			else
			{
				return findContextInContextXmlByFacet(tomcatModel);
			}
		}
		catch(ExecutionException | IOException e)
		{
			LOG.info(e);
		}

		return null;
	}

	@Nullable
	public static Element findContextInContextXmlByFacet(TomcatDeploymentModelBase tomcatModel)
	{
		Artifact artifact = tomcatModel.getArtifact();
		if(artifact != null)
		{
			JavaeeDescriptor contextDescriptor = TomcatIntegration.getInstance().getDescriptorsManager().getItem(TomcatContextRoot.class);
			ConfigFileMetaData metaData = contextDescriptor.getMetaData();
			for(JavaEEModuleExtension facet : JavaeeArtifactUtil.getInstance().getFacetsIncludedInArtifact(tomcatModel.getCommonModel().getProject(), artifact, JavaWebModuleExtension.class))
			{
				ConfigFile configFile = facet.getDescriptorsContainer().getConfigFile(metaData);
				try
				{
					if(configFile != null)
					{
						VirtualFile contextXmlFile = configFile.getVirtualFile();
						if(contextXmlFile != null)
						{
							Document contextDocument = loadXMLFile(VfsUtilCore.virtualToIoFile(contextXmlFile));
							return contextDocument.getRootElement();
						}
					}
				}
				catch(ExecutionException e)
				{
					LOG.info(e);
				}
			}
		}

		return null;
	}

	public static String getContextName(String contextPath)
	{
		String contextName = "".equals(contextPath) ? TomcatContexts.ROOT_DIR_NAME : contextPath.substring(1);
		return contextName.replace('/', '#').replace('\\', '#');
	}

	public static String getContextXML(final String baseDirectoryPath, final String contextPath)
	{
		return hostDir(baseDirectoryPath) + File.separator + getContextName(contextPath) + ".xml";
	}

	public static String hostDir(String baseDirectoryPath)
	{
		return baseConfigDir(baseDirectoryPath) + File.separator + CATALINA_NAME + File.separator + LOCALHOST_DIR;
	}

	public static Document loadXMLFile(final File xmlFile) throws ExecutionException
	{
		String path = xmlFile.getAbsolutePath();
		SAXBuilder builder = new SAXBuilder();
		builder.setEntityResolver(new EntityResolver2()
		{

			@Override
			public InputSource resolveEntity(String publicId, String systemId) throws SAXException, IOException
			{
				if(!StringUtil.isEmpty(systemId))
				{
					File externalEntity = FileUtil.isAbsolutePlatformIndependent(systemId) ? new File(systemId) : new File(FileUtil.getParentFile(xmlFile), systemId);
					if(externalEntity.exists())
					{
						return new InputSource(new FileInputStream(externalEntity));
					}
				}
				return new InputSource(new CharArrayReader(ArrayUtil.EMPTY_CHAR_ARRAY));
			}

			@Override
			public InputSource resolveEntity(String name, String publicId, String baseURI, String systemId) throws SAXException, IOException
			{
				return resolveEntity(publicId, systemId);
			}

			@Override
			public InputSource getExternalSubset(String name, String baseURI) throws SAXException, IOException
			{
				return null;
			}
		});

		try (FileInputStream is = new FileInputStream(xmlFile))
		{
			String xmlText = CharsetToolkit.bytesToString(FileUtil.loadBytes(is), CharsetToolkit.UTF8_CHARSET);
			return JDOMUtil.loadDocument(xmlText);
		}
		catch(JDOMException | IOException e)
		{
			throw new ExecutionException(TomcatBundle.message("exception.text.cannot.load.file", path, e.getMessage()), e);
		}
	}

	@NonNls
	public static String getLogsDirPath(String baseDirectoryPath)
	{
		return baseDirectoryPath + File.separator + "logs";
	}

	private static String getBackupPath(String path)
	{
		int i = 0;
		while(new File(path + "." + i).exists())
		{
			i++;
		}
		return path + "." + i;
	}

	public static void saveXMLFile(Document xmlDocument, File xmlFile, boolean backupOriginalIfExists) throws ExecutionException
	{
		String xmlPath = xmlFile.getAbsolutePath();

		if(backupOriginalIfExists && xmlFile.exists())
		{
			String backupPath = getBackupPath(xmlPath);
			try
			{
				FileUtil.copy(xmlFile, new File(backupPath));
			}
			catch(IOException e)
			{
				throw new ExecutionException(TomcatBundle.message("exception.text.cannot.copy.0.to.1.because.of.2", xmlPath, backupPath, e.getMessage()));
			}
		}

		try
		{
			JDOMUtil.writeDocument(xmlDocument, xmlPath, "\n");
		}
		catch(IOException e)
		{
			throw new ExecutionException(TomcatBundle.message("exception.text.cannot.write.0.because.of.1", xmlPath, e.getMessage()));
		}
	}

	public static void configureWebXml(TomcatLocalModel tomcatConfiguration) throws ExecutionException
	{
		final File webXml = new File(tomcatConfiguration.getBaseDirectoryPath() + File.separator + TomcatConstants.CATALINA_CONFIG_DIRECTORY_NAME + File.separator + TomcatConstants.WEB_XML);
		final Document webXmlDocument = loadXMLFile(webXml);
		final Element rootElement = webXmlDocument.getRootElement();
		final Namespace namespace = rootElement.getNamespace();
		final List<Element> servlets = rootElement.getChildren(SERVLET_ELEMENT_NAME, namespace);

		Element jspServlet = findJspServlet(servlets, namespace);

		if(jspServlet == null)
		{
			String message = TomcatBundle.message("exception.text.cannot.find.configuration", webXml.getAbsolutePath());
			throw new ExecutionException(message);
		}

		if(!tomcatConfiguration.isVersion5OrHigher())
		{
			setParameter(jspServlet, KEEPGEN_PARAMETER_NAME, Boolean.TRUE.toString(), namespace);
			setParameter(jspServlet, MAPPEDFILE_PARAMETER_NAME, Boolean.TRUE.toString(), namespace);

			String scratchdir = getGeneratedFilesPath(tomcatConfiguration).replace('/', File.separatorChar);
			new File(scratchdir).mkdirs();

			setParameter(jspServlet, SCRDIR_PARAMETER_NAME, scratchdir, namespace);
		}

		setParameter(jspServlet, CLS_DI_PARAMETER_NAME, Boolean.TRUE.toString(), namespace);
		saveXMLFile(webXmlDocument, webXml, true);
	}

	@Nullable
	private static Element findJspServlet(List<Element> servlets, Namespace namespace)
	{
		Element jspServlet = null;
		for(final Element servlet : servlets)
		{
			Element nameParam = servlet.getChild(SERVLET_NAME_ELEM_NAME, namespace);
			if(nameParam == null)
			{
				continue;
			}
			if(!JSP_VALUE.equalsIgnoreCase(nameParam.getText()))
			{
				continue;
			}
			jspServlet = servlet;
			break;
		}
		return jspServlet;
	}

	private static void setParameter(Element servletNode, String parameterName, String parameterValue, Namespace namespace)
	{
		Element parameter = findParameter(servletNode, parameterName, namespace);
		if(parameter == null)
		{
			parameter = new Element(INIT_PARAM_ELEM_NAME, namespace);
			//The content of element type "servlet" must match
			//  "(icon?,servlet-name,display-name?,description?,(servlet-class|jsp-file),init-param*,load-on-startup?,run-as?,security-role-ref*)".
			Element anchor = servletNode.getChild(SERVLET_CLASS_ELEM_NAME, namespace);
			insertChildAfter(servletNode, parameter, anchor);
			//servletNode.addContent(parameter);
			Element name = new Element(PARAM_NAME_ELEM_NAME, namespace);
			parameter.addContent(name);
			name.setText(parameterName);
		}
		Element value = parameter.getChild(PARAM_VALUE_ELEM_NAME, namespace);
		if(value == null)
		{
			value = new Element(PARAM_VALUE_ELEM_NAME, namespace);
			parameter.addContent(value);
		}
		value.setText(parameterValue);
	}

	@Nullable
	private static Element findParameter(Element servletNode, String parameterName, Namespace namespace)
	{
		List<Element> parameters = servletNode.getChildren(INIT_PARAM_ELEM_NAME, namespace);
		for(final Element param : parameters)
		{
			Element name = param.getChild(PARAM_NAME_ELEM_NAME, namespace);
			if(name != null && parameterName.equalsIgnoreCase(name.getText()))
			{
				return param;
			}
		}
		return null;
	}

	private static void insertChildAfter(Element parent, Content child, Content anchor)
	{
		List<Content> content = parent.getContent();
		List<Content> newContent = new ArrayList<>(content.size());
		for(Content contentElement : content)
		{
			newContent.add(contentElement.clone());
			if(anchor.equals(contentElement))
			{
				newContent.add(child);
			}
		}
		parent.setContent(newContent);
	}

	@Nullable
	public static Element findElementByAttr(Element parentElement, @NonNls String tagName, @NonNls String attrName, @NonNls final String attrValue)
	{
		if(tagName.equalsIgnoreCase(parentElement.getName()))
		{
			String path = parentElement.getAttributeValue(attrName);
			if(path != null)
			{
				if(path.equalsIgnoreCase(attrValue))
				{
					return parentElement;
				}
			}
		}
		List<Element> children = parentElement.getChildren();
		for(final Element child : children)
		{
			Element elem = findElementByAttr(child, tagName, attrName, attrValue);
			if(elem != null)
			{
				return elem;
			}
		}
		return null;
	}

	public static String getGeneratedFilesPath(TomcatLocalModel tomcatConfiguration)
	{
		String baseDirectoryPath = tomcatConfiguration.getBaseDirectoryPath();
		return FileUtil.toSystemIndependentName(baseDirectoryPath) + "/" + TomcatConstants.CATALINA_WORK_DIRECTORY_NAME + "/" + TomcatConstants.SCRATCHDIR_NAME;
	}

	public static String baseConfigDir(String baseDirectoryPath)
	{
		return baseDirectoryPath + File.separator + TomcatConstants.CATALINA_CONFIG_DIRECTORY_NAME;
	}

	@Nullable
	public static Element findContextElement(String baseDirectoryPath, TomcatDeploymentModelBase deploymentModel) throws ExecutionException
	{
		final TomcatLocalModel serverModel = ((TomcatLocalModel) deploymentModel.getServerModel());
		final boolean isVersion5OrHigher = serverModel.isVersion5OrHigher();

		Element contextElement = null;

		if(isVersion5OrHigher)
		{
			contextElement = findContextInContextXml(deploymentModel);
		}

		if(contextElement == null)
		{
			String contextPath = deploymentModel.getContextPath();
			if(contextPath == null)
			{
				throw new ExecutionException(TomcatBundle.message("exception.text.context.path.not.configured"));
			}

			contextElement = new TomcatContexts(serverModel, baseDirectoryPath).findContextByPath(contextPath);
		}

		return contextElement;
	}
}
