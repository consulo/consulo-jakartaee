package org.jetbrains.idea.tomcat;

import java.io.File;

import javax.annotation.Nonnull;

import com.intellij.openapi.projectRoots.Sdk;
import com.intellij.openapi.util.io.FileUtil;
import com.intellij.openapi.util.text.StringUtil;

/**
 * Created by IntelliJ IDEA.
 * User: michael.golubev
 */
public class TomcatPersistentDataWrapper
{
	private final Sdk mySdk;

	public TomcatPersistentDataWrapper(@Nonnull Sdk sdk)
	{
		mySdk = sdk;
	}

	public String getHomeDirectory()
	{
		return StringUtil.trimEnd(FileUtil.toSystemDependentName(mySdk.getHomePath()), File.separator);
	}

	public String getSourceBaseDirectoryPath()
	{
		String baseDir = null; //myTomcatData.BASE;
		if(StringUtil.isNotEmpty(baseDir))
		{
			return FileUtil.toSystemDependentName(baseDir);
		}
		return getHomeDirectory();
	}

	public int getSourceLocalPort()
	{
		return new TomcatServerXmlWrapper(getSourceBaseDirectoryPath()).getHttpPort();
	}

	public boolean hasSourceLocalPort()
	{
		return new TomcatServerXmlWrapper(getSourceBaseDirectoryPath()).hasSourceLocalPort();
	}
}
