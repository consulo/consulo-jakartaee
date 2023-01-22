package consulo.jsp.impl.language.psi.descriptor;

import com.intellij.xml.XmlAttributeDescriptor;
import com.intellij.xml.XmlElementDescriptor;
import com.intellij.xml.XmlNSDescriptor;
import com.intellij.xml.impl.dtd.BaseXmlElementDescriptorImpl;
import consulo.language.psi.PsiElement;
import consulo.language.psi.PsiModificationTracker;
import consulo.xml.psi.xml.XmlTag;

import java.util.HashMap;
import java.util.Map;

/**
 * @author VISTALL
 * @since 22-Jun-17
 */
public class JspRootElementDescriptor extends BaseXmlElementDescriptorImpl
{
	@Override
	protected XmlElementDescriptor[] doCollectXmlDescriptors(XmlTag context)
	{
		return JspSchemeLoader.getDirectives().values().stream().map(it -> new JspChildXmlElementDescriptor(it, context)).toArray(XmlElementDescriptor[]::new);
	}

	@Override
	protected XmlAttributeDescriptor[] collectAttributeDescriptors(XmlTag context)
	{
		return XmlAttributeDescriptor.EMPTY;
	}

	@Override
	protected HashMap<String, XmlAttributeDescriptor> collectAttributeDescriptorsMap(XmlTag context)
	{
		return new HashMap<>();
	}

	@Override
	protected HashMap<String, XmlElementDescriptor> collectElementDescriptorsMap(XmlTag element)
	{
		HashMap<String, XmlElementDescriptor> map = new HashMap<>();
		for(Map.Entry<String, JspSchemeLoader.Directive> entry : JspSchemeLoader.getDirectives().entrySet())
		{
			map.put(entry.getKey(), new JspChildXmlElementDescriptor(entry.getValue(), element));
		}
		return map;
	}

	@Override
	public String getQualifiedName()
	{
		return null;
	}

	@Override
	public String getDefaultName()
	{
		return null;
	}

	@Override
	public XmlNSDescriptor getNSDescriptor()
	{
		return null;
	}

	@Override
	public int getContentType()
	{
		return 0;
	}

	@Override
	public PsiElement getDeclaration()
	{
		return null;
	}

	@Override
	public String getName(PsiElement context)
	{
		return null;
	}

	@Override
	public String getName()
	{
		return null;
	}

	@Override
	public void init(PsiElement element)
	{
	}

	@Override
	public Object[] getDependences()
	{
		return new Object[] {PsiModificationTracker.MODIFICATION_COUNT};
	}
}
