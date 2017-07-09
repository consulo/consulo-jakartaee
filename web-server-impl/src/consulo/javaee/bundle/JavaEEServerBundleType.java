package consulo.javaee.bundle;

import org.jetbrains.annotations.NonNls;
import com.intellij.javaee.oss.server.JavaeeIntegration;
import com.intellij.openapi.projectRoots.SdkType;

/**
 * @author VISTALL
 * @since 09-Jul-17
 */
public abstract class JavaEEServerBundleType extends SdkType
{
	public JavaEEServerBundleType(@NonNls String name)
	{
		super(name);
	}

	@Deprecated
	public abstract JavaeeIntegration getIntegration();
}
