package org.jetbrains.idea.tomcat.server;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.regex.Pattern;

import org.jdom.Element;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.idea.tomcat.*;
import org.jetbrains.idea.tomcat.admin.TomEEAgentAdminServerImpl;
import org.jetbrains.idea.tomcat.admin.TomcatAdminLocalServerImpl;
import org.jetbrains.idea.tomcat.admin.TomcatAdminServerBase;
import com.intellij.debugger.PositionManager;
import com.intellij.debugger.engine.DebugProcess;
import com.intellij.execution.ExecutionException;
import com.intellij.execution.configurations.LogFileOptions;
import com.intellij.execution.configurations.PredefinedLogFile;
import com.intellij.execution.configurations.RuntimeConfigurationError;
import com.intellij.execution.configurations.RuntimeConfigurationException;
import com.intellij.javaee.appServerIntegrations.ApplicationServer;
import com.intellij.javaee.deployment.DeploymentModel;
import com.intellij.javaee.oss.server.JavaeeServerInstance;
import com.intellij.javaee.run.configuration.CommonModel;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.diagnostic.Logger;
import com.intellij.openapi.fileTypes.FileTypeManager;
import com.intellij.openapi.options.SettingsEditor;
import com.intellij.openapi.projectRoots.JavaSdkVersion;
import com.intellij.openapi.util.Computable;
import com.intellij.openapi.util.InvalidDataException;
import com.intellij.openapi.util.Pair;
import com.intellij.openapi.util.SystemInfo;
import com.intellij.openapi.util.WriteExternalException;
import com.intellij.openapi.util.io.FileUtil;
import com.intellij.openapi.util.text.StringUtil;
import com.intellij.util.SmartList;
import com.intellij.util.net.NetUtils;
import com.intellij.util.xmlb.SkipDefaultValuesSerializationFilters;
import com.intellij.util.xmlb.XmlSerializer;
import com.intellij.util.xmlb.annotations.OptionTag;
import consulo.javaee.module.extension.JavaEEModuleExtension;

/**
 * Created by IntelliJ IDEA.
 * User: michael.golubev
 */
public class TomcatLocalModel extends TomcatServerModel
{

	private static final Logger LOG = Logger.getInstance("#" + TomcatLocalModel.class.getName());

	@NonNls
	private static final String TOMCAT_LOCALHOST_ACCESS_LOG_ID = "Tomcat Localhost Access";

	@NonNls
	private static final String TOMCAT_CATALINA_LOG_ID = "Tomcat Catalina";

	@NonNls
	private static final String TOMCAT_MANAGER_LOG_ID = "Tomcat Manager";

	@NonNls
	private static final String TOMCAT_HOST_MANAGER_LOG_ID = "Tomcat Host Manager";

	public static final int UNDEFINED_PORT = 0;

	@NonNls
	private static final String AUTO_DEPLOY_ATTR = "autoDeploy";
	@NonNls
	private static final String UNPACK_WARS_ATTR = "unpackWARs";
	@NonNls
	private static final String TOMCAT_USERS_XML = "tomcat-users.xml";
	@NonNls
	private static final String MANAGER_CONTEXT_PATH = "/manager";
	@NonNls
	private static final String DEPLOY_ON_STARTUP_ATTR = "deployOnStartup";

	private static Pattern SESSIONS_FILENAME_PATTERN = Pattern.compile("SESSIONS\\.ser");


	public boolean DEPLOY_TOMCAT_APPS;
	public String BASE_DIRECTORY_NAME;
	public int HTTP_PORT;
	public int HTTPS_PORT;
	public int AJP_PORT;
	public int JNDI_PORT = DEFAULT_JNDI_PORT;
	public boolean PRESERVE_SESSIONS;

	public TomcatLocalModel()
	{
		loadData(new TomcatModelData());
	}

