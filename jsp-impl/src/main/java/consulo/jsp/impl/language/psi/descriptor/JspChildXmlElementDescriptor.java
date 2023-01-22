package consulo.jsp.impl.language.psi.descriptor;

import com.intellij.xml.XmlAttributeDescriptor;
import com.intellij.xml.XmlElementDescriptor;
import com.intellij.xml.XmlNSDescriptor;
import com.intellij.xml.impl.dtd.BaseXmlElementDescriptorImpl;
import consulo.jsp.language.JspLanguage;
import consulo.language.impl.psi.LightElement;
import consulo.language.psi.PsiElement;
import consulo.language.psi.PsiManager;
import consulo.project.Project;
import consulo.xml.psi.xml.XmlTag;

import java.util.HashMap;

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
