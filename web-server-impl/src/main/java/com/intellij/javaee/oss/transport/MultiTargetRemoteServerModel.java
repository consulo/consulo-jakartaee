package com.intellij.javaee.oss.transport;

import com.intellij.javaee.run.configuration.ServerModel;

/**
 * @author VISTALL
 * @since 08-Jul-17
 */
public interface MultiTargetRemoteServerModel extends ServerModel
{
	String getTransportHostId();

	void setTransportHostId(String transportHostId);

	boolean isDeployAllowed();

	TransportTargetModelBase getTarget(String targetName);
}
