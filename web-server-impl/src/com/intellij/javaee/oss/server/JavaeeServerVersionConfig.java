package com.intellij.javaee.oss.server;

/**
 * @author VISTALL
 * @since 03-Jul-17
 */
public abstract class JavaeeServerVersionConfig
{
	protected static class Factory<T>
	{
	}

	protected abstract JavaeeServerVersionDescriptor getVersionDescriptor(String home) throws Exception;

	public static <T> String get(Factory<T> persistentData, T value) throws Exception
	{
		return null;
	}
}
