package consulo.javaee.jsp.psi.impl;

import java.util.Map;

import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiReference;
import com.intellij.psi.meta.PsiMetaData;
import com.intellij.psi.search.PsiElementProcessor;
import com.intellij.psi.xml.XmlAttribute;
import com.intellij.psi.xml.XmlTag;
import com.intellij.psi.xml.XmlTagChild;
import com.intellij.psi.xml.XmlTagValue;
import com.intellij.psi.xml.XmlTokenType;
import com.intellij.util.IncorrectOperationException;
import com.intellij.xml.XmlElementDescriptor;
import com.intellij.xml.XmlExtension;
import com.intellij.xml.XmlNSDescriptor;

/**
 * @author VISTALL
 * @since 15.11.13.
 */
public class JspDirectiveImpl extends JspElementImpl implements com.intellij.psi.impl.source.jsp.jspXml.JspDirective
{
	public JspDirectiveImpl(@NotNull ASTNode node)
	{
		super(node);
	}

	@Override
	public void accept(@NotNull JspElementVisitor visitor)
	{
		visitor.visitDirective(this);
	}

	@Nullable
	public PsiElement getNameIdentifier()
	{
		return findChildByType(XmlTokenType.XML_TAG_NAME);
	}

	@Override
	public String getName()
	{
		PsiElement nameIdentifier = getNameIdentifier();
		return nameIdentifier == null ? null : nameIdentifier.getText();
	}

	@NotNull
	@Override
	public String getNamespace()
	{
		return "";
	}

	@NotNull
	@Override
	public String getLocalName()
	{
		return null;
	}

	@Nullable
	@Override
	public XmlElementDescriptor getDescriptor()
	{
		return null;
	}

	@NotNull
	@Override
	public XmlAttribute[] getAttributes()
	{
		return new XmlAttribute[0];
	}

	@Nullable
	@Override
	public XmlAttribute getAttribute(@NonNls String s, @NonNls String s1)
	{
		return null;
	}

	@Nullable
	@Override
	public XmlAttribute getAttribute(@NonNls String s)
	{
		return null;
	}

	@Nullable
	@Override
	public String getAttributeValue(@NonNls String s, @NonNls String s1)
	{
		return null;
	}

	@Nullable
	@Override
	public String getAttributeValue(@NonNls String s)
	{
		return null;
	}

	@Override
	public XmlAttribute setAttribute(@NonNls String s, @NonNls String s1, @NonNls String s2) throws IncorrectOperationException
	{
		return null;
	}

	@Override
	public XmlAttribute setAttribute(@NonNls String s, @NonNls String s1) throws IncorrectOperationException
	{
		return null;
	}

	@Override
	public XmlTag createChildTag(@NonNls String s, @NonNls String s1, @Nullable @NonNls String s2, boolean b)
	{
		return null;
	}

	@Override
	public XmlTag addSubTag(XmlTag xmlTag, boolean b)
	{
		return null;
	}

	@NotNull
	@Override
	public XmlTag[] getSubTags()
	{
		return new XmlTag[0];
	}

	@NotNull
	@Override
	public XmlTag[] findSubTags(@NonNls String s)
	{
		return new XmlTag[0];
	}

	@NotNull
	@Override
	public XmlTag[] findSubTags(@NonNls String s, @Nullable String s1)
	{
		return new XmlTag[0];
	}

	@Nullable
	@Override
	public XmlTag findFirstSubTag(@NonNls String s)
	{
		return null;
	}

	@NotNull
	@Override
	public String getNamespacePrefix()
	{
		return null;
	}

	@NotNull
	@Override
	public String getNamespaceByPrefix(@NonNls String s)
	{
		return null;
	}

	@Nullable
	@Override
	public String getPrefixByNamespace(@NonNls String s)
	{
		return null;
	}

	@Override
	public String[] knownNamespaces()
	{
		return new String[0];
	}

	@Override
	public boolean hasNamespaceDeclarations()
	{
		return false;
	}

	@NotNull
	@Override
	public Map<String, String> getLocalNamespaceDeclarations()
	{
		return null;
	}

	@NotNull
	@Override
	public XmlTagValue getValue()
	{
		return null;
	}

	@Nullable
	@Override
	public XmlNSDescriptor getNSDescriptor(@NonNls String s, boolean b)
	{
		return null;
	}

	@Override
	public boolean isEmpty()
	{
		return false;
	}

	@Override
	public void collapseIfEmpty()
	{

	}

	@Nullable
	@Override
	public String getSubTagText(@NonNls String s)
	{
		return null;
	}

	@Override
	public PsiElement setName(@NonNls @NotNull String s) throws IncorrectOperationException
	{
		return null;
	}

	@Nullable
	@Override
	public PsiMetaData getMetaData()
	{
		return null;
	}

	@Override
	public XmlTag getParentTag()
	{
		return null;
	}

	@Nullable
	@Override
	public XmlTagChild getNextSiblingInTag()
	{
		return null;
	}

	@Nullable
	@Override
	public XmlTagChild getPrevSiblingInTag()
	{
		return null;
	}

	@Override
	public boolean processElements(PsiElementProcessor psiElementProcessor, PsiElement psiElement)
	{
		return false;
	}

	@Override
	public PsiReference getReference()
	{
		XmlExtension extension = XmlExtension.getExtensionByElement(this);
		return extension == null ? null : extension.createTagNameReference(getNameIdentifier().getNode(), true);
	}
}
