package org.jetbrains.idea.tomcat.server.tomee;

import org.jetbrains.idea.tomcat.server.TomcatConfigurationBase;
import consulo.apache.tomcat.bundle.TomcatEEBundleType;

/**
 * Created by IntelliJ IDEA.
 * User: michael.golubev
 */
public class TomeeConfiguration extends TomcatConfigurationBase
{
	public TomeeConfiguration()
	{
		super(TomcatEEBundleType.getInstance());
	}
}
