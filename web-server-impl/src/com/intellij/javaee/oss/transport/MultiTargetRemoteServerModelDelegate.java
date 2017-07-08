package com.intellij.javaee.oss.transport;

import java.io.File;
import java.util.List;
import java.util.Set;

import org.jdom.Element;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import com.intellij.execution.ExecutionException;
import com.intellij.execution.configurations.RuntimeConfigurationException;
import com.intellij.execution.process.ProcessHandler;
import com.intellij.javaee.deployment.DeploymentProvider;
import com.intellij.javaee.run.configuration.CommonModel;
import com.intellij.javaee.run.execution.OutputProcessor;
import com.intellij.javaee.serverInstances.J2EEServerInstance;
import com.intellij.openapi.options.SettingsEditor;
import com.intellij.openapi.util.InvalidDataException;
import com.intellij.openapi.util.Pair;
import com.intellij.openapi.util.WriteExternalException;

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
	@NotNull
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
