package com.intellij.javaee.run.configuration;

import com.intellij.execution.configurations.RunConfiguration;

/**
 * @author VISTALL
 * @since 03-Jul-17
 */
public abstract class CommonStrategy implements CommonModel, Cloneable
{
	public static class SettingsBean
	{
		public String COMMON_VM_ARGUMENTS;
	}

	public SettingsBean getSettingsBean()
	{
		return new SettingsBean();
	}

	@Override
	public RunConfiguration clone()
	{
		try
		{
			return (RunConfiguration) super.clone();
		}
		catch(CloneNotSupportedException e)
		{
			throw new Error(e);
		}
	}
}
