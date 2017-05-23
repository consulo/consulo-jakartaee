package consulo.javaee.jsp.psi.impl;

import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.xml.XmlAttribute;
import consulo.javaee.jsp.psi.JspExpression;
import consulo.javaee.jsp.psi.JspFragment;
import consulo.javaee.jsp.psi.JspLineFragment;

/**
 * @author VISTALL
 * @since 16.11.13.
 */
public class JspElementVisitor extends PsiElementVisitor
{
	public void visitDirective(com.intellij.psi.impl.source.jsp.jspXml.JspDirective directive)
	{
		visitElement(directive);
	}

	public void visitAttribute(XmlAttribute attribute)
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
