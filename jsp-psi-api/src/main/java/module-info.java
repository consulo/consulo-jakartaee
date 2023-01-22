/**
 * @author VISTALL
 * @since 22/01/2023
 */
module consulo.jsp.language.api
{
	requires transitive consulo.language.api;
	requires transitive consulo.java.language.api;
	requires transitive com.intellij.xml;

	exports consulo.jsp.language;
	exports consulo.jsp.language.ast;
	exports consulo.jsp.language.file;
	exports consulo.jsp.language.lexer;
	exports consulo.jsp.language.psi;
	exports consulo.jsp.language.psi.java;
	exports consulo.jsp.language.psi.xml;
}