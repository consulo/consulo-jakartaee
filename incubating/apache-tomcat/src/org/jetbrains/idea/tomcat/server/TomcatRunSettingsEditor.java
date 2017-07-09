package org.jetbrains.idea.tomcat.server;

import javax.swing.JTextField;

import org.jetbrains.idea.tomcat.TomcatBundle;
import com.intellij.javaee.oss.server.JavaeeRunSettingsEditor;
import com.intellij.openapi.options.ConfigurationException;
import consulo.apache.tomcat.bundle.TomcatBundleType;

/**
 * Created by IntelliJ IDEA.
 * User: michael.golubev
 */
public abstract class TomcatRunSettingsEditor<T extends TomcatServerModel> extends JavaeeRunSettingsEditor<T>
{
	public TomcatRunSettingsEditor()
	{
		super(TomcatBundleType.getInstance());
	}

	protected int getJndiPort(JTextField portField, T serverModel) throws ConfigurationException
	{
		int port;
		try
		{
			port = getPort(portField, TomcatBundle.message("error.message.invalid.jmx.port.number"));
		}
		catch(ConfigurationException e)
		{
			if(serverModel.isUseJmx())
			{
				throw e;
			}
			else
			{
				port = TomcatServerModel.DEFAULT_JNDI_PORT;
			}
		}
		return port;
	}
}