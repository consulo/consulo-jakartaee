package com.intellij.javaee.deployment;

import java.util.List;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.intellij.packaging.artifacts.Artifact;

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
  DeploymentModel getOrCreateModel(@Nonnull com.intellij.remoteServer.configuration.deployment.DeploymentSource source);
}