	@Override
	protected TomcatAdminServerBase<?> createTomcatServerAdmin(JavaeeServerInstance serverInstance, TomEEAgentAdminServerImpl tomEEAdmin) throws Exception
	{
		prepareServer();
		addContexts();
		return new TomcatAdminLocalServerImpl(this, tomEEAdmin, serverInstance);
	}

	private TomcatPersistentDataWrapper createPersistentDataWrapper() throws RuntimeConfigurationError
	{
		ApplicationServer applicationServer = getApplicationServer();
		if(applicationServer == null)
		{
			throw new RuntimeConfigurationError(TomcatBundle.message("exception.text.application.server.not.specified"));
		}
		return new TomcatPersistentDataWrapper(applicationServer);
	}

	public String getHomeDirectory() throws RuntimeConfigurationException
	{
		return createPersistentDataWrapper().getHomeDirectory();
	}

	public String getSourceBaseDirectoryPath() throws RuntimeConfigurationException
	{
		return createPersistentDataWrapper().getSourceBaseDirectoryPath();
	}

	public String getBaseDirectoryPath()
	{
		return ApplicationManager.getApplication().runReadAction(new Computable<String>()
		{
			public String compute()
			{
				try
				{
					if(BASE_DIRECTORY_NAME.length() == 0)
					{
						BASE_DIRECTORY_NAME = TomcatSystemBaseDirManager.getInstance().suggestNewDirectoryName(getCommonModel().getName() + "_" + getProject().getName());
					}
					return TomcatSystemBaseDirManager.getInstance().getDirectory(BASE_DIRECTORY_NAME).getCanonicalPath();
				}
				catch(IOException e)
				{
					LOG.error(e);
					return "";
				}
			}
		});
	}

	@Override
	@NotNull
	public PredefinedLogFile[] getPredefinedLogFiles()
	{
		List<PredefinedLogFile> result = new ArrayList<>();
		for(PredefinedLogFile nextLogFile : super.getPredefinedLogFiles())
		{
			if(!COMPATIBLE_TOMCAT_LOCALHOST_LOG_ID.equals(nextLogFile.getId()))
			{
				result.add(nextLogFile);
			}
		}
		return result.toArray(new PredefinedLogFile[result.size()]);
	}

	@Override
	protected List<LogFileFactory> getLogFileFactories()
	{
		List<LogFileFactory> result = new ArrayList<>();
		result.addAll(super.getLogFileFactories());

		result.add(new TomcatLogFileFactory(true)
		{

			@Override
			protected String getId()
			{
				return TOMCAT_CATALINA_LOG_ID;
			}

			@Override
			protected String getFilename()
			{
				return "catalina";
			}
		});
		result.add(new TomcatLogFileFactory()
		{

			@Override
			protected String getId()
			{
				return TOMCAT_MANAGER_LOG_ID;
			}

			@Override
			protected String getFilename()
			{
				return "manager";
			}
		});
		result.add(new TomcatLogFileFactory()
		{

			@Override
			protected String getId()
			{
				return TOMCAT_HOST_MANAGER_LOG_ID;
			}

			@Override
			protected String getFilename()
			{
				return "host-manager";
			}
		});
		result.add(new TomcatLogFileFactory()
		{

			@Override
			protected String getId()
			{
				return TOMCAT_LOCALHOST_ACCESS_LOG_ID;
			}

			@Override
			protected String getFilename()
			{
				return "localhost_access_log";
			}
		});

		return result;
	}

	@Override
	public List<Pair<String, String>> getExternalizationMacros()
	{
		List<Pair<String, String>> list = new SmartList<>();
		if(!StringUtil.isEmpty(BASE_DIRECTORY_NAME))
		{
			final File directory = TomcatSystemBaseDirManager.getInstance().getDirectory(BASE_DIRECTORY_NAME);
			list.add(Pair.create("CATALINA_BASE", FileUtil.toSystemIndependentName(directory.getAbsolutePath())));
		}

		final ApplicationServer server = getApplicationServer();
		if(server != null)
		{
			final String tomcatHome = getHome();
			if(!StringUtil.isEmpty(tomcatHome))
			{
				list.add(Pair.create("TOMCAT_HOME", FileUtil.toSystemIndependentName(tomcatHome)));
			}
		}

		return list;
	}

