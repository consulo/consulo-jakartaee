package consulo.jakartaee.webServer.impl.oss.util;

import javax.annotation.Nullable;

/**
 * @author VISTALL
 * @since 03-Jul-17
 */
public class Version
{
	private final consulo.util.lang.Version myInner;

	public Version(@Nullable String version)
	{
		myInner = version == null ? new consulo.util.lang.Version(0, 0, 0) : consulo.util.lang.Version.parseVersion(version);
	}

	public int compare(int major, int minor, int micro)
	{
		return myInner.compareTo(major, minor, micro);
	}

	public int getMajor()
	{
		return myInner.major;
	}
}
