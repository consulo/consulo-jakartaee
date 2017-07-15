/*
 * Copyright (c) 2004 - 2009 by Fuhrer Engineering AG, CH-2504 Biel/Bienne, Switzerland
 */

package com.intellij.javaee.oss.server;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.jdom.Element;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import com.intellij.debugger.PositionManager;
import com.intellij.debugger.engine.DebugProcess;
import com.intellij.execution.ExecutionException;
import com.intellij.execution.configurations.LogFileOptions;
import com.intellij.execution.configurations.PredefinedLogFile;
import com.intellij.execution.configurations.RuntimeConfigurationError;
import com.intellij.execution.configurations.RuntimeConfigurationException;
import com.intellij.execution.process.ProcessHandler;
import com.intellij.javaee.appServerIntegrations.ApplicationServer;
import com.intellij.javaee.deployment.DeploymentManager;
import com.intellij.javaee.deployment.DeploymentModel;
import com.intellij.javaee.deployment.DeploymentProvider;
import com.intellij.javaee.model.xml.application.JavaeeApplication;
import com.intellij.javaee.model.xml.application.JavaeeModule;
import com.intellij.javaee.oss.JavaeeBundle;
import com.intellij.javaee.oss.admin.JavaeeAdmin;
import com.intellij.javaee.oss.descriptor.JavaeeDescriptor;
import com.intellij.javaee.run.configuration.CommonModel;
import com.intellij.javaee.run.configuration.CommonStrategy;
import com.intellij.javaee.run.configuration.PredefinedLogFilesProvider;
import com.intellij.javaee.run.configuration.ServerModel;
import com.intellij.javaee.run.execution.DefaultOutputProcessor;
import com.intellij.javaee.run.execution.OutputProcessor;
import com.intellij.javaee.serverInstances.J2EEServerInstance;
import com.intellij.openapi.deployment.DeploymentUtil;
import com.intellij.openapi.projectRoots.Sdk;
import com.intellij.openapi.roots.OrderRootType;
import com.intellij.openapi.util.DefaultJDOMExternalizer;
import com.intellij.openapi.util.InvalidDataException;
import com.intellij.openapi.util.Pair;
import com.intellij.openapi.util.WriteExternalException;
import com.intellij.openapi.util.text.StringUtil;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.remoteServer.configuration.deployment.DeploymentSource;
import com.intellij.util.PathUtil;
import consulo.javaee.module.extension.JavaEEApplicationModuleExtension;
import consulo.javaee.module.extension.JavaEEModuleExtension;
import consulo.javaee.module.extension.JavaWebModuleExtension;

public abstract class JavaeeServerModel implements ServerModel, PredefinedLogFilesProvider
{
	protected static class LogFileFactory
	{
		private boolean myEnabled;

		public LogFileFactory(boolean enabled)
		{
			myEnabled = enabled;
		}

		protected String getId()
		{
			return getClass().getSimpleName();
		}

		protected String getName()
		{
			return getId();
		}

		protected LogFileOptions createOptions(PredefinedLogFile file)
		{
			return null;
		}

		protected String getPath()
		{
			return null;
		}
	}

	protected static class DefaultLogFileFactory extends LogFileFactory
	{
		public DefaultLogFileFactory()
		{
			super(true);
		}
	}

	public String USERNAME = getDefaultUsername();

	public String PASSWORD = getDefaultPassword();

	private CommonModel config;

	public PositionManager createPositionManager(DebugProcess process, JavaEEModuleExtension[] scopeFacetsWithIncluded)
	{
		//TODO [VISTALL] STUB
		return null;
	}

	protected String getJdkRequiredMessage()
	{
		//TODO [VISTALL] STUB
		return null;
	}

	public int getPingPort()
	{
		//TODO [VISTALL] STUB
		return -1;
	}

	protected JavaeeAdmin createServerAdmin(JavaeeServerInstance serverInstance) throws Exception
	{
		//TODO [VISTALL] STUB
		return null;
	}

	public String getVmArgument(String key)
	{
		return null;
	}

	protected List<LogFileFactory> getLogFileFactories()
	{
		//TODO [VISTALL] STUB
		return Collections.emptyList();
	}

	protected String getLogFileId()
	{
		//TODO [VISTALL] STUB
		return null;
	}

	public boolean isDetectableLocalPort()
	{
		//TODO [VISTALL] STUB
		return true;
	}

	public boolean isDetectableServerPort()
	{
		//TODO [VISTALL] STUB
		return true;
	}

	public void onNewConfigurationCreated()
	{
		//TODO [VISTALL] STUB
	}

	public void onConfigurationCopied()
	{
		//TODO [VISTALL] STUB
	}

