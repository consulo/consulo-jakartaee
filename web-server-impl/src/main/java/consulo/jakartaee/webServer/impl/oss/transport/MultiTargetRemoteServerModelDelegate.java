package consulo.jakartaee.webServer.impl.oss.transport;

import consulo.jakartaee.webServer.impl.deployment.DeploymentProvider;
import consulo.jakartaee.webServer.impl.run.configuration.CommonModel;
import consulo.jakartaee.webServer.impl.run.execution.OutputProcessor;
import consulo.jakartaee.webServer.impl.serverInstances.J2EEServerInstance;
import consulo.execution.RuntimeConfigurationException;
import consulo.execution.configuration.ui.SettingsEditor;
import consulo.process.ExecutionException;
import consulo.process.ProcessHandler;
import consulo.util.lang.Pair;
import consulo.util.xml.serializer.InvalidDataException;
import consulo.util.xml.serializer.WriteExternalException;
import org.jdom.Element;

import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import java.io.File;
import java.util.List;
import java.util.Set;

/**
 * @author VISTALL
 * @since 08-Jul-17
 */
public class MultiTargetRemoteServerModelDelegate implements MultiTargetRemoteServerModel
{
	protected MultiTargetRemoteServerModel getDelegate()
	{
		return null;
	}

	@Override
	public String getTransportHostId()
	{
		return getDelegate().getTransportHostId();
	}

	@Override
	public void setTransportHostId(String transportHostId)
	{
		getDelegate().setTransportHostId(transportHostId);
	}

	@Override
	public boolean isDeployAllowed()
	{
		return getDelegate().isDeployAllowed();
	}

	@Override
	public TransportTargetModelBase getTarget(String targetName)
	{
		return getDelegate().getTarget(targetName);
	}

	@Override
	public J2EEServerInstance createServerInstance() throws ExecutionException
	{
		return getDelegate().createServerInstance();
	}

	@Override
	@Nullable
	public DeploymentProvider getDeploymentProvider()
	{
		return getDelegate().getDeploymentProvider();
	}

	@Override
	@Nonnull
	public String getDefaultUrlForBrowser()
	{
		return getDelegate().getDefaultUrlForBrowser();
	}

	@Override
	public SettingsEditor getEditor()
	{
		return getDelegate().getEditor();
	}

	@Override
	public OutputProcessor createOutputProcessor(ProcessHandler processHandler, J2EEServerInstance serverInstance)
	{
		return getDelegate().createOutputProcessor(processHandler, serverInstance);
	}

	@Override
	public List<Pair<String, Integer>> getAddressesToCheck()
	{
		return getDelegate().getAddressesToCheck();
	}

	@Override
	public void checkConfiguration() throws RuntimeConfigurationException
	{
		getDelegate().checkConfiguration();
	}

	@Override
	public int getDefaultPort()
	{
		return getDelegate().getDefaultPort();
	}

	@Override
	public void setCommonModel(CommonModel commonModel)
	{
		getDelegate().setCommonModel(commonModel);
	}

	@Override
	public Object clone() throws CloneNotSupportedException
	{
		return new MultiTargetRemoteServerModelDelegate();
	}

	@Override
	public int getLocalPort()
	{
		return getDelegate().getLocalPort();
	}

	@Override
	public void readExternal(Element element) throws InvalidDataException
	{
		getDelegate().readExternal(element);
	}

	@Override
	public void writeExternal(Element element) throws WriteExternalException
	{
		getDelegate().writeExternal(element);
	}

	public boolean transferFile(String contextTargetName, File contextXmlFile)
	{
		return false;
	}

	public String prepareDeployment(String stagingTargetName, String sourcePath, boolean b)
	{
		return null;
	}

	public <T> void checkConfiguration(Set<T> singleton)
	{

	}

	public void readFromData(MultiTargetRemoteServerModelData settings)
	{
	}

	public void writeToData(MultiTargetRemoteServerModelData settings)
	{
	}
}
