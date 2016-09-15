package consulo.javaee.jsp.ide.highlight;

import org.jetbrains.annotations.NotNull;
import com.intellij.codeInsight.daemon.impl.HighlightInfo;
import com.intellij.codeInsight.daemon.impl.HighlightInfoType;
import com.intellij.codeInsight.daemon.impl.HighlightVisitor;
import com.intellij.codeInsight.daemon.impl.analysis.HighlightInfoHolder;
import com.intellij.openapi.editor.DefaultLanguageHighlighterColors;
import com.intellij.openapi.editor.colors.EditorColors;
import com.intellij.openapi.editor.colors.TextAttributesKey;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.psi.PsiNameIdentifierOwner;
import consulo.javaee.jsp.psi.JspAttribute;
import consulo.javaee.jsp.psi.JspDirective;
import consulo.javaee.jsp.psi.JspElement;
import consulo.javaee.jsp.psi.JspExpression;
import consulo.javaee.jsp.psi.JspFile;
import consulo.javaee.jsp.psi.JspFragment;
import consulo.javaee.jsp.psi.JspLineFragment;
import consulo.javaee.jsp.psi.impl.JspElementVisitor;

/**
 * @author VISTALL
 * @since 16.11.13.
 */
public class JspHighlightVisitor extends JspElementVisitor implements HighlightVisitor
{
	private HighlightInfoHolder myHighlightInfoHolder;

	@Override
	public boolean suitableForFile(@NotNull PsiFile psiFile)
	{
		return psiFile instanceof JspFile;
	}

	@Override
	public void visit(@NotNull PsiElement element)
	{
		element.acceptChildren(this);
	}

	@Override
	public boolean analyze(@NotNull PsiFile psiFile, boolean b, @NotNull HighlightInfoHolder highlightInfoHolder, @NotNull Runnable runnable)
	{
		myHighlightInfoHolder = highlightInfoHolder;
		runnable.run();
		return true;
	}

	@Override
	public void visitDirective(JspDirective directive)
	{
		super.visitDirective(directive);

		highlightAsInjected(directive);

		highlightName(directive, DefaultLanguageHighlighterColors.KEYWORD);
	}

	@Override
	public void visitAttribute(JspAttribute attribute)
	{
		highlightName(attribute, DefaultLanguageHighlighterColors.MARKUP_ATTRIBUTE);
	}

	@Override
	public void visitFragment(JspFragment fragment)
	{
		highlightAsInjected(fragment);
	}

	@Override
	public void visitLineFragment(JspLineFragment lineFragment)
	{
		highlightAsInjected(lineFragment);
	}

	@Override
	public void visitExpression(JspExpression expression)
	{
		highlightAsInjected(expression);
	}

	private void highlightAsInjected(JspElement element)
	{
		myHighlightInfoHolder.add(HighlightInfo.newHighlightInfo(HighlightInfoType.INFORMATION).range(element).textAttributes(EditorColors.INJECTED_LANGUAGE_FRAGMENT).create());
	}

	private void highlightName(PsiNameIdentifierOwner owner, TextAttributesKey key)
	{
		PsiElement nameIdentifier = owner.getNameIdentifier();
		if(nameIdentifier == null)
		{
			return;
		}
		myHighlightInfoHolder.add(HighlightInfo.newHighlightInfo(HighlightInfoType.INFORMATION).range(nameIdentifier).textAttributes(key).create());
	}

	@NotNull
	@Override
	public HighlightVisitor clone()
	{
		return new JspHighlightVisitor();
	}

	@Override
	public int order()
	{
		return 0;
	}
}
