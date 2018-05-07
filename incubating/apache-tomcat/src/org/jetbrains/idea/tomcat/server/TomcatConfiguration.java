package org.jetbrains.idea.tomcat.server;

import org.jetbrains.annotations.NonNls;
import javax.annotation.Nonnull;
import com.intellij.execution.configurations.ConfigurationTypeUtil;
import consulo.apache.tomcat.bundle.TomcatBundleType;

/**
 * Created by IntelliJ IDEA.
 * User: michael.golubev
 */
public class TomcatConfiguration extends TomcatConfigurationBase
{
	@NonNls
	private static final String ID = "#com.intellij.j2ee.web.tomcat.TomcatRunConfigurationFactory";

	public static TomcatConfiguration getInstance()
	{
		return ConfigurationTypeUtil.findConfigurationType(TomcatConfiguration.class);
	}

	public TomcatConfiguration()
	{
		super(TomcatBundleType.getInstance());
	}

	@Nonnull
	@Override
	public String getId()
	{
		return ID;
	}
}