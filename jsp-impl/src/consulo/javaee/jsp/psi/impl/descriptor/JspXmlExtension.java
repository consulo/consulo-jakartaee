package consulo.javaee.jsp.psi.impl.descriptor;

import java.util.Collections;
import java.util.List;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import com.intellij.psi.PsiFile;
import com.intellij.psi.impl.source.xml.SchemaPrefix;
import com.intellij.psi.jsp.JspFile;
import com.intellij.psi.xml.XmlFile;
import com.intellij.psi.xml.XmlTag;
import com.intellij.xml.XmlElementDescriptor;
import com.intellij.xml.XmlExtension;

/**
 * @author VISTALL
 * @since 22-Jun-17
 */
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

	@NotNull
	@Override
	public List<TagInfo> getAvailableTagNames(@NotNull XmlFile xmlFile, @NotNull XmlTag xmlTag)
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