	public List<Pair<String, String>> getExternalizationMacros()
	{
		//TODO [VISTALL] STUB
		return Collections.emptyList();
	}

	protected void checkJdkAtLeast7()
	{
		//TODO [VISTALL] STUB
	}

	protected void checkHasJdk()
	{
		//TODO [VISTALL] STUB
	}

	protected String getJdkTooOldMessage(String description)
	{
		//TODO [VISTALL] STUB
		return null;
	}

	public Sdk getJre()
	{
		//TODO [VISTALL] STUB
		return null;
	}

	public String getJrePath()
	{
		//TODO [VISTALL] STUB
		return null;
	}

	public boolean undeployBeforeDeploy(DeploymentModel deploymentModel)
	{
		//TODO [VISTALL] STUB
		return false;
	}

	public void setCommonModel(CommonModel config)
	{
		this.config = config;
	}

	public J2EEServerInstance createServerInstance() throws ExecutionException
	{
		/*try
		{
			((JavaeeIntegration) config.getIntegration()).checkValidServerHome(getHome(), getVersion());
			ClassLoader loader = new JavaeeClassLoader(getLibraries(), getExcludes(), getClass().getClassLoader());
			Constructor<?> constructor = loader.loadClass(getServerName()).getDeclaredConstructor();
			constructor.setAccessible(true);
			JavaeeServer server = JavaeeServer.class.cast(constructor.newInstance());
			server.setHost(getServerHost());
			server.setPort(getServerPort());
			server.setUsername(USERNAME);
			server.setPassword(PASSWORD);
			return new JavaeeServerInstance(config, server);
		}
		catch(Exception e)
		{
			throw new ExecutionException(e.getMessage(), e);
		} */
		throw new UnsupportedOperationException();
	}

	public DeploymentProvider getDeploymentProvider()
	{
		return new JavaeeDeploymentProvider(false);
	}

	public int getDefaultPort()
	{
		return 8080;
	}

	public int getLocalPort()
	{
		return getDefaultPort();
	}

	public String getDefaultUrlForBrowser()
	{
		return getDefaultUrlForBrowser(true);
	}

	public OutputProcessor createOutputProcessor(ProcessHandler handler, J2EEServerInstance instance)
	{
		return new DefaultOutputProcessor(handler);
	}

	@Nullable
	public List<Pair<String, Integer>> getAddressesToCheck()
	{
		return config.isLocal() ? Collections.singletonList(new Pair<String, Integer>(getServerHost(), getServerPort())) : null;
	}

	public void checkConfiguration() throws RuntimeConfigurationException
	{
		if(StringUtil.isNotEmpty(USERNAME) && StringUtil.isEmpty(PASSWORD))
		{
			throw new RuntimeConfigurationError(JavaeeBundle.getText("ServerModel.password"));
		}
	}

	public void readExternal(Element element) throws InvalidDataException
	{
		DefaultJDOMExternalizer.readExternal(this, element);
	}

	public void writeExternal(Element element) throws WriteExternalException
	{
		DefaultJDOMExternalizer.writeExternal(this, element);
	}

	@Override
	public Object clone() throws CloneNotSupportedException
	{
		return super.clone();
	}

	public String getHome()
	{
		ApplicationServer server = config.getApplicationServer();
		return (server != null) ? ((JavaeePersistentData) server.getPersistentData()).HOME : "";
	}

	public String getVersion()
	{
		ApplicationServer server = config.getApplicationServer();
		return (server != null) ? ((JavaeePersistentData) server.getPersistentData()).VERSION : "";
	}

	public String getVmArguments()
	{
		return (config instanceof CommonStrategy) ? ((CommonStrategy) config).getSettingsBean().COMMON_VM_ARGUMENTS : "";
	}

	protected boolean isDeploymentSourceSupported(DeploymentSource source)
	{
		return true;
	}

	@NonNls
	protected abstract String getDefaultUsername();

	@NonNls
	protected abstract String getDefaultPassword();

	protected abstract int getServerPort();

	protected String getServerHost()
	{
		return config.getHost();
	}

	public CommonModel getCommonModel()
	{
		return config;
	}

	protected List<File> getLibraries() throws ExecutionException
	{
		List<File> libraries = new ArrayList<File>();
		libraries.add(new File(PathUtil.getJarPathForClass(getClass())));
		for(VirtualFile file : config.getApplicationServer().getLibrary().getFiles(OrderRootType.CLASSES))
		{
			libraries.add(new File(file.getPresentableUrl()));
		}
		return libraries;
	}

	protected File getPathUnderHome(String name)
	{
		return null;
	}

