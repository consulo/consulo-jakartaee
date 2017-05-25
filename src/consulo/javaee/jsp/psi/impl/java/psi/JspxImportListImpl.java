package consulo.javaee.jsp.psi.impl.java.psi;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import com.intellij.lang.java.JavaLanguage;
import com.intellij.openapi.util.Pair;
import com.intellij.openapi.util.TextRange;
import com.intellij.openapi.util.text.StringUtil;
import com.intellij.psi.FileViewProvider;
import com.intellij.psi.JavaElementVisitor;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.PsiImportList;
import com.intellij.psi.PsiImportStatement;
import com.intellij.psi.PsiImportStatementBase;
import com.intellij.psi.PsiImportStaticStatement;
import com.intellij.psi.impl.light.LightElement;
import com.intellij.psi.impl.source.jsp.jspJava.JspxImportList;
import com.intellij.psi.jsp.JspDirectiveKind;
import com.intellij.psi.jsp.JspFile;
import com.intellij.psi.xml.XmlAttribute;
import com.intellij.psi.xml.XmlAttributeValue;
import com.intellij.psi.xml.XmlTag;
import com.intellij.util.containers.ContainerUtil;
import consulo.javaee.jsp.JspLanguage;
import consulo.javaee.jsp.psi.impl.JspJavaFileImpl;

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

			int startOffsetInParent = valueElement.getStartOffsetInParent();

			List<Pair<String, TextRange>> importList = parseImportList(valueElement.getValue());

			for(Pair<String, TextRange> pair : importList)
			{
				String first = pair.getFirst();
				TextRange second = pair.getSecond();

				list.add(new JspxImportStatementImpl(first, second.shiftRight(startOffsetInParent), myJspJavaFile));
			}

		}
		return ContainerUtil.toArray(list, PsiImportStatement.EMPTY_ARRAY);
	}

	@NotNull
	private static List<Pair<String, TextRange>> parseImportList(@NotNull String string)
	{
		StringTokenizer tokenizer = new StringTokenizer(string, ",");

		int offset = 0;

		List<Pair<String, TextRange>> list = new ArrayList<>();

		while(tokenizer.hasMoreTokens())
		{
			final String token = tokenizer.nextToken();

			int startOffset = offset > 0 ? offset + 1 : offset;
			int endOffset = offset + token.length();

			String trimLeading = StringUtil.trimLeading(token);

			startOffset += token.length() - trimLeading.length();

			String trimTrailing = StringUtil.trimTrailing(trimLeading);

			endOffset -= trimLeading.length() - trimTrailing.length();

			TextRange textRange = new TextRange(startOffset, endOffset);

			offset = offset + token.length() + 1;

			list.add(Pair.create(trimTrailing, textRange));
		}

		return list;
	}

	@NotNull
	@Override
	public PsiImportStaticStatement[] getImportStaticStatements()
	{
		return new PsiImportStaticStatement[0];
	}

	@NotNull
	@Override
	public PsiImportStatementBase[] getAllImportStatements()
	{
		return new PsiImportStatementBase[0];
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
