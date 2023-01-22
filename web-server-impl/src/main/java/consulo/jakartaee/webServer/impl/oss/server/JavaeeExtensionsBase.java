package consulo.jakartaee.webServer.impl.oss.server;

import consulo.compiler.artifact.ArtifactType;

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
