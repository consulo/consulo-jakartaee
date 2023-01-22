package consulo.jakartaee.webServer.impl.context;

/**
 * Created by IntelliJ IDEA.
 * User: michael.golubev
 */
public interface DeploymentModelContext {

  boolean isDefaultContextRoot();

  String getContextRoot();
}
