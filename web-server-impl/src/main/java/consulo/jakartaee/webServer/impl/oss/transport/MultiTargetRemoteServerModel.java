package consulo.jakartaee.webServer.impl.oss.transport;

import consulo.jakartaee.webServer.impl.run.configuration.ServerModel;

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
