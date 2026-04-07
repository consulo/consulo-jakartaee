package consulo.jsp.impl.language.psi.descriptor;

import consulo.annotation.component.ExtensionImpl;
import consulo.jsp.language.psi.xml.JspXmlRootTag;
import consulo.xml.descriptor.XmlElementDescriptor;
import consulo.xml.language.psi.XmlTag;
import consulo.xml.psi.impl.source.xml.XmlElementDescriptorProvider;

import jakarta.annotation.Nullable;

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
