package consulo.jsp.impl.language.psi.descriptor;

import com.intellij.xml.XmlElementDescriptor;
import com.intellij.xml.XmlExtension;
import consulo.annotation.component.ExtensionImpl;
import consulo.jsp.language.psi.JspFile;
import consulo.language.psi.PsiFile;
import consulo.xml.psi.impl.source.xml.SchemaPrefix;
import consulo.xml.psi.xml.XmlFile;
import consulo.xml.psi.xml.XmlTag;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Collections;
import java.util.List;

/**
 * @author VISTALL
 * @since 22-Jun-17
 */
@ExtensionImpl
public class JspXmlExtension extends XmlExtension
{
	@Override
	public boolean isAvailable(PsiFile psiFile)
	{
		return psiFile instanceof JspFile;
	}

	@Nullable
	@Override
	public XmlElementDescriptor getElementDescriptor(XmlTag tag, XmlTag contextTag, XmlElementDescriptor parentDescriptor)
	{
		return super.getElementDescriptor(tag, contextTag, parentDescriptor);
	}

	@Nonnull
	@Override
	public List<TagInfo> getAvailableTagNames(@Nonnull XmlFile xmlFile, @Nonnull XmlTag xmlTag)
	{
		return Collections.emptyList();
	}

	@Nullable
	@Override
	public SchemaPrefix getPrefixDeclaration(XmlTag xmlTag, String s)
	{
		return null;
	}
}
