package consulo.apache.tomcat.bundle;

import javax.annotation.Nonnull;

/**
 * @author VISTALL
 * @since 09-Jul-17
 */
public class TomcatEEBundleType extends TomcatBundleType
{
	@Nonnull
	public static TomcatBundleType getInstance()
	{
		throw new IllegalArgumentException();

		//FIXME [VISTALL] unsupported return EP_NAME.findExtension(TomcatEEBundleType.class);
	}

	public TomcatEEBundleType()
	{
		super("APACHE_TOMCATEE_SDK");
	}
}
