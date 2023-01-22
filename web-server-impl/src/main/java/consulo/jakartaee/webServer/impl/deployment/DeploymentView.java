package consulo.jakartaee.webServer.impl.deployment;

import java.util.List;

/**
 * @author nik
 */
public interface DeploymentView {

  List<DeploymentModel> getSelectedModels();
}
