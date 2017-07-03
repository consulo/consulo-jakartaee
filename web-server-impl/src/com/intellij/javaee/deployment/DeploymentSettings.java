package com.intellij.javaee.deployment;

import com.intellij.packaging.artifacts.Artifact;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

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
  DeploymentModel getOrCreateModel(@NotNull DeploymentSource source);
}
