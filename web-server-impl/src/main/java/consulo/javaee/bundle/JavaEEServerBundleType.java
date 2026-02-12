package consulo.javaee.bundle;

import consulo.content.bundle.SdkType;
import consulo.execution.configuration.ui.SettingsEditor;
import consulo.jakartaee.webServer.impl.deployment.DeploymentModel;
import consulo.jakartaee.webServer.impl.oss.server.JavaeeIntegration;
import consulo.jakartaee.webServer.impl.run.configuration.CommonModel;
import consulo.localize.LocalizeValue;
import consulo.remoteServer.configuration.deployment.DeploymentSource;
import consulo.ui.image.Image;
import jakarta.annotation.Nonnull;

/**
 * @author VISTALL
 * @since 2017-07-09
 */
public abstract class JavaEEServerBundleType extends SdkType {
    public JavaEEServerBundleType(@Nonnull String id, @Nonnull LocalizeValue displayName, @Nonnull Image icon) {
        super(id, displayName, icon);
    }

    public DeploymentModel createNewDeploymentModel(CommonModel commonModel, DeploymentSource source) {
        return null;
    }

    public SettingsEditor<DeploymentModel> createAdditionalDeploymentSettingsEditor(CommonModel commonModel, DeploymentSource source) {
        return null;
    }

    public boolean isJreCustomizable() {
        return false;
    }

    @Deprecated
    public abstract JavaeeIntegration getIntegration();
}
