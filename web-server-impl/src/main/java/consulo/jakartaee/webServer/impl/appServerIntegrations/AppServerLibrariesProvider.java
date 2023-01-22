package consulo.jakartaee.webServer.impl.appServerIntegrations;

/**
 * @author nik
 */
public abstract class AppServerLibrariesProvider {
  /**
   * This operation may be time consuming so it must not be called from the Event Dispatch Thread
   * @return jars providing framework implementation from the application server installation
   */
 /* @NotNull
  public abstract Map<FrameworkType, List<File>> getFrameworkLibraries();*/
}