	@Override
	public void onNewConfigurationCreated()
	{
		BASE_DIRECTORY_NAME = "";
	}

	@Override
	public void onConfigurationCopied()
	{
		BASE_DIRECTORY_NAME = "";
	}

	private void loadData(TomcatModelData data)
	{
		DEPLOY_TOMCAT_APPS = data.isDeployTomcatApps();
		BASE_DIRECTORY_NAME = data.getBaseDirectoryName();
		HTTP_PORT = data.getHttpPort();
		HTTPS_PORT = data.getHttpsPort();
		JNDI_PORT = data.getJndiPort();
		AJP_PORT = data.getAjpPort();
		PRESERVE_SESSIONS = data.isPreserveSessions();
	}

	public void readExternal(Element element) throws InvalidDataException
	{
		boolean clearBaseDirectory = BASE_DIRECTORY_NAME == null;

		TomcatModelData data = new TomcatModelData();
		XmlSerializer.deserializeInto(data, element);
		loadData(data);

		if(clearBaseDirectory)
		{
			BASE_DIRECTORY_NAME = "";
		}
	}

	public void writeExternal(Element element) throws WriteExternalException
	{
		TomcatModelData data = new TomcatModelData();
		data.setDeployTomcatApps(DEPLOY_TOMCAT_APPS);
		data.setBaseDirectoryName(BASE_DIRECTORY_NAME);
		data.setHttpPort(HTTP_PORT);
		data.setHttpsPort(HTTPS_PORT);
		data.setJndiPort(JNDI_PORT);
		data.setAjpPort(AJP_PORT);
		data.setPreserveSessions(PRESERVE_SESSIONS);
		XmlSerializer.serializeInto(data, element, new SkipDefaultValuesSerializationFilters());
	}

	public SettingsEditor<CommonModel> getEditor()
	{
		return new TomcatLocalRunConfigurationEditor();
	}

	public boolean isSourceLocalPort()
	{
		return HTTP_PORT == UNDEFINED_PORT;
	}

	public int getLocalPort()
	{
		Integer port = getHttpPort();
		return port == null ? getDefaultPort() : port;
	}

	public Integer getHttpPort()
	{
		if(!isSourceLocalPort())
		{
			return HTTP_PORT;
		}
		try
		{
			TomcatPersistentDataWrapper dataWrapper = createPersistentDataWrapper();
			if(dataWrapper.hasSourceLocalPort())
			{
				return dataWrapper.getSourceLocalPort();
			}
		}
		catch(RuntimeConfigurationException e)
		{
			//
		}
		return null;
	}

	public void setHttpPort(int port)
	{
		HTTP_PORT = port;
		try
		{
			TomcatPersistentDataWrapper dataWrapper = createPersistentDataWrapper();
			if(dataWrapper.hasSourceLocalPort() && port == dataWrapper.getSourceLocalPort())
			{
				HTTP_PORT = UNDEFINED_PORT;
			}
		}
		catch(RuntimeConfigurationException e)
		{
			//
		}
	}

	@Override
	public boolean isDetectableLocalPort()
	{
		return false;
	}

	@Override
	public boolean isDetectableServerPort()
	{
		return false;
	}

	@Override
	public void checkConfiguration() throws RuntimeConfigurationException
	{
		if(isVersion8OrHigher())
		{
			if(!SystemInfo.isJavaVersionAtLeast("1.7"))
			{
				checkHasJdk();
			}
			checkJdkAtLeast7();
		}

		super.checkConfiguration();
		TomcatServerXmlWrapper serverXmlWrapper = new TomcatServerXmlWrapper(getSourceBaseDirectoryPath());
		serverXmlWrapper.checkHttpConnectorsAmount();
		if(HTTPS_PORT != UNDEFINED_PORT)
		{
			serverXmlWrapper.checkHttpsConnectorsAmount();
		}
		if(AJP_PORT != UNDEFINED_PORT)
		{
			serverXmlWrapper.checkAjpConnectorsAmount();
		}
	}

