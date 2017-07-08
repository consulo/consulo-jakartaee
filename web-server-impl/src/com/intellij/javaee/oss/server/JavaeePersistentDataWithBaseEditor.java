package com.intellij.javaee.oss.server;

import java.io.FileNotFoundException;

import javax.swing.JPanel;

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

	protected JPanel getCustomPanelPlaceHolder()
	{
		return new JPanel();
	}

	protected void onHomeChanged()
	{
	}

	protected void doValidateBaseDir(String baseDir) throws FileNotFoundException
	{
	}

	public boolean isValidHomeSelected()
	{
		return false;
	}

	public String getHome()
	{
		return null;
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
