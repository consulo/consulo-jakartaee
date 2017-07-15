package com.intellij.javaee.deployment;

import java.util.List;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import com.intellij.packaging.artifacts.Artifact;

/**
 * @author nik
 */
public interface DeploymentSettings {
  @NotNull
  List<DeploymentModel> getDeploymentModels();

  void removeModel(@NotNull DeploymentModel model);


  @Nullable
  DeploymentModel getModel(@NotNull Artifact artifact);

  @NotNull
  List<Artifact> getDeployedArtifacts();

  @NotNull
  List<Artifact> getArtifacts2Build();


  @NotNull
  DeploymentModel getOrCreateModel(@NotNull Artifact artifact);

  @NotNull
  DeploymentModel getOrCreateModel(@NotNull com.intellij.remoteServer.configuration.deployment.DeploymentSource source);
}
