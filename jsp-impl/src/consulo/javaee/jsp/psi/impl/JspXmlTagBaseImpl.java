package consulo.javaee.jsp.psi.impl;

import javax.annotation.Nonnull;

import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.impl.source.jsp.jspXml.JspXmlTagBase;
import com.intellij.psi.impl.source.xml.XmlTagImpl;
import com.intellij.psi.tree.IElementType;
import com.intellij.psi.xml.XmlTag;

/**
 * @author VISTALL
 * @since 24-May-17
 */
public abstract class JspXmlTagBaseImpl extends XmlTagImpl implements JspXmlTagBase
{
	protected JspXmlTagBaseImpl(IElementType type)
	{
		super(type);
	}

	@Override
	public XmlTag findParentTag()
	{
		PsiElement parent = getParent();
		if(parent instanceof XmlTag)
		{
			return (XmlTag) parent;
		}
		return null;
	}

	@Override
	public void accept(@Nonnull PsiElementVisitor visitor)
	{
		if(visitor instanceof JspElementVisitor)
		{
			accept((JspElementVisitor) visitor);
		}
		else
		{
			visitor.visitElement(this);
		}
	}

	public abstract void accept(@Nonnull JspElementVisitor visitor);
}
