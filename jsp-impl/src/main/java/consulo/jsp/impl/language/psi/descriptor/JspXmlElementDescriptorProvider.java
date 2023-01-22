package consulo.jsp.impl.language.psi.descriptor;

import com.intellij.xml.XmlElementDescriptor;
import consulo.annotation.component.ExtensionImpl;
import consulo.jsp.language.psi.xml.JspXmlRootTag;
import consulo.xml.psi.impl.source.xml.XmlElementDescriptorProvider;
import consulo.xml.psi.xml.XmlTag;

import javax.annotation.Nullable;

/**
 * @author VISTALL
 * @since 22-Jun-17
 */
@ExtensionImpl
public class JspXmlElementDescriptorProvider implements XmlElementDescriptorProvider
{
	@Nullable
	@Override
	public XmlElementDescriptor getDescriptor(XmlTag tag)
	{
		if(tag instanceof JspXmlRootTag)
		{
			return new JspRootElementDescriptor();
		}
		return null;
	}
}