	@Override
	protected String getJdkRequiredMessage()
	{
		return getJdkTooOldMessage(JavaSdkVersion.JDK_1_7.getDescription());
	}

	private String createLogFilePath(@NonNls String filename)
	{
		return FileUtil.toSystemDependentName(TomcatUtil.getLogsDirPath(getBaseDirectoryPath()) + "/" + filename + ".*");
	}

	@Override
	protected String getLogFilePath(String home)
	{
		return createLogFilePath("localhost");
	}

	@Override
	protected int getJndiPort()
	{
		return JNDI_PORT;
	}

	public static class TomcatModelData
	{

		private boolean myDeployTomcatApps = false;

		private String myBaseDirectoryName = "";

		private int myHttpPort = UNDEFINED_PORT;

		private int myHttpsPort = UNDEFINED_PORT;

		private int myJndiPort = DEFAULT_JNDI_PORT;

		private int myAjpPort = UNDEFINED_PORT;

		private boolean myPreserveSessions = false;

		@OptionTag("DEPLOY_TOMCAT_APPS")
		public boolean isDeployTomcatApps()
		{
			return myDeployTomcatApps;
		}

		public void setDeployTomcatApps(boolean deployTomcatApps)
		{
			myDeployTomcatApps = deployTomcatApps;
		}

		@OptionTag("BASE_DIRECTORY_NAME")
		public String getBaseDirectoryName()
		{
			return myBaseDirectoryName;
		}

		public void setBaseDirectoryName(String baseDirectoryName)
		{
			myBaseDirectoryName = baseDirectoryName;
		}

		@OptionTag("HTTP_PORT")
		public int getHttpPort()
		{
			return myHttpPort;
		}

		public void setHttpPort(int httpPort)
		{
			myHttpPort = httpPort;
		}

		@OptionTag("HTTPS_PORT")
		public int getHttpsPort()
		{
			return myHttpsPort;
		}

		public void setHttpsPort(int httpsPort)
		{
			myHttpsPort = httpsPort;
		}

		@OptionTag("PRESERVE_SESSIONS")
		public boolean isPreserveSessions()
		{
			return myPreserveSessions;
		}

		public void setPreserveSessions(boolean preserveSessions)
		{
			myPreserveSessions = preserveSessions;
		}

		@OptionTag("JNDI_PORT")
		public int getJndiPort()
		{
			return myJndiPort;
		}

		public void setJndiPort(int jndiPort)
		{
			myJndiPort = jndiPort;
		}

		@OptionTag("AJP_PORT")
		public int getAjpPort()
		{
			return myAjpPort;
		}

		public void setAjpPort(int ajpPort)
		{
			myAjpPort = ajpPort;
		}
	}

	@Override
	public PositionManager createPositionManager(DebugProcess process, JavaEEModuleExtension[] scopeFacetsWithIncluded)
	{
		if(isVersion5OrHigher())
		{
			return super.createPositionManager(process, scopeFacetsWithIncluded);
		}
		else
		{
			return new Tomcat40PositionManager(process, TomcatUtil.getGeneratedFilesPath(this), scopeFacetsWithIncluded);
		}
	}

