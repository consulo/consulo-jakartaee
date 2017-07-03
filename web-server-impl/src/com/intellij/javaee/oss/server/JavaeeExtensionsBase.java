package com.intellij.javaee.oss.server;

import com.intellij.packaging.artifacts.ArtifactType;

/**
 * @author VISTALL
 * @since 03-Jul-17
 */
public class JavaeeExtensionsBase
{
	private String myName;

	public JavaeeExtensionsBase(String name)
	{
		myName = name;
	}

	public boolean isValidExtension(ArtifactType instance, String extension)
	{
		return false;
	}
}
