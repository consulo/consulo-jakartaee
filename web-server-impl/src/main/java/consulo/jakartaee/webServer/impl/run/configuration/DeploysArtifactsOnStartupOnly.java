package consulo.jakartaee.webServer.impl.run.configuration;

import consulo.compiler.artifact.Artifact;
import consulo.jakartaee.webServer.impl.appServerIntegrations.AppServerIntegration;

import java.util.List;

/**
 * Implement this interface in your {@link ServerModel}'s implementation if the application server doesn't support undeploy/deploy
 * actions for the running instance, and all artifacts are deployed when the server is started. In that case you need to return {@code null}
 * from {@link AppServerIntegration#getDeploymentProvider(boolean)} method.
 *
 * @author nik
 */
public interface DeploysArtifactsOnStartupOnly {

  boolean isResourcesReloadingSupported();

  List<Artifact> getArtifactsToDeploy();
}