	private void prepareServer() throws ExecutionException
	{
		try
		{
			String baseDirectoryPath = getBaseDirectoryPath();
			final File baseDirectory = new File(baseDirectoryPath);
			final File logsDir = new File(TomcatUtil.getLogsDirPath(baseDirectoryPath));
			final List<File> sessionFiles = PRESERVE_SESSIONS && baseDirectory.isDirectory() //
					? FileUtil.findFilesByMask(SESSIONS_FILENAME_PATTERN, baseDirectory) : Collections.<File>emptyList();
			FileUtil.processFilesRecursively(baseDirectory, file ->
			{
				if(FileUtil.compareFiles(file, baseDirectory) == 0 || (logsDir.exists() && FileUtil.isAncestor(logsDir, file, false)))
				{
					return true;
				}
				for(File sessionFile : sessionFiles)
				{
					if(FileUtil.isAncestor(file, sessionFile, false))
					{
						return true;
					}
				}
				FileUtil.delete(file);
				return true;
			});

			String sourceBaseDirectoryPath = getSourceBaseDirectoryPath();

			File sourceBase = new File(TomcatUtil.baseConfigDir(sourceBaseDirectoryPath));
			File workBase = new File(TomcatUtil.baseConfigDir(baseDirectoryPath));

			try
			{
				FileUtil.copyDir(sourceBase, workBase, file -> !FileTypeManager.getInstance().isFileIgnored(file.getName()));
				if(!getHomeDirectory().equals(sourceBaseDirectoryPath))
				{
					File setEnvFile = TomcatUtil.getSetEnvFile(sourceBaseDirectoryPath);
					if(setEnvFile.exists())
					{
						FileUtil.copy(setEnvFile, TomcatUtil.getSetEnvFile(baseDirectoryPath));
					}
				}
				LOG.debug("Logs dir created: " + logsDir.mkdir());
			}
			catch(IOException e)
			{
				throw new ExecutionException(TomcatBundle.message("message.text.error.copying.configuration.files.from.0.to.1.because.of.2", sourceBase.getPath(), workBase.getPath(), e.getMessage
						()));
			}
			patchCatalinaProperties(new File(workBase, "catalina.properties"), sourceBaseDirectoryPath);


			if(!DEPLOY_TOMCAT_APPS)
			{
				TomcatContexts contexts = new TomcatContexts(this, baseDirectoryPath);
				for(TomcatContextItem contextItem : contexts.getItems())
				{
					if(!isVersion5OrHigher())
					{
						if(MANAGER_CONTEXT_PATH.equals(contextItem.getPath()))
						{
							continue;
						}
					}
					contextItem.remove();
				}
			}

			TomcatServerXmlWrapper workServerXml = new TomcatServerXmlWrapper(baseDirectoryPath);
			Element localHost = workServerXml.findLocalHost();

			String appBase = localHost.getAttributeValue(TomcatConstants.APP_BASE_ATTR);

			if(appBase == null)
			{
				appBase = "";
			}

			if(!FileUtil.isAbsolute(appBase))
			{
				appBase = new File(sourceBaseDirectoryPath, appBase).getAbsolutePath();
			}
			localHost.setAttribute(TomcatConstants.APP_BASE_ATTR, appBase);
			localHost.setAttribute(AUTO_DEPLOY_ATTR, Boolean.TRUE.toString());
			localHost.setAttribute(UNPACK_WARS_ATTR, Boolean.TRUE.toString());

			localHost.setAttribute(DEPLOY_ON_STARTUP_ATTR, Boolean.FALSE.toString());

			if(!DEPLOY_TOMCAT_APPS)
			{
				boolean supportDeployIgnore;
				try
				{
					supportDeployIgnore = isVersionHigher6032();
				}
				catch(IOException e)
				{
					throw new ExecutionException(e);
				}

				if(supportDeployIgnore)
				{
					localHost.setAttribute("deployIgnore", "^(?!(manager)|(tomee)$).*");
				}
			}

			if(!isSourceLocalPort())
			{
				workServerXml.setHttpPort(HTTP_PORT);
			}
			int securedLocalPort = HTTPS_PORT;
			if(securedLocalPort != UNDEFINED_PORT)
			{
				workServerXml.setHttpsPort(securedLocalPort);
			}

			FreePortsProvider freePortsProvider = new FreePortsProvider();

			int shutdownPort = workServerXml.getShutdownPort();
			if(!isPortFree(shutdownPort))
			{
				shutdownPort = freePortsProvider.getFreePorts()[0];
				workServerXml.setShutdownPort(shutdownPort);
			}

			if(AJP_PORT == UNDEFINED_PORT)
			{
				Integer ajpPort = workServerXml.getAjpPort();
				if(ajpPort != null && !isPortFree(ajpPort))
				{
					ajpPort = freePortsProvider.getFreePorts()[1];
					workServerXml.setAjpPort(ajpPort);
				}
			}
			else
			{
				workServerXml.setAjpPort(AJP_PORT);
			}

			workServerXml.save();

			File tomcatUsers = new File(workBase, TOMCAT_USERS_XML);
			if(tomcatUsers.exists() && !isVersion5OrHigher())
			{
				Tomcat4Deployer.addManagerUser(tomcatUsers);
			}

			TomcatUtil.configureWebXml(this);
		}
		catch(RuntimeConfigurationException e)
		{
			throw new ExecutionException(e.getMessage(), e);
		}
	}

