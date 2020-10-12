package consulo.javaee;

import consulo.annotation.DeprecationInfo;
import consulo.javaee.api.icon.JavaEEApiIconGroup;
import consulo.ui.image.Image;

@Deprecated
@DeprecationInfo("Use JavaEEApiIconGroup")
public interface JavaEEIcons
{
	Image BuildOnFrameDeactivation = JavaEEApiIconGroup.buildOnFrameDeactivation();
	Image Jsp = JavaEEApiIconGroup.jsp();
	Image Jspx = JavaEEApiIconGroup.jspx();
	Image WarArtifact = JavaEEApiIconGroup.warArtifact();
	Image Web_xml = JavaEEApiIconGroup.web_xml();
}