package consulo.javaee.jsp.psi.impl.descriptor;

import org.jetbrains.annotations.Nullable;
import com.intellij.psi.impl.source.jsp.jspXml.JspXmlRootTag;
import com.intellij.psi.impl.source.xml.XmlElementDescriptorProvider;
import com.intellij.psi.xml.XmlTag;
import com.intellij.xml.XmlElementDescriptor;

/**
 * @author VISTALL
 * @since 22-Jun-17
 */
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
