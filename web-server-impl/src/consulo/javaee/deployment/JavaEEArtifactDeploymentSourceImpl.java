package consulo.javaee.deployment;

import org.jetbrains.annotations.NotNull;
import com.intellij.javaee.deployment.ArtifactDeploymentSource;
import com.intellij.packaging.artifacts.ArtifactPointer;
import com.intellij.remoteServer.impl.configuration.deploySource.impl.ArtifactDeploymentSourceImpl;

/**
 * @author VISTALL
 * @since 09-Jul-17
 */
public class JavaEEArtifactDeploymentSourceImpl extends ArtifactDeploymentSourceImpl implements ArtifactDeploymentSource
{
	public JavaEEArtifactDeploymentSourceImpl(@NotNull ArtifactPointer pointer)
	{
		super(pointer);
	}
}
