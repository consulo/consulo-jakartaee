package org.mustbe.consulo.java.web.jsp.psi.impl;

import org.jetbrains.annotations.NotNull;
import org.mustbe.consulo.java.web.jsp.psi.JspElement;
import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElementVisitor;

/**
 * @author VISTALL
 * @since 16.11.13.
 */
public abstract class JspElementImpl extends ASTWrapperPsiElement implements JspElement
{
	public JspElementImpl(@NotNull ASTNode node)
	{
		super(node);
	}

	@Override
	public void accept(@NotNull PsiElementVisitor visitor)
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

	public abstract void accept(@NotNull JspElementVisitor visitor);
}
