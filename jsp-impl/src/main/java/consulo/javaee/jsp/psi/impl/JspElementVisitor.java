package consulo.javaee.jsp.psi.impl;

import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.impl.source.jsp.jspXml.JspDeclaration;
import com.intellij.psi.impl.source.jsp.jspXml.JspDirective;
import com.intellij.psi.impl.source.jsp.jspXml.JspExpression;
import com.intellij.psi.impl.source.jsp.jspXml.JspScriptlet;
import com.intellij.psi.xml.XmlAttribute;

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