	private void addContexts() throws ExecutionException
	{
		if(!isVersion5OrHigher())
		{
			for(DeploymentModel deploymentModel : getCommonModel().getDeploymentModels())
			{
				TomcatAdminLocalServerImpl.addApplicationContext((TomcatModuleDeploymentModel) deploymentModel);
			}
		}
	}

	private static boolean isPortFree(int port)
	{
		try
		{
			Socket socket = new Socket(InetAddress.getLoopbackAddress(), port);
			try
			{
				socket.close();
			}
			catch(IOException e)
			{
				LOG.debug(e);
			}
			return false;
		}
		catch(IOException e)
		{
			LOG.debug(e);
		}
		// checking ServerSocket is not enough for some reason...
		try
		{
			final ServerSocket serverSocket = new ServerSocket(port);
			try
			{
				serverSocket.close();
			}
			catch(IOException e)
			{
				LOG.debug(e);
			}
		}
		catch(IOException e)
		{
			LOG.debug(e);
			return false;
		}
		return true;
	}

	private static void patchCatalinaProperties(final File file, final String catalinaBasePath)
	{
		if(!file.exists())
		{
			return;
		}

		try
		{
			File oldFile = new File(file.getAbsolutePath() + ".0");
			FileUtil.rename(file, oldFile);
			BufferedReader input = new BufferedReader(new FileReader(oldFile));
			PrintWriter output = new PrintWriter(file);
			try
			{
				String line;
				while((line = input.readLine()) != null)
				{
					output.println(StringUtil.replace(line, "${catalina.base}", FileUtil.toSystemIndependentName(catalinaBasePath)));
				}
			}
			finally
			{
				output.close();
				input.close();
			}
		}
		catch(IOException e)
		{
			LOG.info(e);
		}
	}

	private static class FreePortsProvider
	{

		private int[] myFreePorts = null;

		public int[] getFreePorts() throws ExecutionException
		{
			if(myFreePorts == null)
			{
				try
				{
					myFreePorts = NetUtils.findAvailableSocketPorts(2);
				}
				catch(IOException e)
				{
					throw new ExecutionException("Unable to find free ports", e);
				}
			}
			return myFreePorts;
		}
	}

	private abstract class TomcatLogFileFactory extends LogFileFactory
	{

		public TomcatLogFileFactory(boolean enabled)
		{
			super(enabled);
		}

		public TomcatLogFileFactory()
		{
			super(false);
		}

		@Override
		public LogFileOptions createOptions(PredefinedLogFile file)
		{
			if(TOMCAT_LOCALHOST_ACCESS_LOG_ID.equals(file.getId()) && !isVersion7OrHigher())
			{
				return null;
			}
			return super.createOptions(file);
		}

		@Override
		protected String getPath()
		{
			return createLogFilePath(getFilename());
		}

		protected abstract String getFilename();
	}
}
