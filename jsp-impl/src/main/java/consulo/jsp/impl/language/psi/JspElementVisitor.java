package consulo.jsp.impl.language.psi;

import consulo.jsp.language.psi.xml.JspDeclaration;
import consulo.jsp.language.psi.xml.JspDirective;
import consulo.jsp.language.psi.xml.JspExpression;
import consulo.jsp.language.psi.xml.JspScriptlet;
import consulo.language.psi.PsiElementVisitor;
import consulo.xml.psi.xml.XmlAttribute;

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

	public void visitAttribute(XmlAttribute attribute)
	{
		visitElement(attribute);
	}

	public void visitDeclaration(JspDeclaration fragment)
	{
		visitElement(fragment);
	}

	public void visitScriptlet(JspScriptlet lineFragment)
	{
		visitElement(lineFragment);
	}

	public void visitExpression(JspExpression expression)
	{
		visitElement(expression);
	}
}
