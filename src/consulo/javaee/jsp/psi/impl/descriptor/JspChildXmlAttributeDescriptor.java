package consulo.javaee.jsp.psi.impl.descriptor;

import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiManager;
import com.intellij.psi.impl.light.LightElement;
import com.intellij.psi.util.PsiModificationTracker;
import com.intellij.util.ArrayUtil;
import com.intellij.xml.impl.BasicXmlAttributeDescriptor;
import consulo.javaee.jsp.JspLanguage;

/**
 * @author VISTALL
 * @since 22-Jun-17
 */
public class JspChildXmlAttributeDescriptor extends BasicXmlAttributeDescriptor
{
	private JspSchemeLoader.Attribute myAttribute;
	private Project myProject;

	public JspChildXmlAttributeDescriptor(JspSchemeLoader.Attribute attribute, Project project)
	{
		myAttribute = attribute;
		myProject = project;
	}

	@Override
	public boolean isRequired()
	{
		return false;
	}

	@Override
	public boolean hasIdType()
	{
		return false;
	}

	@Override
	public boolean hasIdRefType()
	{
		return false;
	}

	@Override
	public boolean isEnumerated()
	{
		return false;
	}

	@Override
	public PsiElement getDeclaration()
	{
		return new LightElement(PsiManager.getInstance(myProject), JspLanguage.INSTANCE)
		{
			@Override
			public String toString()
			{
				return myAttribute.name;
			}
		};
	}

	@Override
	public String getName()
	{
		return myAttribute.name;
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

	@Override
	public boolean isFixed()
	{
		return false;
	}

	@Override
	public String getDefaultValue()
	{
		return null;
	}

	@Override
	public String[] getEnumeratedValues()
	{
		return ArrayUtil.EMPTY_STRING_ARRAY;
	}
}