	protected Set<Class<?>> getExcludes()
	{
		Set<Class<?>> excludes = new HashSet<Class<?>>();
		excludes.add(JavaeeServer.class);
		excludes.add(JavaeeDescriptor.class);
		return excludes;
	}

	String getDefaultUrlForBrowser(boolean addContextPath)
	{
		@NonNls String url = "http://" + config.getHost() + ':' + config.getPort();
		if(addContextPath)
		{
			String path = getContextPathFromAppFacets();
			if(StringUtil.isEmpty(path))
			{
				path = getContextPathFromWebFacets();
			}
			if(StringUtil.isNotEmpty(path))
			{
				url = DeploymentUtil.concatPaths(url, path);
			}
			url = DeploymentUtil.concatPaths(url, "/");
		}
		return url;
	}

	@Nullable
	@NonNls
	private String getContextPathFromAppFacets()
	{
		/*List<JavaEEApplicationModuleExtension> facets = new ArrayList<JavaEEApplicationModuleExtension>();
		for(DeploymentModel deployment : config.getDeploymentModels())
		{
			Artifact artifact = deployment.getArtifact();
			JavaEEModuleExtension facet = deployment.getFacet();
			if(artifact != null)
			{
				facets.addAll(JavaeeArtifactUtil.getInstance().getFacetsIncludedInArtifact(config.getProject(), artifact, JavaEEApplicationModuleExtension.class));
			}
			else if(facet instanceof JavaEEApplicationModuleExtension)
			{
				facets.add((JavaEEApplicationModuleExtension) facet);
			}
		}
		for(JavaEEApplicationModuleExtension facet : facets)
		{
			String path = getContextPathFromAppFacet(facet);
			if(path != null)
			{
				return path;
			}
		}*/
		return null;
	}

	@Nullable
	@NonNls
	private String getContextPathFromWebFacets()
	{
	/*	for(DeploymentModel deployment : config.getDeploymentModels())
		{
			Artifact artifact = deployment.getArtifact();
			JavaEEModuleExtension facet = deployment.getFacet();
			if(artifact != null)
			{
				for(JavaWebModuleExtension webFacet : JavaeeArtifactUtil.getInstance().getFacetsIncludedInArtifact(config.getProject(), artifact, JavaWebModuleExtension.class))
				{
					String path = getContextPathFromWebFacet(webFacet, deployment);
					if(path != null)
					{
						return path;
					}
				}
			}
			else if(facet instanceof WebFacet)
			{
				String path = getContextPathFromWebFacet((WebFacet) facet, deployment);
				if(path != null)
				{
					return path;
				}
			}
		}
		for(JavaWebModuleExtension facet : JavaeeFacetUtil.getInstance().getJavaeeFacets(WebFacet.ID, config.getProject()))
		{
			DeploymentModel model = config.getDeploymentModel(facet);
			if((model instanceof JavaeeDeploymentModel) && ((JavaeeDeploymentModel) model).isDeploymentSourceSupported(model.getDeploymentSource()))
			{
				String path = getContextPathFromWebFacet(facet, model);
				if(path != null)
				{
					return path;
				}
			}
		} */
		return null;
	}

	@Nullable
	@NonNls
	protected String getContextPathFromAppFacet(JavaEEApplicationModuleExtension facet)
	{
		JavaeeApplication application = facet.getRoot();
		if(application != null)
		{
			for(JavaeeModule module : application.getModules())
			{
				if(module.getWeb().getXmlTag() != null)
				{
					return module.getWeb().getContextRoot().getValue();
				}
			}
		}
		return null;
	}

	@Nullable
	@NonNls
	protected String getContextPathFromWebFacet(JavaWebModuleExtension facet, DeploymentModel deployment)
	{
		DeploymentManager manager = DeploymentManager.getInstance(config.getProject());
		File source = manager.getDeploymentSource(deployment);
		return (source != null) ? StringUtil.trimEnd(source.getName(), ".war") : null;
	}

	@NotNull
	public PredefinedLogFile[] getPredefinedLogFiles()
	{
		return new PredefinedLogFile[]{new PredefinedLogFile(getLogFileId(), true)};
	}

	@Nullable
	public LogFileOptions getOptionsForPredefinedLogFile(PredefinedLogFile file)
	{
		if(getLogFileId().equals(file.getId()))
		{
			String path = getLogFilePath(getHome());
			if(path != null)
			{
				String name = JavaeeBundle.getText("ServerModel.logfile", getLogFileId());
				return new LogFileOptions(name, path, file.isEnabled(), true, false);
			}
		}
		return null;
	}

	@Nullable
	protected String getLogFilePath(String home)
	{
		return null;
	}

	protected boolean isTruncateLogFile()
	{
		return false;
	}
}
