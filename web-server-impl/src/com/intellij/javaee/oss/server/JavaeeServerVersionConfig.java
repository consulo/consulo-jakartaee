package com.intellij.javaee.oss.server;

/**
 * @author VISTALL
 * @since 03-Jul-17
 */
public abstract class JavaeeServerVersionConfig
{
	protected abstract static class Factory<T extends JavaeePersistentData>
	{
		public abstract JavaeeServerVersionConfig createConfig(T data);
	}

	protected abstract JavaeeServerVersionDescriptor getVersionDescriptor(String home) throws Exception;

	public static <T extends JavaeePersistentData> String get(Factory<T> persistentData, T value) throws Exception
	{
		return null;
	}
}
