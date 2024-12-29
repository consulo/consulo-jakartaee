package consulo.jakartaee.webServer.impl.deployment;

import consulo.compiler.artifact.Artifact;
import consulo.remoteServer.configuration.deployment.DeploymentSource;

import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import java.util.List;

/**
 * @author nik
 */
public interface DeploymentSettings {
  @Nonnull
  List<DeploymentModel> getDeploymentModels();

  void removeModel(@Nonnull DeploymentModel model);


  @Nullable
  DeploymentModel getModel(@Nonnull Artifact artifact);

  @Nonnull
  List<Artifact> getDeployedArtifacts();

  @Nonnull
  List<Artifact> getArtifacts2Build();


  @Nonnull
  DeploymentModel getOrCreateModel(@Nonnull Artifact artifact);

  @Nonnull
  DeploymentModel getOrCreateModel(@Nonnull DeploymentSource source);
}
