package com.intellij.javaee.oss.server;

import org.jetbrains.annotations.NotNull;
import com.intellij.javaee.appServerIntegrations.AppServerIntegration;
import com.intellij.javaee.appServerIntegrations.ApplicationServerPersistentDataEditor;
import com.intellij.openapi.options.ConfigurationException;

/**
 * @author VISTALL
 * @since 07-Jul-17
 */
public class JavaeePersistentDataWithBaseEditor<T extends JavaeePersistentDataWithBase> extends ApplicationServerPersistentDataEditor<T>
{
	public JavaeePersistentDataWithBaseEditor(AppServerIntegration appServerIntegration)
	{
	}

	@Override
	protected void resetEditorFrom(T s)
	{

	}

	@Override
	protected void applyEditorTo(@NotNull T s) throws ConfigurationException
	{

	}
}
