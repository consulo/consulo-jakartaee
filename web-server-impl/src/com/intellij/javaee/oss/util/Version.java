package com.intellij.javaee.oss.util;

/**
 * @author VISTALL
 * @since 03-Jul-17
 */
public class Version
{
	private final com.intellij.openapi.util.Version myInner;

	public Version(String version)
	{
		myInner = com.intellij.openapi.util.Version.parseVersion(version);
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
