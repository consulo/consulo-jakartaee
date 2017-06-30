package consulo.javaee.jsp.psi.impl.java.psi;

import java.util.ArrayList;
import java.util.List;

import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import com.intellij.lang.java.JavaLanguage;
import com.intellij.openapi.util.Pair;
import com.intellij.openapi.util.TextRange;
import com.intellij.openapi.util.text.StringUtil;
import com.intellij.psi.*;
import com.intellij.psi.impl.light.LightElement;
import com.intellij.psi.impl.source.jsp.jspJava.JspxImportList;
import com.intellij.psi.impl.source.jsp.jspXml.JspDirective;
import com.intellij.psi.jsp.JspDirectiveKind;
import com.intellij.psi.jsp.JspFile;
import com.intellij.psi.util.CachedValueProvider;
import com.intellij.psi.util.PsiModificationTracker;
import com.intellij.psi.xml.XmlAttribute;
import com.intellij.psi.xml.XmlAttributeValue;
import com.intellij.psi.xml.XmlTag;
import com.intellij.util.CachedValuesManagerImpl;
import com.intellij.util.IncorrectOperationException;
import com.intellij.util.containers.ContainerUtil;
import consulo.annotations.RequiredReadAction;
import consulo.javaee.jsp.JspLanguage;

/**
 * @author VISTALL
 * @since 24-May-17
 */
public class JspxImportListImpl extends LightElement implements JspxImportList
{
	private JspJavaFileImpl myJspJavaFile;

	public JspxImportListImpl(JspJavaFileImpl jspJavaFile)
	{
		super(jspJavaFile.getManager(), JavaLanguage.INSTANCE);
		myJspJavaFile = jspJavaFile;
	}

	@Override
	@RequiredReadAction
	public PsiElement add(@NotNull PsiElement element) throws IncorrectOperationException
	{
		if(element instanceof PsiImportStatement)
		{
			JspFile jspFile = (JspFile) myJspJavaFile.getViewProvider().getPsi(JspLanguage.INSTANCE);

			String text = "<%@ page import=\"" + ((PsiImportStatement) element).getQualifiedName() + "\"%>";

			JspFile dummyJsp = (JspFile) PsiFileFactory.getInstance(element.getProject()).createFileFromText("dummy.jsp", JspLanguage.INSTANCE, text);

			JspDirective dummyJspDirective = (JspDirective) dummyJsp.getDocument().getRootTag().getSubTags()[0];

			XmlTag[] directiveTags = jspFile.getDirectiveTags(JspDirectiveKind.PAGE, false);
			if(directiveTags.length == 0)
			{
				XmlTag rootTag = jspFile.getRootTag();
				PsiElement result = rootTag.addBefore(dummyJspDirective, rootTag.getFirstChild());

				result.getNode().addLeaf(TokenType.WHITE_SPACE, "\n", null);
				return this;
			}
			else
			{
				XmlTag lastTag = directiveTags[directiveTags.length - 1];
				XmlTag parentTag = lastTag.getParentTag();

				PsiElement result = parentTag.addAfter(dummyJspDirective, lastTag);

				parentTag.getNode().addLeaf(TokenType.WHITE_SPACE, "\n", result.getNode());
				return this;
			}
		}

		throw new UnsupportedOperationException(element.getClass().getName());
	}

	@Override
	public void accept(@NotNull PsiElementVisitor visitor)
	{
		if(visitor instanceof JavaElementVisitor)
		{
			((JavaElementVisitor) visitor).visitImportList(this);
		}
		else
		{
			super.accept(visitor);
		}
	}

	@RequiredReadAction
	@NotNull
	@Override
	public PsiElement[] getChildren()
	{
		return getImportStatements();
	}

	@NotNull
	@Override
	public PsiImportStatement[] getImportStatements()
	{
		return CachedValuesManagerImpl.getCachedValue(myJspJavaFile, () -> CachedValueProvider.Result.create(calcImportStatements(), PsiModificationTracker.MODIFICATION_COUNT));
	}

	@NotNull
	@RequiredReadAction
	private PsiImportStatement[] calcImportStatements()
	{
		FileViewProvider viewProvider = myJspJavaFile.getContainingFile().getViewProvider();

		JspFile psi = (JspFile) viewProvider.getPsi(JspLanguage.INSTANCE);

		XmlTag[] directiveTags = psi.getDirectiveTags(JspDirectiveKind.PAGE, false);

		List<PsiImportStatement> list = new ArrayList<>();

		for(XmlTag directiveTag : directiveTags)
		{
			XmlAttribute xmlAttribute = directiveTag.getAttribute("import");
			if(xmlAttribute == null)
			{
				continue;
			}

			XmlAttributeValue valueElement = xmlAttribute.getValueElement();
			if(valueElement == null)
			{
				continue;
			}

			int startOffset = valueElement.getTextRange().getStartOffset();

			List<Pair<String, TextRange>> importList = parseImportList2(valueElement.getValue());

			for(Pair<String, TextRange> pair : importList)
			{
				String first = pair.getFirst();
				TextRange second = pair.getSecond();

				boolean error = StringUtil.isEmptyOrSpaces(first);

				// skip start delimiter
				TextRange textRange = new TextRange(second.getStartOffset() + startOffset + 1, second.getEndOffset() + startOffset + 1) ;

				list.add(new JspxImportStatementImpl(first.trim(), textRange, myJspJavaFile, error));
			}

		}
		return ContainerUtil.toArray(list, PsiImportStatement.EMPTY_ARRAY);
	}

	private static List<Pair<String, TextRange>> parseImportList2(@NotNull String string)
	{
		List<Pair<String, TextRange>> list = new ArrayList<>();

		int startOffset = 0;
		while(true)
		{
			int i =  string.indexOf(',', startOffset);
			if(i == -1)
			{
				break;
			}

			String text = string.substring(startOffset, i);

			list.add(Pair.create(text, new TextRange(startOffset, i + 1)));

			// +1 comma
			startOffset = i + 2;
		}

		if(startOffset != string.length())
		{
			list.add(Pair.create(string.substring(startOffset, string.length()), new TextRange(startOffset, string.length())));
		}
		return list;
	}

	public static void main(String[] args)
	{
		List<Pair<String, TextRange>> list = parseImportList2("test, java.test.dsadas.*, dsa dsadas, ,, ,");
		System.out.println();
	}

	@NotNull
	@Override
	public PsiImportStaticStatement[] getImportStaticStatements()
	{
		return PsiImportStaticStatement.EMPTY_ARRAY;
	}

	@NotNull
	@Override
	public PsiImportStatementBase[] getAllImportStatements()
	{
		return getImportStatements();
	}

	@Nullable
	@Override
	public PsiImportStatement findSingleClassImportStatement(String qName)
	{
		return null;
	}

	@Nullable
	@Override
	public PsiImportStatement findOnDemandImportStatement(@NonNls String packageName)
	{
		return null;
	}

	@Nullable
	@Override
	public PsiImportStatementBase findSingleImportStatement(String name)
	{
		return null;
	}

	@Override
	public boolean isReplaceEquivalent(PsiImportList otherList)
	{
		return false;
	}

	@Override
	public String toString()
	{
		return "JspxImportListImpl";
	}
}
