package org.mustbe.consulo.java.web.jsp.psi.impl;

import org.mustbe.consulo.java.web.jsp.psi.JspAttribute;
import org.mustbe.consulo.java.web.jsp.psi.JspDirective;
import org.mustbe.consulo.java.web.jsp.psi.JspExpression;
import org.mustbe.consulo.java.web.jsp.psi.JspFragment;
import org.mustbe.consulo.java.web.jsp.psi.JspLineFragment;
import com.intellij.psi.PsiElementVisitor;

/**
 * @author VISTALL
 * @since 16.11.13.
 */
public class JspElementVisitor extends PsiElementVisitor
{
	public void visitDirective(JspDirective directive)
	{
		visitElement(directive);
	}

	public void visitAttribute(JspAttribute attribute)
	{
		visitElement(attribute);
	}

	public void visitFragment(JspFragment fragment)
	{
		visitElement(fragment);
	}

	public void visitLineFragment(JspLineFragment lineFragment)
	{
		visitElement(lineFragment);
	}

	public void visitExpression(JspExpression expression)
	{
		visitElement(expression);
	}
}
