package consulo.javaee;

import consulo.annotation.DeprecationInfo;
import consulo.javaee.icon.JavaEEApiIconGroup;
import consulo.ui.image.Image;

@Deprecated
@DeprecationInfo("Use JavaEEApiIconGroup")
public interface JavaEEIcons
{
	Image Jsp = JavaEEApiIconGroup.jsp();
	Image Jspx = JavaEEApiIconGroup.jspx();
	Image WarArtifact = JavaEEApiIconGroup.web_artifact();
	Image Web_xml = JavaEEApiIconGroup.web_xml();
}