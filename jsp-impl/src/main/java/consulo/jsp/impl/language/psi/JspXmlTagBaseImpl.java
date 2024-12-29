package consulo.jsp.impl.language.psi;

import consulo.jsp.language.psi.xml.JspXmlTagBase;
import consulo.language.ast.IElementType;
import consulo.language.psi.PsiElement;
import consulo.language.psi.PsiElementVisitor;
import consulo.xml.psi.impl.source.xml.XmlTagImpl;
import consulo.xml.psi.xml.XmlTag;

import jakarta.annotation.Nonnull;

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
