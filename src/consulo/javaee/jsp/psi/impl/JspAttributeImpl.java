package consulo.javaee.jsp.psi.impl;

import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import com.intellij.lang.ASTNode;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.PsiElement;
import com.intellij.psi.search.PsiElementProcessor;
import com.intellij.psi.xml.XmlAttribute;
import com.intellij.psi.xml.XmlAttributeValue;
import com.intellij.psi.xml.XmlElement;
import com.intellij.psi.xml.XmlTag;
import com.intellij.psi.xml.XmlTokenType;
import com.intellij.util.IncorrectOperationException;
import com.intellij.xml.XmlAttributeDescriptor;

/**
 * @author VISTALL
 * @since 16.11.13.
 */
public class JspAttributeImpl extends JspElementImpl implements XmlAttribute
{
	public JspAttributeImpl(@NotNull ASTNode node)
	{
		super(node);
	}

	@Override
	public XmlTag getParent()
	{
		return (XmlTag) super.getParent();
	}

	@Override
	public void accept(@NotNull JspElementVisitor visitor)
	{
		visitor.visitAttribute(this);
	}

	@Nullable
	public PsiElement getNameIdentifier()
	{
		return findChildByType(XmlTokenType.XML_NAME);
	}

	@Override
	public String getName()
	{
		PsiElement nameIdentifier = getNameIdentifier();
		return nameIdentifier == null ? null : nameIdentifier.getText();
	}

	@NotNull
	@Override
	public String getLocalName()
	{
		return null;
	}

	@Override
	public XmlElement getNameElement()
	{
		return (XmlElement) getNameIdentifier();
	}

	@NotNull
	@Override
	public String getNamespace()
	{
		return null;
	}

	@NotNull
	@Override
	public String getNamespacePrefix()
	{
		return null;
	}

	@Nullable
	@Override
	public String getValue()
	{
		return null;
	}

	@Nullable
	@Override
	public String getDisplayValue()
	{
		return null;
	}

	@Override
	public int physicalToDisplay(int i)
	{
		return 0;
	}

	@Override
	public int displayToPhysical(int i)
	{
		return 0;
	}

	@Override
	public TextRange getValueTextRange()
	{
		return null;
	}

	@Override
	public boolean isNamespaceDeclaration()
	{
		return false;
	}

	@Nullable
	@Override
	public XmlAttributeDescriptor getDescriptor()
	{
		return null;
	}

	@Nullable
	@Override
	public XmlAttributeValue getValueElement()
	{
		return null;
	}

	@Override
	public void setValue(String s) throws IncorrectOperationException
	{

	}

	@Override
	public PsiElement setName(@NonNls @NotNull String s) throws IncorrectOperationException
	{
		return null;
	}

	@Override
	public boolean processElements(PsiElementProcessor psiElementProcessor, PsiElement psiElement)
	{
		return false;
	}
}
