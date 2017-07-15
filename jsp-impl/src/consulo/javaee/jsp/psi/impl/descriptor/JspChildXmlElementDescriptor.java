package consulo.javaee.jsp.psi.impl.descriptor;

import java.util.HashMap;

import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiManager;
import com.intellij.psi.impl.light.LightElement;
import com.intellij.psi.xml.XmlTag;
import com.intellij.xml.XmlAttributeDescriptor;
import com.intellij.xml.XmlElementDescriptor;
import com.intellij.xml.XmlNSDescriptor;
import com.intellij.xml.impl.dtd.BaseXmlElementDescriptorImpl;
import consulo.javaee.jsp.JspLanguage;

/**
 * @author VISTALL
 * @since 22-Jun-17
 */
public class JspChildXmlElementDescriptor extends BaseXmlElementDescriptorImpl
{
	private JspSchemeLoader.Directive myDirective;
	private Project myProject;

	public JspChildXmlElementDescriptor(JspSchemeLoader.Directive directive, XmlTag element)
	{
		myDirective = directive;
		myProject = element.getProject();
	}

	@Override
	protected XmlElementDescriptor[] doCollectXmlDescriptors(XmlTag context)
	{
		return XmlElementDescriptor.EMPTY_ARRAY;
	}

	@Override
	protected XmlAttributeDescriptor[] collectAttributeDescriptors(XmlTag context)
	{
		return collectAttributeDescriptorsMap(null).values().stream().toArray(XmlAttributeDescriptor[]::new);
	}

	@Override
	protected HashMap<String, XmlAttributeDescriptor> collectAttributeDescriptorsMap(XmlTag context)
	{
		HashMap<String, XmlAttributeDescriptor> map = new HashMap<>();
		for(JspSchemeLoader.Attribute attribute : myDirective.attributes)
		{
			map.put(attribute.name, new JspChildXmlAttributeDescriptor(attribute, myProject));
		}
		return map;
	}

	@Override
	protected HashMap<String, XmlElementDescriptor> collectElementDescriptorsMap(XmlTag element)
	{
		return new HashMap<>();
	}

	@Override
	public String getQualifiedName()
	{
		return null;
	}

	@Override
	public String getDefaultName()
	{
		return myDirective.name;
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
		return new LightElement(PsiManager.getInstance(myProject), JspLanguage.INSTANCE)
		{
			@Override
			public String toString()
			{
				return myDirective.name;
			}
		};
	}

	@Override
	public String getName(PsiElement context)
	{
		return null;
	}

	@Override
	public String getName()
	{
		return getDefaultName();
	}

	@Override
	public void init(PsiElement element)
	{

	}

	@Override
	public Object[] getDependences()
	{
		return new Object[0];
	}
}
